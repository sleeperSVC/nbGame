import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy extends GameObject {


    public Enemy(int x, int y, int width, int height) {

        super(x, y, width, height);

        frameCounter = 0;
        BufferedImage im;
        try {

            im = ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_3.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_4.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/enemy/enemy1_5.png"));
            frameHolder.add(im);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();

        if (healthCounter == 0){
            isAlive = false;
        }

        //animation loop, idk if this is how u do it but it is how i think we can do it
        //we might be able to move it to gameObject and have it take framecount params and shit but this is easier
        if (frameCounter < 5) {
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
