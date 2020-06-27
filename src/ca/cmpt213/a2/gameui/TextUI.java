package ca.cmpt213.a2.gameui;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * A class implementing text user interface.
 *
 * The UI is able to:
 * 1. Prompt user for valid actions/moves
 * 2. Accept user input
 * 3. Displays game information, and
 * 4. Output a message showing the game result (win/lose).
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.ca)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class TextUI {
    private static final String instructions = "Kill 3 Monsters!";
    private static final String[] legend = {"#: Wall", "@: You (a hero)", "!: Monster", "$: Power", ". Unexplored space"};
    private static final String[] moves = {"W (up)", "A (left", "S (down)", "D (right)"};

    public static void instructions() {
        System.out.println("DIRECTIONS:");
        System.out.format("\t%s\n", instructions);
        System.out.println("LEGEND:");

        IntStream.range(0, legend.length).forEach(i -> System.out.format("\t %s \n", legend[i]));

        System.out.println("MOVES:");
        System.out.format("\tUse %s, %s, %s, and %s to move.\n", moves[0], moves[1], moves[2], moves[3]);
        System.out.println("\t(You must press enter after each move).");

    }

    public static void gameInfo(int monstersTobeKilled, int numPowers, int monstersAlive) {
        System.out.format("Total number of monsters to be killed: %d\n", monstersTobeKilled);
        System.out.format("Number of powers currently in possession: %d\n", numPowers);
        System.out.format("Number of monsters alive: %d\n", monstersAlive);
    }

    public static String inputMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your move [WASD?]: ");
        return sc.nextLine();
    }


    public static void invalidInput() {
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up)!");
    }

    public static void invalidMove() {
        System.out.println("Invalid move: you cannot move through walls!");
    }

    public static void gameLost() {
        System.out.println("I'm sorry, you have been eaten!");
    }

    public static void gameWon() {
        System.out.println("\nCongratulations! You have won the game!");
    }
} // TextUI.java