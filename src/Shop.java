import java.awt.*;
import java.util.ArrayList;

public class Shop {

    ArrayList<ShopButton> shopButtons = new ArrayList<>();

    // firerate, dmg, accuracy, bullet speed,
    public Shop() {
        shopButtons.add(new ShopButton(new Rectangle(50, 100, 500, 50), "Fire Rate"));
        shopButtons.add(new ShopButton(new Rectangle(50, 200, 500, 50), "Dmg"));
        shopButtons.add(new ShopButton(new Rectangle(50, 300, 500, 50), "Accuracy"));
        shopButtons.add(new ShopButton(new Rectangle(50, 400, 500, 50), "Bullet Range"));
    }

    public void updateShops() {
        shopButtons.forEach(ShopButton::update);

    }

    public void drawShops(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);

        shopButtons.forEach(s -> s.draw(g));
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