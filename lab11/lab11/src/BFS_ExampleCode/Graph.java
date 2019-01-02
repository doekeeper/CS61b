/**
 * The Graph class represents an undirected graph of vertices named 0 through V-1.
 * It supports the following two primary operations:
 * 1. add an edge to the graph,
 * 2. iterate over all of the vertices adjacent to a vertex.
 * It also provides methods for returning the number of vertices V and the number of
 * edges E. Parallel edges and self-loops are permitted.
 * By convention, a self-loop v-v appears in the adjacency list of v twice and contributes
 * two to the degree of v.
 * This implementation uses an adjacency-lists representation, which is a vertex-indexed
 * array of Bag objects.
 * All operations take constant time (in the worst case) except iterating over the vertices
 * adjacent to a given vertex, which takes time proportional to the number of such vertices.
 */

package BFS_ExampleCode;

import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator"); // get system-dependent line separator (windows, linux, MacOS are different)
    private final int V;        // # of vertices in the graph
    private int E;              // # of edges
    private Bag<Integer>[] adj; // Array of Bag<Integer> which is used for storing adjacent vertices

    /**
     * Initializes an empty graph with V vertices and 0 edges
     * @param V     the number of vertices
     * @throws IllegalArgumentException if V < 0
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a graph from the specified input stream.
     * The format is the number of vertices V.
     * followed by the number of edges E.
     * followed by E pairs of vertices, with each entry separated by whitespace
     * @param in        the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not in the prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges in negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public Graph(In in) {
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("number of edges in a Graph must be non-negative");
            for (int i = 0; i < E; i++){
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }
        catch(NoSuchElementException e){
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    /**
     * Initializes a new graph that is a deep copy of G.
     * @param G     the graph to copy
     */
    public Graph(Graph G) {
        this(G.V());        // constructor overload - since G.V() returns int V, Graph(int V) will be called for construction
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            // reverse so that adjacency list is the same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in this graph
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    /**
     * throw an IllegalArgumentException unless 0 <= v < V
     * @param v the number of vertices in this graph
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph
     * @param v     one vertex in the edge
     * @param w     the other vertex in this edge
     * @throws IllegalArgumentException unless both 0 <=v < V and 0 <= w < V
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Returns the vertices adjacent to vertex v
     * @param v     the vertices
     * @return the vertices adjacent to vertex v, as an iterable
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex v
     * @param v     the vertex
     * @return  the degree of vertex v
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns a string representation of this graph.
     * @return      the number vertices V, followed by the number of edges E, followed by the V adjacency lists.
     */
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V + "vertices, " + E + " edges" + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w: adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

}
