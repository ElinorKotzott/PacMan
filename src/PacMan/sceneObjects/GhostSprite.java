package PacMan.sceneObjects;

public class GhostSprite extends MovingSprite {
    private boolean dead;
    private boolean respawning;
    private int respawningCounter = 0;

    public GhostSprite(int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isRespawning() {
        return respawning;
    }

    public void setRespawning(boolean respawning) {
        this.respawning = respawning;
    }

    public int getRespawningCounter() {
        return respawningCounter;
    }

    public void setRespawningCounter(int respawningCounter) {
        this.respawningCounter = respawningCounter;
    }
}
