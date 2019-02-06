package graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
    }

    private void DFSCycleDection() {
        marked[0] = true;
        for (int w : maze.adj(0)) {
            if (maze.adj)
        }
    }
}

