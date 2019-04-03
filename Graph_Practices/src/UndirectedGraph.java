import java.util.LinkedList;

public class UndirectedGraph {
    private int V;      // number of vertices
    private int E;      // number of edges
    private LinkedList<Integer>[] adj;      // adjacency lists

    /**
     * constructor
     * create a v-vertex graph, with no edges
     * @param V
     */
    public UndirectedGraph(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];    // instantiate V-length LinkedList array that store Integer
        // create a new list for each vertex such that adjacent nodes can be stored
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    /**
     * constructor
     * read a graph from input stream in
     * @param in
     */
    public UndirectedGraph(In in) {
        this(in.readInt());         // Read V and construct this graph
        int E = in.readInt();       // Read E
        for (int i = 0; i < V; i++) {
            // add an edge
            int v = in.readInt();       //read a vertex
            int w = in.readInt();       //read another vertex
            addEdge(v, w);              // add edges connecting them
        }
    }

    /**
     * return number of vertices V in the graph
     * @return V
     */
    public int V() {
        return V;
    }

    /**
     * return number of edges E in the graph
     * @return E
     */
    public int E() {
        return E;
    }

    /**
     * add one edge that connects vertex v and w
     * @param v one vertex in the graph
     * @param w the other vertex in the graph
     */
    public void addEdge(int v, int w) {
        //TODO
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * @param v the vertex of interest
     * @return a list of vertices which are adjacent to vertex v
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * @return string representation
     * the format will be like:
     * "V vertices, E edges
     * 0: E1, E2,..., etc
     * 1: E1, E2,..., etc
     * V: ... etc
     */
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w: this.adj(V)) s += w + " ";
            s += "\n";
        }
        return s;
    }

    /**
     * @param G     Graph
     * @param v     vertex of interest
     * @return degree of v (number of edges, adjacent vertices)
     */
    public static int degree (UndirectedGraph G, int v) {
        return G.adj[v].size();
    }

    /**
     * @param G
     * @param
     * @return maximum degree in the graph
     */
    public static int maxDegree(UndirectedGraph G) {
        int maxDegree = 0;
        for (int v = 0; v < G.V(); v++) {
            if (degree(G, v) > maxDegree) maxDegree = degree(G, v);
        }
        return maxDegree;
    }

    /**
     * @param G
     * @return average degree in the graph
     */
    public static double avgDegree(UndirectedGraph G) {
        return 2*G.E()/G.V();
    }

    /**
     * @param G
     * @return number of self loops in the graph
     */
    public static int numberOfSelfLoops(UndirectedGraph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) count++;
            }
        }
        return count/2;
    }
}
