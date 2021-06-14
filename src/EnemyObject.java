import java.awt.*;
import java.awt.image.RescaleOp;

public abstract class EnemyObject extends GameMovingObject {

    int damage;
    Player p;
    int frameCount;

    // Used to turn the enemies white when damaged
    RescaleOp rescaleOp = new RescaleOp(2f, 15, null);

    public EnemyObject(int x, int y, int width, int height, int health, int speedFactor, int damage, Player p) {
        super(x, y, width, height, health, speedFactor);
        this.damage = damage;
        this.p = p;

        xVMax = 1;
    }

    @Override
    public void update() {
        super.update();

        if (health <= 0) {
            isAlive = false;
        }

        move();
    }

    @Override
    public void draw(Graphics g) {

        if (canDisplayNextFrame) {
            if (frameIndex < frameCount - 1) {
                frameIndex++;
            } else {
                frameIndex = 0;
            }
        }
        canDisplayNextFrame = !canDisplayNextFrame;

        if (orientation == 1) {
            if (isDamaged) {
                g.drawImage(rescaleOp.filter(frames.get(frameIndex), null), x, y, null);
                isDamaged = false;
            } else {
                g.drawImage(frames.get(frameIndex), x, y, null);
            }
        } else {
            if (isDamaged) {
                g.drawImage(rescaleOp.filter(frames.get(frameIndex), null), x + 32, y, -32, 32, null);
                isDamaged = false;
            } else {
                g.drawImage(frames.get(frameIndex), x + 32, y, -32, 32, null);
            }
        }
    }

    // helper method for determining movement
    public abstract void move();

    // helper method for jumping
    public void jump() {
        yV += speedFactor * 50;
        canJump = false;
    }
}
