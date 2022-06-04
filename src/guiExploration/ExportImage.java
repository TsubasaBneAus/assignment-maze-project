package guiExploration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The class for exporting the generated maze as an image file
 * @author Tsubasa Endo
 */
public class ExportImage extends JPanel {

    private final int rows;
    private final int columns;
    private final int[][] mazeArray;
    private final File importedImage1;
    private BufferedImage importedImage2 = null;

    /**
     * The constructor for this "ExportImage" class
     * @param rows The number of rows of the generated maze
     * @param columns The number of columns of the generated maze
     * @param mazeArray The array representing the structure of the generated maze
     * @param importedImage1 The image file to insert into the maze
     */
    public ExportImage(int rows, int columns, int[][] mazeArray, File importedImage1) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
        this.importedImage1 = importedImage1;
        saveImage();
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
     * The method for converting the generated maze with the designated image into a "generatedMaze.jpg",
     * and saving it to this current Maze-Project folder
     */
    public void saveImage() {
        if (this.importedImage1 != null) {
            try {
                importedImage2 = ImageIO.read(importedImage1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedImage exportedImage = new BufferedImage(20 * columns, 20 * rows, BufferedImage.TYPE_INT_RGB);
            Graphics g = exportedImage.getGraphics();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    // If the value of the mazeArray is 0, paint a block with white
                    // If the value of the mazeArray is 1, paint a block with black
                    // If the value of the mazeArray is 2, paint a block with white
                    // If the value of the mazeArray is 3, paint a block with white
                    // If the value of the mazeArray is 4, insert the designated image
                    // If the value of the mazeArray is 5, put a blank to insert the designated image
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
                    } else if (mazeArray[i][j] == 4) {
                        g.drawImage(importedImage2, 20 * j, 20 * i, 40, 40, null);
                    } else if (mazeArray[i][j] == 5) {
                        continue;
                    }
                }
            }
            g.dispose();
            ImageIO.write(exportedImage, "jpg", new File("generatedMaze.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}