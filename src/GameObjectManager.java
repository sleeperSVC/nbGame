import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<MuzzleFlash> flashes = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    GameObjectAttributeManager am = new GameObjectAttributeManager();
    Player p;

    //constructor class
    public GameObjectManager(Player p) {
        // The object manager needs a reference to the player object created in GamePanel,
        // in order to access its X and Y positions
        this.p = p;

    }

    // iterate through bullets and enemies and call their update methods
    public void updateObjects() {
        checkCollision();
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
    }

    //draws the sprites
    public void drawObjects(Graphics g) {
        p.draw(g);

        bullets.forEach(b -> b.draw(g));
        enemies.forEach(e -> e.draw(g));
        flashes.forEach(f -> f.draw(g));
    }

    // remove all the objects whose "isAlive" is false
    public void killObjects() {
        if (!p.isAlive) {
            System.out.println("player dead");
        }

        bullets.removeIf(b -> !b.isAlive);
        enemies.removeIf(e -> !e.isAlive);
        flashes.removeIf(f -> !f.isAlive);
    }

    // when two objects' collisionBoxes intersect, do something
    public void checkCollision() {

        // if a bullet collides with any enemy, delete the bullet
        // NOTE: IF WE GET LIKE PIERCING SHOTS OR SOMETHING, THEN WE GOTTA CHANGE THIS
        for (int b = 0; b < bullets.size(); b++) {
            for (int e = 0; e < enemies.size(); e++) {
                if (bullets.get(b).collisionBox.intersects(enemies.get(e).collisionBox)) {
                    bullets.remove(b);
                    audioManager.playSound(0);
                    enemies.get(e).damage(am.bulletDamage);
                    b--;
                }
            }
        }
    }

    //adds bullets to the arrayList. muzzle flash is also added in conjunction with bullets
    private void addBullet() {
        bullets.add(new Bullet(p.x, p.y, am.bulletWidth, am.bulletHeight, am.bulletSpeedFactor, am.bulletInaccuracy, am.bulletDamage,  p.orientation, p.xV, p.yV));
        flashes.add(new MuzzleFlash(p.x, p.y, 9, 9, p));
        audioManager.playSound(0);
    }

    //adds an enemy to the enemy arrayList
    public void addEnemy() {
        // Enemy newEnemy = new Enemy(0,0,0,0,0,0,0,);

    }

}