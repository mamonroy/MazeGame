package ca.cmpt213.a2.main;
import  ca.cmpt213.a2.gameui.*;
import ca.cmpt213.a2.model.*;

public class Main {
    public static void main(String []args) {

//        TextUI.mainDisplay();
//        TextUI.outsideCells();
//        int monsters_kill = TextUI.inputMonstersKill();
//        int powers = TextUI.inputPowers();
//        int monsters_alive = TextUI.inputMonstersAlive();
//        char move = TextUI.inputMove();

        Maze obj = new Maze();
        obj.displayMaze();
    }
} // Main.java