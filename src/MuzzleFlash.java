import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class MuzzleFlash extends GameObject {

    Player p;

    public MuzzleFlash(int x, int y, int width, int height, Player p) {

        super(x, y, width, height);
        this.p = p;
        frameCounter = 0;

        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/bullet/bullet_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

        x = p.x;
        y = p.y;

        if (frameCounter < 3) {
            frameCounter++;
        } else {
            frameCounter = 0;
            isAlive = false;
        }
    }

    @Override
    public void draw(Graphics g) {

        if (p.orientation == 1) {
            g.drawImage(frameHolder.get(frameCounter), x + 32, y + 17, 9, 9, null);
        } else {
            g.drawImage(frameHolder.get(frameCounter), x, y + 17, -9, 9, null);
        }
    }
}
