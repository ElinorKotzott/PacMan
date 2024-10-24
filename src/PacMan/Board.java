package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board extends JPanel {
    private int height;
    private int width;
    private Game game;
    private Timer movementTimer;
    private TravelDirection travelDirection = new TravelDirection(Direction.LEFT);
    private int moveDirection = KeyEvent.VK_LEFT;
    boolean[][] pacManArray = MyFileReader.createPacManMap();
    private int elementSize = 50;
    private int pacManSize = 40;
    private int smallFoodSize = 4;
    private int boosterSize = 10;
    private int ghostSize = 30;
    private List<Component> foodComponentsList = new ArrayList<>();
    private Component pacMan;


    public Board(int height, int width) {
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        game = new Game(this::repaint, height, width);
        pacMan = new Component(205, 455);
        // TODO add another food component between each already existing food component.

        for (int i = 0; i < pacManArray.length; i++) {
            for (int j = 0; j < pacManArray[i].length; j++) {
                if (!pacManArray[i][j]) {
                    foodComponentsList.add(new Component(i * elementSize + elementSize / 2 - smallFoodSize / 2, j * elementSize));
                }
            }
        }


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        if (Objects.equals(travelDirection.getTravelDirection(), Direction.RIGHT)) {
                            return;
                        }
                        moveDirection = KeyEvent.VK_LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Objects.equals(travelDirection.getTravelDirection(), Direction.LEFT)) {
                            return;
                        }
                        moveDirection = KeyEvent.VK_RIGHT;
                        break;
                    case KeyEvent.VK_UP:
                        if (Objects.equals(travelDirection.getTravelDirection(), Direction.DOWN)) {
                            return;
                        }
                        moveDirection = KeyEvent.VK_UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (Objects.equals(travelDirection.getTravelDirection(), Direction.UP)) {
                            return;
                        }
                        moveDirection = KeyEvent.VK_DOWN;
                        break;
                }


            }
        });

        int delay = 200;
        movementTimer = new Timer(delay, e -> {
            game.movePacMan(moveDirection, pacManArray, pacMan);
            repaint();
        });
        movementTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

int counter = 0;
        for (int i = 0; i < pacManArray.length; i++) {
            for (int j = 0; j < pacManArray[i].length; j++) {
                if (pacManArray[i][j]) {
                    g.setColor(Color.blue);
                    g.fillRect(j*elementSize, i*elementSize, elementSize, elementSize);
                } else {
                    g.setColor(Color.orange);
                    if (!foodComponentsList.get(counter).isBooster()) {
                        g.fillOval(foodComponentsList.get(counter).getCoordinate().getX(), foodComponentsList.get(counter).getCoordinate().getY(), smallFoodSize, smallFoodSize);
                    } else {
                        g.fillOval(foodComponentsList.get(counter).getCoordinate().getX(), foodComponentsList.get(counter).getCoordinate().getY(), boosterSize, boosterSize);
                    }
                }
            }
        }



        g.setColor(Color.yellow);
        g.fillOval(pacMan.getCoordinate().getX(), pacMan.getCoordinate().getY(), pacManSize, pacManSize);


    }
}
