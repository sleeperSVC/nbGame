import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Shop {

    ArrayList<ShopButton> shopButtons = new ArrayList<>();

    // firerate, dmg, accuracy, bullet speed,
    public Shop() {
        shopButtons.add(new ShopButton(new Rectangle(50, 100, 500, 50), "Fire Rate"));
        shopButtons.add(new ShopButton(new Rectangle(50, 200, 500, 50), "Dmg"));
        shopButtons.add(new ShopButton(new Rectangle(50, 300, 500, 50), "Accuracy"));
        shopButtons.add(new ShopButton(new Rectangle(50, 400, 500, 50), "Bullet Speed"));
    }

    public void updateShops() {
        shopButtons.forEach(ShopButton::update);
    }

    public void drawShops(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);

        shopButtons.forEach(s -> s.draw(g));

        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", Font.BOLD, 14));
        g.drawString("COST: $5", 300, 112);
        g.drawString("COST: $8", 300, 212);
        g.drawString("COST: $1", 300, 312);
        g.drawString("COST: $2", 300, 412);

//        if (Atbs.fireRate >= .5) {
//            g.drawString("Fire Rate = " + Atbs.fireRate, 400, 85);
//        } else {
//            g.drawString("Fire Rate Maxed Out", 400, 85);
//        }
//
//        if (p.bulletDamage <= 110) {
//            g.drawString("Damage = " + p.bulletDamage, 400, 185);
//        } else {
//            g.drawString("Bullet Damage Maxed Out", 400, 185);
//        }
//
//        if (Atbs.bulletInaccuracy >= .5) {
//            g.drawString("Inaccuracy = " + (float) Atbs.bulletInaccuracy, 400, 284);
//        } else {
//            g.drawString("Bullet Accuracy Maxed Out", 400, 284);
//        }
//
//        if (Atbs.bulletSpeedFactor <= 110) {
//            g.drawString("Bullet Speed = " + Atbs.bulletSpeedFactor, 400, 384);
//        } else {
//            g.drawString("Bullet Speed Maxed Out", 400, 384);
//        }
    }

    public int checkClicked(Point point) {
        for (int index = 0; index < shopButtons.size(); index++) {
            if (shopButtons.get(index).getRect().contains(point.getX(), point.getY())) {
                return index;
            }
        }
        return -1;
    }

    public void checkHovering(Point point) {
        for (ShopButton sb : shopButtons) {
            sb.mouseHovering = sb.shopBounds.contains(point);
        }
    }

}