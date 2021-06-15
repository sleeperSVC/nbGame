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
    Point point;    // hold location of mouse

    final int MENU_STATE = 0;
    final int GAME_STATE = 1;
    final int SHOP_STATE = 2;
    final int END_STATE = 3;
    int currentState = MENU_STATE;

    public static final int FRAME_WIDTH = 960;
    public static final int FRAME_HEIGHT = 540;
    public static final Rectangle frameCollisionBox = new Rectangle(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    public static final double GRAVITY = 2;
    public static final int FRICTION = 1;

    ImageIcon menu1 = new ImageIcon(getClass().getResource("resources/image/environment/menu1.png"));
    ImageIcon layer1 = new ImageIcon(getClass().getResource("resources/image/environment/layer1.png"));
    ImageIcon layer2 = new ImageIcon(getClass().getResource("resources/image/environment/layer2.png"));
    ImageIcon layer3 = new ImageIcon(getClass().getResource("resources/image/environment/layer3.png"));
    ImageIcon layer4 = new ImageIcon(getClass().getResource("resources/image/environment/layer4.png"));
    ImageIcon hudIcon = new ImageIcon(getClass().getResource("resources/image/environment/hud.png"));

    Font titleFont = new Font("Courier", Font.BOLD, 36);

    public GamePanel() {
        timer = new Timer(1000 / 60, this);
        menu = new Menu();
        shop = new Shop();
        end = new EndScene();
        restartGame();

        objectManager.audioManager.playSong();
    }

    private void restartGame() {
        Atbs.restart();
        map = new Map();
        p = new Player(416, 288, 32, 32, 10, 3);    // initialize a new player
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
        objectManager.hud.drawScore(g);
//
//        g.setColor(Color.white);
//        g.drawString("relative to window: " + point.x + "," + point.y, 10, 100);
//
//        // draw the collision rectangles
//        map.collisionRects.forEach(r -> g.fillRect(r.x, r.y, r.width, r.height));
    }

    public void drawShopState(Graphics g) {
        shop.drawShops(g);
        g.setFont(titleFont);
        g.drawString("Money: $" + p.money, 50, 50);
    }

    public void drawEndState(Graphics g) {
        menu1.paintIcon(this, g, 0, 0);
        end.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(titleFont);
        g.drawString("Your score: " + p.score, 140, 125);
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

        if (System.currentTimeMillis() - objectManager.audioManager.songLength >= objectManager.audioManager.songStartTime) {
            objectManager.audioManager.playSong();
            objectManager.audioManager.songStartTime = System.currentTimeMillis();
        }
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
                JOptionPane.showMessageDialog(null, "WASD to move\nLeft Mouse to Fire\nP to open the shop\nEarn money and regain health by saving minion babies\nSave the minion babies and kill the Ghosts");
            }
            if (menu.checkClicked(point) == menu.CREDITS_BUTTON) {
                JOptionPane.showMessageDialog(null, "By Bryan, Ray, and Ethan");
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

        if (currentState == SHOP_STATE) {   // fire rate
            if (shop.checkClicked(point) == 0) {
                if (Atbs.bulletFireRate > 30 && (p.money - Atbs.fireRateCost) >= 0) {
                    System.out.println("Rate Up");
                    Atbs.bulletFireRate -= 10;
                    p.money -= Atbs.fireRateCost;
                    Atbs.fireRateCost += 2;

                    System.out.println(Atbs.bulletFireRate);
                } else if (Atbs.bulletFireRate <= 30) {
                    shop.shopButtons.get(0).soldOut = true;
                }
            }

            if (shop.checkClicked(point) == 1) {    // bullet damage
                if (Atbs.bulletDamage <= 4 && (p.money - Atbs.damageCost) >= 0) {
                    System.out.println("Dmg Up");
                    Atbs.bulletDamage += 1;
                    Atbs.bulletWidth += 1;
                    Atbs.bulletHeight += 1;
                    p.money -= Atbs.damageCost;
                    Atbs.damageCost += 5;

                    System.out.println(Atbs.bulletDamage);
                } else if (Atbs.bulletDamage > 4) {
                    shop.shopButtons.get(1).soldOut = true;
                }
            }

            if (shop.checkClicked(point) == 2) {    // bullet accuracy
                if (Atbs.bulletInaccuracy > 1 && (p.money - Atbs.accuracyCost) >= 0) {
                    System.out.println("Accuracy Up");
                    Atbs.bulletInaccuracy -= 1;
                    p.money -= Atbs.accuracyCost;

                    System.out.println(Atbs.bulletInaccuracy);
                } else if (Atbs.bulletInaccuracy <= 1) {
                    shop.shopButtons.get(2).soldOut = true;
                }
            }

            if (shop.checkClicked(point) == 3) {    // bullet range
                if (Atbs.bulletXVMax < 25 && (p.money - Atbs.speedCost) >= 0) {
                    System.out.println("Range Up");
                    Atbs.bulletXVMax++;
                    p.money -= Atbs.speedCost;
                    Atbs.speedCost += 2;

                    System.out.println(Atbs.bulletXVMax);
                } else if (Atbs.bulletXVMax >= 25) {
                    shop.shopButtons.get(3).soldOut = true;
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
