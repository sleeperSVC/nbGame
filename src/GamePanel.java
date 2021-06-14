import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer timer;
    Menu menu;
    EndScene end;
    Shop shop;
    Map map;
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

    public static final double GRAVITY = 3;
    public static final int FRICTION = 1;

    ImageIcon menu1 = new ImageIcon(getClass().getResource("resources/image/environment/menu1.png"));
    ImageIcon layer1 = new ImageIcon(getClass().getResource("resources/image/environment/layer1.png"));
    ImageIcon layer2 = new ImageIcon(getClass().getResource("resources/image/environment/layer2.png"));
    ImageIcon layer3 = new ImageIcon(getClass().getResource("resources/image/environment/layer3.png"));
    ImageIcon layer4 = new ImageIcon(getClass().getResource("resources/image/environment/layer4.png"));
    ImageIcon hudIcon = new ImageIcon(getClass().getResource("resources/image/environment/hud.png"));

    public GamePanel() {
        timer = new Timer(1000 / 60, this);
        menu = new Menu();
        shop = new Shop();
        end = new EndScene();
        restartGame();
    }

    private void restartGame() {
        map = new Map();
        p = new Player(416, 288, 32, 32, 10, 3, 100);    // initialize a new player
        objectManager = new GameObjectManager(p);
    }

    public void updateMenuState() {
        menu.checkHovering(point);
    }

    // updates the player and all game objects
    public void updateGameState() {
        objectManager.updateObjects();
        if (!p.isAlive) {
            currentState = END_STATE;
        }
    }

    public void updateShopState() {
        shop.checkHovering(point);
        shop.updateShops();
    }

    public void updateEndState() {
        end.checkHovering(point);
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
        layer4.paintIcon(this, g, 0, 0);
        hudIcon.paintIcon(this, g, 0, 0);
        objectManager.hud.drawMoney(g);

        g.setColor(Color.white);
        g.drawString("relative to window: " + point.x + "," + point.y, 10, 100);
    }

    // TODO this is why we have the Shop and ShopButton classes, so that we dont have to have 9999 lines clogging up gamepanel
    public void drawShopState(Graphics g) {
        shop.drawShops(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 14));
        g.drawString("COST: $5", 300, 112);
        g.drawString("COST: $8", 300, 212);
        g.drawString("COST: $1", 300, 312);
        g.drawString("COST: $2", 300, 412);

        if (p.fireRate >= .5) {
            g.drawString("Fire Rate = " + p.fireRate, 400, 85);
        } else {
            g.drawString("Fire Rate Maxed Out", 400, 85);
        }

        if (p.bulletDamage <= 110) {
            g.drawString("Damage = " + p.bulletDamage, 400, 185);
        } else {
            g.drawString("Bullet Damage Maxed Out", 400, 185);
        }

        if (Atbs.bulletInaccuracy >= .5) {
            g.drawString("Inaccuracy = " + (float) Atbs.bulletInaccuracy, 400, 284);
        } else {
            g.drawString("Bullet Accuracy Maxed Out", 400, 284);
        }

        if (Atbs.bulletSpeedFactor <= 110) {
            g.drawString("Bullet Speed = " + Atbs.bulletSpeedFactor, 400, 384);
        } else {
            g.drawString("Bullet Speed Maxed Out", 400, 384);
        }
    }

    public void drawEndState(Graphics g) {
        menu1.paintIcon(this, g, 0, 0);
        end.draw(g);
    }

    // illustrates the game display onto the JPanel based on the game state
    public void paintComponent(Graphics g) {
        if (currentState == MENU_STATE) {
            drawMenuState(g);
        } else if (currentState == GAME_STATE) {
            drawGameState(g);
        } else if (currentState == END_STATE) {
            drawEndState(g);
        } else if (currentState == SHOP_STATE) {
            drawShopState(g);
        }
    }

    //updates the game state
    @Override
    public void actionPerformed(ActionEvent e) {

        point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, this);

        if (currentState == MENU_STATE) {
            updateMenuState();
        } else if (currentState == GAME_STATE) {
            updateGameState();
        } else if (currentState == END_STATE) {
            updateEndState();
        } else if (currentState == SHOP_STATE) {
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
        // for some reason if you use MouseEvent e, the coordinates are slightly inaccurate. so instead,
        // just use point.

        //menu state
        if (currentState == MENU_STATE) {
            System.out.println(point.getX() + ", " + point.getY());

            if (menu.checkClicked(point) == menu.START_BUTTON) {
                currentState = GAME_STATE;
            }
            if (menu.checkClicked(point) == menu.HELP_BUTTON) {
                JOptionPane.showMessageDialog(null, "WASD to move\nLeft Mouse to Fire\nSave the minion babies and kill the Ghosts");
            }
            if (menu.checkClicked(point) == menu.CREDITS_BUTTON) {
                JOptionPane.showMessageDialog(null, "Bran Ran and Etan");
            }
        }
        //end state
        if (currentState == END_STATE) {
            if (end.mouseClicked(point) == EndScene.RESTART_BUTTON) {
                restartGame();
                currentState = GAME_STATE;
            }
            if (end.mouseClicked(point) == EndScene.MENU_BUTTON) {
                restartGame();
                currentState = MENU_STATE;
            }
        }

        // TODO this is why we have the Shop and ShopButton classes, so that we dont have to have 9999 lines clogging up gamepanel
        //shop state
        if (currentState == SHOP_STATE) {   // fire rate
            if (shop.checkClicked(point) == 0) {
                if (p.fireRate >= .5 && (p.money - 5) >= 0) {
                    System.out.println("Rate Up");
                    p.raiseFireRate();
                    p.money -= 5;
                    System.out.println(p.fireRate);
                }
            }

            if (shop.checkClicked(point) == 1) {    // bullet damage
                if (p.bulletDamage <= 110 && (p.money - 8) >= 0) {
                    System.out.println("Dmg Up");
                    p.bulletDamage += 1;
                    p.money -= 8;
                    System.out.println(p.bulletDamage);
                }
            }

            if (shop.checkClicked(point) == 2) {    // bullet accuracy
                if (Atbs.bulletInaccuracy >= .5 && (p.money - 1) >= 0) {
                    System.out.println("Accuracy Up");
                    Atbs.bulletInaccuracy -= .1;
                    p.money -= 2;
                    System.out.println(Atbs.bulletInaccuracy);
                }
            }

            if (shop.checkClicked(point) == 3) {    // bullet speed
                if (Atbs.bulletSpeedFactor <= 110 && (p.money - 2) >= 0) {
                    System.out.println("Speed Up");
                    Atbs.bulletSpeedFactor++;
                    p.money -= 3;
                    System.out.println(Atbs.bulletSpeedFactor);
                }
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
