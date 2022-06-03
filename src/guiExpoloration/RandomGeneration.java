package guiExpoloration;

import java.util.Random;
import java.util.Stack;

// This class is used for generating a random maze.
public class RandomGeneration {
    private static final int rows = 30;
    private static final int columns = 30;
    private static int currentRow;
    private static int currentColumn;
    private static final int[][] mazeArray = new int[rows][columns];
    private static final Stack<Integer> rowsStack = new Stack<>();
    private static final Stack<Integer> columnsStack = new Stack<>();
    private static final int startRow = 0;
    private static final int startColumn = 1;
    private static final int goalRow = rows - 1;
    private static final int goalColumn = columns - 2;
    public RandomGeneration() {
        createMaze();
    }
    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }
    public int[][] getMazeArray() {
        return mazeArray;
    }

    public static void createMaze() {
        // Make all cells become walls at first
        // If the value of the mazeArray is 0, the value means a path
        // If the value of the mazeArray is 1, the value means a wall
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                mazeArray[i][k] = 1;
            }
        }

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

    }

    // Create a new path
    public static void createPathA() {
        boolean continueLoop = true;
        while (continueLoop) {
            continueLoop = createPathB();
        }
    }

    // Check if the program succeeds to create a new path
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

    // Check if the program can create a path with a designated direction
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

    // Move the current point to the next designated point
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
