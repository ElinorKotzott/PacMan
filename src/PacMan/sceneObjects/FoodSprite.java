package PacMan.sceneObjects;

public class FoodSprite extends Component{
    private boolean booster;
    private boolean eaten;

    public FoodSprite (int x, int y) {
        super(x, y);
    }

    public boolean isBooster() {
        return booster;
    }

    public void setBooster(boolean booster) {
        this.booster = booster;
    }

    public boolean isEaten () {
        return eaten;
    }

    public void setEaten(boolean isEaten) {
        this.eaten = isEaten;
    }
}
