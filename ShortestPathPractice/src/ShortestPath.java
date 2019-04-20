// inspiration from: https://www.codingame.com/playgrounds/1608/shortest-paths-with-dijkstras-algorithm/dijkstras-algorithm

import java.util.*;

/**
 * Create a weighted graph with 5 vertices and 7 weighted edges,
 * then find the shortest path tree using Dijkstra's algorithm,
 * and then create a method to return shortest path between start node (node c in this case)
 * and the rest of the nodes in the graph
 */


public class ShortestPath {

    String startVertexID;      // start vertex is C
    String currentVertexID;
    Set<String> visited = new HashSet<>();       // set of visited vertices
    Map<String, Integer> distanceTo = new HashMap<>();  // Map for storing distanceTo all Vertex in the map
    Set<WeightedEdge> SPT = new HashSet<>();        // set of all the edges in the shortest path tree
    EdgeWeightedGraph g;

    public ShortestPath(EdgeWeightedGraph g, String startID) {
        this.g = g;
        this.startVertexID = startID;
    }

    public void shortestPath() {
        currentVertexID = startVertexID;
        // initialize the distanceTo; distance = 0 for startVertex, distance = Integer.MAX_VALUE for the rest of vertices
        for (String id: g.verticesID()) {
            if (id.equals(startVertexID)) distanceTo.put(id, 0);
            else distanceTo.put(id, Integer.MAX_VALUE);
        }
        visited.add(currentVertexID);       // marked current vertex as visited

        Queue<String> nextVertex = new PriorityQueue<>();
        // set non-visited node with the smallest current distance as the current node
        while (!allVerticesVisited()) {     // continue if there is still unvisited vertex
            // iterate through all the adjacent edges of current vertex, update the distance of nearby vertex
            for (WeightedEdge e : g.adj(currentVertexID)) {
                String adjVertexID = e.other(currentVertexID);  // adjacent vertex id
                if(e.getLength() + distanceTo.get(currentVertexID) < distanceTo.get(adjVertexID)) {
                    distanceTo.put(adjVertexID, e.getLength() + distanceTo.get(currentVertexID));
                }
                if(! visited.contains(e.other(currentVertexID))) nextVertex.add(e.other(currentVertexID));
            }
            visited.add(currentVertexID);
            currentVertexID = nextVertex.remove();
        }
    }

    private boolean allVerticesVisited() {
        return visited.size() == g.verticesID().size();
    }

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

        ShortestPath sp = new ShortestPath(g, "C");
        sp.shortestPath();
        /**
        for (WeightedEdge e: sp.shortestPath()) {
             System.out.println(e.toString());
        }
         */
    }

}
