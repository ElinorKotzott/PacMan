package PacMan;

public class TravelDirection {
    private Direction travelDir;

    public TravelDirection (Direction travelDirection) {
        this.travelDir = travelDirection;
    }

    public Direction getTravelDir() {
        return travelDir;
    }

    public void setTravelDir(Direction travelDir) {
        this.travelDir = travelDir;
    }

    public Direction getTravelDirection() {
        return travelDir;
    }
}
