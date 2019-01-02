package BFS_ExampleCode;

import java.util.*;

/**
 * The BreadthFirstPaths class represents a data type for finding shortest
 * paths (number of edges) from a source vertex s (or a set of source vertices)
 * to every other vertex in an undirected graph.
 * This implementation uses breadth-first search.
 * The constructor takes time proportional to V + E,
 * where V is the number of vertices and E is the number of edges.
 * Each call to distTo(int) and hasPathTo(int) takes constant time;
 * each call to pathTo(int) takes time proportional to the length of the path.
 * It uses extra space (not including the graph) proportional to V.
 * Reference: Algorithms, 4th Edition by R. Sedgewick & K. Wayne.
 */
public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    // Use Integer.MAX_VALUE (7FFFFFFF) as positive INFINITY is okay for this example, but it is obviously not a very good solution.
    // For example, positive inifinity + 5 should be still equal to positive infinity, however Integer.MAX_VALUE + 5 = Integer.MIN_VALUE + 4 = -2147483644.
    // Therefore, Integer.MAX_VALUE is not equivalent to positive infinity.
    // So far, the best solution I've found (with some search) is to use Double.POSITIVE_INFINITY.
    private boolean[] marked;       // marked[v] = is there an s-v path;
    private int[] edgeTo;           // edgeTo[v] = previous edge on the shortest s-v path
    private int[] distTo;            // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between the source vertex s
     * and every other vertex in the graph G.
     * @param G     the graph
     * @param s     the source vertex
     */
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
        assert check(G, s);
    }

    /**
     * Compute the shortest path between any one of the source vertices in the sources
     * and every other vertex in graph G.
     * @param G     the graph
     * @param source    the source vertices
     */
    public BreadthFirstPaths(Graph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        for(int v = 0; v < G.V(); v++){
            distTo[v] = INFINITY;
        }
        validateVertices(sources);
        bfs(G, sources);
    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;               // set all distTo[] to INFINITY - no vertice is connected to source s vertice
        }
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while(!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

}
