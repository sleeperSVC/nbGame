import java.awt.*;
import java.util.ArrayList;

public class Map {

    ArrayList<Rectangle> collisionRects = new ArrayList<>();
    ArrayList<Point> babySpawnPoints = new ArrayList<>();
    ArrayList<Point> enemySpawnPoints = new ArrayList<>();

    // rescale constant
    final double RC = 1.5;
    final static int LADDER_WIDTH = 44;

    final Rectangle GROUND_LEFT_RECT = new Rectangle(0, 320, 442, 48);
    final Rectangle GROUND_RIGHT_RECT = new Rectangle(442 + LADDER_WIDTH, 320, GamePanel.FRAME_WIDTH - (442 + LADDER_WIDTH), 48);
    final Rectangle ROOF = new Rectangle(220, 256, 486, 16);
    final Rectangle TOP_SEWER_LEFT_RECT = new Rectangle(0, 416, 442, 48);
    final Rectangle TOP_SEWER_RIGHT_RECT = new Rectangle(444 + LADDER_WIDTH, 416, GamePanel.FRAME_WIDTH - (442 + LADDER_WIDTH), 48);
    final Rectangle BOTTOM_SEWER_RECT = new Rectangle(0, 512, GamePanel.FRAME_WIDTH, 100);
    final Rectangle LEFT_BUILDING_RECT = new Rectangle(0, 96, 160, 180);
    final Rectangle RIGHT_BUILDING_RECT = new Rectangle(828, 192, GamePanel.FRAME_WIDTH - 828, 90);

    final Rectangle RIGHT_BOUND = new Rectangle(GamePanel.FRAME_WIDTH, 0, 100, GamePanel.FRAME_HEIGHT);
    final Rectangle LEFT_BOUND = new Rectangle(-100, 0, 100, GamePanel.FRAME_HEIGHT);
    final Rectangle TOP_BOUND = new Rectangle(0, -100, GamePanel.FRAME_WIDTH, 100);
    final Rectangle BOTTOM_BOUND = new Rectangle(0, GamePanel.FRAME_HEIGHT, GamePanel.FRAME_WIDTH, 100);

    final static Rectangle LADDER = new Rectangle(442, 302, LADDER_WIDTH, 300);

    public Map() {
        collisionRects.add(GROUND_LEFT_RECT);
        collisionRects.add(GROUND_RIGHT_RECT);
        collisionRects.add(ROOF);
        collisionRects.add(TOP_SEWER_LEFT_RECT);
        collisionRects.add(TOP_SEWER_RIGHT_RECT);
        collisionRects.add(BOTTOM_SEWER_RECT);
        collisionRects.add(LEFT_BUILDING_RECT);
        collisionRects.add(RIGHT_BUILDING_RECT);

        collisionRects.add(RIGHT_BOUND);
        collisionRects.add(LEFT_BOUND);
        collisionRects.add(TOP_BOUND);
        collisionRects.add(BOTTOM_BOUND);

        //Spawnpoints take in account sprite height, dont worry about compensating in the draw method
        enemySpawnPoints.add(new Point(75, 288));
        enemySpawnPoints.add(new Point(883, 288));
        enemySpawnPoints.add(new Point(854, 160));
        enemySpawnPoints.add(new Point(127, 198));
        enemySpawnPoints.add(new Point(706, 384));
        enemySpawnPoints.add(new Point(196, 384));
        enemySpawnPoints.add(new Point(270, 480));
        enemySpawnPoints.add(new Point(624, 480));

        babySpawnPoints.add(new Point(500, 297));
        babySpawnPoints.add(new Point(676, 304));
        babySpawnPoints.add(new Point(284, 304));
        babySpawnPoints.add(new Point(375, 400));
        babySpawnPoints.add(new Point(589, 400));
        babySpawnPoints.add(new Point(497, 496));
    }

    public Point getEnemySpawn() {
        return enemySpawnPoints.get((int) (Math.random() * 8));
    }

    public Point getBabySpawn() {
        return babySpawnPoints.get((int) (Math.random() * 6));
    }


}
