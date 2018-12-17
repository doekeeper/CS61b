package byog.Core;

import byog.TileEngine.*;


/* this class include all relevant methods for moving the object */
public class Move {

    /* determine if the object can move to certain direction - return false if there is obstacle, such as WALL, or other unmovable object */
    public boolean canMove(String direction, int[] currentPosition, TETile[][] map) {
        int[] nextMove = nextMove(direction, currentPosition);
        int xPos = nextMove[0];
        int yPos = nextMove[1];

        if (map[xPos][yPos] == Tileset.FLOOR) {
            return true;
        } else {
            return false;
        }
    }

    /* return the new position after the move */
    public int[] move(String direction, int[] currentPosition) {
        int [] nextMove = nextMove(direction, currentPosition);
        return nextMove;
    }

    /* determine the location of next move based on current position and direction of next move */
    public int[] nextMove(String direction, int[] currentPosition) {
        int xPos = currentPosition[0];
        int yPos = currentPosition[1];

        switch (direction) {
            case "NORTH":
                yPos++;
                break;
            case "SOUTH":
                yPos--;
                break;
            case "EAST":
                xPos++;
                break;
            case "WEST":
                xPos--;
                break;
        }
        int[] nextMove = new int[]{xPos, yPos};
        return nextMove;
    }
}
