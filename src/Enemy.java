import javax.swing.*;
import java.awt.*;

public class Enemy extends GameObject {


    public Enemy(int x, int y, int width, int height) {

        super(x, y, width, height);

        frameCounter = 0;
        Image im;

        String enemy1Path = "src/assets/img/entities/enemy1/enemy1_1.png";
        ImageIcon ii = new ImageIcon(enemy1Path);
        im = ii.getImage();
        frameHolder.add(im);

        enemy1Path = "src/assets/img/entities/enemy1/enemy1_2.png";
        ii = new ImageIcon(enemy1Path);
        im = ii.getImage();
        frameHolder.add(im);

        enemy1Path = "src/assets/img/entities/enemy1/enemy1_3.png";
        ii = new ImageIcon(enemy1Path);
        im = ii.getImage();
        frameHolder.add(im);

        enemy1Path = "src/assets/img/entities/enemy1/enemy1_4.png";
        ii = new ImageIcon(enemy1Path);
        im = ii.getImage();
        frameHolder.add(im);

        enemy1Path = "src/assets/img/entities/enemy1/enemy1_5.png";
        ii = new ImageIcon(enemy1Path);
        im = ii.getImage();
        frameHolder.add(im);
    }

    @Override
    public void update() {
        super.update();

        if (healthCounter == 0){
            isAlive = false;
        }

        //animation loop, idk if this is how u do it but it is how i think we can do it
        //we might be able to move it to gameObject and have it take framecount params and shit but this is easier
        if (frameCounter < 5) {
            frameCounter++;
        } else {
            frameCounter = 0;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frameHolder.get(frameCounter), x, y, null);

    }
}
