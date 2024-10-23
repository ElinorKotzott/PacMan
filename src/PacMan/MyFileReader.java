package PacMan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
    public static boolean[][] createPacManMap() {

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Elinor\\IdeaProjects\\PacManGame\\src\\PacManMap.txt"))) {

            boolean[][] pacManMap = new boolean[12][10];
            String line = br.readLine();
            try {
                int row = 0;
                while (line != null) {
                    String[] elements = line.split(",");
                    for (int column = 0; column < pacManMap[row].length; column++) {
                        if (Integer.parseInt(elements[column].trim()) == 1) {
                            pacManMap[row][column] = true;
                        }
                    }
                    line = br.readLine();
                    row++;
                }
                return pacManMap;
            } catch (NumberFormatException nfe) {
                System.out.println(nfe + "The file could not be read. Wrong format provided.");
            }
        } catch (IOException ioe) {
            System.out.println(ioe + "The file could not be read.");
        }
        return null;
    }
}
