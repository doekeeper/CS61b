/**
 * The DepthFirstPaths class represents a data type for finding paths
 * from a source vertex s to every other vertex in an undirected graph.
 * This implementation usese depth-first search.
 * The constructor takes time proportional to V+E,
 * where V is the number of vertices and E is the number of edges.
 * Each call to hasPathTo(int) takes constant time (why constant time?).
 * Each call to pathTo(int) takes proportional to the length of the path.
 * It uses extra space (not including the graph) proportional to V.
 * Reference: Algorithm, 4th edition by R. Sedgewick & K. Wayne.
 */

public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there a an s-v path?
    private int[] edgeTo;       // edgeTo[v] = last edge on s-v graph
    private final int s;        // source vertex; final variable has to initialized during its declaration

    /**
     * Computes a path between s and every other vertex in graph G.
     * @param G     the graph
     * @param s     the source vertex
     * @ throws IllegalArgumentException unless(0 <= s < V)
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;                         // initialize source vertex
        edgeTo = new int[G.V()];            // initialize edgeTo as int array
        marked = new boolean[G.V()];        // initialize marked as boolean array
        validateVertex(s);                  // validate vertex s?
        dfs(G,s);                           // run depth-first search
    }

    /**
     * depth first search from v
     * @param G the graph
     * @param v the source vertex
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;       // mark v true (as visited) first
        for (int w: G.adj(v)) { // loop through v's adjecent vertices (nodes)
            if(!marked[w]) {    // run if condition if marked[w] is false (w is not visited before)
                edgeTo[w] = v;  // set edgeTo[w] as v (w is connected to v node）
                dfs(G, w);      // run dfs search from source vertex w (recursively)
            }
        }
    }

    /**
     * Is there a path between the source vertex s and vertex v?
     * @param v     the vertex
     * @return      true if there is a path, false otherwise
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex s and vertex v,
     * or null if no such path.
     * @param v the vertex
     * @return  the sequence of vertices on a path between the source vertex s
     * and vertex v, as an Iterable
     * throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    /**
     * throw an IllegalArgumentException unless 0 <= v < V
     * @param v
     */
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}
