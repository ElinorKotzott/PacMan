package PacMan;

import PacMan.sceneObjects.FoodSprite;
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
    private TravelDirection travelDirection = new TravelDirection(Direction.LEFT);
    private int moveDirection = KeyEvent.VK_LEFT;
    boolean[][] booleanArray = MyFileReader.createPacManMap();
    private int elementSize = 50;
    private int pacManSize = 40;
    private int smallFoodSize = 4;
    private int boosterSize = 10;
    private int ghostSize = 30;
    private List<FoodSprite> foodComponentsList = new ArrayList<>();
    private PacManSprite pacMan;
    private int numberOfBoosters = 3;
    private List<Integer> randomNumbersList;


    public Board(int height, int width) {
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        game = new Game(this::repaint, height, width);
        pacMan = new PacManSprite(455, 205);
        fillFoodComponentsList();
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

        int delay = 10;
        movementTimer = new Timer(delay, e -> {
            game.movePacMan(moveDirection, booleanArray, pacMan, travelDirection.getTravelDirection());
            repaint();
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
                    g.setColor(Color.orange);
                    if (!foodComponentsList.get(counter).isBooster()) {
                        g.fillOval(foodComponentsList.get(counter).getCoordinate().getY(), foodComponentsList.get(counter).getCoordinate().getX(), smallFoodSize, smallFoodSize);
                        counter++;
                    } else {
                        g.fillOval(foodComponentsList.get(counter).getCoordinate().getY(), foodComponentsList.get(counter).getCoordinate().getX(), boosterSize, boosterSize);
                        counter++;
                    }
                }
            }
        }


        g.setColor(Color.yellow);
        g.fillOval(pacMan.getCoordinate().getY(), pacMan.getCoordinate().getX(), pacManSize, pacManSize);


    }

    private List<FoodSprite> fillFoodComponentsList() {
        for (int i = 0; i < booleanArray.length; i++) {
            for (int j = 0; j < booleanArray[i].length; j++) {
                if (!booleanArray[i][j]) {
                    foodComponentsList.add(new FoodSprite(i * elementSize + elementSize / 2 - smallFoodSize / 2, j * elementSize + elementSize / 2 - smallFoodSize / 2));
                }
            }
        }
        return foodComponentsList;
    }


    private List<Integer> createRandomNumbersList() {
        randomNumbersList = new ArrayList();
        Random random = new Random();
        while (randomNumbersList.size() < numberOfBoosters) {
            int randomNumber = random.nextInt(foodComponentsList.size());
            if (!randomNumbersList.contains(randomNumber)) {
                randomNumbersList.add(randomNumber);
            }
        }
        return randomNumbersList;
    }

    private void setBoosterStatusToTrue() {
        for (int i = 0; i < randomNumbersList.size(); i++) {
            foodComponentsList.get(randomNumbersList.get(i)).setBooster(true);
        }
    }


}
