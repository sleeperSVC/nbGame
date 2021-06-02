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
            if (b.getAlive()) {
                bullets.remove(b);
            }
        }

        for (Enemy e : enemies) {
            if (e.getAlive()) {
                enemies.remove(e);
            }
        }

    }

    private void checkCollision() {
        // when two thingies intersect, do something
    }



}
