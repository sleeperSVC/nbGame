import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public GameObjectManager() {

    }

    public void updateObjects() {
        // iterate through bullets and enemies and call their update methods
    }

    public void drawObjects(Graphics g) {
        for (Bullet b : bullets) {
            b.draw(g);
        }

        for (Enemy e : enemies) {
            e.draw(g);
        }
    }

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

    public void checkCollision() {
        // when two objects' collisionBoxes intersect, do something
    }

    public void addBullet() {

    }

    public void addEnemy() {

    }

}
