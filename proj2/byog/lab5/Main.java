package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Main {

    public static void main(String [] args) {
        /** instantiate a TERenderer for generating space with pre-defined tiles selection */
        TERenderer ter = new TERenderer();
        // space dimension is WIDTH*HEIGHT
        ter.initialize(60, 30);

        // generate 2D array of TETile represent the world
        TETile[][] world = new TETile[60][30];
        // instantiate WorldGenPractice class for generating random world layout with rooms and hallways
        WorldGenerator wg = new WorldGenerator();
        // world = wg.worldInit(world);
        // intialize world;
        world = wg.generateRandomWorld(world);
        // display the world
        ter.renderFrame(world);

    }
}
