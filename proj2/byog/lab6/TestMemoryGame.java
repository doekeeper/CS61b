package byog.lab6;


import org.junit.Test;
import static org.junit.Assert.*;

public class TestMemoryGame {

    @Test

    public void testGenerateRandomString() {
        MemoryGame mg = new MemoryGame(50, 50, 0);
        String actual = mg.generateRandomString(0);
        assertTrue(actual.equals(""));
        actual = mg.generateRandomString(1);
        assertTrue(actual.length() == 1);
        actual = mg.generateRandomString(10);
        assertTrue(actual.length() == 10);
    }
}
