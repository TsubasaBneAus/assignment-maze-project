package guiExploration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class MazeGUI extends JFrame implements ActionListener, MouseListener {
    private static int rows;
    private static int columns;
    private static int[][] mazeArray;
    private static JButton[][] buttons;
    private static final int defaultRows = 10;
    private static final int defaultColumns = 10;
    private static final Object[] arrayForDifferentDataTypes = new Object[3];
    private static JPanel panelForButtons;
    private static File imageFile = null;
    private static final JToggleButton buttonStart = new JToggleButton("Select the starting block");
    private static final JToggleButton buttonEnd = new JToggleButton("Select the ending block");
    private static final JToggleButton buttonImageLocation = new JToggleButton("Select a block to locate an image");

    /**
     * The method for creating an input section on the right side of the window
     *
     * @param panelOnTheLeft  The JPanel to be displayed on the right side of the window
     * @param panelOnTheRight The JPanel to be displayed on the left side of the window
     */
    public static void CreateInputSection(JPanel panelOnTheLeft, JPanel panelOnTheRight) {
        JLabel label1ForInput = new JLabel("Rows:");
        JLabel label2ForInput = new JLabel("Columns:");

        JTextField text1ForInput = new JTextField(5);
        JTextField text2ForInput = new JTextField(5);

        JButton buttonForInput = new JButton("Change a Maze Size");
        JButton buttonRandom = new JButton("Generate a Random Maze");
        JButton buttonGenerate = new JButton("Generate a Customised Maze");
        JButton buttonChooseImage = new JButton("Select an image file to insert in the maze");
        JButton buttonExport = new JButton("Export a Maze Image");

        JPanel panelForRows = new JPanel();
        JPanel panelForColumns = new JPanel();

        // Generate a maze that a user customised
        buttonGenerate.addActionListener(e -> {
            JFrame customisedMaze = new JFrame("Customised Maze");
            arrayForDifferentDataTypes[0] = rows;
            arrayForDifferentDataTypes[1] = columns;
            arrayForDifferentDataTypes[2] = mazeArray;
            PrintMaze printMaze = new PrintMaze(rows, columns, mazeArray, imageFile);
            customisedMaze.add(printMaze);
            customisedMaze.setSize(800, 800);
            customisedMaze.setVisible(true);
        });

        // Generate a random maze
        buttonRandom.addActionListener(e -> {
            JFrame randomMaze = new JFrame("Random Maze");
            RandomGeneration randGen = new RandomGeneration(imageFile);
            FindMazePath findMazePath = new FindMazePath(randGen.getMazeArray());
            arrayForDifferentDataTypes[0] = randGen.getRows();
            arrayForDifferentDataTypes[1] = randGen.getColumns();
            arrayForDifferentDataTypes[2] = randGen.getMazeArray();
            PrintMaze printMaze = new PrintMaze(randGen.getRows(), randGen.getColumns(), randGen.getMazeArray(), imageFile);
            randomMaze.add(printMaze);
            randomMaze.setSize(625, 650);
            randomMaze.setVisible(true);
        });

        buttonStart.addActionListener(e -> {
            // Get the button that was clicked
            if (buttonEnd.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the end function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonStart.setSelected(false);
            } else if (buttonImageLocation.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the upload function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonStart.setSelected(false);
            }

        });
        buttonEnd.addActionListener(e -> {
            // Get the button that was clicked
            if (buttonStart.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the start function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonEnd.setSelected(false);

            } else if (buttonImageLocation.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the upload function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonEnd.setSelected(false);
            }
        });

        buttonChooseImage.addActionListener(e1 -> {
            JFrame imageFrame = new JFrame("Confirmation");
            JPanel imagePanel = new JPanel();
            JLabel imageLabel = new JLabel();
            JButton saveButton = new JButton("Save");
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            int isSelected = chooser.showOpenDialog(null);
            if (isSelected == JFileChooser.APPROVE_OPTION) {
                imageLabel.setText(chooser.getSelectedFile().getName());
            } else {
                imageLabel.setText("Not selected");
            }
            saveButton.addActionListener(e2 -> {
                imageFile = chooser.getSelectedFile();
                imageFrame.dispose();
            });

            imagePanel.add(imageLabel);
            imageFrame.add(imagePanel, BorderLayout.CENTER);
            imageFrame.add(saveButton, BorderLayout.PAGE_END);
            imageFrame.setSize(300, 300);
            imageFrame.setVisible(true);
        });

        buttonImageLocation.addActionListener(e -> {
            if (buttonEnd.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the end function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonImageLocation.setSelected(false);

            } else if (buttonStart.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the start function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonImageLocation.setSelected(false);

            } else if (imageFile == null) {
                JOptionPane.showMessageDialog(buttonForInput, "Please select the image file to insert into the maze first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonImageLocation.setSelected(false);
            }
        });

        buttonForInput.addActionListener(e -> {
            rows = Integer.parseInt(text1ForInput.getText());
            columns = Integer.parseInt(text2ForInput.getText());
            panelOnTheLeft.removeAll();
            if (rows > 100 || columns > 100) {
                JOptionPane.showMessageDialog(buttonForInput, "The size you selected is too big",
                        "Wiring Class: Error", JOptionPane.ERROR_MESSAGE);
                rows = 10;
                columns = 10;
            }
            CreateSelectionButtons(panelOnTheLeft, rows, columns);
            panelOnTheLeft.revalidate();
            panelOnTheLeft.repaint();
        });

        buttonExport.addActionListener(e1 -> {
            JFrame exportFrame = new JFrame("Confirmation");
            JPanel exportPanel = new JPanel();
            JLabel exportLabel = new JLabel("Please name the generated maze.");
            JButton saveButton = new JButton("Save");
            JTextField inputName = new JTextField(10);
            int currentRows = (int) arrayForDifferentDataTypes[0];
            int currentColumns = (int) arrayForDifferentDataTypes[1];
            int[][] currentMazeArray = (int[][]) arrayForDifferentDataTypes[2];
            saveButton.addActionListener(e2 -> {
                String textName = inputName.getText();
                new ExportImage(currentRows, currentColumns, currentMazeArray, imageFile, textName);
                exportFrame.dispose();
            });

            exportPanel.add(exportLabel);
            exportPanel.add(inputName);
            exportFrame.add(exportPanel, BorderLayout.CENTER);
            exportFrame.add(saveButton, BorderLayout.PAGE_END);
            exportFrame.setSize(300, 300);
            exportFrame.setVisible(true);
        });

        panelOnTheRight.setLayout(new GridLayout(15, 1));
        panelForRows.add(label1ForInput);
        panelForRows.add(text1ForInput);
        panelForColumns.add(label2ForInput);
        panelForColumns.add(text2ForInput);
        panelOnTheRight.add(panelForRows);
        panelOnTheRight.add(panelForColumns);
        panelOnTheRight.add(buttonForInput);
        panelOnTheRight.add(buttonStart);
        panelOnTheRight.add(buttonEnd);
        panelOnTheRight.add(buttonChooseImage);
        panelOnTheRight.add(buttonImageLocation);
        panelOnTheRight.add(buttonGenerate);
        panelOnTheRight.add(buttonRandom);
        panelOnTheRight.add(buttonExport);
    }

    /**
     * The method for creating multiple selection buttons for users to customise their original maze
     *
     * @param panelOnTheLeft The JPanel to be displayed on the left side of the window
     * @param currentRows    The number of "currentRows"
     * @param currentColumns The number of "currentColumns"
     */
    public static void CreateSelectionButtons(JPanel panelOnTheLeft, int currentRows, int currentColumns) {
        mazeArray = new int[currentRows][currentColumns];
        rows = currentRows;
        columns = currentColumns;
        // Make all cells become walls at first
        for (int i = 0; i < currentRows; i++) {
            for (int k = 0; k < currentColumns; k++) {
                mazeArray[i][k] = 1;
            }
        }
        panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(currentRows, currentColumns));
        buttons = new JButton[currentRows][currentColumns];

        // Deploy multiple buttons to select
        for (int i = 0; i < currentRows; i++) {
            for (int k = 0; k < currentColumns; k++) {
                buttons[i][k] = new JButton();
                buttons[i][k].setPreferredSize(new Dimension(15, 15));
                buttons[i][k].setBackground(Color.BLACK);
                int currentRow = i;
                int currentColumn = k;
                buttons[i][k].addActionListener(e -> {
                    // Get the button that was clicked
                    JButton currentButton = (JButton) e.getSource();
                    Color currentColor = currentButton.getBackground();

                    // If the value of the mazeArray is 0, the value means a path
                    // If the value of the mazeArray is 1, the value means a wall
                    // If the value of the mazeArray is 2, the value means the starting point
                    // If the value of the mazeArray is 3, the value means the goal point
                    // If the value of the mazeArray is 4, the value means a location to insert an image
                    // If the value of the mazeArray is 5, the value means surrounding locations to insert an image
                    if (buttonImageLocation.isSelected()) {
                        if (currentColor == Color.YELLOW) {
                            currentButton.setBackground(Color.WHITE);
                            buttons[currentRow][currentColumn + 1].setBackground(Color.WHITE);
                            buttons[currentRow + 1][currentColumn].setBackground(Color.WHITE);
                            buttons[currentRow + 1][currentColumn + 1].setBackground(Color.WHITE);
                            mazeArray[currentRow][currentColumn] = 0;
                            mazeArray[currentRow][currentColumn + 1] = 0;
                            mazeArray[currentRow + 1][currentColumn] = 0;
                            mazeArray[currentRow + 1][currentColumn + 1] = 0;
                        } else {
                            currentButton.setBackground(Color.YELLOW);
                            buttons[currentRow][currentColumn + 1].setBackground(Color.BLUE);
                            buttons[currentRow + 1][currentColumn].setBackground(Color.BLUE);
                            buttons[currentRow + 1][currentColumn + 1].setBackground(Color.BLUE);
                            mazeArray[currentRow][currentColumn] = 4;
                            mazeArray[currentRow][currentColumn + 1] = 5;
                            mazeArray[currentRow + 1][currentColumn] = 5;
                            mazeArray[currentRow + 1][currentColumn + 1] = 5;
                        }
                    } else if (buttonStart.isSelected()) {
                        if (currentColor == Color.GREEN) {
                            currentButton.setBackground(Color.WHITE);
                            mazeArray[currentRow][currentColumn] = 0;
                        } else {
                            currentButton.setBackground(Color.GREEN);
                            mazeArray[currentRow][currentColumn] = 2;
                        }
                    } else if (buttonEnd.isSelected()) {
                        if (currentColor == Color.RED) {
                            currentButton.setBackground(Color.WHITE);
                            mazeArray[currentRow][currentColumn] = 0;
                        } else {
                            currentButton.setBackground(Color.RED);
                            mazeArray[currentRow][currentColumn] = 3;
                        }
                    } else if (currentColor == Color.BLACK) {
                        currentButton.setBackground(Color.WHITE);
                        mazeArray[currentRow][currentColumn] = 0;
                    } else {
                        currentButton.setBackground(Color.BLACK);
                        mazeArray[currentRow][currentColumn] = 1;
                    }
                });
                panelForButtons.add(buttons[i][k]);
            }
        }
        panelOnTheLeft.add(panelForButtons, BorderLayout.WEST);
    }

    /**
     * The main method to run this "MazeGUI"
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Builder");
        frame.setSize(1440,1000);
        JPanel panelOnTheLeft = new JPanel();
        JPanel panelOnTheRight = new JPanel();
        frame.setLayout(new BorderLayout());
        mazeArray = new int[defaultRows][defaultColumns];
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                mazeArray[i][k] = 1;
            }
        }
        arrayForDifferentDataTypes[0] = defaultRows;
        arrayForDifferentDataTypes[1] = defaultColumns;
        arrayForDifferentDataTypes[2] = mazeArray;
        CreateSelectionButtons(panelOnTheLeft, defaultRows, defaultColumns);
        CreateInputSection(panelOnTheLeft, panelOnTheRight);
        JScrollPane scrollPane = new JScrollPane(panelOnTheLeft, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(panelOnTheRight, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
