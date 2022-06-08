package guiExploration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {
    DBConnection dbConnection = new DBConnection();
    @Test
    void getInstance() {
        assertEquals(null, dbConnection.getInstance());
    }
}