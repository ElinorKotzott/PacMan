package PacMan;

import PacMan.sceneObjects.Component;
import java.awt.event.KeyEvent;

public class Game {
    private Runnable r;
    private int height;
    private int width;
    private int elementSize = 50;

    public Game(Runnable r, int height, int width) {
        this.height = height;
        this.width = width;
        this.r = r;
    }

    public void movePacMan(int moveDirection, boolean[][] booleanArray, Component pacMan, TravelDirection d) {

        switch(moveDirection){
            case KeyEvent.VK_LEFT -> {
                if (canMoveLeft(booleanArray, pacMan)) {
                    moveLeft(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (canMoveRight(booleanArray, pacMan)) {
                    moveRight(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_UP -> {
                if (canMoveUp(booleanArray, pacMan)) {
                    moveUp(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (canMoveDown(booleanArray, pacMan)) {
                    moveDown(pacMan, d);
                    return;
                }
            }
        }

        if (d.getTravelDir() != null && d.getTravelDir().equals(Direction.LEFT) && canMoveLeft( booleanArray, pacMan)) {
            moveLeft(pacMan, d);
            return;
        }

        if (d.getTravelDir() != null && d.getTravelDir().equals(Direction.RIGHT) && canMoveRight( booleanArray, pacMan)) {
            moveRight(pacMan, d);
            return;
        }

        if (d.getTravelDir() != null && d.getTravelDir().equals(Direction.UP) &&canMoveUp( booleanArray, pacMan)) {
            moveUp(pacMan, d);
            return;
        }

        if (d.getTravelDir() != null && d.getTravelDir().equals(Direction.DOWN) &&canMoveDown(booleanArray, pacMan)) {
            moveDown(pacMan, d);
        }


    }

    private void moveDown(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() + 1);
        d.setTravelDir(Direction.DOWN);
        r.run();
    }

    private void moveUp(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() - 1);
        d.setTravelDir(Direction.UP);
        r.run();
    }

    private void moveRight(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() + 1);
        d.setTravelDir(Direction.RIGHT);
        r.run();
    }

    private void moveLeft(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() - 1);
        d.setTravelDir(Direction.LEFT);
        r.run();
    }

    private boolean canMoveDown(boolean[][] booleanArray, Component pacMan) {
        return !booleanArray[(pacMan.getCoordinate().getX() + 45) / elementSize][(pacMan.getCoordinate().getY() - 5) / elementSize]
                && !booleanArray[(pacMan.getCoordinate().getX() + 45) / elementSize][(pacMan.getCoordinate().getY() + 44) / elementSize];
    }

    private boolean canMoveUp(boolean[][] booleanArray, Component pacMan) {
        return !booleanArray[(pacMan.getCoordinate().getX() - 6) / elementSize][(pacMan.getCoordinate().getY() - 5) / elementSize]
                && !booleanArray[(pacMan.getCoordinate().getX() - 6) / elementSize][((pacMan.getCoordinate().getY()) + 44) / elementSize];
    }

    private boolean canMoveRight(boolean[][] booleanArray, Component pacMan) {
        return !booleanArray[(pacMan.getCoordinate().getX() - 5) / elementSize][(pacMan.getCoordinate().getY() + 45) / elementSize]
                && !booleanArray[(pacMan.getCoordinate().getX() + 44) / elementSize][(pacMan.getCoordinate().getY() + 45) / elementSize];
    }

    private boolean canMoveLeft(boolean[][] booleanArray, Component pacMan) {
        return !booleanArray[(pacMan.getCoordinate().getX() - 5) / elementSize][(pacMan.getCoordinate().getY() - 6) / elementSize]
                && !booleanArray[(pacMan.getCoordinate().getX() + 44) / elementSize][(pacMan.getCoordinate().getY() - 6) / elementSize];
    }
}
