// out of bound exceptions - hot to handle exception well?

package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.lang.Math;

public class WorldGenerator {

    /**
     * Try to generate a world using existing library.
     */
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;
    private int SEED;
    private static final Random RANDOM = new Random();

    /* accept an integer as a seed for generating world
     * @param seed: the integer should be from player's input
     */
    public WorldGenerator(int seed) {
        this.SEED = seed;
    }

    // default seed is 0 if input is none
    public WorldGenerator() {
        this.SEED = 0;
    }

    public TETile[][] generateRandomWorld(TETile[][] world) {
        TETile[][] temp = worldInit(world);             // initialize world with NOTHING on each tile
        temp = addRooms(temp);                          // add rooms on the map
        // TETile[][] completeWorld = addHallways(temp);   // add hallway connecting rooms on the map
        return temp;
    }
    /* filled world with square room with random size, and location, number of rooms are determined by attempts
     * rooms shouldn't overlap each other
     * @param world - TETile[][] 2D array
     * @param attempts - try to
     */

    public TETile[][] addRooms(TETile[][] world) {
        int numberOfRooms = 10000;
        int roomWIDTHmin = 2;       // set room minimum width to 2
        int roomWIDTHmax = 12;       // set room maximum width to 8
        int roomHEIGHTmin = 2;      // set room minimum height to 2
        int roomHEIGHTmax = 8;      // set room maximum height to 6
        while (numberOfRooms > 0) {
            int rowPos = RANDOM.nextInt(30);
            int colPos = RANDOM.nextInt(60);
            int roomWIDTH = RANDOM.nextInt(roomWIDTHmax - roomWIDTHmin) + roomWIDTHmin;
            int roomHEIGHT = RANDOM.nextInt(roomHEIGHTmax - roomHEIGHTmin) + roomWIDTHmin;
            addRoom(world, rowPos, colPos, roomWIDTH, roomHEIGHT);
            numberOfRooms--;
        }
        return world;
    }

    /* add one room in the world with provided location, room width and height */
    public void addRoom(TETile[][] world, int row, int col, int roomWIDTH, int roomHEIGHT) {
        /* offset can affect the distribution of squares in the map
         * offset = -1 - rooms overlap
         * offset = 0 - it is possible that there is no space between two rooms
         * offset = 1 - rooms are isolated, at least 1 line between any two rooms*/
        int offset = 0;

        // location check - if proposed room is out of bound, don't add the room
        if (row - (1 + Math.abs(offset)) < 0 || row + roomHEIGHT + (1 + Math.abs(offset)) >= 30 || col - (1 + Math.abs(offset)) < 0 || col + roomWIDTH + (1 + Math.abs(offset)) >= 60) {
            return; // exit method if the room is out of bound
        }
        // space check - if world still has enough space for the new room created, add the room; discard the room otherwise
        if (isSpaceEnough(world, row - (1 + offset), col - (1 + offset), roomWIDTH + 2 * (1 + offset), roomHEIGHT + 2 * (1 + offset))) {
            // add room
            /* room is generated by the following steps:
             * 1. add a layer of WALLs with roomWIDTH + 2 and roomHEIGHT + 2, at starting point (row-1, col-1)
             * 2. add a layer of space (eg, NOTHING) with roomWIDTH and roomHEIGHT, at starting point(row, col)
             */
            addWALLS(world, row - 1, col - 1, roomWIDTH + 2, roomHEIGHT + 2);
            addSpace(world, row, col, roomWIDTH, roomHEIGHT, Tileset.FLOOR);
        }
    }

    public boolean isSpaceEnough(TETile[][] world, int row, int col, int roomWIDTH, int roomHEIGHT) {
        for (int i = row; i < row + roomHEIGHT; i++) {
            for (int j = col; j < col + roomWIDTH; j++) {
                if (world[j][i] == Tileset.WALL || world[j][i] == Tileset.FLOOR) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addWALLS(TETile[][] world, int row, int col, int wallWIDTH, int wallHEIGHT) {
        for (int i = row; i < row + wallHEIGHT; i++) {
            for (int j = col; j < col + wallWIDTH; j++) {
                world[j][i] = Tileset.WALL;
            }
        }
    }

    public void addSpace(TETile[][] world, int row, int col, int spaceWIDTH, int spaceHEIGHT, TETile TileSetType) {
        for (int i = row; i < row + spaceHEIGHT; i++) {
            for (int j = col; j < col + spaceWIDTH; j++) {
                world[j][i] = TileSetType;
            }
        }

    }

    public void addSpace(TETile[][] world, int row, int col, int spaceWIDTH, int spaceHEIGHT) {
        addSpace(world, row, col, spaceWIDTH, spaceHEIGHT, Tileset.NOTHING);
    }

    /* add hallways in the world to connect rooms
     */
    public TETile[][] addHallways(TETile[][] world) {
        return null;
    }

    /* random initialization */
    public TETile[][] worldInit(TETile[][] world) {
        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                world[col][row] = Tileset.NOTHING;
            }
        }
        return world;
    }
}
