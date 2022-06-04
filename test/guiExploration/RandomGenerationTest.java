package guiExploration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RandomGenerationTest {
    RandomGeneration  randGen = new RandomGeneration(null);

    @Test
    void getRows() {
        assertEquals(30, randGen.getRows());
    }

    @Test
    void getColumns() {
        assertEquals(30, randGen.getRows());
    }

    @Test
    void getMazeArray() {
        int[][] mazeArray = new int[30][30];
        assertEquals(mazeArray, randGen.getMazeArray());
    }
}