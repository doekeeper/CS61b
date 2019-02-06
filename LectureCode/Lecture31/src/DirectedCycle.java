// Code example for the algorithm of finding a cycle in a directed graph (digraph)
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;       // vertices on a cycle (if one exists)
    private boolean[] onStack;          // vertices on recursive call stack

    public DirectedCycle(Digraph G) {   // constructor, find reachability (connection) via recursive dfs
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) dfs(G, V);
        }

        private void dfs(Digraph G, int v) {
            onStack[v] = true;
            marked[v] = true;
            for (int w: G.adj(v)) {
                if (this.hasCycle()) return;
                else if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(G, w);
                } else if (onStack[w]) {            // w is already on the onStack, so it forms a cycle
                    cycle = new Stack<Integer>();               // create a stack to store the cycle
                    for (int x = v; x != w; x = edgeTo[x]) {    // start with vertex v, push v and its
                        cycle.push(x);
                    }
                    cycle.push(w);
                    cycle.push(v);
                }
            }
        }

    }
}
