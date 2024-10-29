package PacMan.sceneObjects;

public class FoodSprite extends Component{
    private boolean isBooster;
    private boolean isEaten;

    public FoodSprite (int x, int y) {
        super(x, y);
    }

    public boolean isBooster() {
        return isBooster;
    }

    public void setBooster(boolean booster) {
        isBooster = booster;
    }

    public boolean isEaten () {
        return isEaten;
    }

    public void setEaten(boolean isEaten) {
        this.isEaten = isEaten;
    }
}
