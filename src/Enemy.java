import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy extends GameMovingObject {


    public Enemy(int x, int y, int width, int height, int health, int speedFactor) {

        super(x, y, width, height, health, speedFactor);

        frameCounter = 0;
        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();

        if (health == 0) {
            isAlive = false;
        }

        //animation loop, idk if this is how u do it but it is how i think we can do it
        //we might be able to move it to gameObject and have it take framecount params and shit but this is easier
        if (frameCounter < 4) {
            frameCounter++;
        } else {
            frameCounter = 0;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frameHolder.get(frameCounter), x, y, null);

    }
}
