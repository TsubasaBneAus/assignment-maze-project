package guiExploration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * The class for exporting the generated maze as an image file
 */
public class ExportImage extends JPanel {

    private final int rows;
    private final int columns;
    private final int[][] mazeArray;
    private final File importedImage1;
    private final String mazeName;
    private final String email;
    private BufferedImage importedImage2 = null;
    private BufferedImage generatedImage;
    private Connection connection;
    private static final String INSERT_IMAGES = "INSERT INTO MazeImages (email, mazeImage) VALUES (?, ?);";
    private PreparedStatement addImages;
    private ByteArrayOutputStream baos;
    byte[] imageData;

    /**
     * The constructor for this "ExportImage" class
     *
     * @param rows           The number of rows of the generated maze
     * @param columns        The number of columns of the generated maze
     * @param mazeArray      The array representing the structure of the generated maze
     * @param importedImage1 The image file to insert into the maze
     */
    public ExportImage(int rows, int columns, int[][] mazeArray, File importedImage1, String mazeName, String email) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
        this.importedImage1 = importedImage1;
        this.mazeName = mazeName;
        this.email = email;
        saveImage();
    }

    /**
     * The method for getting the value of "importedImage1"
     *
     * @return "importedImage1"
     */
    public File getImportedImage1() {
        return importedImage1;
    }

    /**
     * The method for getting the value of "importedImage2"
     *
     * @return "importedImage2"
     */
    public BufferedImage getImportedImage2() {
        return importedImage2;
    }

    /**
     * The method for converting the generated maze with the designated image into a JPG file,
     * and saving it to this current Maze-Project folder by naming the maze
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
            BufferedImage exportedImage = new BufferedImage(10 * columns, 10 * rows, BufferedImage.TYPE_INT_RGB);
            Graphics g = exportedImage.getGraphics();
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
                        g.setColor(Color.WHITE);
                        g.fillRect(10 * j, 10 * i, 10, 10);
                    }
                }
            }
            g.dispose();
            ImageIO.write(exportedImage, "jpg", new File(mazeName + ".jpg"));
            generatedImage = ImageIO.read(new File(mazeName + ".jpg"));
            baos = new ByteArrayOutputStream();
            ImageIO.write(generatedImage, "jpg", baos);
            imageData = baos.toByteArray();
            connection = DBConnection.getInstance();
            addImages = connection.prepareStatement(INSERT_IMAGES);
            addImages.setString(1, email);
            addImages.setBytes(2, imageData);
            addImages.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
