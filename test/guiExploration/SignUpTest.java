package guiExploration;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SignUpTest {
    SignUp signUp = new SignUp();
    @Test
    void getFrameName() {
        assertEquals("Registration Form", signUp.getFrameName());
    }

    @Test
    void getFirstNameLabel() {
        assertEquals("First Name ", signUp.getFirstNameLabel());
    }

    @Test
    void getLastNameLabel() {
        assertEquals("Last Name ", signUp.getLastNameLabel());
    }

    @Test
    void getEmailLabel() {
        assertEquals("Email Address ", signUp.getEmailLabel());
    }

    @Test
    void getPasswordLabel() {
        assertEquals("Password ", signUp.getPasswordLabel());
    }

    @Test
    void getTerm() {
        assertEquals("Accept Terms And Conditions.", signUp.getTerm());
    }

    @Test
    void getSub() {
        assertEquals("Submit", signUp.getSub());
    }

    @Test
    void getReset() {
        assertEquals("Reset", signUp.getReset());
    }

    @Test
    void getBack() {
        assertEquals("Back", signUp.getBack());
    }
}