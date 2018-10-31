package byog.Test;

import byog.Core.DirectionPicker;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestDirectionPicker {

    @Test
    public void testPickDirection() {
        DirectionPicker dp = new DirectionPicker();
        String direction = dp.pickRandomDirection();
        boolean actual = false;
        if (direction == "NORTH" || direction == "SOUTH" || direction == "EAST" || direction == "WEST") {
            actual = true;
        } else {
            actual = false;
        }
        assertTrue(actual);
    }

}
