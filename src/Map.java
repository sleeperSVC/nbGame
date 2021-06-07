import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {

    ArrayList<Point> babySpawnPoints = new ArrayList<>();
    ArrayList<Point> enemySpawnPoints = new ArrayList<>();

    // platforms for checking collission
    final int LADDER_WIDTH = 34;

    final Rectangle GROUND_LEFT_PLATFORM = new Rectangle(0, 320, 446, 48);
    final Rectangle GROUND_RIGHT_PLATFORM = new Rectangle(446 + LADDER_WIDTH, 320, GamePanel.FRAME_WIDTH-446+LADDER_WIDTH, 368);
    final Rectangle TOP_SEWER_LEFT_PLATFORM = new Rectangle(0, 368,0,0);
    final Rectangle BOTTOM_SEWER_RIGHT_PLATFORM = new Rectangle();
    final Rectangle LEFT_BUILDING_PLATFORM = new Rectangle();
    final Rectangle RIGHT_BUILDING_PLATFORM = new Rectangle();
    final Rectangle LEFT_BUILDING_BALCONY_PLATFORM = new Rectangle();


    public Map() {

        //Spawnpoints take in account sprite height, dont worry about compensating in the draw method
        enemySpawnPoints.add(new Point(75,288));
        enemySpawnPoints.add(new Point(883,288));
        enemySpawnPoints.add(new Point(854,160));
        enemySpawnPoints.add(new Point(127,198));
        enemySpawnPoints.add(new Point(706,384));
        enemySpawnPoints.add(new Point(196,384));
        enemySpawnPoints.add(new Point(270,480));
        enemySpawnPoints.add(new Point(624,480));


        babySpawnPoints.add(new Point(500, 297));
        babySpawnPoints.add(new Point(676, 304));
        babySpawnPoints.add(new Point(284, 304));
        babySpawnPoints.add(new Point(375, 400));
        babySpawnPoints.add(new Point(589, 400));
        babySpawnPoints.add(new Point(497, 496));
    }

}
