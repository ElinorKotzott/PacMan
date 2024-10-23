package PacMan;

public class Component {

    private Coordinate coordinate;


    public Component(int x, int y) {
        coordinate = new Coordinate(x, y);
    }


   /* public boolean checkIfAppleIsEaten() {

    }

    public boolean checkIfBoostIsEaten() {

    }

    public boolean checkIfGhostHasBeenTouched() {

    }
*/


    public Coordinate getCoordinate() {
        return coordinate;
    }
}

