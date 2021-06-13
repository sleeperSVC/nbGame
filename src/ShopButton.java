import java.awt.*;

public class ShopButton {

    int upgradeX, upgradeY;
    Rectangle shopBounds;
    String upgradeText;

    Font shopButtonFont = new Font("Courier", Font.BOLD, 14);

    boolean mouseHovering = false;

    public ShopButton(Rectangle shopBounds, String upgradeText) {
        this.shopBounds = shopBounds;
        this.upgradeX = (int) (shopBounds.getX() + shopBounds.getWidth() / 20);
        this.upgradeY = (int) (shopBounds.getY() + shopBounds.getHeight() / 2);
        this.upgradeText = upgradeText;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        if (mouseHovering) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }
        g.setFont(shopButtonFont);
        g.drawRect(shopBounds.x, shopBounds.y, shopBounds.width, shopBounds.height);
        g.drawString(upgradeText, upgradeX, upgradeY + shopButtonFont.getSize());
    }

    public Rectangle getRect() {
        return shopBounds;
    }
}
