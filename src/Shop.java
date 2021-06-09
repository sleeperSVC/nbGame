import java.awt.*;
import java.util.ArrayList;

public class Shop {

    ArrayList<ShopButton> shopButtons = new ArrayList<ShopButton>();
    int currentHovered = -1;
//firerate, dmg, accuracy, bullet speed,
    public Shop() {
        shopButtons.add(new ShopButton(new Rectangle(50,100,500,50), "Fire Rate", 60, 125));
        shopButtons.add(new ShopButton(new Rectangle(50,200,500,50), "Dmg", 60, 225));
        shopButtons.add(new ShopButton(new Rectangle(50,300,500,50), "Accuracy", 60, 325));
        shopButtons.add(new ShopButton(new Rectangle(50,400,500,50), "Bullet Speed", 60, 425));
    }

    public void updateShops() {
        shopButtons.forEach(s -> s.update());
    }

    public void drawShops(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, GamePanel.FRAME_WIDTH, GamePanel.FRAME_HEIGHT);
        shopButtons.forEach(s -> s.draw(g));
    }
/*
    public int mouseClicked(MouseEvent e) {
        for(int index = 0; index < shopButtons.size(); index++){
            if (shopButtons.get(index).Rectangle.intersects(e.getX(), e.getY(), 1, 1)){
                return index;
            }
        }
        return -1;
    }

    public void checkHover(Point e) {
        for(int index=0;index<shopButtons.size();index++){
            if(shopButtons.get(index).Rectangle.intersects(e.getX(), e.getY(), 1, 1){
                currentHovered = index;
            }
        }
    }
*/
}