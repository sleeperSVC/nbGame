import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ShopButton {

    int upgradeX, upgradeY;
    Rectangle shopBounds;
    String upgradeText;
    int upgradeCost;
    Font shopButtonFont = new Font("Courier", Font.BOLD, 14);
    boolean mouseHovering = false;
    boolean soldOut = false;
    double upgradeValue;

    NumberFormat formatter;

    public ShopButton(Rectangle shopBounds, String upgradeText) {
        this.shopBounds = shopBounds;
        this.upgradeX = (int) (shopBounds.getX() + shopBounds.getWidth() / 20);
        this.upgradeY = (int) (shopBounds.getY() + shopBounds.getHeight() / 2);
        this.upgradeText = upgradeText;
        upgradeCost = 0;

        formatter = new DecimalFormat("#.##");
    }

    public void update() {
        switch (upgradeText) {
            case "Fire Rate":
                upgradeValue = Atbs.bulletFireRate;
                upgradeCost = Atbs.fireRateCost;
                break;
            case "Dmg":
                upgradeValue = Atbs.bulletDamage;
                upgradeCost = Atbs.damageCost;
                break;
            case "Accuracy":
                upgradeValue = Atbs.bulletInaccuracy;
                upgradeCost = Atbs.accuracyCost;
                break;
            case "Bullet Range":
                upgradeValue = Atbs.bulletSpeedFactor;
                upgradeCost = Atbs.speedCost;
                break;
        }
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
        if (!soldOut) {
            g.drawString("COST: $" + upgradeCost + "          " + upgradeText + " = " + upgradeValue, upgradeX + 100, upgradeY + shopButtonFont.getSize());
        } else {
            g.drawString("SOLD OUT!", upgradeX + 100, upgradeY + shopButtonFont.getSize());
        }
    }

    public Rectangle getRect() {
        return shopBounds;
    }
}
