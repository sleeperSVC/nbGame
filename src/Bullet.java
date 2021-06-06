import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject {

    int bulletWidth = 1;
    int bulletHeight = 1;
    int orientation;

    public Bullet(int x, int y, int width, int height, int speed, int orientation) {
        super(x, y, width, height);
        this.speed = speed;
        this.orientation = orientation;
        this.y += 17;

        if (orientation == 1) {
            this.x += 32;
        }
    }

    @Override
    public void update() {
        super.update();

        if (!collisionBox.intersects(GameRunner.frameCollisionBox)) {
            isAlive = false;
        }

        if (orientation == 1) {
            x += speed;
        } else {
            x -= speed;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, width, height);
    }
}
