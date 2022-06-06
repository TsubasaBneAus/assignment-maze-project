package guiExploration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class FindMazePathTest {
    RandomGeneration  randGen = new RandomGeneration(null);
    FindMazePath findMazePath = new FindMazePath(randGen.getRows(), randGen.getColumns(), randGen.getMazeArray());

    @Test
    void getRows() {
        assertEquals(50, findMazePath.getRows());
    }

    @Test
    void getColumns() {
        assertEquals(50, findMazePath.getColumns());
    }

    @Test
    void getSolvedMazeArray() {
        for (int i = 0; i < findMazePath.getRows(); i++) {
            for (int k = 0; k < findMazePath.getColumns(); k++) {
                assertNotNull(findMazePath.getSolvedMazeArray()[i][k]);
            }
        }
    }
}