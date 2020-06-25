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

        Random randGen = new Random();
        int range_X = 18;
        int range_Y = 13;
        int randomPos_X = randGen.nextInt(range_X) + 1;
        int randomPos_Y = randGen.nextInt(range_Y) + 1;
        Power pow = new Power(randomPos_X, randomPos_Y);

        mazeMap.putHeroPosition(theHero);
        mazeMap.putMonsterPosition(monster1);
        mazeMap.putMonsterPosition(monster2);
        mazeMap.putMonsterPosition(monster3);
        mazeMap.putPowerPosition(pow);
        mazeMap.displayCurrMaze();
        mazeMap.revealMaze();



//        int monsters_kill = TextUI.inputMonstersKill();
//        int powers = TextUI.inputPowers();
//        int monsters_alive = TextUI.inputMonstersAlive();
//        char move = TextUI.inputMove();
//
//        if(move == 'm') {
//            mazeMap.revealMaze();
//        }





    }
} // Main.java