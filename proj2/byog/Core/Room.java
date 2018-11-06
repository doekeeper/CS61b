package byog.Core;

import byog.TileEngine.*;

import java.io.Serializable;
import java.util.Random;


public class Room implements Serializable {
    private int xDim;
    private int yDim;


    /* check if there is enough space to add a room */
    public boolean hasSpaceForRoom(TETile[][] world, int[] currentPosition, int[] roomSize){
        int xPos = currentPosition[0];
        int yPos = currentPosition[1] + 1;
        xDim = roomSize[0];
        yDim = roomSize[1];

        for (int x = xPos - xDim/2; x < xPos + xDim/2; x++ ) {
            for (int y = yPos; y < yPos + yDim; y++) {
                if (world[x][y] !=Tileset.NOTHING ){
                    return false;
                }
            }
        }
        return true;
    }

    /* check if the room is within bound */
    public boolean isRoomWithinBound(TETile[][] world, int[] currentPosition, int[] roomSize) {
        if (currentPosition[0] + roomSize[0]/2 < world.length && currentPosition[1] + roomSize[1] < world[0].length) {
            if (currentPosition[0] - roomSize[0]/2 >= 0) {
                return true;
            }
        }
        return false;
    }
    /* add Room in the given position with given size */
    public void addRoom(TETile[][] world, int[] currentPosition, int[] roomSize) {
        if (isRoomWithinBound(world, currentPosition, roomSize)) {
            if (hasSpaceForRoom(world, currentPosition, roomSize)) {
                addWalls(world, currentPosition, roomSize);
                addFloors(world, currentPosition, roomSize);
                connectRoomAndHallway(world, currentPosition);
            }
        }
    }
    /* fill rooms with WALLs with roomSize
    * @param startPosition is position to start drawing rooms, startPosition should be one row above currentPosition
    */
    public void addWalls(TETile[][] world, int[] currentPosition, int[] roomSize) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1] + 1;
        xDim = roomSize[0];
        yDim = roomSize[1];
        for (int x = colPos - xDim/2; x < colPos + xDim/2; x++) {
            for (int y = rowPos; y < rowPos + yDim; y++) {
                world[x][y] = Tileset.WALL;
            }
        }
    }
    /* fill room with FLOOR with roomSize-1 */
    public void addFloors(TETile[][] world, int[] currentPosition, int[] roomSize) {
        int colPos = currentPosition[0] + 1;
        int rowPos = currentPosition[1] + 2;
        xDim = roomSize[0] - 2;
        yDim = roomSize[1] - 2;
        for (int x = colPos - xDim/2 - 1; x < colPos + xDim/2 - 1; x++) {
            for (int y = rowPos; y < rowPos + yDim; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }
    /* connect room and hallways */
    public void connectRoomAndHallway(TETile[][] world, int[] currentPosition) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];
        world[colPos][rowPos] = Tileset.FLOOR;
        world[colPos][rowPos + 1] = Tileset.FLOOR;
    }

    /* random generate a room size {xDim, yDim}
     * room size has to be equal or larger than 2 * 2, and smaller than 10 * 10
     */
    public int[] roomSize() {
        Random RANDOM = new Random();
        xDim = RANDOM.nextInt(8) + 4;
        yDim = RANDOM.nextInt(8) + 4;
        return new int[]{xDim, yDim};
    }
}
