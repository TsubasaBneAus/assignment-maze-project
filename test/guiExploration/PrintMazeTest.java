package guiExploration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PrintMazeTest {
    RandomGeneration randGen = new RandomGeneration(null);
    PrintMaze printMaze = new PrintMaze(randGen.getRows(), randGen.getColumns(), randGen.getMazeArray(), null);

    @Test
    void getImportedImage1() {
        assertEquals(printMaze.getImportedImage1(), null);
    }

    @Test
    void getImportedImage2() {
        assertEquals(printMaze.getImportedImage2(), null);
    }
}