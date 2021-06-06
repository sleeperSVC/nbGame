import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer timer;
    Player p;
    GameObjectManager objectManager;
    MuzzleFlash mF;
    final int MENU_STATE = 0;
    final int GAME_STATE = 1;
    final int END_STATE = 2;
    int currentState = MENU_STATE;

    public static final int FRAME_WIDTH = 960;
    public static final int FRAME_HEIGHT = 540;
    public static final Rectangle frameCollisionBox = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    final static double GRAVITY = 5;
    final static int FRICTION = 2;

    ImageIcon layer1 = new ImageIcon(getClass().getResource("resources/image/environment/layer1.png"));
    ImageIcon layer2 = new ImageIcon(getClass().getResource("resources/image/environment/layer2.png"));
    ImageIcon layer3 = new ImageIcon(getClass().getResource("resources/image/environment/layer3.png"));
    ImageIcon layer4 = new ImageIcon(getClass().getResource("resources/image/environment/layer4.png"));

    public GamePanel() {
        timer = new Timer(1000 / 60, this);
        p = new Player(448, 288, 32, 32, 200, 4, 10);    // initialize a new player
        mF = new MuzzleFlash(448, 288, 5, 5, p);
        objectManager = new GameObjectManager(p);
    }

    public void drawMenuState(Graphics g) {

    }

    public void drawGameState(Graphics g) {

        layer1.paintIcon(this, g, 0, 0);
        layer2.paintIcon(this, g, 0, 0);
        layer3.paintIcon(this, g, 0, 0);
        objectManager.drawObjects(g);   // painting game objects before layer 4
        layer4.paintIcon(this, g, 0, 0);
    }

    public void drawEndState(Graphics g) {

    }

    public void updateMenuState() {

    }

    // updates the player and all game objects
    public void updateGameState() {
        objectManager.updateObjects();
    }

    public void updateEndState() {

    }

    // illustrates the game display onto the JPanel based on the game state
    public void paintComponent(Graphics g) {
        if (currentState == MENU_STATE) {
            drawMenuState(g);
        }
        if (currentState == GAME_STATE) {
            drawGameState(g);
        }
        if (currentState == END_STATE) {
            drawEndState(g);
        }
    }

    //updates the game state
    @Override
    public void actionPerformed(ActionEvent e) {

        if (currentState == MENU_STATE) {
            updateMenuState();
        }
        if (currentState == GAME_STATE) {
            updateGameState();
        }
        if (currentState == END_STATE) {
            updateEndState();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //starts player movement when the keys are pressed
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p.move('w');
                break;
            case KeyEvent.VK_A:
                p.move('a');
                break;
            case KeyEvent.VK_S:
                p.move('s');
                break;
            case KeyEvent.VK_D:
                p.move('d');
                break;
        }
    }

    //stops player movement when the keys are released
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p.stopMove('w');
                break;
            case KeyEvent.VK_A:
                p.stopMove('a');
                break;
            case KeyEvent.VK_S:
                p.stopMove('s');
                break;
            case KeyEvent.VK_D:
                p.stopMove('d');
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //sets the firing status to true
    @Override
    public void mousePressed(MouseEvent e) {
        objectManager.isFiring = true;
        //objectManager.addBullet();
    }

    //sets the firing status to false
    @Override
    public void mouseReleased(MouseEvent e) {
        objectManager.isFiring = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
