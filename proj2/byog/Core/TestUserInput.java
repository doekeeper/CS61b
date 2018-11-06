package byog.Core;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestUserInput {

    @Test
    public void testSeed() {
        UserInput user_input = new UserInput();
        int seed = user_input.seed("N123SASDSWA:Q");
        assertEquals(seed, 123);
    }

    @Test
    public void testUserCommand() {
        UserInput user_input = new UserInput();
        String userCommand = user_input.userCommand("N123SASDSWA:Q");
        assertEquals("ASDSWA", userCommand);
    }
}
