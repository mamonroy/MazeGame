package ca.cmpt213.a2.main;

import ca.cmpt213.a2.gameui.TextUI;
import ca.cmpt213.a2.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int FIRST_HERO_INITIAL_X_POS = 1;
    private static final int FIRST_HERO_INITIAL_Y_POS = 1;

    private static final int FIRST_MONSTER_INITIAL_X_POS = 18;
    private static final int FIRST_MONSTER_INITIAL_Y_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_X_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_Y_POS = 13;
    private static final int THIRD_MONSTER_INITIAL_X_POS = 18;
    private static final int THIRD_MONSTER_INITIAL_Y_POS = 13;

    private static boolean cheatActive = false;

    private static Hero theHero;
    private static final List<Monster> monsters = new ArrayList<>();
    private static final char validMoves[] = {'w','W','a','A','s','S','d','D'};
    private static final char validOptions[] = {'m','c','?'};
    private static Maze mazeMap;
    private static Power power;
    private static char move;

    public static void main(String[] args) {

        TextUI.instructions();
        mazeMap = new Maze();
        theHero = new Hero(FIRST_HERO_INITIAL_Y_POS, FIRST_HERO_INITIAL_X_POS);
        monsters.add(new Monster(FIRST_MONSTER_INITIAL_X_POS, FIRST_MONSTER_INITIAL_Y_POS));
        monsters.add(new Monster(SECOND_MONSTER_INITIAL_X_POS, SECOND_MONSTER_INITIAL_Y_POS));
        monsters.add(new Monster(THIRD_MONSTER_INITIAL_X_POS, THIRD_MONSTER_INITIAL_Y_POS));
        setPositionOfPowerOnMaze();
        setPositionOfMonstersOnMaze();
        setPositionOfHeroOnMaze();
        setVisibleNeighbours();

        mazeMap.displayCurrMaze();

        boolean gameOver = false;

        // Loops over until the game is over!
        while(!gameOver) {

            move = TextUI.inputMove();

            if (cheatActive && monsters.size() < 3) {
                TextUI.gameWon();
                gameOver = true;

            } else if (monsters.size() == 0) {
                TextUI.gameWon();
                gameOver = true;

            } else if (!theHero.isHeroAlive()) {
                TextUI.gameLost();
                gameOver = true;

            } else if (new String(validMoves).indexOf(move) >= 0) {
                moveAliveMonsters();
                checkHeroMove(move);
                setVisibleNeighbours();
                mazeMap.displayCurrMaze();
                gameInfo();

            } else if (new String(validOptions).indexOf(move) >= 0) {
                checkOptions(move);

            } else {
                TextUI.invalidInput();
            }
        }
        mazeMap.revealMaze();
    }

    private static void setPositionOfHeroOnMaze() {

        if (mazeMap.getMazeCellContent(theHero.getHeroYPos(), theHero.getHeroXPos())
                .equals(CellContent.POWER)) {
            theHero.incrementPowerCount();
            theHero.setOccupyingMazeCellContent(CellContent.HERO);
            setPositionOfPowerOnMaze();
        }else { // cell is empty!
            theHero.setOccupyingMazeCellContent(CellContent.EMPTY);
        }

        mazeMap.setHeroPositionInMaze(theHero);
    }
    private static void setPositionOfPowerOnMaze() {

        // Checks if it's the first call to avoid null error
        if(power == null) {
            mazeMap.placePowerRandomlyInMaze(power);
        }

        else {
            setCurrentMoveEmpty("Power");
            mazeMap.placePowerRandomlyInMaze(power);
        }
    }

    private static void setPositionOfMonstersOnMaze() {
        for (Monster monster : monsters) {
            setPositionOfIndividualMonsterOnMaze(monster);
        }
    }

    private static void setPositionOfIndividualMonsterOnMaze(Monster monster) {
        if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.POWER)) {
            monster.setOccupyingMazeCellContent(CellContent.POWER);
        } else if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.MONSTER)) {
            monster.setOccupyingMazeCellContent(CellContent.MONSTER);
        } else if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.HERO)) {
            monster.setOccupyingMazeCellContent(CellContent.HERO);
        } else { // cell is empty!
            monster.setOccupyingMazeCellContent(CellContent.EMPTY);
        }

        mazeMap.setMonsterPositionInMaze(monster);
    }

    private static void removeDeadMonster(Monster monster) {
        monsters.remove(monster);
    }

    private static void setVisibleNeighbours() {

        // Up, Left, Down, Right
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() - 1, theHero.getHeroXPos());
        mazeMap.setMazeCellVisible(theHero.getHeroYPos(), theHero.getHeroXPos() - 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos());
        mazeMap.setMazeCellVisible(theHero.getHeroYPos(), theHero.getHeroXPos() + 1);

        // Diagonals
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos() - 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() - 1, theHero.getHeroXPos() + 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() -1, theHero.getHeroYPos() -1 );
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos() + 1);

    }

    // Set the current cell to be EMPTY AND/OR Visible (Visible Only applies to hero!) before going to another cell!
    private static void setCurrentMoveEmpty(String inGameCharacter) {

        if(inGameCharacter == "Hero") {
            int row = theHero.getHeroYPos();
            int column = theHero.getHeroXPos();
            mazeMap.setMazeCellContent(row,column,CellContent.EMPTY);
            mazeMap.setMazeCellVisible(row,column);
        }

        else if(inGameCharacter == "Power") {
            int row = power.getPowerYPos();
            int column = power.getPowerXPos();
            mazeMap.setMazeCellContent(row,column,CellContent.EMPTY);
        }

    }

    private static void moveHero(char move) {

        setCurrentMoveEmpty("Hero");
        switch (move) {
            case 'w', 'W' -> theHero.moveHeroUp();
            case 'a', 'A' -> theHero.moveHeroLeft();
            case 's', 'S' -> theHero.moveHeroDown();
            case 'd', 'D' -> theHero.moveHeroRight();
        }
    }

    private static void checkHeroMove(char move) {

        boolean successfulMove = false;
        CellContent nextCellContent;

        while (!successfulMove) {

            switch (move) {
                case 'w', 'W' -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos() - 1, theHero.getHeroXPos());

                case 'a', 'A' -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos(), theHero.getHeroXPos() - 1);

                case 's', 'S' -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos() + 1, theHero.getHeroXPos());

                case 'd', 'D' -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos(), theHero.getHeroXPos() + 1);

                default -> {
                    TextUI.invalidInput();
                    move = TextUI.inputMove();
                    continue;
                }
            }

            switch(nextCellContent) {
                case WALL -> {
                    successfulMove = false;
                    TextUI.invalidMove();
                    move = TextUI.inputMove();
                }
                case POWER, EMPTY -> {
                    moveHero(move);
                    setPositionOfHeroOnMaze();
                    successfulMove = true;
                }
            }
        }
    }

    private static void moveAliveMonsters() {
        RandomDirection randomDir;
        List<String> randomDirs;
        boolean successfulMove;
        CellContent nextCellContent;

        for (Monster monster : monsters) {
            successfulMove = false;
            nextCellContent = CellContent.WALL;

            randomDir = new RandomDirection();
            randomDirs = randomDir.getRandomizedDir();

            while (!successfulMove) {
                for (String dir : randomDirs) {
                    if (!successfulMove) {
                        switch (dir) {
                            case "left" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monster.getMonsterYPos(), monster.getMonsterXPos() - 1);

                            case "right" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monster.getMonsterYPos(), monster.getMonsterXPos() + 1);

                            case "up" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monster.getMonsterYPos() - 1, monster.getMonsterXPos());

                            case "down" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monster.getMonsterYPos() + 1, monster.getMonsterXPos());
                            default -> {
                                assert false : "Unknown direction!";
                            }
                        }
                        switch (nextCellContent) {
                            case WALL -> successfulMove = false;
                            case EMPTY, MONSTER, POWER -> {
                                moveIndividualMonster(monster, nextCellContent, dir);
                                setPositionOfIndividualMonsterOnMaze(monster);
                                successfulMove = true;
                            }
                            case HERO -> {
                                moveIndividualMonster(monster, nextCellContent, dir);
                                killHeroOrMonster(monster);

                                if (monster.isMonsterAlive()) {
                                    setPositionOfIndividualMonsterOnMaze(monster);
                                } else {
                                    removeDeadMonster(monster);
                                }
                                successfulMove = true;
                            }
                            default -> {
                                assert false;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void moveIndividualMonster(Monster monster, CellContent nextCellValue, String direction) {
        mazeMap.setMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos(),
                monster.getOccupyingMazeCellContent());
        switch (direction) {
            case "left" -> monster.moveMonsterLeft();
            case "right" -> monster.moveMonsterRight();
            case "up" -> monster.moveMonsterUp();
            case "down" -> monster.moveMonsterDown();
            default -> {
                assert false : "No a valid direction for movement!";
            }
        }
    }

    private static void killHeroOrMonster(Monster monster) {

        int numberOfPowers = theHero.getPowerCount();
        if(numberOfPowers >= 1) {
            theHero.decrementPowerCount();
            removeDeadMonster(monster);
        }
        else {
            theHero.killHero();
        }
    }

    private static void checkOptions(char symbol) {

        int monstersAlive = monsters.size();
        switch(symbol) {
            case '?' -> {
                gameInfo();
            }
            case 'm' -> {
                mazeMap.revealMaze();
            }
            case 'c' -> {
                cheatActive = true;
            }
        }
    }

    private static void gameInfo() {

        int monstersAlive = monsters.size();

        if(!cheatActive) {
            TextUI.gameInfo(monstersAlive, theHero.getPowerCount(), monstersAlive);
        }
        else {
            TextUI.gameInfo(1, theHero.getPowerCount(), monstersAlive);
        }
    }

} // Main.java