package byog.Test;

import byog.Core.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import byog.TileEngine.*;
import java.util.Random;

public class TestMap {

    Map map = new Map();
    TETile[][] world = map.initialize();
    Random RANDOM = new Random();

    @Test
    public void testMoveActiveCellPosition() {
        // fill 2D array with WALL and FLOOR at random location
        for (int x = 0; x < 80; x++) {
            for (int y = 0; y < 50; y++) {
                int tiletype = RANDOM.nextInt(3);
                switch(tiletype) {
                    case 0:
                        world[x][y] = Tileset.WALL;
                        break;
                    case 1:
                        world[x][y] = Tileset.FLOOR;
                        break;
                    case 2:
                        break;
                }
            }
        }
        map.moveActiveCellPosition(world);      // move active cell position
        int[] cellPosition = map.getCurrentPos();   // get active cell position
        assertTrue(map.isValidActiveCellPosition(world, cellPosition));
    }


    @Test
    public void testCellPositionGenerator() {
        int[] cellPosition = map.cellPositionGenerator();
        int colPos = cellPosition[0];
        int rowPos = cellPosition[1];
        assertTrue(colPos < 80 && colPos > 0);
        assertTrue(rowPos < 50 && rowPos > 0);

    }

    @Test
    public void testIsValidActiveCellPosition() {
        world[10][10] = Tileset.WALL;
        world[9][10] = Tileset.WALL;
        world[9][9] = Tileset.FLOOR;
        world[9][8] = Tileset.FLOOR;
        assertTrue(map.isValidActiveCellPosition(world, new int[]{9, 10}));
        assertFalse(map.isValidActiveCellPosition(world, new int[]{10, 10}));
    }

    @Test
    public void testHasWALL() {
        world[10][10] = Tileset.WALL;
        assertTrue(map.hasWALL(world, new int[]{10, 10}));
        assertFalse(map.hasWALL(world, new int[]{9, 9}));
    }

    @Test
    public void testFLOORNeighbour() {
        world[10][10] = Tileset.FLOOR;
        assertTrue(map.hasFLOORNeighbour(world, new int[]{11, 10}));
        assertFalse(map.hasFLOORNeighbour(world, new int[]{10, 10}));
        assertFalse(map.hasWALL(world, new int[]{9, 9}));
    }

}
