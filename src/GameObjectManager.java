import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    GameObjectAttributeManager am = new GameObjectAttributeManager();
    Player p;

    public GameObjectManager(Player p) {
        // The object manager needs a reference to the player object created in GamePanel,
        // in order to access its X and Y positions
        this.p = p;

    }

    // iterate through bullets and enemies and call their update methods
    public void updateObjects() {
        for (Bullet b : bullets) {
            b.update();
        }

        for (Enemy e : enemies) {
            e.update();
        }
    }

    public void drawObjects(Graphics g) {
        for (Bullet b : bullets) {
            b.draw(g);
        }

        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

    // remove all the objects whose "isAlive" is false
    public void killObjects() {
        for (int i = 0; i < bullets.size(); i++) {
            if (!bullets.get(i).isAlive) {
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

    public void addBullet() {
        Bullet newBullet = new Bullet(p.x, p.y, 1, 1, p);
        bullets.add(newBullet);
    }

    public void addEnemy() {

    }

}
