package PacMan;

public class Game {
    private Runnable r;
    private int height;
    private int width;
    private static final int LEFT_ARROW = 37;
    private static final int RIGHT_ARROW = 39;
    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;
    private int componentSize = 50;

    public Game (Runnable r, int height, int width) {
        this.height = height;
        this.width = width;
        this.r = r;
    }

    public void movePacMan(int moveDirection, boolean[][] pacManArray, Component pacMan) {

        if (moveDirection == LEFT_ARROW && !pacManArray[(int)pacMan.getCoordinate().getX()/componentSize][(int)pacMan.getCoordinate().getX()/componentSize]) {

        }




    }

}
