import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends GameObject {

    int fireRate;

    public Player(int x, int y, int width, int height, int health, int fireRate) {
        super(x, y, width, height);
        healthCounter = health;
        this.fireRate = fireRate;
        isRunning = false;

        frameCounter = 0;
        BufferedImage im;
        try {
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_3.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/idle/idle_4.png"));
            frameHolder.add(im);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeRunningStatus(boolean isRunning) {
        this.isRunning = isRunning;

        BufferedImage im;
        try {

            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_1.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_2.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_3.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_4.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_5.png"));
            frameHolder.add(im);
            im = ImageIO.read(getClass().getResource("resources/image/entities/run/run_6.png"));
            frameHolder.add(im);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        super.update();

        if (healthCounter == 0) {
            isAlive = false;
        }

    }

    @Override
    public void draw(Graphics2D g) {
        if (isRunning == false) {
            if (frameCounter < 4) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }

        if (isRunning == true) {
            if (frameCounter < 6) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }
        g.drawImage(frameHolder.get(frameCounter), x, y, null);
    }

}
