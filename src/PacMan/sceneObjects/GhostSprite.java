package PacMan.sceneObjects;

public class GhostSprite extends MovingSprite {
    private boolean respawning;
    private int respawningCounter = 0;
    private static final int MAX_RESPAWNING_COUNTER = 200;
    private boolean flashingCounter;

    public GhostSprite(int x, int y) {
        super(x, y);
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

    public static int getMaxRespawningCounter () {
        return MAX_RESPAWNING_COUNTER;
    }

    public boolean isFlashingCounter() {
        return flashingCounter;
    }

    public void setFlashingCounter(boolean flashingCounter) {
        this.flashingCounter = flashingCounter;
    }
}
