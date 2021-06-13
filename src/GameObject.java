import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class GameObject {

    int x;
    int y;
    int width;
    int height;
    int health;
    boolean isAlive = true;
    boolean isDamaged = false;

    int orientation = 1;
    Rectangle cBox;

    ArrayList<BufferedImage> frameHolder = new ArrayList<>();
    int frameCounter;
    boolean frameCheck;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        cBox = new Rectangle(x, y, width, height);
        frameCounter = 0;
        frameCheck = true;

    }

    public void update() {
        cBox.setBounds(x, y, width, height);    // updates collisionBox to have the same values as the GameObject itself
    }

    public abstract void draw(Graphics g);
}
