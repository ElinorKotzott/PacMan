package PacMan.sceneObjects;

import PacMan.TravelDirection;

public class MovingSprite extends Component{
    private boolean dead;
    private TravelDirection travelDirection;

    public MovingSprite(int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public TravelDirection getTravelDirection() {
        return travelDirection;
    }

    public void setTravelDirection(TravelDirection travelDirection) {
        this.travelDirection = travelDirection;
    }
}
