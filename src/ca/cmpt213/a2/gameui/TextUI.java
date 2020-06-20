package ca.cmpt213.a2.gameui;

public class TextUI {

    private static String directions = "Kill 3 Monsters!";
    private static String[] legend = {"#: Wall", "@: You (a hero)", "!: Monster", "$: Power", ". Unexplored space"};
    private static String[] moves = {"W (up)", "A (left", "S (down)", "D (right)"};

    public static void mainDisplay() {
        System.out.println("DIRECTIONS:");
        System.out.format("\t %s \n ", directions );
        System.out.println("LEGEND:");

        for(int i = 0; i < legend.length; i++) {
            System.out.format("\t %s \n", legend[i]);
        }

        System.out.println("MOVES:");
        System.out.format("\t Use %s, %s, %s, and %s to move. \n", moves[0], moves[1], moves[2], moves[3]);
        System.out.println("(You must press enter after each move).");
    }
    
}
