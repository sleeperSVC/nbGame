import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy1 extends EnemyObject {

    public Enemy1(int x, int y, int width, int height, int health, int speedFactor, int damage, Player p) {
        super(x, y, width, height, health, speedFactor, damage, p);
        xVMax = 1;
        maxFrames = 5;

        try {
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_1.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_2.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_3.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_4.png")));
            frameHolder.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
