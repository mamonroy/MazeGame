package ca.cmpt213.a2.main;
import  ca.cmpt213.a2.gameui.*;
import ca.cmpt213.a2.model.*;

public class Main {

    private static final int FIRST_MONSTER_INITIAL_X_POS = 18;
    private static final int FIRST_MONSTER_INITIAL_Y_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_X_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_Y_POS = 13;
    private static final int THIRD_MONSTER_INITIAL_X_POS = 18;
    private static final int THIRD_MONSTER_INITIAL_Y_POS = 13;

    public static void main(String []args) {

        TextUI.mainDisplay();

        Maze mazeMap = new Maze();
        Hero theHero = new Hero();
        Monster monster1 = new Monster(FIRST_MONSTER_INITIAL_X_POS, FIRST_MONSTER_INITIAL_Y_POS);
        Monster monster2 = new Monster(SECOND_MONSTER_INITIAL_X_POS, SECOND_MONSTER_INITIAL_Y_POS);
        Monster monster3 = new Monster(THIRD_MONSTER_INITIAL_X_POS, THIRD_MONSTER_INITIAL_Y_POS);

        mazeMap.setHeroPositionInMaze(theHero);
        mazeMap.setMonsterPositionInMaze(monster1);
        mazeMap.setMonsterPositionInMaze(monster2);
        mazeMap.setMonsterPositionInMaze(monster3);
        mazeMap.placePowerRandomlyInMaze();
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