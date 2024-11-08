package PacMan;

import PacMan.sceneObjects.FoodSprite;
import PacMan.sceneObjects.GhostSprite;
import PacMan.sceneObjects.PacManSprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends JPanel {
    private int height;
    private int width;
    private Game game;
    private Timer movementTimer;
    private TravelDirection travelDirection = new TravelDirection();
    private int moveDirection;
    boolean[][] booleanArray = MyFileReader.createPacManMap();
    public static final int elementSize = 50;
    private final int pacManSize = 40;
    private int smallFoodSize = 4;
    private int boosterSize = 10;
    private int ghostSize = 40;
    private List<FoodSprite> foodSpriteList = new ArrayList<>();
    private final PacManSprite pacMan;
    private List<GhostSprite> ghostSpriteList = new ArrayList<>();
    private final int numberOfBoosters = 3;
    private final int numberOfGhostSprites = 3;
    private List<Integer> randomNumbersList;
    private boolean gameOver;
    private int freezePaintingCounter = 0;
    private final Random r = new Random();


    public Board(int height, int width) {
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        game = new Game(r);
        pacMan = new PacManSprite(455, 205);
        fillGhostSpriteList();
        fillFoodSpriteList();
        createRandomNumbersList();
        setBoosterStatusToTrue();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        moveDirection = KeyEvent.VK_LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveDirection = KeyEvent.VK_RIGHT;
                        break;
                    case KeyEvent.VK_UP:
                        moveDirection = KeyEvent.VK_UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDirection = KeyEvent.VK_DOWN;
                        break;
                }
            }
        });

        int delay = 1;
        movementTimer = new Timer(delay, e -> {
            if (!gameOver) {
                game.movePacMan(moveDirection, booleanArray, pacMan, travelDirection);
                for(int i = 0; i < ghostSpriteList.size(); i++) {
                    game.moveGhostSprite(booleanArray, ghostSpriteList.get(i));
                }
                repaint();
            } else {
                freezePaintingCounter++;
                if (freezePaintingCounter == 150.000) {
                    System.exit(0);
                }
            }
        });
        movementTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // TODO add another food component between each already existing food component

        int counter = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray[i].length; j++) {
                if (booleanArray[i][j]) {
                    g.setColor(Color.blue);
                    g.fillRect(j * elementSize, i * elementSize, elementSize, elementSize);
                } else {
                    checkIfFoodIsEaten();
                    if (foodSpriteList.get(counter).isEaten()) {
                        counter++;
                        checkIfIsWin();
                        continue;
                    }
                    g.setColor(Color.orange);
                    if (!foodSpriteList.get(counter).isBooster()) {
                        g.fillOval(foodSpriteList.get(counter).getCoordinate().getY(), foodSpriteList.get(counter).getCoordinate().getX(), smallFoodSize, smallFoodSize);
                    } else {
                        foodSpriteList.get(counter).getCoordinate().setX(i * elementSize + elementSize / 2 - boosterSize / 2);
                        foodSpriteList.get(counter).getCoordinate().setY(j * elementSize + elementSize / 2 - boosterSize / 2);
                        g.fillOval(foodSpriteList.get(counter).getCoordinate().getY(), foodSpriteList.get(counter).getCoordinate().getX(), boosterSize, boosterSize);
                    }
                    counter++;
                }
            }
        }


        g.setColor(Color.yellow);
        g.fillOval(pacMan.getCoordinate().getY(), pacMan.getCoordinate().getX(), pacManSize, pacManSize);

        g.setColor(Color.green);
        for (int i = 0; i < ghostSpriteList.size(); i++) {
            g.fillOval(ghostSpriteList.get(i).getCoordinate().getY(), ghostSpriteList.get(i).getCoordinate().getX(), ghostSize, ghostSize);
        }

        if (checkIfIsWin()) {
            paintWinnerMessage(g);
        }
    }


    private void fillFoodSpriteList() {
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray[i].length; j++) {
                if (!booleanArray[i][j]) {
                    foodSpriteList.add(new FoodSprite(i * elementSize + elementSize / 2 - smallFoodSize / 2, j * elementSize + elementSize / 2 - smallFoodSize / 2));
                }
            }
        }
    }

    private void fillGhostSpriteList() {
        for (int i = 0; i < numberOfGhostSprites; i++) {
            ghostSpriteList.add(new GhostSprite(155, 205));
            ghostSpriteList.get(i).setTravelDirection(new TravelDirection());
        }
    }


    private void createRandomNumbersList() {
        randomNumbersList = new ArrayList();
        while (randomNumbersList.size() < numberOfBoosters) {
            int randomNumber = r.nextInt(foodSpriteList.size());
            if (!randomNumbersList.contains(randomNumber)) {
                randomNumbersList.add(randomNumber);
            }
        }
    }

    private void setBoosterStatusToTrue() {
        for (int i = 0; i < randomNumbersList.size(); i++) {
            foodSpriteList.get(randomNumbersList.get(i)).setBooster(true);
        }
    }

    private void checkIfFoodIsEaten() {
        for (int i = 0; i < foodSpriteList.size(); i++) {
            if (foodSpriteList.get(i).getCoordinate().getY() - pacMan.getCoordinate().getY() >= 15
                    && foodSpriteList.get(i).getCoordinate().getY() - pacMan.getCoordinate().getY() <= 25
                    && foodSpriteList.get(i).getCoordinate().getX() - pacMan.getCoordinate().getX() > 10
                    && foodSpriteList.get(i).getCoordinate().getX() - pacMan.getCoordinate().getX() < 20) {
                foodSpriteList.get(i).setEaten(true);
            }
        }
    }

    public boolean checkIfIsWin() {
        for (int i = 0; i < foodSpriteList.size(); i++) {
            if (!foodSpriteList.get(i).isEaten()) {
                return false;
            }
        }
        gameOver = true;
        return true;
    }

    public void paintWinnerMessage(Graphics g) {
        g.setColor(Color.white);
        String winnerMessage = "Congrats, you won!";
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (width - metrics.stringWidth(winnerMessage)) / 2;
        int y = (height - metrics.getHeight()) / 2 + metrics.getAscent();
        g.drawString(winnerMessage, x, y);
    }

}
