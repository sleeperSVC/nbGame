import java.awt.*;
import java.awt.event.MouseEvent;

public class Menu {

    Rectangle startRect = new Rectangle(0, 0, 0, 0);
    Rectangle helpRect = new Rectangle(0, 0, 0, 0);
    Rectangle creditsRect = new Rectangle(0, 0, 0, 0);
    final static int START_BUTTON = 0;
    final static int HELP_BUTTON = 1;
    final static int CREDITS_BUTTON = 2;


    // well have this method return an integer, and then in GamePanel mouseClicked() depending
    // on the returned value it will do something
    public int mouseClicked(MouseEvent e) {
        if (startRect.intersects(e.getX(), e.getY(), 1, 1)) {
            return START_BUTTON;
        }

        return -1;
    }

    public void mouseEntered(MouseEvent e) {
        if (startRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = START_BUTTON;
        }
        if (helpRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = HELP_BUTTON;
        }
        if (creditsRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = CREDITS_BUTTON;
        }
        System.out.println("mouse entered " + currentHovered);

    }

    public void draw(Graphics g) {
        g.drawRect(startRect.x, startRect.y, startRect.width, startRect.height);
    }

}
