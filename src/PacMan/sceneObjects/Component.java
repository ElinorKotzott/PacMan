package PacMan.sceneObjects;

import PacMan.Coordinate;

public class Component {

    protected Coordinate coordinate;

    public Component(int x, int y) {
        this.coordinate = new Coordinate (x, y);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
