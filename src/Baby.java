import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Baby extends GameObject {
    boolean frameChecker;
    Player p;

    public Baby(int x, int y, int width, int height, int health, Player p) {
        super(x, y, width, height);
        this.health = health;
        this.p = p;
        isAlive = true;
        frameChecker = true;

        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {
        if (p.collisionBox.intersects(x, y, width, height)) {
            isAlive = false;
            //need to add points method
        }

        if (frameChecker) {
            if (frameCounter < 3) {
                frameCounter++;
            } else {
                frameCounter = 0;
            }
        }
        frameChecker = !frameChecker;


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frameHolder.get(frameCounter), x, y, null);
    }
}
