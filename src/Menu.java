import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.Font.*;
import java.awt.color.*;

//960 x 540
public class Menu {

    Rectangle startRect = new Rectangle(140, 150, 210, 50);
    Rectangle helpRect = new Rectangle(140, 220, 210, 50);
    Rectangle creditsRect = new Rectangle(140, 290, 210, 50);

    final static int START_BUTTON = 0;
    final static int HELP_BUTTON = 1;
    final static int CREDITS_BUTTON = 2;

    int currentHovered = -1;

    Font titleFont = new Font("Courier", Font.BOLD, 36);


    // we'll have this method return an integer, and then in GamePanel mouseClicked() depending
    // on the returned value it will do something
    public int mouseClicked(MouseEvent e) {
        if (startRect.intersects(e.getX(), e.getY(), 1, 1)) {
            return START_BUTTON;
        }
        if (helpRect.intersects(e.getX(), e.getY(), 1, 1)) {
            return HELP_BUTTON;
        }
        if (creditsRect.intersects(e.getX(), e.getY(), 1, 1)) {
            return CREDITS_BUTTON;
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
        g.setColor(Color.white);
        g.setFont(titleFont);
        g.drawString("Start Game", startRect.x, startRect.y);
        g.drawString("How To Play", helpRect.x, helpRect.y);
        g.drawString("Credits", creditsRect.x, creditsRect.y);

        switch (currentHovered) {
            case START_BUTTON:
                g.drawRect(startRect.x, startRect.y, startRect.width, startRect.height);
                break;
            case CREDITS_BUTTON:
                g.drawRect(creditsRect.x, creditsRect.y, creditsRect.width, creditsRect.height);
                break;
            case HELP_BUTTON:
                g.drawRect(helpRect.x, helpRect.y, helpRect.width, helpRect.height);
                break;
        }
    }

}
