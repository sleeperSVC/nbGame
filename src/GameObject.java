import java.awt.*;

public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    boolean isAlive;
    Rectangle collisionBox;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collisionBox = new Rectangle(x, y, width, height);
    }

    void update() {
        collisionBox.setBounds(x, y, width, height);
    }

    void draw(Graphics g) {

    }

}
