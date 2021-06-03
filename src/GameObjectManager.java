import java.awt.*;
import java.util.ArrayList;

public class GameObjectManager {
    //dimensions are 960x540, use these for coordinates

    ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    Player p1 = new Player(448,288, 32, 32,200);
    //starting location coords in the top left of the sprite is 448,288. there are 2 levels, top is 288, bottom is 384.
    //sprite dimensions are 32x32, so we should make the hitboxes the same size

    //gun barrel coords for the minion sprites are 32, 17

    //player and enemies have a healthCounter param now

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
