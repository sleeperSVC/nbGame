import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Bullet extends GameObject {


    public Bullet(int x, int y, int width, int height) {
        super(x, y, width, height);
        frameCounter = 0;
        Image im;

        String bulletPath = "src/assets/img/entities/bullet/bullet_1.png";
        ImageIcon ii = new ImageIcon(bulletPath);
        im = ii.getImage();
        frameHolder.add(im);

        bulletPath = "src/assets/img/entities/bullet/bullet_2.png";
        ii = new ImageIcon(bulletPath);
        im = ii.getImage();
        frameHolder.add(im);

        bulletPath = "src/assets/img/entities/bullet/bullet_3.png";
        ii = new ImageIcon(bulletPath);
        im = ii.getImage();
        frameHolder.add(im);
    }

    @Override
    public void update() {
        super.update();

        // check if the bullet is out of bounds
        if (!collisionBox.intersects(GameRunner.frameCollisionBox)) {
            isAlive = false;
        }

        //animation loop, idk if this is how u do it but it is how i think we can do it
        //we might be able to move it to gameObject and have it take framecount params and shit but this is easier
        if (frameCounter < 3 )
        {
            frameCounter++;
        }
        else    //isAlive becomes false after 3 frames cause the animation is 3 frames long, we will have to change the penetration and shit cause the animation depends on isAlive
        {
            frameCounter = 0;
            isAlive = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frameHolder.get(frameCounter), x, y, null);
    }
}
