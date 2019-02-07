package hw4.puzzle;
import java.util.ArrayList;

public class Board implements WorldState{
    int[][] board;

    public Board(int[][] tiles) {
        board = new int[tiles.length][tiles[0].length];
        for(int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                board[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        return board[i][j];
    }

    public int size() {
        return board.length * board[0].length;
    }

    public Iterable<WorldState> neighbors() {
        return null;
    }

    public int hamming() {
        return -1;
    }

    public int manhattan() {
        return -1;
    }

    public int estimatedDistanceToGoal() {
        return -1;
    }

    public boolean equals(Object y) {
        return false;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
