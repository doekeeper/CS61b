// further optimization - build hallway and room back and forth ( 10 attempts of hallway build, then one attempts for room build)
// currentPosition intilization - should be FLOOR enclosed by WALL
// move to project - part 2

package byog.lab5;

import byog.Core.Map;
import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Main {

    public static void main(String [] args) {
        Map map = new Map();
        TETile[][] dungeonMap = map.generateDungeonMap();

        TERenderer ter = new TERenderer();
        ter.initialize(dungeonMap.length, dungeonMap[0].length);
        ter.renderFrame(dungeonMap);
    }
}
