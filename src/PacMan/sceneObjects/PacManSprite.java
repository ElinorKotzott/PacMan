package PacMan.sceneObjects;

public class PacManSprite extends Component {
    private boolean isDead;
    private boolean isBoosted;

    public PacManSprite (int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isBoosted() {
        return isBoosted;
    }

    public void setBoosted(boolean boosted) {
        isBoosted = boosted;
    }
}
