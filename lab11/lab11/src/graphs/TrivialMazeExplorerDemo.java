package graphs;

/**
 *  @author Josh Hug
 */
public class TrivialMazeExplorerDemo {
    public static void main(String[] args) {
        Maze maze = new Maze("F:\\1_ML\\Java\\CS61b\\Repo\\CS61b\\lab11\\lab11\\src\\graphs\\maze.txt");
        TrivialMazeExplorer tme = new TrivialMazeExplorer(maze);
        tme.solve();
    }
}
