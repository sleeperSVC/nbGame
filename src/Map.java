import java.awt.*;
import java.util.*;
import java.util.List;

public class Map {

    ArrayList<Point> babySpawnPoints;
    ArrayList<Point> enemySpawnPoints;

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
        //enemySpawnPoints.add(new Point());
        //babySpawnPoints.add(new Point());
    }

}
