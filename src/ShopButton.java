import java.awt.*;

public class ShopButton {

    int upgradeX, upgradeY;
    Rectangle shopBounds;
    String upgrade;

    public ShopButton(int x, int y, int width, int height, String text, int textX, int textY) {
        shopBounds = new Rectangle(x, y, width, height);
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
