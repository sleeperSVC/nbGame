import javax.imageio.ImageIO;
import java.io.IOException;

public class Enemy1 extends EnemyObject {

    long randomMovementStartTime = System.currentTimeMillis();
    int randomMovementInterval = 2000;

    public Enemy1(int x, int y, int width, int height, int health, int speedFactor, int damage, Player p) {
        super(x, y, width, height, health, speedFactor, damage, p);

        xVMax = 1;
        frameCount = 5;

        try {
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_1.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_2.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_3.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_4.png")));
            frames.add(ImageIO.read(getClass().getResource("resources/image/entities/enemy1/enemy1_5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void move() {

        // if on the same Y level as the player, chase the player
        if (y >= p.y - 10 && y <= p.y + 50) {
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

            // else, move around randomly
        } else {
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
