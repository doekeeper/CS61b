package byog.lab5;

import byog.lab5.DungeonGenerator;
import byog.TileEngine.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDungeonGenerator {

    @Test
    public void testMarkCellUnvisited() {
        DungeonGenerator dg = new DungeonGenerator();
        TETile[][] world = dg.markCellUnvisited(dg.world);
        TETile[][] expected = new TETile[dg.WIDTH][dg.HEIGHT];
        for (int i = 0; i < dg.WIDTH; i++) {
            for (int j = 0; j < dg.HEIGHT; j++) {
                expected[i][j] = Tileset.NOTHING;
            }
        }
        assertArrayEquals(expected, world);
    }

    @Test
    public void testPickRandomCellAndMarkItVisited() {
        DungeonGenerator dg = new DungeonGenerator();
        TETile[][] world = dg.markCellUnvisited(dg.world);
        int[] pos = dg.pickRandomCellAndMarkItVisited(dg.world);
        int rowPos = pos[0];
        int colPos = pos[1];
        TETile expected = Tileset.WALL;
        assertEquals(expected, world[colPos][rowPos]);
    }

    @Test
    public void TestDungeonGenerator() {
        DungeonGenerator dg = new DungeonGenerator();
        TETile[][] world = dg.generatorInstantiation();

        int visitedCellCount = 0;

        for (int i = 0; i < dg.WIDTH; i++) {
            for(int j = 0; j < dg.HEIGHT; j++) {
                if (world[i][j] == Tileset.WALL) {
                    visitedCellCount++;
                }
            }
        }
        assertEquals(1, visitedCellCount);
    }

    @Test
    public void TestMaphasAdjacentCellInDirection() {
        DungeonGenerator dg = new DungeonGenerator();
        TETile[][] world = dg.markCellUnvisited(dg.world);

    }

}
