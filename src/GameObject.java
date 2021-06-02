import java.awt.*;
import javax.swing.ImageIcon;

public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isAlive;
    Rectangle collisionBox;
    //private Image image;
    //private String imageName;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        collisionBox = new Rectangle(x, y, width, height);
    }

    public void update() {
        collisionBox.setBounds(x, y, width, height);    // updates collisionBox
    }

    public void draw(Graphics g) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean getAlive() {
        return isAlive;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    /* TODO ok we gonna mess around with images later ok

    void getImageDimensions() {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    */

}
