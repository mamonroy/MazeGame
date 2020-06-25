package ca.cmpt213.a2.main;
import  ca.cmpt213.a2.gameui.*;
import ca.cmpt213.a2.model.*;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String []args) {

        char validMoves[] = {'w','W','a','A','s','S','d','D'};
        char validOptions[] = {'m','c','?'};

        TextUI.instructions();

        Maze mazeMap = new Maze();
        Hero theHero = new Hero();

        Monster monster1 = new Monster(18,1);
        Monster monster2 = new Monster(1,13);
        Monster monster3 = new Monster(18,13);

        while(true) {

            mazeMap.putHeroPosition(theHero);
            mazeMap.putMonsterPosition(monster1);
            mazeMap.putMonsterPosition(monster2);
            mazeMap.putMonsterPosition(monster3);
            mazeMap.putPowerRandomly();
            mazeMap.displayCurrMaze();
            TextUI.gameInfo(0,0,0);

            // Loops over if user keeps entering invalid inputs
            boolean flag = true;
            while(flag) {
                char move = TextUI.inputMove();
                if(new String(validMoves).indexOf(move) >= 0) {
                    while(true) {
                        switch(move) {
                            case 'w', 'W' -> {
                                //Samples
                            }
                            case 'a', 'A' -> {
                                //Sample
                            }
                            case 's', 'S' -> {
                                //Sampless
                            }
                            case 'd', 'D' -> {
                                //Samplessss
                            }
                        }
                    }
                }
                else if(new String(validOptions).indexOf(move) >= 0) {
                    switch(move) {
                        case '?' -> {
                            TextUI.gameInfo(0,0,0);
                        }
                        case 'm' -> {
                            mazeMap.revealMaze();
                        }
                        case 'c' -> {
                            // Monster killed should be set to 1
                        }
                    }
                }
                else {
                    TextUI.invalidInput();
                }
            }
        }
    }
} // Main.java