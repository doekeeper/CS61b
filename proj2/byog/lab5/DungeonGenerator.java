package byog.lab5;

import byog.TileEngine.*;
import java.util.Random;

public class DungeonGenerator {
    int WIDTH = 60;                             // world's WIDTH, fixed value of 60
    int HEIGHT = 30;                            // world's HEIGHT, fixed value of 30
    TETile unvisited = Tileset.NOTHING;         // use Tileset.NOTHING to represent unvisited state
    TETile[][] world = new TETile[60][30];
    int[] currentLocation;
    Random RANDOM = new Random();               // instantiation of Random class
    String DIRECTION;



    /* initialization - set every tile in the 2D array to unvisited state
    * @Return world
    * */
    public TETile[][] markCellUnvisited(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = unvisited;
            }
        }
        return world;
    }

    /* pick a random cell within map to start; the picked cell has to be unvisited before
     * @return - int[] array with int rowPos, and int colPos
    */
    public int[] pickRandomCellAndMarkItVisited(TETile[][] world) {
        Random RANDOM = new Random();
        int rowPos = RANDOM.nextInt(HEIGHT);
        int colPos = RANDOM.nextInt(WIDTH);
        int[] pos = new int[] {rowPos, colPos};
        world[colPos][rowPos] = Tileset.WALL;
        return pos;
    }

    /*
     *
     */
    public TETile[][] generatorInstantiation() {
        world = markCellUnvisited(world);
        currentLocation = pickRandomCellAndMarkItVisited(world);
        return world;
    }

    /* determine if adjecent cell is within bounds
    * return true if yes
    */
    public boolean hasAdjecentCellInDirection(int[] currentLocation, String direction) {
        int rowPos = currentLocation[0];
        int colPos = currentLocation[1];
        // check that the current location falls within the bounds of the map
        if (rowPos < 0 || rowPos >= HEIGHT || colPos < 0 || colPos >= WIDTH) {
            return false;
        }

        // check if there is adjacent cell in the direction
        switch(direction) {
            case "NORTH": // move north
                return rowPos + 1 < HEIGHT;
            case "SOUTH": // move south
                return rowPos - 1 >= 0;
            case "WEST": // move west
                return colPos - 1 >= 0;
            case "EAST": // move east
                return colPos + 1 <WIDTH;
            default:
                return false;
        }
    }

    public String direction(){
        switch(RANDOM.nextInt(4)) {
            case 0:
                DIRECTION = "NORTH";
                break;
            case 1:
                DIRECTION = "SOUTH";
                break;
            case 2:
                DIRECTION = "WEST";
            case 3:
                DIRECTION = "EAST";
            default:
                return null;
        }
        return DIRECTION;
    }

    public boolean IsAdjacentCellInDirectionVisited(int[] currentLocation, String direction) {
        int rowPos = currentLocation[0];
        int colPos = currentLocation[1];
        switch(direction) {
            case "NORTH":
                return world[colPos][rowPos + 1] != Tileset.NOTHING;
            case "SOUTH":
                return world[colPos][rowPos - 1] != Tileset.NOTHING;
            case "WEST":
                return world[colPos - 1][rowPos] != Tileset.NOTHING;
            case "EAST":
                return world[colPos + 1][rowPos] != Tileset.NOTHING;
            default:
                return false;
        }
    }
}
