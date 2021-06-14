import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends GameMovingObject {

    int idleFrameCount = 4;
    int movingFrameCount = 6;
    int movingFrameIndex = 0;
    boolean canTravelLadder = false;
    boolean travelingLadderUp = false;
    boolean travelingLadderDown = false;
    int ladderSpeed = 2;

    double yVMaxOriginal = yVMax;

    int money = 0;
    int score = 0;

    ArrayList<BufferedImage> movingFrameHolder = new ArrayList<>();

    public Player(int x, int y, int width, int height, int health, int speedFactor) {
        super(x, y, width, height, health, speedFactor);
        xVMax = 4;

        try {
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_1.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_2.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_3.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_5.png")));
            movingFrameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/run/run_6.png")));

            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_1.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_2.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_3.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_4.png")));
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

        if (canTravelLadder) {
            yV += GamePanel.GRAVITY;        // counteract gravity
            if (travelingLadderDown) {
                yV -= ladderSpeed;
                yVMax = 3;                  // when moving up or down ladder, limit the max Y velocity
            } else if (travelingLadderUp) {
                yV += ladderSpeed;
                yVMax = 3;                  // when moving up or down ladder, limit the max Y velocity
            } else {
                yVMax = yVMaxOriginal;
            }
        }

        if (canTravelLadder) {
            canJump = false;
        }
    }

    //draw method for the player's sprite
    @Override
    public void draw(Graphics g) {

        isMoving = movingRight || movingLeft || movingUp || movingDown;

        if (isMoving && canDisplayNextFrame) {
            if (movingFrameIndex < movingFrameCount - 1) {
                movingFrameIndex++;
            } else {
                movingFrameIndex = 0;
            }
        }
        if (!isMoving && canDisplayNextFrame) {
            if (frameIndex < idleFrameCount - 1) {
                frameIndex++;
            } else {
                frameIndex = 0;
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
                g.drawImage(frames.get(frameIndex), x, y, 32, 32, null);
            } else {
                g.drawImage(frames.get(frameIndex), x + 32, y, -32, 32, null);
            }
        }

        // This boolean is used so that sprites are updated every TWO frames, rather than every single frame.
        // ie: the animation becomes 30 frames per second instead of 60 frames per second
        canDisplayNextFrame = !canDisplayNextFrame;
    }

    //method for player movement
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

                    jump();
                } else if (canTravelLadder)
                    travelingLadderUp = true;
                break;
            case 's':   // down
                movingDown = true;
                if (canTravelLadder)
                    travelingLadderDown = true;
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
                if (travelingLadderUp) {
                    travelingLadderUp = false;
                }
                break;
            case 's':   // down
                movingDown = false;
                if (travelingLadderDown) {
                    travelingLadderDown = false;
                }
                break;
        }
    }

    public void changeMovingStatus(boolean isMoving) {
        this.isMoving = isMoving;
    }


    @Override
    public void jump() {
        yV += speedFactor * 50;
        canJump = false;
    }
}

