import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy2 extends EnemyObject{

    public Enemy2(int x, int y, int width, int height, int health, int speedFactor, Player p) {
        super(x, y, width, height, health, speedFactor, p);

        xVMax = 2;
        yVMax = 1;
        maxFrames = 6;
        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_5.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
