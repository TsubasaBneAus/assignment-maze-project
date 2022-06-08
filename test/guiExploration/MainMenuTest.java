package guiExploration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {
    MainMenu mainMenu = new MainMenu();

    @Test
    void getFrameName() {
        assertEquals("Main Menu", mainMenu.getFrameName());
    }

    @Test
    void getReg() {
        assertEquals("Sign Up", mainMenu.getReg());
    }

    @Test
    void getLog() {
        assertEquals("Login", mainMenu.getLog());
    }

    @Test
    void getExit() {
        assertEquals("Exit", mainMenu.getExit());
    }
}