import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends GameObject {

    int fireRate;

    public Player(int x, int y, int width, int height, int health, int fireRate) {
        super(x, y, width, height);
        healthCounter = health;
        this.fireRate = fireRate;
        isMoving = false;
        frameCheck = true;

        this.speed = 10;
        frameCounter = 0;
        BufferedImage im;
        try {
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_3.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_4.png"));
            frameHolder.add(im);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //animation for the player's model
    public void changeMovingStatus(boolean isMoving) {
        this.isMoving = isMoving;

        BufferedImage im;
        try {

            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_3.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_5.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_6.png"));
            frameHolder.add(im);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        super.update();

        if (healthCounter == 0) {
            isAlive = false;
        }

        if (movingRight) {
            xV += speed;
        }
        if (movingLeft) {
            xV -= speed;
        }

        if (Math.abs(xV) > 0) {
            if (xV > 0)
                xV--;
            if (xV < 0)
                xV++;
        }
        if (Math.abs(xV) > xVMax) {
            if (xV > 0)
                xV = xVMax;;
            if (xV < 0)
                xV = -xVMax;
        }
//        if (Math.abs(yV) > 0) {
//            yV--;
//        }
//        if (Math.abs(yV) > yVMax) {
//            yV = yVMax;
//        }

        // move the player by the velocity
        x += xV;
        y -= yV;
        //y += GamePanel.GRAVITY; // move player down by GRAVITY pixel
    }

    @Override
    public void draw(Graphics2D g) {
        if (isRunning == false) {
            if (frameCounter < 4) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }

        if (isRunning == true) {
            if (frameCounter < 6) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }
        g.drawImage(frameHolder.get(frameCounter), x, y, null);
    }

    //method for player movement(running)
    public void move(char dir) {
        switch (dir) {
            case 'd':   // right
                orientation = 1;
                movingRight = true;
                break;
            case 'a':   // left
                orientation = 0;
                movingLeft = true;
                break;
            case 'w':   // up

                break;
            case 's':   // down
                /*
                    if(canGoDown) ladder
                    y -= speed;
                 */
                break;
        }
    }

    //method for player movement(stopping)
    public void stopMove(char dir) {
        switch (dir) {
            case 'd':   // right
                movingRight = false;
                break;
            case 'a':   // left
                movingLeft = false;
                break;
            case 'w':   // up

                break;

            case 's':   // down

                break;


        }
    }

}
