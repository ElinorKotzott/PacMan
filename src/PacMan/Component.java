package PacMan;

public class Component {
    private int x;
    private int y;
    private boolean isBooster;
    private Coordinate coordinate;

    public Component(int x, int y) {
        this.coordinate = new Coordinate (x, y);
    }


    public boolean isBooster() {
        return isBooster;
    }

    public void setBooster(boolean booster) {
        isBooster = booster;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
