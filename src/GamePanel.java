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
    int currentState = GAME_STATE;

    final static double GRAVITY = 5;
    final static double FRICTION = 2;

    public GamePanel() {
        timer = new Timer(1000 / 60, this);   // the timer will call actionPerformed() 60 times a second
        p = new Player(448, 288, 32, 32, 200, 2, 10, 1);    // initialize a new player
        mF = new MuzzleFlash(448, 288, 5, 5, p);
        objectManager = new GameObjectManager(p);
    }

    public void drawMenuState(Graphics g) {

    }

    public void drawGameState(Graphics g) {
        g.fillRect(0, 0, GameRunner.WIDTH, GameRunner.HEIGHT);
        objectManager.drawObjects(g);
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
