import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer timer;
    Menu menu;
    EndScene end;
    Map map;
    HUD hud;
    GameObjectManager objectManager;
    Player p;
    Point point;

    final int MENU_STATE = 0;
    final int GAME_STATE = 1;
    final int SHOP_STATE = 2;
    final int END_STATE = 3;
    int currentState = MENU_STATE;

    public static final int FRAME_WIDTH = 960;
    public static final int FRAME_HEIGHT = 540;
    public static final Rectangle frameCollisionBox = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    public static final double GRAVITY = 5;
    public static final int FRICTION = 2;

    ImageIcon menu1 = new ImageIcon(getClass().getResource("resources/image/environment/menu1.png"));
    ImageIcon layer1 = new ImageIcon(getClass().getResource("resources/image/environment/layer1.png"));
    ImageIcon layer2 = new ImageIcon(getClass().getResource("resources/image/environment/layer2.png"));
    ImageIcon layer3 = new ImageIcon(getClass().getResource("resources/image/environment/layer3.png"));
    ImageIcon layer4 = new ImageIcon(getClass().getResource("resources/image/environment/layer4.png"));
    ImageIcon hudIcon = new ImageIcon(getClass().getResource("resources/image/environment/hud.png"));

    public GamePanel() {
        timer = new Timer(1000 / 60, this);
        menu = new Menu();
        end = new EndScene();
        map = new Map();
        p = new Player(448, 288, 32, 32, 200, 4, 100);    // initialize a new player
        objectManager = new GameObjectManager(p);

        objectManager.addEnemy();// TODO: delete this eventually
    }

    public void updateMenuState() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        menu.checkHover(point);
    }

    // updates the player and all game objects
    public void updateGameState() {
        objectManager.updateObjects();
        if(!p.isAlive){
            currentState = END_STATE;
        }
    }

    public void updateEndState() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        end.checkHover(point);
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


    public void drawMenuState(Graphics g) {
        menu1.paintIcon(this, g, 0, 0);
        menu.draw(g);
    }

    public void drawGameState(Graphics g) {
        layer1.paintIcon(this, g, 0, 0);
        layer2.paintIcon(this, g, 0, 0);
        layer3.paintIcon(this, g, 0, 0);
        objectManager.drawObjects(g);   // painting game objects before layer 4
        hud.draw(g);
        hudIcon.paintIcon(this, g, 0, 0);
        layer4.paintIcon(this, g, 0, 0);

    }

    public void drawEndState(Graphics g) {
        menu1.paintIcon(this, g, 0, 0);
        end.draw(g);
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
                p.changeMovingStatus(true);
                break;
            case KeyEvent.VK_A:
                p.move('a');
                p.changeMovingStatus(true);
                break;
            case KeyEvent.VK_S:
                p.move('s');
                p.changeMovingStatus(true);
                break;
            case KeyEvent.VK_D:
                p.move('d');
                p.changeMovingStatus(true);
                break;
        }
    }

    //stops player movement when the keys are released
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                p.stopMove('w');
                p.changeMovingStatus(false);
                break;
            case KeyEvent.VK_A:
                p.stopMove('a');
                p.changeMovingStatus(false);
                break;
            case KeyEvent.VK_S:
                p.stopMove('s');
                p.changeMovingStatus(false);
                break;
            case KeyEvent.VK_D:
                p.stopMove('d');
                p.changeMovingStatus(false);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (currentState == MENU_STATE) {
            if (menu.mouseClicked(e) == Menu.HELP_BUTTON){
                JOptionPane.showMessageDialog(null, "WASD to move\nLeft Mouse to Fire\nSave the minion babies and kill the Ghosts");
            }
            if(menu.mouseClicked(e) == Menu.START_BUTTON){
                currentState = GAME_STATE;
               // updateGameState();
            }
            if (menu.mouseClicked(e) == Menu.CREDITS_BUTTON) {
                JOptionPane.showMessageDialog(null, "Bryan Rayan and Ethan");
            }
        }

        if (currentState == END_STATE){
            if (end.mouseClicked(e) == end.RESTART_BUTTON){
                currentState = GAME_STATE;
            }
            if (end.mouseClicked(e) == EndScene.MENU_BUTTON) {
                currentState = MENU_STATE;
            }
        }
    }

    //sets the firing status to true
    @Override
    public void mousePressed(MouseEvent e) {
        if (currentState == GAME_STATE) {
            objectManager.isFiring = true;
        }
        //objectManager.addBullet();
    }

    //sets the firing status to false
    @Override
    public void mouseReleased(MouseEvent e) {
        if (currentState == GAME_STATE) {
            objectManager.isFiring = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        objectManager.addBullet();//this is not done
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
