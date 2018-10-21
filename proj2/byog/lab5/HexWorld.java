package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.awt.Color;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 30;
    private static final int LENGTH = 30;

    /** fill hexagon pattern in world array given specific hexagon size and starting position
     * @param size
     * @param t
     * @param xPos
     * @param yPos
     * @param world
     */
    public void addHexagon(int size, TETile t, int xPos, int yPos, TETile[][] world) {
        for (int col = 0; col < size * 2; col++) {
            int x = xPos + xOffset(size, col);
            int y = yPos + col;
            int width = rowWidth(size, col);
            addRow(t, x, y, width, world);
        }

    }

    /** fill a row with specific Tileset and width at specific starting point in array world */
    private void addRow(TETile t, int xPos, int yPos, int width, TETile[][] world) {
        for (int i = xPos; i < xPos + width; i++) {
            world[i][yPos] = t;
        }
    }

    /** return width of a row */
    private int rowWidth(int size, int col) {
        if (col >= size) {
            return size * 5 - 2 * col - 2;
        }
        return size + 2 * col;
    }
    /* return starting xOffset relative to starting point */
    private int xOffset(int size, int col) {
        if(col >= size) {
            return -size * 2 + col + 1;
        }
        return -col;
    }

    /** filled background with floor Tileset */
    public static TETile[][] BGInit(TETile[][] world) {
        int height = world[0].length;
        int width = world.length;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = Tileset.FLOOR;
            }
        }
        return world;
    }

    /**
    public void addHexagon(int dim, int xOffset, int yOffset, TETile[][] world) {

        int xPos = xOffset;
        int yPos = yOffset;
        int colCounter = dim;
        int rowLength = dim;

        while(colCounter > 0) {
            for (int i = 0; i < rowLength; i++){
                world[xPos][yPos] = Tileset.PLAYER;
                xPos++;
            }
            xOffset--;
            xPos = xOffset;
            yPos++;
            colCounter--;
            rowLength += 2;
        }
        xOffset++;
        xPos = xOffset;
        colCounter = dim;
        rowLength -= 2;
        while(colCounter > 0) {
            for ( int i = 0; i < rowLength; i++) {
                world[xPos][yPos] = Tileset.PLAYER;
                xPos++;
            }
            xOffset++;
            xPos = xOffset;
            yPos++;
            colCounter--;
            rowLength -= 2;
        }
    }
     */

    public static void main(String[] args) {
        // instantiation of a TERenderer class
        TERenderer ter = new TERenderer();
        // initialize canvas size and RenderFrame location
        ter.initialize(WIDTH, LENGTH);
        // fill the background with floor TileSet
        TETile [][] world = new TETile[WIDTH][LENGTH];
        world = HexWorld.BGInit(world);
        // draw hexagon pattern on the world array
        HexWorld hex = new HexWorld();
        hex.addHexagon(3, Tileset.GRASS,4, 4, world);
        hex.addHexagon(3, Tileset.MOUNTAIN, 8, 10, world);
        hex.addHexagon(4, Tileset.FLOWER, 12, 14, world);
        hex.addHexagon(3, Tileset.RADIATION, 12, 4, world);
        ter.renderFrame(world);
    }
}
