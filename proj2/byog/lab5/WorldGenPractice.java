package byog.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.lang.Math;

/**
 * Try to generate a world using existing library.
 */
public class WorldGenPractice {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private int SEED;
    private static final Random RANDOM = new Random();

    public WorldGenPractice(int seed) {
        this.SEED = seed;
    }

    public WorldGenPractice() {
        this.SEED = 0;
    }

    /** world initialization - filled with emptiness */
    public TETile[][] worldInit(TETile[][] world) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                world[col][row] = Tileset.NOTHING;
            }
        }
        return world;
    }
    public void buildWall(TETile[][] world) {
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
}
