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
     * @param
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
            // set all distTo[] to INFINITY - no vertex is connected to source s vertex
            distTo[v] = INFINITY;
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
    // breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> q = new Queue<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.enqueue(s);
        }
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

    /**
     * Is there a path between the source vertex s and vertex v?
     * @param v the vertex
     * @return true if there is a path, and false otherwise
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path
     * between the source vertex s and vertex v
     * @param v the vertex
     * @return the number of edges in a shortest path
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public int distTo(int v) {
        return distTo(v);
    }

    /**
     * Returns a shortest path between the source vertex s and v,
     * or there is no such a path
     * @param v the vertex
     * @return the sequence of vertices on a shortest path, as an Iterable
     * @throws IllegalArgumentException unless 0 <= v < V
     */
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        return path;
    }

    // check optimality conditions for single source
    private boolean check(Graph G, int s) {
        // check that the distance of s = 0
        if (distTo[s] != 0) {
            System.out.println("distance of source " + s + " to itself = " + distTo[s]);
            return false;
        }

        // check that for each edge v-w dist[w] <= dist[v] + 1
        // provided v is reachable from s
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (hasPathTo(v) != hasPathTo(w)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
                    System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
                    return false;
                }
                if (hasPathTo(v) && (distTo[w] > distTo[v] + 1)) {
                    System.out.println("edge " + v + "-" + w);
                    System.out.println("distTo[" + v + "] = " + distTo[v]);
                    System.out.println("distTo[" + w + "] = " + distTo[w]);
                    return false;
                }
            }
        }
        // check that v = edgeTo[w] satisfies distTo[w] = distTo[v] + 1
        // provide v is reachable from s
        for (int w = 0; w < G.V(); w++) {
            if (!hasPathTo(w) || w == s) continue;
            int v = edgeTo[w];
            if (distTo[w] != distTo[v] + 1) {
                System.out.println("shortest path edge " + v + "-" + w);
                System.out.println("distTo[" + v + "] = " + distTo[v]);
                System.out.println("distTo{" + w + "] = " + distTo[w]);
                return false;
            }
        }
        return true;
    }

    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
        }
    }

    private void validateVertices (Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int V = marked.length;
        for(int v: vertices) {
            if (v < 0 || v >= V) {
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
            }
        }
    }




}
