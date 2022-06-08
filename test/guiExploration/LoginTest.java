package guiExploration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    Login login = new Login();
    @Test
    void getFrameName() {
        assertEquals("Login Form", login.getFrameName());
    }

    @Test
    void getEmailLabel() {
        assertEquals("Email Address ", login.getEmailLabel());
    }

    @Test
    void getPasswordLabel() {
        assertEquals("Password ", login.getPasswordLabel());
    }

    @Test
    void getTerm() {
        assertEquals("Show Password", login.getTerm());
    }

    @Test
    void getSub() {
        assertEquals("Submit", login.getSub());
    }

    @Test
    void getReset() {
        assertEquals("Reset", login.getReset());
    }

    @Test
    void getBack() {
        assertEquals("Back", login.getBack());
    }
}