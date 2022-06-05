package guiExploration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class for printing the generated maze to the new window in order to display how it looks
 */
public class PrintMaze extends JPanel {
    private final int rows;
    private final int columns;
    private final int[][] mazeArray;
    private final File importedImage1;
    private BufferedImage importedImage2 = null;

    /**
     * The constructor for this "PrintMaze" class
     * @param rows The number of "rows" of the generated maze
     * @param columns The number of "columns" of the generated maze
     * @param mazeArray The array representing the structure of the generated maze
     * @param importedImage1 The image file to insert into the maze
     */
    public PrintMaze(int rows, int columns, int[][] mazeArray, File importedImage1) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
        this.importedImage1 = importedImage1;
    }

    /**
     * The method for getting "importedImage1"
     * @return "importedImage1"
     */
    public File getImportedImage1() {
        return importedImage1;
    }

    /**
     * The method for getting "importedImage2"
     * @return "importedImage2"
     */
    public BufferedImage getImportedImage2() {
        return importedImage2;
    }

    /**
     * The method for printing a maze based on the structure of "mazeArray"
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.importedImage1 != null) {
            try {
                importedImage2 = ImageIO.read(importedImage1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // If the value of the mazeArray is 0, paint a block with white
                // If the value of the mazeArray is 1, paint a block with black
                // If the value of the mazeArray is 2, paint a block with green
                // If the value of the mazeArray is 3, paint a block with red
                // If the value of the mazeArray is 4, insert the designated image
                // If the value of the mazeArray is 5, put a blank to insert the designated image
                if (mazeArray[i][j] == 0) {
                    g.setColor(Color.WHITE);
                    g.fillRect(10 * j, 10 * i, 10, 10);
                } else if (mazeArray[i][j] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(10 * j, 10 * i, 10, 10);
                } else if (mazeArray[i][j] == 2) {
                    g.setColor(Color.GREEN);
                    g.fillRect(10 * j, 10 * i, 10, 10);
                } else if (mazeArray[i][j] == 3) {
                    g.setColor(Color.RED);
                    g.fillRect(10 * j, 10 * i, 10, 10);
                } else if (mazeArray[i][j] == 4) {
                    g.drawImage(importedImage2, 10 * j, 10 * i, 20, 20, null);
                } else if (mazeArray[i][j] == 5) {
                    continue;
                } else if (mazeArray[i][j] == 6) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(10 * j, 10 * i, 10, 10);
                }
            }
        }
    }
}