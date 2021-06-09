import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer timer;
    Menu menu;
    EndScene end;
    Shop shop;
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
        restartGame();
    }

    private void restartGame() {
        map = new Map();
        p = new Player(448, 288, 32, 32, 10, 4, 100);    // initialize a new player
        hud = new HUD(0, 0, 960, 540, p);
        objectManager = new GameObjectManager(p);
        objectManager.addEnemy1();// TODO: delete this eventually
        objectManager.addEnemy2();
    }

    public void updateMenuState() {
        Point point = MouseInfo.getPointerInfo().getLocation();
        menu.checkHover(point);
    }

    // updates the player and all game objects
    public void updateGameState() {
        objectManager.updateObjects();
        if (!p.isAlive) {
            currentState = END_STATE;
        }
    }

    public void updateShopState() {
        Point point = MouseInfo.getPointerInfo().getLocation();
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
        if (currentState == SHOP_STATE) {
            drawShopState(g);
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

    public void drawShopState(Graphics g) {
        shop.drawShops(g);
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
        if (currentState == SHOP_STATE) {
            updateShopState();
        }
        repaint();
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
            case KeyEvent.VK_P:
                if (currentState == GAME_STATE) {
                    currentState = SHOP_STATE;
                } else if (currentState == SHOP_STATE) {
                    currentState = GAME_STATE;
                }
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
            if (menu.mouseClicked(e) == menu.HELP_BUTTON) {
                JOptionPane.showMessageDialog(null, "WASD to move\nLeft Mouse to Fire\nSave the minion babies and kill the Ghosts");
            }
            if (menu.mouseClicked(e) == menu.START_BUTTON) {
                currentState = GAME_STATE;
            }
            if (menu.mouseClicked(e) == menu.CREDITS_BUTTON) {
                JOptionPane.showMessageDialog(null, "Bryan Rayan and Ethan");
            }
        }

        if (currentState == END_STATE) {
            if (end.mouseClicked(e) == EndScene.RESTART_BUTTON) {
                restartGame();
                currentState = GAME_STATE;
            }
            if (end.mouseClicked(e) == EndScene.MENU_BUTTON) {
                currentState = MENU_STATE;
            }
        }

        if (currentState == SHOP_STATE) {

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

    // unused methods that we still have to override cause interface
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
