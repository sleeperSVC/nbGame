import java.awt.*;

public class Bullet extends GameObject {

    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();

        // check if the bullet is out of bounds
        if (!collisionBox.intersects(GameRunner.frameCollisionBox)) {
            isAlive = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }
}
