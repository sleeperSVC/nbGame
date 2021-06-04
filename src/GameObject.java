import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameObject {

    // TODO im making all these have the default access modifier (which is basically the same as public) cause
    //  its annoying to have to do Balls.getX().setX(Nuts.getX()).setY(Balls.getAlive()))()()()()
    //  by the way, after you see this comment, delete it
    int x;
    int y;
    int width;
    int height;
    int healthCounter;
    int speed;

    boolean isAlive;
    int orientation;
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


//    // TODO ok we gonna mess around with images later ok
//          TODO decide if we are gonna do this or use arraylists and stuff, see bullet.java
//    void getImageDimensions() {
//        width = image.getWidth(null);
//        height = image.getHeight(null);
//    }
//
//    protected void loadImage(String imageName) {
//        ImageIcon ii = new ImageIcon(imageName);
//        image = ii.getImage();
//    }
//
//    public Image getImage() {
//        return image;
//    }


}
