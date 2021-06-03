import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public GameObjectManager() {

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

        for (Bullet b : bullets) {
            if (!b.isAlive) {
                bullets.remove(b);
            }
        }

        for (Enemy e : enemies) {
            if (!e.isAlive) {
                enemies.remove(e);
            }
        }

    }

    // when two objects' collisionBoxes intersect, do something
    public void checkCollision() {

        // if a bullet collides with any enemy, delete the bullet
        // NOTE: IF WE GET LIKE PIERCING SHOTS OR SOMETHING, THEN WE GOTTA CHANGE THIS
        for (Bullet b : bullets) {
            for (Enemy e : enemies) {
                if (b.collisionBox.intersects(e.collisionBox)) {
                    bullets.remove(b);
                }
            }
        }

    }

    public void addBullet() {

    }

    public void addEnemy() {

    }

}
