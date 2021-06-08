import java.awt.*;
import java.awt.event.MouseEvent;

public class EndScene {
    Rectangle playAgain = new Rectangle(140, 150, 240, 50);
    Rectangle menuRect = new Rectangle(140, 220, 240, 50);

    final static int RESTART_BUTTON = 0;
    final static int MENU_BUTTON = 1;

    int currentHovered = -1;

    Font endFont = new Font("Courier", Font.BOLD, 36);

    public int mouseClicked(MouseEvent e) {
        if (playAgain.intersects(e.getX(), e.getY(), 1, 1)) {
            return RESTART_BUTTON;
        }
        if (menuRect.intersects(e.getX(), e.getY(), 1, 1)) {
            return MENU_BUTTON;
        }

        return -1;
    }

    public void checkHover(Point e) {
        if (playAgain.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = RESTART_BUTTON;
        } else if (menuRect.intersects(e.getX(), e.getY(), 1, 1)) {
            currentHovered = MENU_BUTTON;
        } else {
            currentHovered = -1;
        }
        System.out.println("mouse entered " + currentHovered);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(endFont);

        g.fillRect(playAgain.x, playAgain.y - 35, playAgain.width, playAgain.height);
        g.fillRect(menuRect.x, menuRect.y - 35, menuRect.width, menuRect.height);

        g.setColor(Color.white);
        g.drawString("Play Again?", playAgain.x, playAgain.y);
        g.drawString("Back to Menu", menuRect.x, menuRect.y);

        g.setColor(Color.DARK_GRAY);
        switch (currentHovered) {
            case RESTART_BUTTON:
                g.fillRect(playAgain.x - 10, playAgain.y - 30, playAgain.width, playAgain.height);

                g.setColor(Color.GRAY);
                g.fillRect(playAgain.x, playAgain.y - 35, playAgain.width, playAgain.height);

                g.setColor(Color.red);
                g.drawString("Play Again?", playAgain.x, playAgain.y);
                break;
            case MENU_BUTTON:
                g.fillRect(menuRect.x - 10, menuRect.y - 30, menuRect.width, menuRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(menuRect.x, menuRect.y - 35, menuRect.width, menuRect.height);

                g.setColor(Color.red);
                g.drawString("Back to Menu", menuRect.x, menuRect.y);
                break;
        }
    }
}
