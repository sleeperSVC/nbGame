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
        frameIndex = 0;
        try {
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_1.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_2.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_3.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/baby/baby_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update() {

        if (frameChecker) {
            if (frameIndex < 3) {
                frameIndex++;
            } else {
                frameIndex = 0;
            }
        }
        frameChecker = !frameChecker;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frames.get(frameIndex), x, y, null);
    }
}
