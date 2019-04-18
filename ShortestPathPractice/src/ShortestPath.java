// inspiration from: https://www.codingame.com/playgrounds/1608/shortest-paths-with-dijkstras-algorithm/dijkstras-algorithm

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Create a weighted graph with 5 vertices and 7 weighted edges,
 * then find the shortest path tree using Dijkstra's algorithm,
 * and then create a method to return shortest path between start node (node c in this case)
 * and the rest of the nodes in the graph
 */


public class ShortestPath {

    Map<String, Vertex> g = new HashMap<>();


    /**
     * object which contains information of a vertex
     */
    static class Vertex {
        String id;      // store id
        private Map<Vertex, Integer> adj = new HashMap<>();      // store adjacent nodes and the corresponding weight

        // constructor
        public Vertex(String id) {
            this.id = id;
        }

        /**
         *
         * @return
         */
        public Iterable<Vertex, Integer> getAdj() {

        }
    }

}
