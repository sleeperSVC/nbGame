import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

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
    long spawnStartTime = System.currentTimeMillis();
    long babyStartTime = System.currentTimeMillis();
    long difficultyStartTime = System.currentTimeMillis();

    int difficultyInterval = 10000;  // every 10,000 miliseconds, the enemy spawn time will decrease

    boolean isFiring;


    //constructor class
    public GameObjectManager(Player p) {
        // The object manager needs a reference to the player object created in GamePanel, in order to access its X and Y positions
        this.p = p;
        map = new Map();
        hud = new HUD(0, 0, 960, 540, p);
    }

    // iterate through bullets and enemies and call their update methods
    public void updateObjects() {

        if (isFiring) {
            if (System.currentTimeMillis() - bulletStartTime >= Atbs.bulletFireRate) {
                addBullet();
                bulletStartTime = System.currentTimeMillis();
            }
        }
        bullets.forEach(Bullet::update);
        enemies.forEach(EnemyObject::update);
        flashes.forEach(MuzzleFlash::update);
        babies.forEach(Baby::update);
        hud.update();
        p.update();
        killObjects();
        spawnBabies();
        spawnEnemies();
        doEntityCollision();
        doEnvironmentCollision(p);
        enemies.forEach(this::doEnvironmentCollision);
    }

    //draws the sprites
    public void drawObjects(Graphics g) {
        p.draw(g);
        hud.draw(g);

//        g.setColor(Color.BLACK);
//        map.collisionRects.forEach(r -> g.fillRect(r.x, r.y, r.width, r.height));

        bullets.forEach(b -> b.draw(g));
        enemies.forEach(e -> e.draw(g));
        babies.forEach(c -> c.draw(g));
        flashes.forEach(f -> f.draw(g));

    }

    // remove all the objects whose "isAlive" is false
    public void killObjects() {
        if (!p.isAlive) {
            System.out.println("player dead");
        }
        bullets.removeIf(b -> !b.isAlive);
        flashes.removeIf(f -> !f.isAlive);

        // remove enemies
        for (Iterator<EnemyObject> iterator = enemies.iterator(); iterator.hasNext(); ) {
            EnemyObject e = iterator.next();
            if (!e.isAlive) {
                p.money++;
                iterator.remove();
                System.out.println("enemy killed");
            }
        }

        // remove babies
        for (Iterator<Baby> iterator = babies.iterator(); iterator.hasNext(); ) {
            Baby b = iterator.next();
            if (!b.isAlive) {
                p.money += 5;
                if (p.health < 10) {
                    p.health++;
                }
                iterator.remove();
                System.out.println("baby collected");
            }
        }
    }

    public void doEntityCollision() {

        // if a bullet collides with any enemy or environment collision box, bullet is dead
        for (Bullet b : bullets) {

            for (EnemyObject e : enemies) {
                if (b.cBox.intersects(e.cBox)) {
                    b.isAlive = false;
                    audioManager.playSound((int) (Math.random() * (audioManager.hitSoundList.size() - 1) + 1), audioManager.HIT_SOUND_LIST);
                    e.health -= b.damage;
                    e.isDamaged = true;
                    p.score += b.damage * 5;
                    break;
                }
            }

            if (b.isAlive) {
                for (Rectangle r : map.collisionRects) {
                    if (r.intersects(b.cBox)) {
                        b.isAlive = false;
                    }
                }
            }
        }

        for (Baby b : babies) {
            if (p.cBox.intersects(b.cBox)) {
                b.isAlive = false;
            }
        }

        //if an enemy1 collides with the player, damage player
        for (EnemyObject e : enemies) {
            //System.out.println("Enemy type = " + e.getClass().getSimpleName() + " collisionBox x= " + e.collisionBox.x);

            if (System.currentTimeMillis() - immuneStartTime >= 1000) {

                if (e.cBox.intersects(p.cBox)) {
                    p.health -= e.damage;
                    audioManager.playSound(0, 1);
                    immuneStartTime = System.currentTimeMillis();
                }

            }
        }
    }

    public void doEnvironmentCollision(GameMovingObject obj) {
        obj.canMoveRight = true;
        obj.canMoveLeft = true;
        obj.canMoveUp = true;
        obj.canMoveDown = true;
        for (Rectangle r : map.collisionRects) {
            if (obj.cBox.intersects(r)) {

                double dx = obj.cBox.getCenterX() - r.getCenterX(); // diff between player and rect center X
                double dy = obj.cBox.getCenterY() - r.getCenterY(); // diff between player and rect center Y

                if (Math.abs(dx / r.width) > Math.abs(dy / r.height)) {
                    if (dx > 0) {
                        // right side
                        obj.x = r.x + r.width;
                        obj.canMoveLeft = false;
                    } else {
                        // left side
                        obj.x = r.x - obj.cBox.width;
                        obj.canMoveRight = false;
                    }
                    obj.xV = 0;

                } else {
                    if (dy < 0) {
                        // top side
                        obj.y = r.y - obj.cBox.width;
                        obj.canMoveDown = false;
                        obj.canJump = true;
                    } else {
                        // bottom side
                        obj.y = r.y + r.height;
                        obj.canMoveUp = false;
                    }
                    obj.yV = 0;
                }
            }
        }

        if (Map.LADDER.intersects(p.cBox)) {
            p.canTravelLadder = true;
            p.canJump = false;
        } else {
            p.canTravelLadder = false;
        }
    }


    //adds bullets to the arrayList. muzzle flash is also added in conjunction with bullets
    public void addBullet() {
        bullets.add(new Bullet(p.x, p.y, Atbs.bulletWidth, Atbs.bulletHeight, Atbs.bulletSpeedFactor, Atbs.bulletInaccuracy, Atbs.bulletDamage, p.orientation, p.xV, Atbs.bulletXVMax));
        flashes.add(new MuzzleFlash(p.x, p.y, 9, 9, p));
        audioManager.playSound(0, audioManager.SOUND_LIST);
    }

    public void addBaby(Point point) {
        babies.add(new Baby(point.x, point.y, 16, 16, 1, p));
    }

    //adds an enemy to the enemy arrayList
    public void addEnemy1(Point point) {
        enemies.add(new Enemy1(point.x, point.y, 32, 32, Atbs.enemy1Health, 4, Atbs.enemy1Damage, p));
    }

    public void addEnemy2(Point point) {
        enemies.add(new Enemy2(point.x, point.y, 32, 32, Atbs.enemy2Health, 8, Atbs.enemy2Damage, p));
    }

    public void spawnEnemies() {
        if (System.currentTimeMillis() - spawnStartTime >= Atbs.spawnInterval) {
            if (Math.random() < 0.5) {
                addEnemy1(map.getEnemySpawn());
                System.out.println("enemy 1 spawned");
            } else {
                addEnemy2(map.getEnemySpawn());
                System.out.println("enemy 2 spawned");
            }
            spawnStartTime = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() - difficultyStartTime >= difficultyInterval) {
            if (Atbs.spawnInterval - 100 >= 200) {
                Atbs.spawnInterval -= 100;
            }
            difficultyStartTime = System.currentTimeMillis();
        }
    }

    public void spawnBabies() {
        if (System.currentTimeMillis() - babyStartTime >= Atbs.babyInterval) {
            addBaby(map.getBabySpawn());
            babyStartTime = System.currentTimeMillis();
            System.out.println("baby spawned");
        }
    }


}