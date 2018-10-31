package byog.Test;


import byog.Core.Map;
import byog.Core.Room;
import org.junit.Test;
import static org.junit.Assert.*;
import byog.TileEngine.*;

public class TestRoom {

    Map map = new Map();
    TETile[][] world = map.initialize();
    int colPos = 10;
    int rowPos = 10;
    Room room = new Room();


    @Test
    public void testHasSpaceForRoom() {
        boolean actual = room.hasSpaceForRoom(world, new int[]{colPos, rowPos}, room.roomSize());
        assertTrue(actual);
        world[11][11] = Tileset.WALL;
        actual = room.hasSpaceForRoom(world, new int[]{colPos, rowPos}, room.roomSize());
        assertFalse(actual);
    }

    @Test
    public void testRoomSize() {
        int[] roomSize = room.roomSize();
        boolean actual = (roomSize[0] > 1 && roomSize[0] < 10 && roomSize[1] > 1 && roomSize[1] < 10);
        assertTrue(actual);

    }

    @Test
    public void testIsRoomWithinBound() {
        boolean actual = room.isRoomWithinBound(world, new int[] {65, 45}, new int[]{4, 4});
        assertTrue(actual);
        actual = room.isRoomWithinBound(world, new int[] {65, 45}, new int[] {10, 10});
        assertFalse(actual);

    }


}
