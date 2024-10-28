package PacMan.sceneObjects;

public class FoodSprite extends Component{
    private boolean isBooster;

    public FoodSprite (int x, int y) {
        super(x, y);
    }

    public boolean isBooster() {
        return isBooster;
    }

    public void setBooster(boolean booster) {
        isBooster = booster;
    }
}
