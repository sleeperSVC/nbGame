import java.awt.*;
import java.awt.event.MouseEvent;

public class ShopButton {

    int upgradeX, upgradeY;
    Rectangle shopBounds;
    String upgrade;

    public ShopButton(Rectangle upgradeRect, String text, int textX, int textY) {
        shopBounds = upgradeRect;
        upgradeX = textX;
        upgradeY = textY;
        upgrade = text;
    }

    public void update() {
        shopBounds.setBounds(shopBounds.x, shopBounds.y, shopBounds.width, shopBounds.height);
    }


    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(shopBounds.x, shopBounds.y, shopBounds.width, shopBounds.height);
        g.drawString(upgrade, upgradeX, upgradeY);

    }
}
