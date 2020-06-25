package ca.cmpt213.a2.main;
import  ca.cmpt213.a2.gameui.*;
import ca.cmpt213.a2.model.*;

import java.util.Random;

public class Main {
    public static void main(String []args) {

        TextUI.mainDisplay();
        Maze mazeMap = new Maze();
        Hero theHero = new Hero();
        Monster monster1 = new Monster(18,1);
        Monster monster2 = new Monster(1,13);
        Monster monster3 = new Monster(18,13);
        mazeMap.putHeroPosition(theHero);
        mazeMap.putMonsterPosition(monster1);
        mazeMap.putMonsterPosition(monster2);
        mazeMap.putMonsterPosition(monster3);
        mazeMap.putPowerRandomly();
        mazeMap.displayCurrMaze();

//        int monsters_kill = TextUI.inputMonstersKill();
//        int powers = TextUI.inputPowers();
//        int monsters_alive = TextUI.inputMonstersAlive();
//        char move = TextUI.inputMove();
//
//        if(move == 'm') {
//            mazeMap.revealMaze();
//        }
        mazeMap.revealMaze();
//        while(loop for execution to keep asking user) // In progress







    }
} // Main.java