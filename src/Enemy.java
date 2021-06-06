import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends GameMovingObject {

    int damageTaken;
    boolean frameChecker;
    Player p;

    public Enemy(int x, int y, int width, int height, int health, int speedFactor, Player p) {

        super(x, y, width, height, health, speedFactor);
        this.p = p;

        xVMax = 1;
        yVMax = 1;

        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void damage(int d) {
        isDamaged = true;
        damageTaken = d;
    }

    @Override
    public void update() {
        super.update();

        if (isDamaged) {
            health -= damageTaken;
        }

        if (health == 0) {
            isAlive = false;
        }
        if (frameChecker) {
            if (frameCounter < 4) {
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
        System.out.println("xV = " + xV + " speedFactor = " + speedFactor);
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

