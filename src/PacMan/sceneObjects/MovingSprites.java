package PacMan.sceneObjects;

import PacMan.TravelDirection;

public class MovingSprites extends Component{
    private boolean isDead;
    private TravelDirection travelDirection;

    public MovingSprites(int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public TravelDirection getTravelDirection() {
        return travelDirection;
    }

    public void setTravelDirection(TravelDirection travelDirection) {
        this.travelDirection = travelDirection;
    }
}
