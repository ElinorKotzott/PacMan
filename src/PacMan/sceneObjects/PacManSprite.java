package PacMan.sceneObjects;

public class PacManSprite extends MovingSprites {
    private boolean isBoosted;

    public PacManSprite (int x, int y) {
        super(x, y);
    }

    public boolean isBoosted() {
        return isBoosted;
    }

    public void setBoosted(boolean boosted) {
        isBoosted = boosted;
    }
}
