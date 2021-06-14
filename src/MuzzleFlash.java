import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MuzzleFlash extends GameObject {

    Player p;

    public MuzzleFlash(int x, int y, int width, int height, Player p) {

        super(x, y, width, height);
        this.p = p;
        frameIndex = 0;

        try {
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_2.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_3.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_4.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        x = p.x;
        y = p.y;

        if (frameIndex < 3) {
            frameIndex++;
        } else {
            frameIndex = 0;
            isAlive = false;
        }
    }

    @Override
    public void draw(Graphics g) {

        if (p.orientation == 1) {
            g.drawImage(frames.get(frameIndex), x + 32, y + 14, 9, 9, null);
        } else {
            g.drawImage(frames.get(frameIndex), x, y + 14, -9, 9, null);
        }
    }
}
