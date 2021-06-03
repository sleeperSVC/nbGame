import java.awt.*;
import java.util.ArrayList;

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
    ArrayList<Image> frameHolder;   //moved this here cause mos of the obects have frames, I only did the bullet class btw
    int frameCounter;               // ^ this makes the loadimage methods and shit obsolete beacuse we will outsource the animation to the individual classes ok

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
