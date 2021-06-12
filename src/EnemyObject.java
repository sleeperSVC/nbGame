import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class EnemyObject extends GameMovingObject{

    boolean frameChecker;
    int damage;
    Player p;
    int maxFrames;

    public EnemyObject(int x, int y, int width, int height, int health, int speedFactor, int damage, Player p) {
        super(x, y, width, height, health, speedFactor);
        this.damage = damage;
        this.p = p;

        xVMax = 1;
    }

    @Override
    public void update() {
        super.update();

        if (health == 0) {
            isAlive = false;
        }
        if (frameChecker) {
            if (frameCounter < maxFrames - 1) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }
        frameChecker = !frameChecker;
        move();
    }

    //helper method for moving
    private void move() {
        if (x < p.x) {
            movingRight = true;
            movingLeft = false;
        } else if (x > p.x) {
            movingLeft = true;
            movingRight = false;
        }
    }

    @Override
    public void draw(Graphics g) {

        if (orientation == 1) {
            if (isDamaged) {
                g.drawImage(rescaleOp.filter(frameHolder.get(frameCounter), null), x, y, null);
                isDamaged = false;
            } else {
                g.drawImage(frameHolder.get(frameCounter), x, y, null);
            }
        } else {
            if (isDamaged) {
                g.drawImage(rescaleOp.filter(frameHolder.get(frameCounter), null), x + 32, y, -32, 32, null);
                isDamaged = false;
            } else {
                g.drawImage(frameHolder.get(frameCounter), x + 32, y, -32, 32, null);
            }
        }
    }


}
