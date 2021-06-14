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

    }

    public void drawMoney(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(hudFont);
        String str = "MONEY: $" + p.money;
        if (str.length() >= 12) {
            str = str.substring(0, 12);
        }
        g.drawString(str, 490, 14);
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(hudFont);
        String str = "SCORE: " + p.score;
        if (str.length() >= 12) {
            str = str.substring(0, 12);
        }
        g.drawString(str, 490, 30);
    }
}



