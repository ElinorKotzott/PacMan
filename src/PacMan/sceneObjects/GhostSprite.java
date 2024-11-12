package PacMan.sceneObjects;

public class GhostSprite extends MovingSprite {
    private boolean dead;

    public GhostSprite(int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
