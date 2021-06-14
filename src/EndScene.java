import java.awt.*;

public class EndScene {
    Rectangle playAgain = new Rectangle(140, 150, 240, 50);
    Rectangle menuRect = new Rectangle(140, 220, 270, 50);

    final static int RESTART_BUTTON = 0;
    final static int MENU_BUTTON = 1;

    int currentHovered = -1;

    Font endFont = new Font("Courier", Font.BOLD, 36);

    public int mouseClicked(Point point) {
        if (playAgain.contains(point)) {
            return RESTART_BUTTON;
        }
        if (menuRect.contains(point)) {
            return MENU_BUTTON;
        }

        return -1;
    }

    public void checkHovering(Point point) {
        if (playAgain.contains(point)) {
            currentHovered = RESTART_BUTTON;
        } else if (menuRect.contains(point)) {
            currentHovered = MENU_BUTTON;
        } else {
            currentHovered = -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.setFont(endFont);

        g.fillRect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);
        g.fillRect(menuRect.x, menuRect.y, menuRect.width, menuRect.height);

        g.setColor(Color.white);
        g.drawString("YOU DIED!", playAgain.x, playAgain.y - 100 + endFont.getSize());
        g.drawString("Play Again?", playAgain.x, playAgain.y + endFont.getSize());
        g.drawString("Back to Menu", menuRect.x, menuRect.y + endFont.getSize());

        g.setColor(Color.DARK_GRAY);
        switch (currentHovered) {
            case RESTART_BUTTON:
                g.fillRect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);

                g.setColor(Color.GRAY);
                g.fillRect(playAgain.x, playAgain.y, playAgain.width, playAgain.height);

                g.setColor(Color.red);
                g.drawString("Play Again?", playAgain.x, playAgain.y + endFont.getSize());
                break;
            case MENU_BUTTON:
                g.fillRect(menuRect.x, menuRect.y, menuRect.width, menuRect.height);

                g.setColor(Color.GRAY);
                g.fillRect(menuRect.x, menuRect.y, menuRect.width, menuRect.height);

                g.setColor(Color.red);
                g.drawString("Back to Menu", menuRect.x, menuRect.y + endFont.getSize());
                break;
        }
    }
}
