import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy2 extends EnemyObject {

    long randomMovementStartTime = System.currentTimeMillis();
    int randomMovementInterval = 1500;
    int xVMaxOriginal = 2;


    public Enemy2(int x, int y, int width, int height, int health, int speedFactor, int damage, Player p) {
        super(x, y, width, height, health, speedFactor, damage, p);

        xVMax = xVMaxOriginal;
        frameCount = 6;

        try {
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_1.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_2.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_3.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_4.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_5.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy2/enemy2_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        super.update();
    }

    public void move() {

        // if on the same Y level as the player, chase the player
        if (y >= p.y - 10 && y <= p.y + 50) {
            xVMax = xVMaxOriginal;
            if (x < p.x) {
                movingRight = true;
                movingLeft = false;
            } else if (x > p.x) {
                movingLeft = true;
                movingRight = false;
            }

            // if lower than player, jump up
            if (y > p.y && canJump && Math.abs(x - p.x) < 50) {
                jump();

            }

            // else, move around randomly and slowly
        } else {
            xVMax = 1;
            if (System.currentTimeMillis() - randomMovementStartTime >= randomMovementInterval) {
                if (Math.random() > 0.5) {
                    movingRight = true;
                    movingLeft = false;
                } else {
                    movingLeft = true;
                    movingRight = false;
                }
                randomMovementStartTime = System.currentTimeMillis();
            }
        }

    }
}
