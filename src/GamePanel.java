import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    Timer timer;
    GameObjectManager objectManager = new GameObjectManager();

    public GamePanel() {
        timer = new Timer(1000/60, this);   // the timer will call actionPerformed() 60 times a second
    }

    public void startTimer() {
        timer.start();
    }

    public void paintComponent(Graphics g) {

        objectManager.drawObjects(g);

        g.setColor(Color.BLACK);
        g.fillRect(100,100,100,100);
        g.setColor(Color.RED);
        g.drawString("Test String asdfadsfasdf", 300,500);

        System.out.println("I was called by the repaint() method in actionPerformed()!");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        System.out.println("The timer is calling me!");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("\tThe _" + e.getKeyChar() + "_ key was pressed!");
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
