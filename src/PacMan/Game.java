package PacMan;

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

    public void movePacMan(Integer moveDirection, boolean[][] booleanArray, MovingSprite pacMan, TravelDirection d) {

        if (moveDirection == null) {
            return;
        }

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

    private void moveDown(MovingSprite sprite, TravelDirection d) {
        sprite.getCoordinate().setX(sprite.getCoordinate().getX() + 1);
        d.setDirection(Direction.DOWN);
    }

    private void moveUp(MovingSprite sprite, TravelDirection d) {
        sprite.getCoordinate().setX(sprite.getCoordinate().getX() - 1);
        d.setDirection(Direction.UP);
    }

    private void moveRight(MovingSprite sprite, TravelDirection d) {
        sprite.getCoordinate().setY(sprite.getCoordinate().getY() + 1);
        d.setDirection(Direction.RIGHT);
    }

    private void moveLeft(MovingSprite sprite, TravelDirection d) {
        sprite.getCoordinate().setY(sprite.getCoordinate().getY() - 1);
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

    public void determineGhostSpritesTravelDirection(boolean[][] booleanArray, MovingSprite ghostSprite) {
        List<Direction> possibleDirectionsList = new ArrayList<>();

        if (canSpriteMoveDown(booleanArray, ghostSprite) && ghostSprite.getTravelDirection().getDirection() != Direction.UP) {
            possibleDirectionsList.add(Direction.DOWN);
        }
        if (canSpriteMoveUp(booleanArray, ghostSprite) && ghostSprite.getTravelDirection().getDirection() != Direction.DOWN) {
            possibleDirectionsList.add(Direction.UP);
        }
        if (canSpriteMoveRight(booleanArray, ghostSprite) && ghostSprite.getTravelDirection().getDirection() != Direction.LEFT) {
            possibleDirectionsList.add(Direction.RIGHT);
        }
        if (canSpriteMoveLeft(booleanArray, ghostSprite) && ghostSprite.getTravelDirection().getDirection() != Direction.RIGHT) {
            possibleDirectionsList.add(Direction.LEFT);
        }

        if (possibleDirectionsList.isEmpty()) {
            addDirectionToDirectionsListIfListIsEmpty(ghostSprite, possibleDirectionsList, Direction.UP, Direction.DOWN);
            addDirectionToDirectionsListIfListIsEmpty(ghostSprite, possibleDirectionsList, Direction.DOWN, Direction.UP);
            addDirectionToDirectionsListIfListIsEmpty(ghostSprite, possibleDirectionsList, Direction.RIGHT, Direction.LEFT);
            addDirectionToDirectionsListIfListIsEmpty(ghostSprite, possibleDirectionsList, Direction.LEFT, Direction.RIGHT);
        }
        ghostSprite.getTravelDirection().setDirection(possibleDirectionsList.get(r.nextInt(possibleDirectionsList.size())));
    }

    private void addDirectionToDirectionsListIfListIsEmpty(MovingSprite ghostSprite, List<Direction> possibleDirectionsList, Direction d1, Direction d2) {
        if (ghostSprite.getTravelDirection().getDirection() == d1) {
            possibleDirectionsList.add(d2);
        }
    }

    public void moveGhostSprite(boolean[][] booleanArray, MovingSprite ghostSprite) {
        determineGhostSpritesTravelDirection(booleanArray, ghostSprite);
        if (ghostSprite.getTravelDirection().getDirection() == Direction.UP) {
            moveUp(ghostSprite, ghostSprite.getTravelDirection());
        }
        if (ghostSprite.getTravelDirection().getDirection() == Direction.DOWN) {
            moveDown(ghostSprite, ghostSprite.getTravelDirection());
        }
        if (ghostSprite.getTravelDirection().getDirection() == Direction.LEFT) {
            moveLeft(ghostSprite, ghostSprite.getTravelDirection());
        }
        if (ghostSprite.getTravelDirection().getDirection() == Direction.RIGHT) {
            moveRight(ghostSprite, ghostSprite.getTravelDirection());
        }
    }
}

