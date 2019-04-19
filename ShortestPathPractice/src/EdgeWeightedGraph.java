import java.util.*;

/**
 * Inspiration from : p608, Algorithm, 4th edition
 */


public class EdgeWeightedGraph {
    private Map<String, Vertex> vertices = new HashMap<>();      // List of k-v pairs, k is id, v is Vertex
    private Set<WeightedEdge> edges = new HashSet<>();           // list of weighted edges

    // return # of vertices in the map
    public int V() {
        //TODO
        return vertices.size();
    }

    // return # of edges in the map
    public int E() {
        //TODO
        return edges.size();
    }

    // return list of vertices' id
    public Set<String> verticesID() {
        return vertices.keySet();
    }

    // add the vertex to this graph
    public void addVertex(Vertex v) {
        vertices.put(v.getID(), v);
    }
    // add the edge to this graph
    public void addEdge(WeightedEdge e) {
        //TODO
        String v = e.either();
        String w = e.other(v);
        vertices.get(v).addEdge(e);
        vertices.get(w).addEdge(e);
        edges.add(e);
    }

    // return list of edges adjacent to v
    public Iterable<WeightedEdge> adj(String id){
        //TODO
        return vertices.get(id).getAdj();
    }

    // return list of all the edges in this graph
    public Iterable<WeightedEdge> edges() {
        //TODO
        return edges;
    }
}
