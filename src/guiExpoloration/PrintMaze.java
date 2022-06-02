package guiExpoloration;

import javax.swing.*;
import java.awt.*;

// This class is used for displaying created maze.
public class PrintMaze extends JPanel {
    private final int rows;
    private final int columns;
    private final int[][] mazeArray;

    public PrintMaze(int rows, int columns, int[][] mazeArray) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // If the value of the mazeArray means a path, paint a block with white
                // If the value of the mazeArray means a wall, paint a block with black
                // If the value of the mazeArray means a starting point, paint a block with white
                // If the value of the mazeArray means a goal point, paint a block with white
                if (mazeArray[i][j] == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                } else if (mazeArray[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                } else if (mazeArray[i][j] == 2) {
                    g.setColor(Color.GREEN);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                } else if (mazeArray[i][j] == 3) {
                    g.setColor(Color.RED);
                    g.fillRect(20 * j, 20 * i, 20, 20);
                }
            }
        }
    }
}
