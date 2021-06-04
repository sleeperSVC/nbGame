import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends GameObject {

    int bulletWidth = 10;
    int bulletHeight = 10;
    Player p;

    public Bullet(int x, int y, int width, int height, Player p) {
        super(x, y, width, height);
        this.p = p;
        frameCounter = 0;

        // loading imgs
        BufferedImage im;
        try {
            im = ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_3.png"));
            frameHolder.add(im);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();

        if (!collisionBox.intersects(GameRunner.frameCollisionBox)) {
            isAlive = false;
        }

        //animation loop, idk if this is how u do it but it is how i think we can do it
        //we might be able to move it to gameObject and have it take framecount params and shit but this is easier
        if (frameCounter < 3) {
            frameCounter++;
        } else {   //isAlive becomes false after 3 frames cause the animation is 3 frames long, we will have to change the penetration and shit cause the animation depends on isAlive
            frameCounter = 0;
            isAlive = false;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(frameHolder.get(frameCounter), x, y, null);
    }
}
