import javax.swing.*;
import java.awt.*;

public class GameRunner {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final Rectangle frameCollisionBox = new Rectangle(0, 0, 1280,720);

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        GamePanel gamePanel = new GamePanel();

        frame.add(gamePanel);
        frame.addKeyListener(gamePanel);
        frame.addMouseListener(gamePanel);
        frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));  // set size of window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // when user clicks the X in the window, quit the program
        frame.pack();
        frame.setVisible(true);

        gamePanel.startTimer();
    }
}
