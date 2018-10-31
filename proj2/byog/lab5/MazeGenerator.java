package byog.lab5;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.TileEngine.TERenderer;

import java.util.Random;
import java.lang.Math;

public class MazeGenerator {
    private int HEIGHT = 30;
    private int WIDTH = 60;
    private TETile[][] world = new TETile[WIDTH][HEIGHT]; // instantiate an 2D array of TETile
    private Random RANDOM = new Random();
    private TETile hallway = Tileset.WALL;

    public MazeGenerator() {
        init(world);
    }

    /* Initialization - set all tile to Tileset.NOTHING */
    public void init(TETile[][] world) {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                world[i][j] = Tileset.NOTHING;
            }
        }
    }

    public TETile[][] mazeGenerator(TETile[][] world) {
        /*
        * 1. intialization - filled the whole map with TETile.NOTHING;
        * 2. Randomly pick a starting point - has to be a grid with Tileset.NOTHING, and then mark it with Tileset.FLOOR
        * 3. pick a random direction, and make sure the adjecent cell on that direction is valid and filled with Tileset.NOTHING
        * 4. If all 4 directions are invalid, restart from step 2
        * 5. create a corrider
         */
        // randomize start location;

        int moves = 100;// 1st trial, set moves to 100
        int[] currentPos = randomLocation(world);
        int rowPos = currentPos[0];
        int colPos = currentPos[1];
        world[colPos][rowPos] = hallway;    // set current cell to Tileset.

        while (moves > 0) {
            // move to next available cell
            int[] nextMove = nextMove(currentPos);                      // next Move to be checked
            int count = 16;
            while (!isNextMoveValid(nextMove, world) & count > 0) {     // if next Move is not valid, select another direction
                nextMove = nextMove(currentPos);
                count --;                                               // only allow 16 attempts, otherwise restart with another start point
            }
            if (count > 0) {
                currentPos = nextMove;
                rowPos = currentPos[0];
                colPos = currentPos[1];
                world[colPos][rowPos] = hallway;
            } else {
                currentPos = randomLocation(world);
                rowPos = currentPos[0];
                colPos = currentPos[1];
                world[colPos][rowPos] = hallway;
            }
            moves --;
        }
        return world;
    }

    /* randomly select starting position on a grid with Tileset.NOTHING */
    public int[] randomLocation(TETile[][] world) {
        int[] pos = new int [2];        // int array to save position int[0] is rowPos; int[1] is colPos;
        int rowPos = RANDOM.nextInt(30);
        int colPos = RANDOM.nextInt(60);
        pos[0] = rowPos;
        pos[1] = colPos;
        int attempts = 10;
        while (world[colPos][rowPos] != Tileset.NOTHING || !isNeighcourEmpty(pos, world)){ // if current selected cell is filled, randomly select another cell
            rowPos = RANDOM.nextInt(30);
            colPos = RANDOM.nextInt(60);
            attempts--;
        }
        pos[0] = rowPos;
        pos[1] = colPos;
        return pos;
    }

    public int[] nextMove(int[] pos) {
        int nextRowPos = pos[0];
        int nextColPos = pos[1];
        switch(RANDOM.nextInt(4)) {
            case 0:
                nextRowPos--;   // move down by one step
                break;
            case 1:
                nextRowPos++;   // move up by one step
                break;
            case 2:
                nextColPos--;   // move left by one step
                break;
            case 3:
                nextColPos++;   // move right by one step
                break;
        }
        int[] nextPos = {nextRowPos, nextColPos};
        return nextPos;
    }
    public boolean isNextMoveValid(int[] pos, TETile[][] world) {
        int rowPos = pos[0];
        int colPos = pos[1];
        if(rowPos < 0 || rowPos >= HEIGHT || colPos < 0 || colPos >=HEIGHT || !isNeighcourEmpty(pos, world)){
            return false;
        }
        else return true;
    }

    public boolean isNeighcourEmpty(int[] pos, TETile[][] world) {
        int rowPos = pos[0];
        int colPos = pos[1];
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                rowPos = Math.max(0, Math.min(rowPos + i, HEIGHT - 1));
                colPos = Math.max(0, Math.min(colPos + j, WIDTH - 1));
                if (!(i == 0 && j == 0) && world[colPos][rowPos] != Tileset.NOTHING) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MazeGenerator mg = new MazeGenerator();
        TERenderer ter = new TERenderer();
        ter.initialize(mg.WIDTH, mg.HEIGHT);
        TETile[][] maze = mg.mazeGenerator(mg.world);
        ter.renderFrame(maze);
    }
}
