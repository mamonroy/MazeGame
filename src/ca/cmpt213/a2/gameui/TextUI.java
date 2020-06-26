package ca.cmpt213.a2.gameui;
import ca.cmpt213.a2.model.*;
import java.util.*;

public class TextUI {

    private static String instructions = "Kill 3 Monsters!";
    private static String[] legend = {"#: Wall", "@: You (a hero)", "!: Monster", "$: Power", ". Unexplored space"};
    private static String[] moves = {"W (up)", "A (left", "S (down)", "D (right)"};

    public static void instructions() {
        System.out.println("DIRECTIONS:");
        System.out.format("\t%s\n", instructions);
        System.out.println("LEGEND:");

        for(int i = 0; i < legend.length; i++) {
            System.out.format("\t %s \n", legend[i]);
        }

        System.out.println("MOVES:");
        System.out.format("\tUse %s, %s, %s, and %s to move.\n", moves[0], moves[1], moves[2], moves[3]);
        System.out.println("\t(You must press enter after each move).");

    }

    public static void gameInfo(int monstersTobeKilled, int numPowers, int monstersAlive) {

        System.out.format("Total number of monsters to be killed: %d\n", monstersTobeKilled);
        System.out.format("Number of powers currently in possession: %d\n", numPowers);
        System.out.format("Number of monsters alive: %d\n", monstersAlive);
    }

    public static char inputMove() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your move [WASD?]: ");
        char move = sc.next().charAt(0);
        return move;
    }


    public static void invalidInput() {
        System.out.println("Invalid move. Please enter just A (left), S (down), D (right), or W (up).!");
    }

    public static void invalidMove() {
        System.out.println("Invalid move: you cannot move through walls!");
    }

    public static void gameLost() {
        System.out.println("I'm sorry, you have been eaten!");
    }

    public static void gameWon() {
        System.out.println("Congratulations! You have won the game!");
    }
    

} // TextUI.java

