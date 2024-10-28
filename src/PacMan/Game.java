package PacMan;

import PacMan.sceneObjects.Component;

public class Game {
    private Runnable r;
    private int height;
    private int width;
    private static final int LEFT_ARROW = 37;
    private static final int RIGHT_ARROW = 39;
    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;
    private int elementSize = 50;

    public Game(Runnable r, int height, int width) {
        this.height = height;
        this.width = width;
        this.r = r;
    }

    public void movePacMan(int moveDirection, boolean[][] booleanArray, Component pacMan, Direction d) {

        if (moveDirection == LEFT_ARROW && !booleanArray[pacMan.getCoordinate().getX() / elementSize][(pacMan.getCoordinate().getY() - 6) / elementSize]  ) {
            pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() - 1);
            d = Direction.LEFT;
            r.run();
        }

        if (moveDirection == RIGHT_ARROW && !booleanArray[pacMan.getCoordinate().getX() / elementSize][(pacMan.getCoordinate().getY() + 46) / elementSize]) {
            pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() + 1);
            d = Direction.RIGHT;
            r.run();
        }

        if (moveDirection == UP_ARROW && !booleanArray[(pacMan.getCoordinate().getX() - 6) / elementSize][(pacMan.getCoordinate().getY()) / elementSize]) {
            pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() - 1);
            d = Direction.UP;
            r.run();
        }

        if (moveDirection == DOWN_ARROW && !booleanArray[(pacMan.getCoordinate().getX() + 46) / elementSize][(pacMan.getCoordinate().getY()) / elementSize]) {
            pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() + 1);
            d = Direction.DOWN;
            r.run();
        }


    }

}
