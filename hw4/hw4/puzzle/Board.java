package hw4.puzzle;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState{
    private int[] board;        // 1-D array representation of the puzzle
    private int N;              // the length of input 2-D array

    public Board(int[][] tiles) {
        N = tiles.length;
        board = new int[N * N];
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[N * i + j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {

        return board[N * i + j];
    }

    public int size() {
        return N * N;
    }

    /**
     * Answers from Josh Hug
     * @return
     */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<> ();
        int size = N;
        int row = -1;
        int col = -1;

        // find out the location of zero; let "row" and "col" be the coordinate of "zero"
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) {
                    row = i;
                    col = j;
                }
            }
        }
        // create a new 2-D array "arr1" and copy Board info to "arr1"
        int[][] arr1 = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr1[i][j] = tileAt(i, j);
            }
        }
        // find the neighbour board and enqueue in the queue "neighbors"
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.abs(-row + i) + Math.abs(-col + j) - 1 == 0) {
                    arr1[row][col] = arr1[i][j];
                    arr1[i][j] = 0;
                    Board neighbor = new Board(arr1);
                    neighbors.enqueue(neighbor);
                    arr1[i][j] = arr1[row][col];
                    arr1[row][col] = 0;
                }
            }
        }
        return neighbors;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != 0 && (board[i] - i != 1)) {
                count++;
            }
        }
        return count;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (board[i] != 0) {
                int rowTarget = board[i] / N;
                int colTarget = board[i] % N;
                int row = i / N;
                int col = i % N;
                count = count + Math.abs(row - rowTarget) + Math.abs(col - colTarget);
            }
        }
        return count;
    }

    public int estimatedDistanceToGoal() {
        return hamming();
    }

    public boolean equals(Object y) {
        return (Integer) y == board[(Integer) y];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append((int) (Math.sqrt(N)) + "\n");
        for (int i = 0; i < Math.sqrt(N); i++) {
            for (int j = 0; j < Math.sqrt(N); j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
