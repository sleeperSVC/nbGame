import java.awt.*;

public class HUD extends GameObject {
    Player p;
    Rectangle hudRect;
    int y1;
    String str;
    Font hudFont = new Font("Courier", Font.BOLD, 14);

    public HUD(int x, int y, int width, int height, Player p) {
        super(x, y, width, height);
        this.p = p;
        y1 = 359;
    }

    @Override
    public void update() {

        //TODO need to add waves counter later on, also need to add money display
    }

    @Override
    public void draw(Graphics g) {

        for (int i = 1; i <= p.health; i++) {
            hudRect = new Rectangle(y1, 4, 9, 23);
            g.setColor(Color.RED);
            g.fillRect(y1, 4, 9, 23);
            y1 += 13;
        }
        y1 = 359;

        drawMoney(g);
    }

    public void drawMoney(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(hudFont);
        str = "MONEY: $" + p.money;
        g.drawString(str, 500, 20);
    }

}



