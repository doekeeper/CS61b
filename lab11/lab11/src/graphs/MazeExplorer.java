package graphs;

import java.util.Observable;

/**
 * @author Josh Hug
 */
public abstract class MazeExplorer extends Observable {
    protected int[] distTo;
    protected int[] edgeTo;
    protected boolean[] marked;
    protected Maze maze;


    /**
     * Notify all Observers of a change.
     */
    protected void announce() {
        setChanged();               // mark this Observable object as having been changed; the hasChanged method will now return true.
        notifyObservers();          // notify all of its observers and then call the clearChanged method to indicate that this object has no longer changed.
    }

    /**
     * Parameterized constructor;
     * distTo array, edgeTo array, marked array will be initialized based on Maze's length;
     * distTo array and edgeTo array are then set at Integer.MAX_VALUE (which means all vertices are disconnected from the source
     * @param m     input - Maze class
     */
    public MazeExplorer(Maze m) {
        maze = m;

        distTo = new int[maze.V()];         // set distTo to an int array with length of V
        edgeTo = new int[maze.V()];         // set edgeTo to an int array with length of V
        marked = new boolean[maze.V()];     // set marked to an boolean array with length of V
        for (int i = 0; i < maze.V(); i += 1) {
            distTo[i] = Integer.MAX_VALUE;  // set distTo to MAX_VALUE - it is disconnected from the source
            edgeTo[i] = Integer.MAX_VALUE;  // set edgeTo to MAX_VALUE - it is disconnected from the source
        }
        addObserver(maze);
    }

    /**
     * Solves the maze, modifying distTo and edgeTo as it goes.
     */
    public abstract void solve();

    /* Getters for AG. */
    public int[] getDistTo() {
        return distTo;
    }

    public int[] getEdgeTo() {
        return edgeTo;
    }
}
