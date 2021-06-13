import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends GameMovingObject {

    int fireRate;

    int idleFrameCount = 4;
    int movingFrameCount = 6;
    int movingFrameIndex = 0;

    int money;
    int score;

    ArrayList<BufferedImage> movingFrameHolder = new ArrayList<>();

    //constructor class for player
    public Player(int x, int y, int width, int height, int health, int speedFactor, int fireRate) {
        super(x, y, width, height, health, speedFactor);
        this.fireRate = fireRate;
        money = 5;
        xVMax = 4;  // placeholder value

        try {
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_1.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_2.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_3.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_5.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_6.png")));

            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();

        if (health <= 0) {
            isAlive = false;
        }
        if (System.currentTimeMillis() - jumpStartTime >= 300) {
            canJump = true;
            jumpStartTime = System.currentTimeMillis();
        }
    }

    //draw method for the player's sprite
    @Override
    public void draw(Graphics g) {

        if (isMoving && frameCheck) {
            if (movingFrameIndex < movingFrameCount - 1) {
                movingFrameIndex++;
            } else {
                movingFrameIndex = 0;
            }
        }
        if (!isMoving && frameCheck) {
            if (frameCounter < idleFrameCount - 1) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }

        if (isMoving) {
            if (orientation == 1) {
                g.drawImage(movingFrameHolder.get(movingFrameIndex), x, y, 32, 32, null);
            } else {
                g.drawImage(movingFrameHolder.get(movingFrameIndex), x + 32, y, -32, 32, null);
            }
        } else {
            if (orientation == 1) {
                g.drawImage(frameHolder.get(frameCounter), x, y, 32, 32, null);
            } else {
                g.drawImage(frameHolder.get(frameCounter), x + 32, y, -32, 32, null);
            }
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
                if (canJump) {
                    yV += speedFactor * 50;
                    canJump = false;
                }
                canMoveUp = true;
                break;
            case 's':   // down
                movingDown = true;

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
                movingUp = false;
                break;
            case 's':   // down
                movingDown = false;
                break;
        }
    }

    public void changeMovingStatus(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void raiseFireRate() {
        fireRate -= 10;
    }

}
