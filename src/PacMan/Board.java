package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Board extends JPanel {
    private int height;
    private int width;
    private Game game;
    private Timer movementTimer;
    private TravelDirection travelDirection = new TravelDirection(Direction.LEFT);
    private int moveDirection = KeyEvent.VK_LEFT;
    boolean[][] pacManMap = MyFileReader.createPacManMap();
    private int componentSize = 50;
    private int pacManSize = 40;
    private int foodSize = 4;
    private int boosterSize = 10;
    private int ghostSize = 30;


    public Board(int height, int width) {
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        this.height = height;
        this.width = width;
        game = new Game(this::repaint, height, width);


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
            game.movePacMan();
            repaint();
        });
        movementTimer.start();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);



        for (int i = 0; i < pacManMap.length; i++) {
            for (int j = 0; j < pacManMap[i].length; j++) {
                if (pacManMap[i][j]) {
                    g.setColor(Color.blue);
                    g.fillRect(j * componentSize, i * componentSize, componentSize, componentSize);

                } else {
                    g.setColor(Color.orange);
                g.fillOval(j*componentSize+(componentSize/2 - foodSize/2), i*componentSize+(componentSize/2-foodSize/2), foodSize, foodSize);
                // TODO add another food component between each already existing food component
                }
            }

        }

        g.setColor(Color.yellow);
        g.fillOval(205, 455, 40, 40);


    }
}