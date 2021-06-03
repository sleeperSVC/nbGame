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
        for(int index = 0; index < bullets.size(); index++)
        {
            if(!bullets.get(index).isAlive)
            {
                bullets.remove(index);
                index--;
            }
        }
        for(int index = 0; index < enemies.size(); index++)
        {
            if(!enemies.get(index).isAlive)
            {
                enemies.remove(index);
                index--;
            }
        }

    }

    // when two objects' collisionBoxes intersect, do something
    public void checkCollision() {

        // if a bullet collides with any enemy, delete the bullet
        // NOTE: IF WE GET LIKE PIERCING SHOTS OR SOMETHING, THEN WE GOTTA CHANGE THIS
        for(int indexB = 0; indexB < bullets.size(); indexB++)
        {
            for(int indexE = 0 ; indexE < enemies.size(); indexE++)
            {
                if(bullets.get(indexB).collisionBox.intersects(enemies.get(indexE).collisionBox))
                {
                    bullets.remove(indexB);
                    indexB--;
                }
            }
        }
    }

    public void addBullet() {
        Bullet newBullet=new Bullet(1,1,1,1);
        bullets.add(newBullet);
    }

    public void addEnemy() {

    }

}
