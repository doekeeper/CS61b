package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.lang.Math;

/**
 * Try to generate a world using existing library.
 */
public class CaveWorld {

    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private int SEED;
    private static final Random RANDOM = new Random();

    public CaveWorld(int seed) {
        this.SEED = seed;
    }

    public CaveWorld() {
        this.SEED = 0;
    }

    /* generate random Tileset - WALL or NOTHING */
    private TETile randomTileset() {
        switch (RANDOM.nextInt(2)) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.NOTHING;
        }
        return null;
    }

    /* random initialization */
    public TETile[][] randWorldInit (TETile[][] world) {
        for (int row = 0; row < HEIGHT; row ++) {
            for (int col = 0; col < WIDTH; col ++) {
                world[col][row] = randomTileset();
            }
        }
        return world;
    }

    /* cellular automatition - 4-5 rules*/
    public TETile[][] cellularAuto(TETile[][] world){
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (checkNeighbor(i, j, world)) {
                    world[j][i] = Tileset.WALL;
                } else {
                    world[j][i] = Tileset.NOTHING;
                }
            }
        }
        return world;
    }
    /*
    * @param: centre tile coordinate int row, int col
    * @param: TETile[][] world, the 2-D array world map (with randomly generated walls and emptiness)
    * return true is there are 5 or more WALLS (include the centre tile)
    * return false is there are 4 or less WALLS (include the centre tile)
    */
    private boolean checkNeighbor(int row, int col, TETile[][] world) {
        int count = 0; // WALL counter for neighbouring cells
        for (int i = -1; i < 2; i++) {
            int rowPos = Math.min(29, Math.max(0, row + i));
            for (int j = -1; j < 2; j++) {
                int colPos = Math.min(59, Math.max(0, col + j));
                if (world[colPos][rowPos] == Tileset.WALL) {
                    count++;
                }
            }
        }
        if (count >= 5) {
            return true;
        } else {
            return false;
        }
    }
}




    /*
    // world initialization - filled with emptiness
    private TETile[][] worldInit(TETile[][] world) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                world[col][row] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private void buildWall(TETile[][] world) {
        int row = 0;
        while (row < HEIGHT) {
            int START = RANDOM.nextInt(WIDTH);
            int END = Math.max(START, Math.min(WIDTH, RANDOM.nextInt(WIDTH)));
            for (int i = START; i < END; i++) {
                world[i][row] = Tileset.WALL;
            }
            row++;
        }
    }

    public void buildWorld(){

    }
    */
