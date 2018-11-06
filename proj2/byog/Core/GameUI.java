package byog.Core;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;
import byog.TileEngine.*;

/* class GameUI includes all relevant methods/fields about game interface and user interactivities */
public class GameUI implements Serializable {
    int width;     // width of menu screen
    int height;    // height of menu screen
    Game game = new Game();
    ObjectIO objIO = new ObjectIO();
    TERenderer ter = new TERenderer();

    public GameUI(int width, int height) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the bottom left is (0,0) and the top right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }


    /* show menu screen including "Game Title: CS61B: THE GAME, New Game(N), Load Game(L), Quit Game(Q)" */
    public void showMenuScreen() {
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(0.5 * width, 0.7 * height, "CS61B: THE GAME");
        StdDraw.text(0.5 * width, 0.5 * height, "New Game(N)");
        StdDraw.text(0.5 * width, 0.475 * height, "Load Game(L)");
        StdDraw.text(0.5 * width, 0.45 * height, "Quit(Q)");
        StdDraw.show();
        StdDraw.pause(1000);
    }

    /* when "N" is pressed, new game will start - display dungeonMap with movable player object */
    public void newGame() {
        game.playWithKeyboard();
        return;
    }

    /* if "L" is pressed, load game - resume the game saved from last time*/
    public void loadGame() {
        Game game = (Game) objIO.readObjectFromFile();
        TETile[][] world = game.getMap();
        ter.renderFrame(world);
    }
    /* if q is pressed, save current status of game including maps and player status, prior to quiteGame() */
    private void saveGame() {
        objIO.writeObjectToFile(game);
    }

    /* save the current map/player status in the local file, and then close game UI */
    public void quitGame() {
        saveGame();
        System.exit(0);
    }

    public static void main(String[] args) {
        GameUI gameUI = new GameUI(80, 50);
        /*
        gameUI.showMenuScreen();
        gameUI.newGame();
        StdDraw.pause(10000);
        gameUI.saveGame();
        gameUI.quitGame();
        */
        // restart game and load game for last save
        gameUI.showMenuScreen();
        gameUI.loadGame();
    }
}
