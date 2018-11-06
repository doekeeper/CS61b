package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Hallway implements Serializable {

    /* add one section of hallway in the world toward the given direction
     * @param int[] cellPosition
     * @param String direction
     */
    public void addHallway(TETile[][] map, int[] currentPosition, String direction) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];
        switch (direction) {
            case "NORTH":
                map[colPos][rowPos + 1] = Tileset.FLOOR;
                map[colPos - 1][rowPos + 1] = Tileset.WALL;
                map[colPos + 1][rowPos + 1] = Tileset.WALL;
                map[colPos - 1][rowPos + 2] = Tileset.WALL;
                map[colPos][rowPos + 2] = Tileset.WALL;
                map[colPos + 1][rowPos + 2] = Tileset.WALL;
                break;
            case "SOUTH":
                map[colPos][rowPos - 1] = Tileset.FLOOR;
                map[colPos - 1][rowPos - 1] = Tileset.WALL;
                map[colPos + 1][rowPos - 1] = Tileset.WALL;
                map[colPos - 1][rowPos - 2] = Tileset.WALL;
                map[colPos][rowPos - 2] = Tileset.WALL;
                map[colPos + 1][rowPos - 2] = Tileset.WALL;
                break;
            case "WEST":
                map[colPos - 1][rowPos] = Tileset.FLOOR;
                map[colPos - 1][rowPos - 1] = Tileset.WALL;
                map[colPos - 1][rowPos + 1] = Tileset.WALL;
                map[colPos - 2][rowPos - 1] = Tileset.WALL;
                map[colPos - 2][rowPos] = Tileset.WALL;
                map[colPos - 2][rowPos + 1] = Tileset.WALL;
                break;
            case "EAST":
                map[colPos + 1][rowPos] = Tileset.FLOOR;
                map[colPos + 1][rowPos - 1] = Tileset.WALL;
                map[colPos + 1][rowPos + 1] = Tileset.WALL;
                map[colPos + 2][rowPos + 1] = Tileset.WALL;
                map[colPos + 2][rowPos] = Tileset.WALL;
                map[colPos + 2][rowPos - 1] = Tileset.WALL;
                break;
        }
    }

    /* determine if it is valid to add hallway based on currentPosition and direction to move
     *  @param currentPosition, int[]
     *  @param direction to move, String
     */
    public boolean hasSpaceForHallway(TETile[][] map, int[] currentPosition, String direction) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];

        switch (direction) {
            case "EAST":
                for (int y = -1; y < 2; y++) {
                    if (map[colPos + 2][rowPos + y] != Tileset.NOTHING) {
                        return false;
                    }
                }
                return true;
            case "WEST":
                for (int y = -1; y < 2; y++) {
                    if (map[colPos - 2][rowPos + y] != Tileset.NOTHING) {
                        return false;
                    }
                }
                return true;
            case "NORTH":
                for (int x = -1; x < 2; x++) {
                    if (map[colPos + x][rowPos + 2] != Tileset.NOTHING) {
                        return false;
                    }
                }
                return true;
            case "SOUTH":
                for (int x = -1; x < 2; x++) {
                    if (map[colPos + x][rowPos - 2] != Tileset.NOTHING) {
                        return false;
                    }
                }
                return true;
            default:
                return true;
        }
    }

    /* determine if it is valid to add hallway based on currentPosition and direction to move
     *  @param currentPosition, int[]
     *  @param direction to move, String
     */
    public boolean isWithinBounds(TETile[][] map, int[] currentPosition, String direction) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];

        switch (direction) {
            case "EAST":
                if (colPos + 2 >= map.length || rowPos - 1 < 0 || rowPos + 1 >= map[0].length) {
                    return false;
                } else return true;

            case "WEST":
                if (colPos - 2 < 0 || rowPos - 1 < 0 || rowPos + 1 >= map[0].length) {
                    return false;
                } else return true;

            case "NORTH":
                if (rowPos + 2 >= map[0].length || colPos - 1 < 0 || colPos + 1 >= map.length) {
                    return false;
                } else return true;

            case "SOUTH":
                if (rowPos - 2 < 0 || colPos - 1 < 0 || colPos + 1 >= map.length) {
                    return false;
                } else return true;
            default:
                return false;
        }

    }


}
