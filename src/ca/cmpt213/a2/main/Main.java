package ca.cmpt213.a2.main;

import ca.cmpt213.a2.gameui.TextUI;
import ca.cmpt213.a2.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int FIRST_MONSTER_INITIAL_X_POS = 18;
    private static final int FIRST_MONSTER_INITIAL_Y_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_X_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_Y_POS = 13;
    private static final int THIRD_MONSTER_INITIAL_X_POS = 18;
    private static final int THIRD_MONSTER_INITIAL_Y_POS = 13;

    private static List<Monster> monsters = new ArrayList<>();
    private static Maze mazeMap;

    public static void main(String []args) {

        TextUI.mainDisplay();
        mazeMap = new Maze();

        Hero theHero = new Hero();
        mazeMap.setHeroPositionInMaze(theHero);

        mazeMap.placePowerRandomlyInMaze();

        monsters.add(new Monster(FIRST_MONSTER_INITIAL_X_POS, FIRST_MONSTER_INITIAL_Y_POS));
        monsters.add(new Monster(SECOND_MONSTER_INITIAL_X_POS, SECOND_MONSTER_INITIAL_Y_POS));
        monsters.add(new Monster(THIRD_MONSTER_INITIAL_X_POS, THIRD_MONSTER_INITIAL_Y_POS));
        setPositionOfMonstersOnMaze();

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

    private static void setPositionOfMonstersOnMaze() {
        for (Monster monster : monsters) {
            if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                    .equals(CellContent.POWER)) {
                monster.setOccupyingMazeCellContent(CellContent.POWER);
            }
            mazeMap.setMonsterPositionInMaze(monster);
        }
    }

    private boolean isMonsterAlive (int monsterIndex) {
        return monsters.get(monsterIndex).getMonsterLifeStatus();
    }

    private void removeDeadMonster (int monsterIndex) {
        monsters.remove(monsterIndex);
    }

    private void moveAliveMonsters() {
        RandomDirection randomDir;
        List<String> randomDirs;
        boolean successfulMove;
        CellContent nextCellContent;


        for (Monster monster : monsters) {
            successfulMove = false;

            randomDir = new RandomDirection();
            randomDirs = randomDir.getRandomizedDir();

            while (!successfulMove) {
                for (String dir : randomDirs) {
                    if (!successfulMove) {
                        switch (dir) {
                            case "left" -> {
                                nextCellContent = mazeMap.getMazeCellContent(monster.getMonsterYPos(),
                                        monster.getMonsterXPos() - 1);
                            }
                            case "right" -> {
                                nextCellContent = mazeMap.getMazeCellContent(monster.getMonsterYPos(),
                                        monster.getMonsterXPos() + 1);
                            }
                            case "up" -> {
                                nextCellContent = mazeMap.getMazeCellContent(monster.getMonsterYPos() - 1,
                                        monster.getMonsterXPos());
                            }
                            case "down" -> {
                                nextCellContent = mazeMap.getMazeCellContent(monster.getMonsterYPos() + 1,
                                        monster.getMonsterXPos());
                            }
                            default -> {
                                assert false : "Unknown direction!";
                            }
                        }
                        // Deal with different cell cases here
                    }

                }
            }
        }
    }

} // Main.java