public class Atbs {

    // This class holds data for other classes to use. The restart method "resets" the data back to original values.

    public static int enemy1Health;
    public static int enemy1Damage;
    public static int enemy2Health;
    public static int enemy2Damage;
    public static int bulletFireRate;
    public static double bulletDamage;
    public static int bulletWidth;
    public static int bulletHeight;
    public static double bulletSpeedFactor;
    public static double bulletXVMax;
    public static double bulletInaccuracy;
    public static int spawnInterval;
    public static int babyInterval;

    public static int fireRateCost;
    public static int damageCost;
    public static int accuracyCost;
    public static int speedCost;

    public static void restart() {
        enemy1Health = 30;
        enemy1Damage = 2;
        enemy2Health = 10;
        enemy2Damage = 2;
        bulletFireRate = 100;
        bulletDamage = 1;
        bulletWidth = 5;
        bulletHeight = 5;
        bulletSpeedFactor = 100;
        bulletXVMax = 20;
        bulletInaccuracy = 5;
        spawnInterval = 4000;
        babyInterval = 10000;

        fireRateCost = 5;
        damageCost = 10;
        accuracyCost = 2;
        speedCost = 3;
    }
}