import java.awt.*;

//960 x 540
public class Menu {

    Rectangle startRect = new Rectangle(130, 150, 240, 50);
    Rectangle helpRect = new Rectangle(130, 220, 255, 50);
    Rectangle creditsRect = new Rectangle(130, 290, 200, 50);

    final int START_BUTTON = 0;
    final int HELP_BUTTON = 1;
    final int CREDITS_BUTTON = 2;

    int currentHovered = -1;

    Font titleFont = new Font("Courier", Font.BOLD, 36);


    // we'll have this method return an integer, and then in GamePanel mouseClicked() depending
    // on the returned value it will do something
    public int checkClicked(Point e) {
        if (startRect.contains(e.getX(), e.getY())) {
            return START_BUTTON;
        }
        if (helpRect.contains(e.getX(), e.getY())) {
            return HELP_BUTTON;
        }
        if (creditsRect.contains(e.getX(), e.getY())) {
            return CREDITS_BUTTON;
        }
        return -1;
    }

    public void checkHovering(Point e) {
        if (startRect.contains(e.getX(), e.getY())) {
            currentHovered = START_BUTTON;
        } else if (helpRect.contains(e.getX(), e.getY())) {
            currentHovered = HELP_BUTTON;
        } else if (creditsRect.contains(e.getX(), e.getY())) {
            currentHovered = CREDITS_BUTTON;
        } else {
            currentHovered = -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(titleFont);

        g.fillRect(startRect.x, startRect.y, startRect.width, startRect.height);
        g.fillRect(creditsRect.x, creditsRect.y, creditsRect.width, creditsRect.height);
        g.fillRect(helpRect.x, helpRect.y, helpRect.width, helpRect.height);

        g.setColor(Color.white);
        g.drawString("Minion Rescue", startRect.x, startRect.y - 100 + titleFont.getSize());
        g.drawString("START GAME", startRect.x, startRect.y + titleFont.getSize());
        g.drawString("HOW TO PLAY", helpRect.x, helpRect.y + titleFont.getSize());
        g.drawString("CREDITS", creditsRect.x, creditsRect.y + titleFont.getSize());

        g.setColor(Color.DARK_GRAY);
        switch (currentHovered) {
            case START_BUTTON:
                g.fillRect(startRect.x, startRect.y, startRect.width, startRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(startRect.x, startRect.y, startRect.width, startRect.height);

                g.setColor(Color.red);
                g.drawString("START GAME", startRect.x, startRect.y + titleFont.getSize());
                break;
            case CREDITS_BUTTON:
                g.fillRect(creditsRect.x, creditsRect.y, creditsRect.width, creditsRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(creditsRect.x, creditsRect.y, creditsRect.width, creditsRect.height);

                g.setColor(Color.red);
                g.drawString("CREDITS", creditsRect.x, creditsRect.y + titleFont.getSize());
                break;
            case HELP_BUTTON:
                g.fillRect(helpRect.x, helpRect.y, helpRect.width, helpRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(helpRect.x, helpRect.y, helpRect.width, helpRect.height);

                g.setColor(Color.red);
                g.drawString("HOW TO PLAY", helpRect.x, helpRect.y + titleFont.getSize());
                break;
        }
    }
}
