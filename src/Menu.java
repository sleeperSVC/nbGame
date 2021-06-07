import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.Font.*;
import java.awt.color.*;

//960 x 540
public class Menu {

    Rectangle startRect = new Rectangle(140, 150, 215, 50);
    Rectangle helpRect = new Rectangle(140, 220, 215, 50);
    Rectangle creditsRect = new Rectangle(140, 290, 215, 50);

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

    public void checkHover(Point e) {
        if (startRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = START_BUTTON;
        } else if (helpRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = HELP_BUTTON;
        } else if (creditsRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = CREDITS_BUTTON;
        } else {
            currentHovered = -1;
        }
        System.out.println("mouse entered " + currentHovered);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(titleFont);

        g.fillRect(startRect.x, startRect.y-35, startRect.width, startRect.height);
        g.fillRect(creditsRect.x, creditsRect.y-35, creditsRect.width, creditsRect.height);
        g.fillRect(helpRect.x, helpRect.y-35, helpRect.width, helpRect.height);

        g.setColor(Color.white);
        g.drawString("Start Game", startRect.x, startRect.y);
        g.drawString("How To Play", helpRect.x, helpRect.y);
        g.drawString("Credits", creditsRect.x, creditsRect.y);

        g.setColor(Color.DARK_GRAY);
        switch (currentHovered) {
            case START_BUTTON:
                g.fillRect(startRect.x-10, startRect.y-30, startRect.width, startRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(startRect.x, startRect.y-35, startRect.width, startRect.height);

                g.setColor(Color.red);
                g.drawString("Start Game", startRect.x, startRect.y);
                break;
            case CREDITS_BUTTON:
                g.fillRect(creditsRect.x-10, creditsRect.y-30, creditsRect.width, creditsRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(creditsRect.x, creditsRect.y-35, creditsRect.width, creditsRect.height);

                g.setColor(Color.red);
                g.drawString("Credits", creditsRect.x, creditsRect.y);
                break;
            case HELP_BUTTON:
                g.fillRect(helpRect.x-10, helpRect.y-30, helpRect.width, helpRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(helpRect.x,helpRect.y-35, helpRect.width, helpRect.height);

                g.setColor(Color.red);
                g.drawString("How To Play", helpRect.x, helpRect.y);
                break;
        }
    }

}
