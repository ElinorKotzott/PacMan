package PacMan;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        JFrame jframe = new JFrame("EllysPacManGame");
        Board board = new Board(610, 510);

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.add(board);
        jframe.pack();
        jframe.setVisible(true);
    }
}
