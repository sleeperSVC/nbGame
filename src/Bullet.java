import java.awt.*;

public class Bullet extends GameMovingObject {

    double inaccuracy;
    double damage;

    //bullet colorings
    Color fillColor;
    Color outlineColor;

    public Bullet(int x, int y, int width, int height, double speedFactor, double inaccuracy, double damage, int orientation, double pxV, double xVMax) {
        super((orientation == 1 ? x + 32 : x), y + 17, width, height, 1, speedFactor);
        // this ^ ternary operator is just the same as "if orientation == 1, then return x+32, else return x"
        this.inaccuracy = inaccuracy;
        this.damage = damage;
        this.orientation = orientation;
        this.xV = pxV; // initial velocity is the player's velocity, simulates intertia

        if (orientation == 1) {
            movingRight = true;
        } else {
            movingLeft = true;
        }

        this.xVMax = xVMax;
        yVMax = 0;
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

        // if you wanna make the bullet slow down faster, or make it not slow down at all, edit this line
        xVMax -= Math.random() * .5 + 1;

        if (!cBox.intersects(GamePanel.frameCollisionBox) || xVMax <= 1) {
            isAlive = false;
        }

    }

    @Override
    public void draw(Graphics g) {

        // random colors
        fillColor = new Color((int) (Atbs.bulletDamage * 255 / Atbs.bulletDamage), 0, 0);
        outlineColor = new Color((int) (255 - (2.5 * Atbs.bulletFireRate)), 0, 0);

        int randWidth = (int) (Math.random() * 5);
        int randHeight = (int) (Math.random() * 5);
        g.setColor(fillColor);
        g.fillOval(x, y, width + randWidth, height + randHeight);
        g.setColor(outlineColor);
        g.drawOval(x, y, width + randWidth, height + randHeight);
    }

    public void jump() {

    }
}
