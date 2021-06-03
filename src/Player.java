public class Player extends GameObject {

    int fireRate;
    public Player(int x, int y, int width, int height, int healthCounter) {
        super(x, y, width, height);
    }

    @Override
    public void update() {
        super.update();
        //enemy collision thing or whatnot dmg yay die
    }

    public int getFireRate() {
        return fireRate;
    }
}
