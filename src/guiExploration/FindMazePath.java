package guiExploration;

import java.util.Stack;

/**
 * The class to find a path from the start to the end of a maze
 */
public class FindMazePath {
    private static int rows;
    private static int columns;
    private static int currentRow;
    private static int currentColumn;
    private static int[][] mazeArray = new int[rows][columns];
    private static int[][] solvedMazeArray;
    private static final Stack<Integer> rowsStack = new Stack<>();
    private static final Stack<Integer> columnsStack = new Stack<>();
    private static final Stack<Integer> directionsStack = new Stack<>();
    private static final int startRow = 0;
    private static final int startColumn = 1;

    /**
     * The constructor for this "FindMazePath" class
     *
     * @param rows      The number of rows of the "mazeArray"
     * @param columns   The number of columns of the "mazeArray"
     * @param mazeArray The array including the structure of a maze
     */
    public FindMazePath(int rows, int columns, int[][] mazeArray) {
        this.rows = rows;
        this.columns = columns;
        this.mazeArray = mazeArray;
        solvedMazeArray = new int[rows][columns];
        initialiseMazes();
        findPath1();
        while (true) {
            if (rowsStack.empty() && columnsStack.empty()) {
                break;
            } else {
                solvedMazeArray[rowsStack.pop()][columnsStack.pop()] = 6;
            }
        }
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
     * The method for getting "solvedMazeArray"
     *
     * @return "solvedMazeArray"
     */
    public int[][] getSolvedMazeArray() {
        return solvedMazeArray;
    }

    /**
     * The method to initialise mazes by copying the contents of the "mazeArray" to a "solvedMazeArray
     * and changing all values of the "mazeArray" to either 0 or 1, which means paths or walls respectively
     */
    public void initialiseMazes() {
        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                if (mazeArray[i][k] == 0) {
                    solvedMazeArray[i][k] = 0;
                } else if (mazeArray[i][k] == 1) {
                    solvedMazeArray[i][k] = 1;
                } else if (mazeArray[i][k] == 2) {
                    solvedMazeArray[i][k] = 2;
                } else if (mazeArray[i][k] == 3) {
                    solvedMazeArray[i][k] = 3;
                } else if (mazeArray[i][k] == 4) {
                    solvedMazeArray[i][k] = 4;
                } else if (mazeArray[i][k] == 5) {
                    solvedMazeArray[i][k] = 5;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int k = 0; k < columns; k++) {
                if (mazeArray[i][k] == 2) {
                    mazeArray[i][k] = 1;
                } else if (mazeArray[i][k] == 3) {
                    continue;
                } else if (mazeArray[i][k] == 4) {
                    mazeArray[i][k] = 1;
                } else if (mazeArray[i][k] == 5) {
                    mazeArray[i][k] = 1;
                } else if (mazeArray[i][k] == 6) {
                    mazeArray[i][k] = 1;
                }
            }
        }
    }


    /**
     * The method for searching a path of the maze
     */
    public static void findPath1() {
        currentRow = startRow + 1;
        currentColumn = startColumn;
        rowsStack.push(currentRow);
        columnsStack.push(currentColumn);

        while (true) {
            if (findPath2() == 1) {
                continue;
            } else if (findPath2() == 2) {
                break;
            } else {
                mazeArray[currentRow][currentColumn] = 1;
                rowsStack.pop();
                columnsStack.pop();
                directionsStack.pop();
                currentRow = rowsStack.peek();
                currentColumn = columnsStack.peek();
                continue;
            }
        }
    }

    /**
     * The method for checking if there are paths surrounding a designated location of the maze
     * and checking if its location is next to the goal of the maze
     *
     * @return The integer value representing whether the conditions above are met
     */
    public static int findPath2() {
        for (int i = 0; i < 4; i++) {
            if (canFindPathWithDirection(i) == 1) {
                moveToTheNextPoint(i);
                return 1;
            } else if (canFindPathWithDirection(i) == 2) {
                return 2;
            }
        }
        return 0;
    }


    /**
     * The method for checking if the program can find a path with a designated direction
     *
     * @param direction The integer value representing which the program find a path
     * @return The boolean value representing whether the program can find a path with a designated direction
     */
    public static int canFindPathWithDirection(int direction) {
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

        if (directionsStack.empty()) {
            if (mazeArray[nextRow][nextColumn] == 0) {
                return 1;
            } else if (mazeArray[nextRow][nextColumn] == 3) {
                return 2;
            } else {
                return 0;
            }
        } else if (directionsStack.peek() == 0 && direction != 1) {
            if (mazeArray[nextRow][nextColumn] == 0) {
                return 1;
            } else if (mazeArray[nextRow][nextColumn] == 3) {
                return 2;
            } else {
                return 0;
            }
        } else if (directionsStack.peek() == 1 && direction != 0) {
            if (mazeArray[nextRow][nextColumn] == 0) {
                return 1;
            } else if (mazeArray[nextRow][nextColumn] == 3) {
                return 2;
            } else {
                return 0;
            }
        } else if (directionsStack.peek() == 2 && direction != 3) {
            if (mazeArray[nextRow][nextColumn] == 0) {
                return 1;
            } else if (mazeArray[nextRow][nextColumn] == 3) {
                return 2;
            } else {
                return 0;
            }
        } else if (directionsStack.peek() == 3 && direction != 2) {
            if (mazeArray[nextRow][nextColumn] == 0) {
                return 1;
            } else if (mazeArray[nextRow][nextColumn] == 3) {
                return 2;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }


    /**
     * The method for moving the current point to the next designated point
     *
     * @param direction The integer value representing which the program find a path
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
        rowsStack.push(currentRow);
        columnsStack.push(currentColumn);
        directionsStack.push(direction);
    }
}

