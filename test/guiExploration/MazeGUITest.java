package guiExploration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;


class MazeGUITest {
    MazeGUI mazeGUI = new MazeGUI();

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
        assertEquals(null, mazeGUI.getMazeArray());
    }

    @Test
    void getButtons() {
        assertEquals(null, mazeGUI.getButtons());
    }

    @Test
    void getArrayForDifferentDataTypes1() {
        assertEquals(null, mazeGUI.getArrayForDifferentDataTypes()[0]);
    }

    @Test
    void getArrayForDifferentDataTypes2() {
        assertEquals(null, mazeGUI.getArrayForDifferentDataTypes()[1]);
    }

    @Test
    void getArrayForDifferentDataTypes3() {
        assertEquals(null, mazeGUI.getArrayForDifferentDataTypes()[2]);
    }

    @Test
    void getImageFile() {
        assertEquals(null, mazeGUI.getImageFile());
    }
}