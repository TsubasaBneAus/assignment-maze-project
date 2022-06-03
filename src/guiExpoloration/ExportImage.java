package guiExpoloration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


// This class is used for exporting a maze as an JPG file.
public class ExportImage extends JPanel {
    private final int rows;
    private final int columns;
    private final int[][] mazeArray;
    private File importedImage1;
    private BufferedImage importedImage2;

    public ExportImage(int rows, int columns, int[][] mazeArray, File importedImage1) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
        this.importedImage1 = importedImage1;
        saveImage();
    }

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
                    } else if (mazeArray[i][j] == 4) {
//                        g.setColor(Color.YELLOW);
//                        g.fillRect(20 * j, 20 * i, 20, 20);
                        g.drawImage(importedImage2, 20 * j, 20 * i, 20, 20, null);
                    }
                }
            }
            g.dispose();
            ImageIO.write(exportedImage, "jpg", new File("mazeScreenshot.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
