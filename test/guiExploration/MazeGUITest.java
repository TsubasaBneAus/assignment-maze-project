package guiExploration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class MazeGUITest {
    MazeGUI mazeGUI = new MazeGUI("Sample@gmail.com");

    @Test
    void getDefaultRows() {
        assertEquals(10, mazeGUI.getDefaultRows());
    }

    @Test
    void getDefalutColumns() {
        assertEquals(10, mazeGUI.getDefaultColumns());
    }

    @Test
    void getMazeArray() {
        assertNotNull(mazeGUI.getMazeArray());
    }

    @Test
    void getButtons() {
        assertNotNull(mazeGUI.getButtons());
    }

    @Test
    void getArrayForDifferentDataTypes1() {
        assertEquals(10, mazeGUI.getArrayForDifferentDataTypes()[0]);
    }

    @Test
    void getArrayForDifferentDataTypes2() {
        assertEquals(10, mazeGUI.getArrayForDifferentDataTypes()[1]);
    }

    @Test
    void getArrayForDifferentDataTypes3() {
        int[][] array = new int[10][10];
        assertNotNull(mazeGUI.getArrayForDifferentDataTypes()[2]);
    }

    @Test
    void getImageFile() {
        assertEquals(null, mazeGUI.getImageFile());
    }
}