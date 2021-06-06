import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends GameObject {

    int fireRate;
    int speed;

    // velocity
    private int xV = 0;
    private int yV = 0;
    private int xVMax = 10;
    private int yVMax = 10;

    int mouseX;
    int mouseY;

    boolean isMoving;
    boolean frameCheck;
    int idleFrameCount = 4;
    int movingFrameCount = 6;

    //constructor class for player
    public Player(int x, int y, int width, int height, int health, int speed, int jumpSpeed, int fireRate) {
        super(x, y, width, height, health, speed);
        this.fireRate = fireRate;
        isMoving = false;
        frameCheck = true;

        this.speed = 10;
        frameCounter = 0;

        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //animation for the player's model
    public void changeMovingStatus(boolean isMoving) {
        this.isMoving = isMoving;

        BufferedImage im;
        try {
            frameHolder.clear();
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //
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

    //draw method for the player's sprite
    @Override
    public void draw(Graphics g) {

        if (isMoving == false && frameCheck) {
            if (frameCounter < idleFrameCount - 1) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }

        if (isMoving == true && frameCheck) {
            if (frameCounter < movingFrameCount - 1) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }
        //orientation 0==left | 1==right
        if (orientation == 1) {
            g.drawImage(frameHolder.get(frameCounter), x, y, 32, 32, null);
        } else {
            g.drawImage(frameHolder.get(frameCounter), x + 32, y, -32, 32, null);
        }
        //changes fps to 30
        frameCheck = !frameCheck;
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
