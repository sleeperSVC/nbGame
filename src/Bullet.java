import java.awt.*;

public class Bullet extends GameMovingObject {

    double inaccuracy;
    int damage;

    //bullet colorings
    Color purple1 = new Color(125, 52, 235);
    Color purple2 = new Color(96, 30, 191);
    Color purple3 = new Color(131, 79, 222);
    Color current;

    public Bullet(int x, int y, int width, int height, double speedFactor, double bulletInaccuracy, int bulletDamage, int orientation, double pxV, double pyV) {
        super((orientation == 1 ? x + 32 : x), y + 17, width, height, 1, speedFactor);
        // don't get scared of this ^ ternary operator, its just the same as "if orientation == 1, then return x+32, else return x
        this.inaccuracy = bulletInaccuracy;
        this.orientation = orientation;
        this.xV = pxV; // TODO initial velocity is the player's velocity. simulates inertia. kinda scuffed atm
        this.yV = pyV;

        if (orientation == 1) {
            movingRight = true;
        } else {
            movingLeft = true;
        }

        xVMax = 20; // placeholder value
        yVMax = 20; // placeholder value
    }

    @Override
    public void update() {
        super.update();

        // randomness in accuracy
        if (Math.random() > .75) {
            y += inaccuracy;
        } else if (Math.random() > .5) {
            y -= inaccuracy;
        }

        // if you wanna make the bullet slow down faster, or make it not slow down at all, edit this lines
        //xVMax -= Math.random() * .5 + 1;

        if (!collisionBox.intersects(GamePanel.frameCollisionBox) || xVMax <= 1) {
            isAlive = false;
        }


    }

    @Override
    public void draw(Graphics g) {

        //updates color
        int randColor = (int) (Math.random() * 3);
        switch (randColor) {
            case 0:
                current = purple1;
                break;
            case 1:
                current = purple2;
                break;
            default:
                current = purple3;
                break;
        }

        int randWidth = (int) (Math.random() * 5);
        int randHeight = (int) (Math.random() * 5);
        g.setColor(current);
        g.fillOval(x, y, width + randWidth, height + randHeight);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width + randWidth, height + randHeight);
    }
}
