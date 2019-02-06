package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private ArrayList<WorldState> solution;

    /**
     * constructor which solves the puzzle, computing everything necessary for moves()
     * and solution() to not have to solve the problem again. Solves the puzzle using the
     * A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        //solution ArrayList
        solution = new ArrayList<>();

        // priority queue
        MinPQ<SearchNode> pq = new MinPQ<>();

        // insert initial WorldState
        pq.insert(new SearchNode(initial, 0, null));

        /**
         * remove the serach with minimum priority 'X'. If it is the goal node,
         * then we're done. Otherwise,for each neighbor of X's world state, create
         * a new search node that obeys the description above and insert it into
         * priority queue.
         */
        while(!pq.min().getWorldState().isGoal()) {
            SearchNode X = pq.delMin();
            for (WorldState neighbor : X.getWorldState().neighbors()) {
                // critical optimization
                if (X.getPrev() == null || !(neighbor.equals(X.getPrev().getWorldState()))) {
                    pq.insert(new SearchNode(neighbor, X.getMoves() + 1, X));
                }
            }
        }
        SearchNode s = pq.min();
        while (s != null) {
            solution.add(0, s.getWorldState());
            s = s.getPrev();
        }

    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting at the initial
     * WorldState
     */
    public int moves() {
        return -1;
    }

    /**
     * @return returns a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return null;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState ws;      // WorldState
        private int numOfMoves;              // # of moves to reach the current SearchNode
        private SearchNode prev;     // a reference to the previous SearchNode

        private SearchNode (WorldState ws, int m, SearchNode p) {
            this.ws = ws;
            this.numOfMoves = m;
            this.prev = p;
        }

        public WorldState getWorldState() {
            return ws;
        }

        public int getMoves() {
            return numOfMoves;
        }
        public SearchNode getPrev() {
            return prev;
        }
        @Override
        public int compareTo(SearchNode sn) {
            return ((this.numOfMoves + ws.estimatedDistanceToGoal()) - (sn.numOfMoves + sn.ws.estimatedDistanceToGoal()));
        }
    }
}
