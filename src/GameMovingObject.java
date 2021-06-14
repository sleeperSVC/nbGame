public abstract class GameMovingObject extends GameObject {

    double speedFactor;
    double xV = 0;
    double yV = 0;
    double xVMax = 1;
    double yVMax = 20;

    boolean movingRight = false;
    boolean movingLeft = false;
    boolean movingUp = false;
    boolean movingDown = false;

    boolean canMoveRight = true;
    boolean canMoveLeft = true;
    boolean canMoveUp = true;
    boolean canMoveDown = true;
    boolean canJump = false;

    // This is only used to see if the moving animation should be used, it has nothing to do with movement
    boolean isMoving = false;

    public GameMovingObject(int x, int y, int width, int height, int health, double speedFactor) {
        super(x, y, width, height);
        this.health = health;
        this.speedFactor = speedFactor;
    }

    @Override
    public void update() {

        // IF MOVING, INCREASE VELOCITIES BY SPEEDFACTOR
        if (movingRight && canMoveRight) {
            xV += speedFactor;
            orientation = 1;
        }
        if (movingLeft && canMoveLeft) {
            xV -= speedFactor;
            orientation = 0;
        }
        if (movingUp && canMoveUp) {
            yV += speedFactor;
        }
        if (movingDown && canMoveDown) {
            yV -= speedFactor;
        }

        // DECREASE VELOCITY BY FRICTION, AND CAP THE MAX VELOCITY
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

        // CHANGE Y VELOCITY WITH GRAVITY
        if (canMoveDown) {
            yV -= GamePanel.GRAVITY;
        }

        super.update(); // update collisionBox
    }

    protected abstract void jump();
}
