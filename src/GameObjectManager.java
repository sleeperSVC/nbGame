import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<MuzzleFlash> flashes = new ArrayList<>();
    ArrayList<EnemyObject> enemies = new ArrayList<>();
    ArrayList<Baby> babies = new ArrayList<>();

    Player p;
    Map map;
    GameObjectAttributes am = new GameObjectAttributes();
    AudioManager audioManager = new AudioManager();

    long immuneStartTime = System.currentTimeMillis();
    long bulletStartTime = System.currentTimeMillis();
    boolean isFiring;

    //constructor class
    public GameObjectManager(Player p) {
        // The object manager needs a reference to the player object created in GamePanel, in order to access its X and Y positions
        this.p = p;
        map = new Map();
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
    }

    // remove all the objects whose "isAlive" is false
    public void killObjects() {
        if (!p.isAlive) {
            System.out.println("player dead");
        }

        bullets.removeIf(b -> !b.isAlive);
        enemies.removeIf(e -> !e.isAlive);
        flashes.removeIf(f -> !f.isAlive);
        babies.removeIf(c -> !c.isAlive);
    }

    public void checkEntityCollision() {

        // if a bullet collides with any enemy, delete the bullet, damage the enemy
        for (int b = 0; b < bullets.size(); b++) {
            for (int e = 0; e < enemies.size(); e++) {

                if (bullets.get(b).collisionBox.intersects(enemies.get(e).collisionBox)) {
                    bullets.remove(b);
                    audioManager.playSound((int) (Math.random() * (audioManager.hitSoundList.size() - 1) + 1), audioManager.HIT_SOUND_LIST);
                    enemies.get(e).damage(am.bulletDamage);
                    b--;
                }
            }
        }

        //if an enemy1 collides with the player, damage player
        for (int i = 0; i < enemies.size(); i++) {

            if (System.currentTimeMillis() - immuneStartTime >= 500) {
                if (enemies.get(i) instanceof Enemy1 && enemies.get(i).collisionBox.intersects(p.collisionBox)) {
                    p.health -= am.enemy1Damage;
                    audioManager.playSound(0, 1);
                }
              //  immuneStartTime = System.currentTimeMillis();
                //gotta figure out where this goes lol
            }

        }

        //if an enemy2 collides with the player, damage player
        for (int i = 0; i < enemies.size(); i++) {
            if (System.currentTimeMillis() - immuneStartTime >= 500) {
                if (enemies.get(i) instanceof Enemy2 && enemies.get(i).collisionBox.intersects(p.collisionBox)) {
                    p.health -= am.enemy2Damage;
                    audioManager.playSound(0, 1);
                }
            }

           // immuneStartTime = System.currentTimeMillis();
        }

    }

    public void checkEnvironmentCollision() {


    }

    //adds bullets to the arrayList. muzzle flash is also added in conjunction with bullets
    public void addBullet() {
        bullets.add(new Bullet(p.x, p.y, am.bulletWidth, am.bulletHeight, am.bulletSpeedFactor, am.bulletInaccuracy, am.bulletDamage, p.orientation, p.xV, p.yV));
        flashes.add(new MuzzleFlash(p.x, p.y, 9, 9, p));
        audioManager.playSound(0, audioManager.SOUND_LIST);
    }

    public void addBaby(int babyX, int babyY) {
        Baby newBaby = new Baby(babyX, babyY, 16, 16, 1, p);

    }

    //adds an enemy to the enemy arrayList
    public void addEnemy1() {
        enemies.add(new Enemy1(20, 288, 32, 32, am.enemy1Health, 4, p));
    }
    public void addEnemy2() {
        enemies.add(new Enemy2(20, 288, 32, 32, am.enemy2Health, 8, p));
    }

}