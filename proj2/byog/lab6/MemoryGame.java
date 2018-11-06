package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
       //   if (args.length < 1) {
       //        System.out.println("Please enter a seed");
       //        return;
       //    }

       // int seed = Integer.parseInt(0);
        int seed = 0;
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
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

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String randomString = "";
        while (n > 0) {
            int charPosition = rand.nextInt(26);
            randomString = randomString + CHARACTERS[charPosition];
            n--;
        }

        return randomString;
    }

    public void drawFrame(String s, int round, String playerAction, String encouragement) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();
        Font helvetica = new Font("Helvetica", Font.PLAIN, 30);
        StdDraw.setFont(helvetica);
        StdDraw.text(width/2, height/2, s);
        StdDraw.text(5, 38, "Round: " + round);
        StdDraw.text(20, 38, playerAction);
        StdDraw.text(32, 38, encouragement);
        StdDraw.line(0, 37, 40, 37);
        StdDraw.show();
        StdDraw.pause(500);


    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        StdDraw.enableDoubleBuffering();
        int stringLength = letters.length();
        int i = 0;
        while (i < stringLength) {
            char letter = letters.charAt(i);
            drawFrame(Character.toString(letter), round, "WATCH!",ENCOURAGEMENT[rand.nextInt(7)]);
            StdDraw.pause(1000);
            StdDraw.show();
            StdDraw.pause(500);
            i++;
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        drawFrame("", round, "TYPE!", ENCOURAGEMENT[rand.nextInt(7)]);
        int i = 0;
        String playerInput = "";
        while (i < n) {
            if(StdDraw.hasNextKeyTyped()) {
                playerInput = playerInput + StdDraw.nextKeyTyped();
                i++;
                drawFrame(playerInput, round, "TYPE!", ENCOURAGEMENT[rand.nextInt(7)]);
            }
        }
        return playerInput;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        int round = 1;
        String stringToGuess;
        String playerInput;
        //TODO: Establish Game loop
        while (true) {
            drawFrame("Round " + round, round, "WATCH!", ENCOURAGEMENT[rand.nextInt(7)]);
            StdDraw.pause(1000);
            stringToGuess = generateRandomString(round);
            flashSequence(stringToGuess);
            playerInput = solicitNCharsInput(round);
            if (playerInput.equals(stringToGuess)) {
                drawFrame("You are correct! Next level is awaiting!", round, "", ENCOURAGEMENT[rand.nextInt(7)]);
                StdDraw.pause(1000);
                round++;
            } else {
                drawFrame("Game Over! You made it to level " + round, round, "", ENCOURAGEMENT[rand.nextInt(7)]);
                break;
            }
        }
    }
}
