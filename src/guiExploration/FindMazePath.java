package guiExploration;

import java.io.File;
import java.util.Random;
import java.util.Stack;

public class FindMazePath {
    private static final int rows = 50;
    private static final int columns = 50;
    private static int currentRow;
    private static int currentColumn;
    private static int[][] mazeArray = new int[rows][columns];
    private static int[][] solvedMazeArray = new int[rows][columns];
    private static final Stack<Integer> rowsStack = new Stack<>();
    private static final Stack<Integer> columnsStack = new Stack<>();
    private static final int startRow = 0;
    private static final int startColumn = 1;
    private static final int goalRow = rows - 1;
    private static final int goalColumn = columns - 2;
    private static int imageRow;
    private static int imageColumn;

    public FindMazePath(int[][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    /**
     * The method for getting "rows"
     * @return "rows"
     */
    public int getRows() {
        return rows;
    }

    /**
     * The method for getting "columns"
     * @return "columns"
     */
    public int getColumns() {
        return columns;
    }

    /**
     * The method for getting "mazeArray"
     * @return "mazeArray"
     */
    public int[][] getSolvedMazeArray() {
        return solvedMazeArray;
    }

    /**
     * The method for generating a random maze
     */
    public static void createMaze(File imageFile) {
        // Start digging wall from the starting point
        currentRow = startRow + 1;
        currentColumn = startColumn;
        mazeArray[currentRow][currentColumn] = 0;
        rowsStack.push(currentRow);
        columnsStack.push(currentColumn);

        // Create a path until the program visits all walls of mazeArray by digging walls
        boolean continueLoop = true;
        while (continueLoop) {
            createPathA();
            continueLoop = false;
            while (rowsStack.empty() == false && columnsStack.empty() == false) {
                currentRow = rowsStack.pop();
                currentColumn = columnsStack.pop();
                if (canCreatePathWithDirection(0) ||
                        canCreatePathWithDirection(1) ||
                        canCreatePathWithDirection(2) ||
                        canCreatePathWithDirection(3)) {
                    continueLoop = true;
                    break;
                }
            }

        }

        // Make the starting point and goal point become paths
        // If the value of the mazeArray is 2, the value means the starting point
        // If the value of the mazeArray is 3, the value means the goal point
        mazeArray[startRow][startColumn] = 2;
        mazeArray[goalRow][goalColumn] = 3;

        if (imageFile != null) {
            // Insert an image into the random position in all walls of the maze
            mazeArray[imageRow][imageColumn] = 4;
            mazeArray[imageRow][imageColumn + 1] = 5;
            mazeArray[imageRow + 1][imageColumn] = 5;
            mazeArray[imageRow + 1][imageColumn + 1] = 5;
        }
    }

    /**
     * The method for creating a new path
     */
    public static void createPathA() {
        boolean continueLoop = true;
        while (continueLoop) {
            continueLoop = createPathB();
        }
    }

    /**
     * The method for checking if the program succeeds to create a new path
     * @return The boolean value representing whether the program succeeds to create a new path
     */
    public static boolean createPathB() {
        Random rand = new Random();
        int direction = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            direction = (direction + i) % 4;
            if (canCreatePathWithDirection(direction)) {
                moveToTheNextPoint(direction);
                return true;
            }
        }
        return false;
    }

    /**
     * The method for checking if the program can create a path with a designated direction
     * @param direction The integer value representing which the program create a path
     * @return The boolean value representing whether the program can create a path with a designated direction
     */
    public static boolean canCreatePathWithDirection(int direction) {
        int nextRow = currentRow;
        int nextColumn = currentColumn;
        switch (direction) {
            // Top
            case 0 -> nextRow--;

            // Bottom
            case 1 -> nextRow++;

            // Left
            case 2 -> nextColumn--;

            // Right
            case 3 -> nextColumn++;
        }

        // Calculate the number of surrounding paths of the point
        int numSurroundingPaths = 0;

        // Check if the nextRow is the first row, which must be walls
        if (nextRow == 0) {
            numSurroundingPaths++;
        } else if (mazeArray[nextRow - 1][nextColumn] == 0) {
            numSurroundingPaths++;
        }

        // Check if the nextRow is the last row, which must be walls
        if (nextRow == rows - 1) {
            numSurroundingPaths++;
        } else if (mazeArray[nextRow + 1][nextColumn] == 0) {
            numSurroundingPaths++;
        }

        // Check if the nextColumn is the first column, which must be walls
        if (nextColumn == 0) {
            numSurroundingPaths++;
        } else if (mazeArray[nextRow][nextColumn - 1] == 0) {
            numSurroundingPaths++;
        }

        // Check if the nextColumn is the last column, which must be walls
        if (nextColumn == columns - 1) {
            numSurroundingPaths++;
        } else if (mazeArray[nextRow][nextColumn + 1] == 0) {
            numSurroundingPaths++;
        }

        if (numSurroundingPaths > 1) {
            return false;
        }
        return true;
    }


    /**
     * The method for moving the current point to the next designated point
     * @param direction The integer value representing which the program create a path
     */
    public static void moveToTheNextPoint(int direction) {
        switch (direction) {
            // Move top
            case 0 -> currentRow--;

            // Move bottom
            case 1 -> currentRow++;

            // Move left
            case 2 -> currentColumn--;

            // Move right
            case 3 -> currentColumn++;
        }
        mazeArray[currentRow][currentColumn] = 0;
        rowsStack.push(currentRow);
        columnsStack.push(currentColumn);
    }
}

