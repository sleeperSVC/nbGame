import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {

    int x;
    int y;
    int width;
    int height;
    int healthCounter;
    int speed;

    boolean isAlive = true;

    boolean movingRight = false;
    boolean movingLeft = false;

    int orientation = 1;
    Rectangle collisionBox;

    ArrayList<BufferedImage> frameHolder = new ArrayList<BufferedImage>();
    int frameCounter;


    // constructor with health and speed parameter
    public GameObject(int x, int y, int width, int height, int health, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.healthCounter = health;
        this.speed = speed;
        collisionBox = new Rectangle(x, y, width, height);
    }

    // constructor with no health parameter
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collisionBox = new Rectangle(x, y, width, height);
    }

    public void update() {
        collisionBox.setBounds(x, y, width, height);    // updates collisionBox to have the same values as the GameObject itself
    }

    public void draw(Graphics g) {

    }
}
