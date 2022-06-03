package guiExpoloration;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MazeGui extends JFrame implements ActionListener, MouseListener {
    private static int rows;
    private static int columns;
    private static int[][] mazeArray;
    private static JButton[][] buttons;
    private static int defaultRows = 10;
    private static int defaultColumns = 10;
    private static boolean isCustomisedGeneration;
    private static Object[] arrayForDifferentDataTypes = new Object[3];
    private static  JPanel panelForButtons = new JPanel();
    private static JToggleButton buttonStart = new JToggleButton("Select the starting block");
    private static JToggleButton buttonEnd = new JToggleButton("Select the ending block");
    private static JToggleButton buttonImageLocation = new JToggleButton("Select a block to locate an image");

    public static void CreateInputSection(JFrame frame, JPanel panelOnTheLeft, JPanel panelOnTheRight) {
        JLabel label1ForInput = new JLabel("Rows:");
        JLabel label2ForInput = new JLabel("Columns:");

        JTextField text1ForInput = new JTextField(5);
        JTextField text2ForInput = new JTextField(5);

        JButton buttonForInput = new JButton("Change a Maze Size");
        JButton buttonRandom = new JButton("Generate a Random Maze");
        JButton buttonGenerate = new JButton("Generate a Customised Maze");
        JButton buttonExport = new JButton("Export a Maze Image");
        JButton buttonReset = new JButton("Reset a Maze Settings");

//        JButton buttonTest = new JButton("Reset");
        JPanel panelForRows = new JPanel();
        JPanel panelForColumns = new JPanel();

        // Generate a maze that a user customised
        buttonGenerate.addActionListener(e -> {
            JFrame customisedMaze = new JFrame("Customised Maze");
            arrayForDifferentDataTypes[0] = rows;
            arrayForDifferentDataTypes[1] = columns;
            arrayForDifferentDataTypes[2] = mazeArray;
            PrintMaze printMaze = new PrintMaze(rows, columns, mazeArray);
            customisedMaze.add(printMaze);
            customisedMaze.setSize(panelForButtons.getWidth() - 20, panelForButtons.getHeight());
            customisedMaze.setVisible(true);
        });

        // Generate a random maze
        buttonRandom.addActionListener(e -> {
            JFrame randomMaze = new JFrame("Random Maze");
            RandomGeneration randGen = new RandomGeneration();
            arrayForDifferentDataTypes[0] = randGen.getRows();
            arrayForDifferentDataTypes[1] = randGen.getColumns();
            arrayForDifferentDataTypes[2] = randGen.getMazeArray();
            PrintMaze printMaze = new PrintMaze(randGen.getRows(), randGen.getColumns(), randGen.getMazeArray());
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

        buttonImageLocation.addActionListener(e -> {
            if (buttonEnd.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the end function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonImageLocation.setSelected(false);

            } else if (buttonStart.isSelected()) {
                JOptionPane.showMessageDialog(buttonForInput, "Please stop the start function first!",
                        "Invalid Input!", JOptionPane.ERROR_MESSAGE);
                buttonImageLocation.setSelected(false);

            }
        });

        buttonForInput.addActionListener(e -> {
            rows = Integer.parseInt(text1ForInput.getText());
            columns = Integer.parseInt(text2ForInput.getText());
            panelOnTheLeft.removeAll();
//            if (size[0] > 10 || size[1] > 10) {
//                JOptionPane.showMessageDialog(buttonForInput, "Too big",
//                        "Wiring Class: Error", JOptionPane.ERROR_MESSAGE);
//                size[0] = 10;
//                size[1] = 10;
//            }
            CreateSelectionButtons(panelOnTheLeft, rows, columns);
            panelOnTheLeft.revalidate();
            panelOnTheLeft.repaint();
        });

        buttonExport.addActionListener(e -> {
            rows = (int) arrayForDifferentDataTypes[0];
            columns = (int) arrayForDifferentDataTypes[1];
            mazeArray = (int[][]) arrayForDifferentDataTypes[2];
            new ExportImage(rows, columns, mazeArray);
        });

//        buttonReset.addActionListener(new ActionListener() {
//            @Override
//            public
//            void actionPerformed(ActionEvent e) {
//                JButton[] button = (JButton[]) e.getSource();
//                for (JButton jButton : button) {
//                    jButton.setBackground(Color.WHITE);
//                }
//            }
//        });


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
        panelOnTheRight.add(buttonImageLocation);
        panelOnTheRight.add(buttonGenerate);
        panelOnTheRight.add(buttonRandom);
        panelOnTheRight.add(buttonExport);
        panelOnTheRight.add(buttonReset);
    }

    public static void CreateSelectionButtons(JPanel panelOnTheLeft, int currentRows, int currentColumns) {
        rows = currentRows;
        columns = currentColumns;
        mazeArray = new int[rows][columns];
        // Make all cells become walls at first
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                mazeArray[i][k] = 1;
            }
        }
        panelForButtons.setLayout(new GridLayout(rows, columns));
        buttons = new JButton[rows][columns];

        // Deploy multiple buttons to select
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                buttons[i][k] = new JButton();
                buttons[i][k].setPreferredSize(new Dimension(25, 25));
                buttons[i][k].setBackground(Color.BLACK);
                int currentRow = i;
                int currentColumn = k;
                buttons[i][k].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the button that was clicked
                        JButton currentButton = (JButton) e.getSource();
                        Color currentColor = currentButton.getBackground();

                        // If the value of the mazeArray is 0, the value means a path
                        // If the value of the mazeArray is 1, the value means a wall
                        // If the value of the mazeArray is 2, the value means the starting point
                        // If the value of the mazeArray is 3, the value means the goal point
                        // If the value of the mazeArray is 4, the value means a location to insert an image
                        if (buttonImageLocation.isSelected()) {
                            currentButton.setBackground(Color.YELLOW);
                            mazeArray[currentRow][currentColumn] = 4;
                            buttonImageLocation.setSelected(false);
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
                    }
                });
                panelForButtons.add(buttons[i][k]);
            }
        }
        panelOnTheLeft.add(panelForButtons, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Builder");
        frame.setSize(1440, 1220);
        JPanel panelOnTheLeft = new JPanel();
        JPanel panelOnTheRight = new JPanel();
        frame.setLayout(new BorderLayout());
        CreateSelectionButtons(panelOnTheLeft, defaultRows, defaultColumns);
        CreateInputSection(frame, panelOnTheLeft, panelOnTheRight);
        frame.add(panelOnTheLeft, BorderLayout.WEST);
        frame.add(panelOnTheRight, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
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