import java.awt.*;
import java.util.ArrayList;

public class Shop {

    ArrayList<ShopButton> shopButtons = new ArrayList<ShopButton>();

    public Shop() {
        shopButtons.add(new ShopButton(50, 150, 500, 50, "", 60, 180));
        shopButtons.add(new ShopButton(50, 250, 500, 50, "", 60, 280));
        shopButtons.add(new ShopButton(50, 350, 500, 50, "", 60, 380));
        shopButtons.add(new ShopButton(50, 450, 500, 50, "", 60, 480));
    }

    public void updateShops() {
        shopButtons.forEach(s -> s.update());
    }

    public void drawShops(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
        shopButtons.forEach(s -> s.draw(g));
    }
}