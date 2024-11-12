package PacMan.sceneObjects;

import PacMan.TravelDirection;

public class MovingSprite extends Component{
    private TravelDirection travelDirection;

    public MovingSprite(int x, int y) {
        super(x, y);
    }

    public TravelDirection getTravelDirection() {
        return travelDirection;
    }

    public void setTravelDirection(TravelDirection travelDirection) {
        this.travelDirection = travelDirection;
    }
}
