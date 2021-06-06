import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<MuzzleFlash> flashes = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    boolean isFiring;

    GameObjectAttributeManager am = new GameObjectAttributeManager();
    Player p;

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
            addBullet();
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

        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isAlive) {
                System.out.println("removed a bullet");
                bullets.remove(i);
                i--;
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive) {
                enemies.remove(i);
                i--;
            }
        }
        for (int i = 0; i < flashes.size(); i++) {
            if (!flashes.get(i).isAlive) {
                flashes.remove(i);
                i--;
            }
        }
    }

    // when two objects' collisionBoxes intersect, do something
    public void checkCollision() {

        // if a bullet collides with any enemy, delete the bullet
        // NOTE: IF WE GET LIKE PIERCING SHOTS OR SOMETHING, THEN WE GOTTA CHANGE THIS
        for (int b = 0; b < bullets.size(); b++) {
            for (int e = 0; e < enemies.size(); e++) {
                if (bullets.get(b).collisionBox.intersects(enemies.get(e).collisionBox)) {
                    bullets.remove(b);
                    b--;
                }
            }
        }
    }

    //adds bullets to the arrayList. muzzle flash is also added in conjunction with bullets
    public void addBullet() {
        Bullet newBullet = new Bullet(p.x, p.y, am.bulletWidth, am.bulletHeight, am.bulletSpeed, p.orientation);
        bullets.add(newBullet);

        MuzzleFlash muzzle = new MuzzleFlash(p.x, p.y, 9, 9, p);
        flashes.add(muzzle);
    }

    public void addEnemy() {

    }

}
