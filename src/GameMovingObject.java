import java.awt.*;
import java.awt.image.RescaleOp;

public class GameMovingObject extends GameObject {

    double speedFactor;
    double xV = 0;
    double yV = 0;
    double xVMax = 0;
    double yVMax = 0;
    boolean movingRight = false;    // NOT JUST FOR WASD INPUT ANYMORE
    boolean movingLeft = false;     // NOT JUST FOR WASD INPUT ANYMORE

    boolean isMoving = false;   // for seeing which frame arraylist to use

    RescaleOp rescaleOp = new RescaleOp(2f, 15, null);

    public GameMovingObject(int x, int y, int width, int height, int health, double speedFactor) {
        super(x, y, width, height);
        this.health = health;
        this.speedFactor = speedFactor;
    }

    @Override
    public void update() {
        super.update();

        // IF MOVING, INCREASE VELOCITIES BY SPEED
        if (movingRight) {
            xV += speedFactor;
            orientation = 1;
        }
        if (movingLeft) {
            xV -= speedFactor;
            orientation = 0;
        }

        // FRICTION STUFF AND MAX VELOCITY CAP
        if (Math.abs(xV) > 0) {
            if (xV > 0)
                xV -= GamePanel.FRICTION;
            if (xV < 0)
                xV += GamePanel.FRICTION;
        }
        if (Math.abs(xV) > xVMax) {
            if (xV > 0)
                xV = xVMax;
            if (xV < 0)
                xV = -xVMax;
        }
        if (Math.abs(yV) > 0) {
            if (yV > 0)
                yV -= GamePanel.FRICTION;
            if (yV < 0)
                yV += GamePanel.FRICTION;
        }
        if (Math.abs(yV) > yVMax) {
            if (yV > 0)
                yV = yVMax;
            if (yV < 0)
                yV = -yVMax;
        }

        // SHIFT THE OBJECT BY THE VELOCITY
        x += xV;
        y -= yV;

        //y += GamePanel.GRAVITY; // move object down by GRAVITY pixels
    }

    @Override
    public void draw(Graphics g) {

    }

}
