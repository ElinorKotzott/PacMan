package PacMan.sceneObjects;

public class GhostSprite extends Component {
    private boolean isDead;
    public GhostSprite(int x, int y) {
        super(x, y);
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
