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


    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph();

        // create 5 vertices
        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");

        // add 5 vertices in the graph
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);

        // create 7 edges
        WeightedEdge AC = new WeightedEdge("A", "C", 1);
        WeightedEdge AB = new WeightedEdge("A", "B", 3);
        WeightedEdge BC = new WeightedEdge("B", "C", 7);
        WeightedEdge BD = new WeightedEdge("B", "D", 5);
        WeightedEdge BE = new WeightedEdge("B", "E", 1);
        WeightedEdge CD = new WeightedEdge("C", "D", 2);
        WeightedEdge DE = new WeightedEdge("D", "E", 7);

        // add 7 edges in the graph
        g.addEdge(AC);
        g.addEdge(AB);
        g.addEdge(BC);
        g.addEdge(BD);
        g.addEdge(BE);
        g.addEdge(CD);
        g.addEdge(DE);

        // Graph with 5 vertices and 7 edges should be constructed by now

        // the next step is to find the shortest path

    }

}
