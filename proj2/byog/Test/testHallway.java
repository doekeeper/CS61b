package byog.Test;

import byog.Core.Hallway;
import byog.Core.Map;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.*;

public class testHallway {

    @Test
    public void testAddHallway() {
        Map map = new Map();          // instantiate a Map class 80 * 50
        TETile[][] newMap = map.initialize();               // create an 2D array which is filled with Tileset.NOTHING;
        Hallway hallway = new Hallway();                    // instantiate a Hallway class
        int[] cellPos = new int[]{10, 10};                  // set current cell position to (10, 10)
        hallway.addHallway(newMap, cellPos, "EAST");   //add one hallway section toward EAST direction of current location
        assertEquals(Tileset.FLOOR, newMap[11][10]);
        assertEquals(Tileset.WALL, newMap[11][11]);
        assertEquals(Tileset.WALL, newMap[11][9]);

        hallway.addHallway(newMap, cellPos, "WEST");   //add one hallway section toward WEST direction of current location
        assertEquals(Tileset.FLOOR, newMap[9][10]);
        assertEquals(Tileset.WALL, newMap[9][11]);
        assertEquals(Tileset.WALL, newMap[9][9]);

        hallway.addHallway(newMap, cellPos, "NORTH");   //add one hallway section toward NORTH direction of current location
        assertEquals(Tileset.FLOOR, newMap[10][11]);
        assertEquals(Tileset.WALL, newMap[9][11]);
        assertEquals(Tileset.WALL, newMap[11][11]);

        hallway.addHallway(newMap, cellPos, "SOUTH");   //add one hallway section toward SOUTH direction of current location
        assertEquals(Tileset.FLOOR, newMap[10][9]);
        assertEquals(Tileset.WALL, newMap[11][9]);
        assertEquals(Tileset.WALL, newMap[9][9]);
    }

    @Test
    public void testHasSpaceForHallway() {
        Map map = new Map();          // instantiate a Map class 80 * 50
        TETile[][] newMap = map.initialize();               // create an 2D array which is filled with Tileset.NOTHING;
        Hallway hallway = new Hallway();                    // instantiate a Hallway class
        int[] cellPos = new int[]{10, 10};                  // set current cell position to (10, 10)
        boolean actual = hallway.hasSpaceForHallway(newMap, cellPos, "EAST");
        assertTrue(actual);
        newMap[12][9] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "EAST");
        assertFalse(actual);
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "SOUTH");
        assertTrue(actual);
        newMap[10][9] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "SOUTH");
        assertTrue(actual);
        newMap[10][8] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "SOUTH");
        assertFalse(actual);
        newMap[9][9] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "WEST");
        assertTrue(actual);
        newMap[8][9] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "WEST");
        assertFalse(actual);
        newMap[9][11] = Tileset.WALL;
        actual = hallway.hasSpaceForHallway(newMap, cellPos, "NORTH");
        assertTrue(actual);
    }
}
