import java.awt.*;

public class GameMovingObject extends GameObject {

    double speedFactor;
    double jumpSpeed;

    double xV = 0;
    double yV = 0;
    double xVMax = 10;
    double yVMax = 10;

    boolean isMoving = false;   // for seeing which frame arraylist to use

    boolean movingRight = false;// for input
    boolean movingLeft = false; // for input

    public GameMovingObject(int x, int y, int width, int height, int health, double speedFactor, double jumpSpeed) {
        super(x, y, width, height);
        this.healthCounter = health;
        this.speedFactor = speedFactor;
        this.jumpSpeed = jumpSpeed;
        collisionBox = new Rectangle(x, y, width, height);
    }

    @Override
    public void update() {
        // IF MOVING, INCREASE VELOCITIES BY SPEED
        if (movingRight) {
            xV += speedFactor;
        }
        if (movingLeft) {
            xV -= speedFactor;
        }

        // FRICTION STUFF AND MAX VELOCITY
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

        // MOVE THE OBJECT BY THE VELOCITY
        x += xV;
        y -= yV;

        System.out.println("x = " + x + " y = " + y + ", xV = " + xV + " yV = " + yV);

        //y += GamePanel.GRAVITY; // move object down by GRAVITY pixels
    }

    @Override
    public void draw(Graphics g) {

    }

}
