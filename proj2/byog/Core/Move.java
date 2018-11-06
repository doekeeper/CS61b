package byog.Core;

import byog.TileEngine.*;


/* this class include all relevant methods for moving the object */
public class Move {

    /* determine if the object can move to certain direction - return false if there is obstacle, such as WALL, or other unmovable object */
    public boolean canMove(String direction, int[] currentPosition, TETile[][] map) {
        return false;
    }

    public void move(String direction, int[] currentPosition, TETile[][] map) {
    }
}
