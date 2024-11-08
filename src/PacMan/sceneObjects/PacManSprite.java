package PacMan.sceneObjects;

public class PacManSprite extends MovingSprite {
    private boolean boosted;

    public PacManSprite (int x, int y) {
        super(x, y);
    }

    public boolean isBoosted() {
        return boosted;
    }

    public void setBoosted(boolean boosted) {
        this.boosted = boosted;
    }
}
