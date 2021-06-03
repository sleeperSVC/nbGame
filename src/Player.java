import java.awt.*;

public class Player extends GameObject {

    int health;
    int fireRate;

    public Player(int x, int y, int width, int height, int health, int fireRate) {
        super(x, y, width, height);
        this.health = health;
        this.fireRate = fireRate;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics g) {

    }

}
