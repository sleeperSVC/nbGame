import java.awt.*;
import javax.swing.ImageIcon;

public class GameObject {

    // TODO im making all these have the default access modifier (which is basically the same as public) cause
    //  its annoying to have to do Balls.getX().setX(Nuts.getX()).setY(Balls.getAlive()))()()()()
    //  by the way, after you see this comment, delete it
    int x;
    int y;
    int width;
    int height;
    boolean isAlive;
    Rectangle collisionBox;
    // Image image;
    // String imageName;

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
