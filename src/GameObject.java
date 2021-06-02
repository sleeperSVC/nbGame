import java.awt.*;
import javax.swing.ImageIcon;

public class GameObject {

    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
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

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }

    public boolean isAlive()
    {
        return isAlive;
    }
}
