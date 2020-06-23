package ca.cmpt213.a2.gameui;
import ca.cmpt213.a2.model.*;
import java.util.*;

public class TextUI {

    private static String directions = "Kill 3 Monsters!";
    private static String[] legend = {"#: Wall", "@: You (a hero)", "!: Monster", "$: Power", ". Unexplored space"};
    private static String[] moves = {"W (up)", "A (left", "S (down)", "D (right)"};

    public static void mainDisplay() {
        System.out.println("DIRECTIONS:");
        System.out.format("\t%s\n", directions );
        System.out.println("LEGEND:");

        for(int i = 0; i < legend.length; i++) {
            System.out.format("\t %s \n", legend[i]);
        }

        System.out.println("MOVES:");
        System.out.format("\tUse %s, %s, %s, and %s to move.\n", moves[0], moves[1], moves[2], moves[3]);
        System.out.println("\t(You must press enter after each move).");
    }

    public static void outsideCells() {

        int width = Maze.getMazeWidth();
        int tall = Maze.getMazeTall();

        System.out.println("Maze");
        for (int i = 0; i < tall; i++) {
            if(i == 0 || i == tall - 1) {
                for (int j = 0; j < width; j++) {
                    System.out.print("#");
                }
            } else {
                for (int j = 0; j < width; j++) {
                    if (j == 0 || j == width - 1) {
                        System.out.print("#");
                    } else {
                        System.out.print(" ");
                    }
                }
            }
            System.out.println("");
        }
    }

    public static int inputMonstersKill() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Total number of monsters to be killed: ");
        int monsters_dead = sc.nextInt();
        return monsters_dead;
    }
    public static int inputPowers() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Number of powers currently in possession: ");
        int powers = sc.nextInt();
        return powers;
    }
    public static int inputMonstersAlive() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Number of monsters alive: ");
        int monsters_alive = sc.nextInt();
        return monsters_alive;
    }
    public static char inputMove() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your move [WASD?]: ");
        char move = sc.next().charAt(0);
        return move;
    }
}
