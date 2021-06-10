import java.awt.*;
import java.util.*;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<MuzzleFlash> flashes = new ArrayList<>();
    ArrayList<EnemyObject> enemies = new ArrayList<>();
    ArrayList<Baby> babies = new ArrayList<>();

    Player p;
    Map map;
    HUD hud;
    AudioManager audioManager = new AudioManager();

    long immuneStartTime = System.currentTimeMillis();
    long bulletStartTime = System.currentTimeMillis();
    boolean isFiring;

    //constructor class
    public GameObjectManager(Player p) {
        // The object manager needs a reference to the player object created in GamePanel, in order to access its X and Y positions
        this.p = p;
        map = new Map();
        hud = new HUD(0,0, 960, 540, p);
    }

    // iterate through bullets and enemies and call their update methods
    public void updateObjects() {
        checkEntityCollision();
        killObjects();
        p.update();
        if (isFiring) {
            if (System.currentTimeMillis() - bulletStartTime >= p.fireRate) {
                addBullet();
                bulletStartTime = System.currentTimeMillis();
            }
        }
        hud.update();
        bullets.forEach(b -> b.update());
        enemies.forEach(e -> e.update());
        flashes.forEach(f -> f.update());
        babies.forEach(c -> c.update());

    }

    //draws the sprites
    public void drawObjects(Graphics g) {
        p.draw(g);

        bullets.forEach(b -> b.draw(g));
        enemies.forEach(e -> e.draw(g));
        flashes.forEach(f -> f.draw(g));
        babies.forEach(c -> c.draw(g));
        hud.draw(g);


    }

    // remove all the objects whose "isAlive" is false
    public void killObjects() {
        if (!p.isAlive) {
            System.out.println("player dead");
        }
        bullets.removeIf(b -> !b.isAlive);
        flashes.removeIf(f -> !f.isAlive);

        // remove enemies
        for (int e = 0; e < enemies.size(); e++) {
            if (!enemies.get(e).isAlive) {
                p.money++;
                enemies.remove(e);
                System.out.println("enemy killed");
            }
        }

        // remove babies
        for (int b = 0; b < babies.size(); b++) {
            if (!babies.get(b).isAlive) {
                p.money += 5;
                babies.remove(b);
                System.out.println("baby collected");
            }
        }
    }

    public void checkEntityCollision() {

        //TODO: delete this eventualy

        // if a bullet collides with any enemy, delete the bullet, damage the enemy
//        for (int b = 0; b < bullets.size(); b++) {
//            for (int e = 0; e < enemies.size(); e++) {
//                if (bullets.get(b).collisionBox.intersects(enemies.get(e).collisionBox)) {
//                    bullets.remove(b);
//                    audioManager.playSound((int) (Math.random() * (audioManager.hitSoundList.size() - 1) + 1), audioManager.HIT_SOUND_LIST);
//                    enemies.get(e).health -= Atbs.bulletDamage;
//                }
//            }
//        }

        //TODO: fix this
//        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
//            if (bullets.get(b).collisionBox.intersects(enemies.get(e).collisionBox)) {
//                    bullets.remove(b);
//                    audioManager.playSound((int) (Math.random() * (audioManager.hitSoundList.size() - 1) + 1), audioManager.HIT_SOUND_LIST);
//                    enemies.get(e).health -= Atbs.bulletDamage;
//        }

        for (int b = 0; b < babies.size(); b++) {
            if (babies.get(b).collisionBox.intersects(p.collisionBox)) {
                babies.remove(b);
            }
        }

        //if an enemy1 collides with the player, damage player
        for (EnemyObject e : enemies) {
            //System.out.println("Enemy type = " + e.getClass().getSimpleName() + " collisionbox x= " + e.collisionBox.x);

            if (System.currentTimeMillis() - immuneStartTime >= 500) {

                if (e.collisionBox.intersects(p.collisionBox)) {
                    p.health -= e.damage;
                    audioManager.playSound(0, 1);
                }
                immuneStartTime = System.currentTimeMillis();
            }
        }
    }


    public void checkEnvironmentCollision() {
        for (Rectangle r : map.collisionRects) {
            if (p.collisionBox.intersects(r)) {
            //    if (p.collisionBox.intersection(r).x )
            }
        }
    }


    //adds bullets to the arrayList. muzzle flash is also added in conjunction with bullets
    public void addBullet() {
        bullets.add(new Bullet(p.x, p.y, Atbs.bulletWidth, Atbs.bulletHeight, Atbs.bulletSpeedFactor, Atbs.bulletInaccuracy, Atbs.bulletDamage, p.orientation, p.xV, p.yV));
        flashes.add(new MuzzleFlash(p.x, p.y, 9, 9, p));
        audioManager.playSound(0, audioManager.SOUND_LIST);
    }

    public void addBaby(int babyX, int babyY) {
        Baby newBaby = new Baby(babyX, babyY, 16, 16, 1, p);

    }

    //adds an enemy to the enemy arrayList
    public void addEnemy1(Point point) {
        enemies.add(new Enemy1(point.x, point.y, 32, 32, Atbs.enemy1Health, 4, Atbs.enemy1Damage, p));
    }

    public void addEnemy2(Point point) {
        enemies.add(new Enemy2(point.x, point.y, 32, 32, Atbs.enemy2Health, 8, Atbs.enemy2Damage, p));
    }

    public void spawnEnemies() {

    }
}