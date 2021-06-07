import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.Font.*;
import java.awt.color.*;

public class EndScene {
    Rectangle playAgain = new Rectangle(140, 150, 210, 50);
    Rectangle menuRect = new Rectangle(140, 220, 210, 50);
    
    final static int RESTART_BUTTON = 0;
    final static int MENU_BUTTON = 1;

    int currentHovered = -1;

    Font endFont = new Font("Courier", Font.BOLD, 36);

}
