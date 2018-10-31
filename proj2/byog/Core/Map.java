package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/* class Map contains fields and methods for generating an 2D-array dungeon map.
 */
public class Map {

    private TETile[][] map;
    private static final int WIDTH = 80;                // width of the map     (col)
    private static final int HEIGHT = 50;               // height of the map    (row)
    private static final int HALLWAYINDEX = 1000;       // indicate the complexity of HALLWAY, hallway is more complex with higher index
    private static final int ROOMINDEX = 1000;          // indicate the complexity of ROOM, room is more complex with higher index
    private int colPos;
    private int rowPos;

    /* fill the map with Tileset.NOTHING
     * @return 2D map which is filled with Tileset.NOTHING
     */
    public TETile[][] initialize() {
        TETile[][] map = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                map[x][y] = Tileset.NOTHING;
            }
        }
        return map;
    }
    /* set current position as input */
    public void setCurrentPos(int colPos, int rowPos) {
        setColPos(colPos);
        setRowPos(rowPos);
    }
    /* set current position by direction that moves toward */
    public void updateCurrentPos(String direction) {
        int[] currentPosition = getCurrentPos();
        colPos = currentPosition[0];
        rowPos = currentPosition[1];
        switch(direction) {
            case "EAST":
                setColPos(colPos + 1);
                break;
            case "WEST":
                setColPos(colPos - 1);
            case "NORTH":
                setRowPos(rowPos + 1);
            case "SOUTH":
                setRowPos(rowPos - 1);
        }
    }

    /* move active cell position to a new location (when current active cell position has no available direction to move further)
     * new active cell position has to meet the following requirements:
     * The tile on the new cell position has to be WALL (will change to FLOOR once it is selected)
     * the new cell position must have at least one neighbour with FLOOR ( in its E, W, S, N direction)
     * once the new cell position is selected, set it to FLOOR
     */
    public void moveActiveCellPosition(TETile[][] world) {
        int[] newActiveCellPosition = cellPositionGenerator();
        while(!isValidActiveCellPosition(world, newActiveCellPosition)) {
            newActiveCellPosition = cellPositionGenerator();
        }
        setCurrentPos(newActiveCellPosition[0],newActiveCellPosition[1]);
    }

    /* generate random cell position within the map's bound (edge not included)
    * @return: int[], cell position
    */
    public int[] cellPositionGenerator() {
        Random RANDOM = new Random();
        int colPos = RANDOM.nextInt(WIDTH-2) + 1;       // first and last column excluded
        int rowPos = RANDOM.nextInt(HEIGHT - 2) + 1;    // first and last row excluded
        return new int[] {colPos, rowPos};
    }

    /* check if cell Position is meeting the following requirement:
     * 1. cellPosition has WALL as tile
     * 2. cellPosition has at least one neighbour with FLOOR (N, S, E, W direction only)
     * @param: TETile[][] world
     * @param: int[] cellPosition
     * @return: boolean value
     */
    public boolean isValidActiveCellPosition(TETile[][] world, int[] cellPosition) {
        if(hasWALL(world, cellPosition) && hasFLOORNeighbour(world, cellPosition)) {
            return true;
        } else {
            return false;
        }
    }

    /* check if selected cell position has WALL tile */
    public boolean hasWALL(TETile[][] world, int[] cellPosition) {
        colPos = cellPosition[0];
        rowPos = cellPosition[1];
        return world[colPos][rowPos].equals(Tileset.WALL);
    }

    /* check if selected cell position has FLOOR neighbours */
    public boolean hasFLOORNeighbour(TETile[][] world, int[] cellPosition) {
        colPos = cellPosition[0];
        rowPos = cellPosition[1];
        if (world[colPos-1][rowPos].equals(Tileset.FLOOR)) {
            return true;
        } else if (world[colPos+1][rowPos].equals(Tileset.FLOOR)) {
            return true;
        } else if (world[colPos][rowPos-1].equals(Tileset.FLOOR)) {
            return true;
        } else if (world[colPos][rowPos+1].equals(Tileset.FLOOR)) {
            return true;
        } else {
            return false;
        }
    }
    /* currently there is a bug-ish issue after building hallways - some FLOOR tile has neighbour of NOTHING (E, W, S, N direction)
     * root cause is unclear
     * as temporary fix, after hallway build, scan the whole map to find the FLOOR tile with NOTHING neighbour, can replace it with WALL
     */
    public void wallPatch(TETile[][] world) {
        for(int x = 1; x < world.length - 1; x++) {
            for (int y = 1; y < world[0].length - 1; y++) {
                if (world[x][y].equals(Tileset.FLOOR) && hasNOTHINGNeighbour(world, new int[]{x, y})) {
                    int[] cellPosition = NOTHINGNeighbourPosition(world, new int[]{x, y});
                    int colPos = cellPosition[0];
                    int rowPos = cellPosition[1];
                    world[colPos][rowPos] = Tileset.WALL;
                }
            }
        }
    }
    /* check if currentPosition has any neighbour (E, W, S, N direction) with NOTHING tile */
    private boolean hasNOTHINGNeighbour(TETile[][] world, int[] currentPosition) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];
        if (world[colPos-1][rowPos] == Tileset.NOTHING) {
            return true;
        } else if (world[colPos+1][rowPos] == Tileset.NOTHING) {
            return true;
        } else if (world[colPos][rowPos-1] == Tileset.NOTHING) {
            return true;
        } else if (world[colPos][rowPos+1] == Tileset.NOTHING) {
            return true;
        } else {
            return false;
        }
    }
    private int[] NOTHINGNeighbourPosition(TETile[][] world, int[] currentPosition) {
        int colPos = currentPosition[0];
        int rowPos = currentPosition[1];
        if (world[colPos - 1][rowPos] == Tileset.NOTHING) {
            return new int[]{colPos - 1, rowPos};
        } else if (world[colPos + 1][rowPos] == Tileset.NOTHING) {
            return new int[]{colPos + 1, rowPos};
        } else if (world[colPos][rowPos - 1] == Tileset.NOTHING) {
            return new int[]{colPos, rowPos - 1};
        } else if (world[colPos][rowPos + 1] == Tileset.NOTHING) {
            return new int[]{colPos, rowPos - 1};
        }
        return null;
    }

    /* Move active cell to a random tile with WALL, this is a required step as part of room generation process
     */
    public void moveActiveCellToWALL(TETile[][] world) {
        int[] newActiveCellPosition = cellPositionGenerator();
        colPos = newActiveCellPosition[0];
        rowPos = newActiveCellPosition[1];
        while(world[colPos][rowPos] != Tileset.WALL || !hasFLOORNeighbour(world, newActiveCellPosition)) {
            newActiveCellPosition = cellPositionGenerator();
            colPos = newActiveCellPosition[0];
            rowPos = newActiveCellPosition[1];
        }
    }


    /* build a start point as follows:
     * set currentPosition (active cell position) to the centre of the map
     * build 3 * 3 walls around current position
     * set current position to FLOOR
     */
    public void initialStartPoint(TETile[][] world) {
        setCurrentPos(WIDTH/2, HEIGHT/2);   // set active cell position at the center of map
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                world[WIDTH/2 - i][HEIGHT/2 - j] = Tileset.WALL;
            }
        }
        world[WIDTH/2][HEIGHT/2] = Tileset.FLOOR;            // debug, set starting point as FLOWER
    }
    /* generate random hallways */
    public void generateHallways(TETile[][] world) {
        Hallway hallway = new Hallway();
        int count = HALLWAYINDEX;
        int failToPickNewDirection = 0;
        while (count > 0) {
            int[] currentPos = getCurrentPos();                                 // get current cell position
            String direction = DirectionPicker.pickRandomDirection();           // get direction of potential next move
            if (hallway.isWithinBounds(world, currentPos, direction) && hallway.hasSpaceForHallway(world, currentPos, direction)) {
                hallway.addHallway(world, currentPos, direction);          // if there is available space for new hallway, build the hallway and update the cell location to new position
                world[currentPos[0]][currentPos[1]] = Tileset.FLOOR;       // if it is valid move, set current tile to FLOOR
                updateCurrentPos(direction);
            } else {
                failToPickNewDirection++;                                       // if proposed direction failes, keep record of failure times
                if (failToPickNewDirection > 64) {                               // if fail to pick new direction by 8 times, assume there is no available direction for current cell
                    failToPickNewDirection = 0;                                 // then move active cell position to a new location to
                    moveActiveCellPosition(world);                                     // new cell position has to be currently a wall, and at least one of its neighhour (N, S, E, W) must be FLOOR
                }
            }
            count--;
        }
        wallPatch(world);
    }
    /* generate random rooms */
    public void generateRooms(TETile[][] world) {
        Room room = new Room();
        int count = ROOMINDEX;
        while (count > 0) {
            moveActiveCellToWALL(world);
            room.addRoom(world, getCurrentPos(), room.roomSize());
            count--;
        }
    }
    /* generator random dungeon map based on a seed
     * same map should be recreated with same seed
     */
    public TETile[][] generateDungeonMap() {
        TETile[][] dungeonMap = initialize();               // create a 2D array map with WIDTH(80) * HEIGHT(50) and fill the array with Tileset.NOTHING
        initialStartPoint(dungeonMap);                        // set active cell to the centre of map, set it to FLOOR and set a layer of WALL around it.
        generateHallways(dungeonMap);
        generateRooms(dungeonMap);
        return dungeonMap;
    }


    /* getters and setters */
    public int[] getCurrentPos() {
        return new int[]{getColPos(), getRowPos()};
    }
    public int getColPos() {
        return this.colPos;
    }
    public int getRowPos(){
        return this.rowPos;
    }
    public void setColPos(int colPos) {
        this.colPos = colPos;
    }
    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }
    public int getWIDTH() {
        return WIDTH;
    }
    public int getHEIGHT(){
        return HEIGHT;
    }

}
