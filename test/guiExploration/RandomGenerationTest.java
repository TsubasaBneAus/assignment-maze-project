package guiExploration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RandomGenerationTest {
    RandomGeneration  randGen = new RandomGeneration(null);

    @Test
    void getRows() {
        assertEquals(50, randGen.getRows());
    }

    @Test
    void getColumns() {
        assertEquals(50, randGen.getRows());
    }

    @Test
    void getMazeArray() {
        for (int i = 0; i < randGen.getRows(); i++) {
            for (int k = 0; k < randGen.getColumns(); k++) {
                assertNotNull(randGen.getMazeArray()[i][k]);
            }
        }
    }
}