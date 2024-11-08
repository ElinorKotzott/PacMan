package PacMan;

import PacMan.sceneObjects.Component;
import PacMan.sceneObjects.MovingSprite;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static PacMan.Board.elementSize;

public class Game {
    private final Random r;
    public Game(Random r) {
        this.r = r;
    }

    public void movePacMan(int moveDirection, boolean[][] booleanArray, MovingSprite pacMan, TravelDirection d) {

        switch (moveDirection) {
            case KeyEvent.VK_LEFT -> {
                if (canSpriteMoveLeft(booleanArray, pacMan)) {
                    moveLeft(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (canSpriteMoveRight(booleanArray, pacMan)) {
                    moveRight(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_UP -> {
                if (canSpriteMoveUp(booleanArray, pacMan)) {
                    moveUp(pacMan, d);
                    return;
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (canSpriteMoveDown(booleanArray, pacMan)) {
                    moveDown(pacMan, d);
                    return;
                }
            }
        }

        if (d.getDirection() != null && d.getDirection().equals(Direction.LEFT) && canSpriteMoveLeft(booleanArray, pacMan)) {
            moveLeft(pacMan, d);
            return;
        }

        if (d.getDirection() != null && d.getDirection().equals(Direction.RIGHT) && canSpriteMoveRight(booleanArray, pacMan)) {
            moveRight(pacMan, d);
            return;
        }

        if (d.getDirection() != null && d.getDirection().equals(Direction.UP) && canSpriteMoveUp(booleanArray, pacMan)) {
            moveUp(pacMan, d);
            return;
        }

        if (d.getDirection() != null && d.getDirection().equals(Direction.DOWN) && canSpriteMoveDown(booleanArray, pacMan)) {
            moveDown(pacMan, d);
        }


    }

    private void moveDown(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() + 1);
        d.setDirection(Direction.DOWN);
    }

    private void moveUp(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setX(pacMan.getCoordinate().getX() - 1);
        d.setDirection(Direction.UP);
    }

    private void moveRight(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() + 1);
        d.setDirection(Direction.RIGHT);
    }

    private void moveLeft(Component pacMan, TravelDirection d) {
        pacMan.getCoordinate().setY(pacMan.getCoordinate().getY() - 1);
        d.setDirection(Direction.LEFT);
    }

    private boolean canSpriteMoveDown(boolean[][] booleanArray, MovingSprite sprite) {
        return !booleanArray[(sprite.getCoordinate().getX() + 45) / elementSize][(sprite.getCoordinate().getY() - 5) / elementSize]
                && !booleanArray[(sprite.getCoordinate().getX() + 45) / elementSize][(sprite.getCoordinate().getY() + 44) / elementSize];
    }

    private boolean canSpriteMoveUp(boolean[][] booleanArray, MovingSprite sprite) {
        return !booleanArray[(sprite.getCoordinate().getX() - 6) / elementSize][(sprite.getCoordinate().getY() - 5) / elementSize]
                && !booleanArray[(sprite.getCoordinate().getX() - 6) / elementSize][((sprite.getCoordinate().getY()) + 44) / elementSize];
    }

    private boolean canSpriteMoveRight(boolean[][] booleanArray, MovingSprite sprite) {
        return !booleanArray[(sprite.getCoordinate().getX() - 5) / elementSize][(sprite.getCoordinate().getY() + 45) / elementSize]
                && !booleanArray[(sprite.getCoordinate().getX() + 44) / elementSize][(sprite.getCoordinate().getY() + 45) / elementSize];
    }

    private boolean canSpriteMoveLeft(boolean[][] booleanArray, MovingSprite sprite) {
        return !booleanArray[(sprite.getCoordinate().getX() - 5) / elementSize][(sprite.getCoordinate().getY() - 6) / elementSize]
                && !booleanArray[(sprite.getCoordinate().getX() + 44) / elementSize][(sprite.getCoordinate().getY() - 6) / elementSize];
    }


    private void determineGhostSpritesInitialTravelDirection(boolean[][] booleanArray, MovingSprite ghostSprite) {
        List<Direction> possibleDirectionsList = new ArrayList<>();

            if (canSpriteMoveDown(booleanArray, ghostSprite)) {
                possibleDirectionsList.add(Direction.DOWN);
            }
            if (canSpriteMoveUp(booleanArray, ghostSprite)) {
                possibleDirectionsList.add(Direction.UP);
            }
            if (canSpriteMoveRight(booleanArray, ghostSprite)) {
                possibleDirectionsList.add(Direction.RIGHT);
            }
            if (canSpriteMoveLeft(booleanArray, ghostSprite)) {
                possibleDirectionsList.add(Direction.LEFT);
            }

            ghostSprite.getTravelDirection().setDirection(possibleDirectionsList.get(r.nextInt(possibleDirectionsList.size())));
        }

}


