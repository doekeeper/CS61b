package graphs;

/**
 *  @author Josh Hug
 */
public class MazeDepthFirstPaths extends MazeExplorer {
    // MazeDepthFirstPaths extends MazeExplorer class, therefore, the following methods
    // FYI, MazeExplorer extends Observable class

    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;


    public MazeDepthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);       // call parent constructor (MazeExplorer constructor)
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);      // set s to an integer based on the source vertex coordinates
        t = maze.xyTo1D(targetX, targetY);      // set t to an integer based on the target vertex coordinates
        distTo[s] = 0;                          // set s to source distance to 0 (of course)
        edgeTo[s] = s;                          // set edge to s as s (weird but acceptable)
    }

    private void dfs(int v) {
        marked[v] = true;
        announce();

        if (v == t) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                dfs(w);
                if (targetFound) {
                    return;
                }
            }
        }
    }

    @Override
    public void solve() {
        dfs(s);
    }
}

