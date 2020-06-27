package ca.cmpt213.a2.main;

import ca.cmpt213.a2.gameui.TextUI;
import ca.cmpt213.a2.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Main class - implements complete game logic and initiates a new game.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.ca)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Main {
    private static final int FIRST_HERO_INITIAL_X_POS = 1;
    private static final int FIRST_HERO_INITIAL_Y_POS = 1;

    private static final int FIRST_MONSTER_INITIAL_X_POS = 18;
    private static final int FIRST_MONSTER_INITIAL_Y_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_X_POS = 1;
    private static final int SECOND_MONSTER_INITIAL_Y_POS = 13;
    private static final int THIRD_MONSTER_INITIAL_X_POS = 18;
    private static final int THIRD_MONSTER_INITIAL_Y_POS = 13;
    private static final List<Monster> monsters = new ArrayList<>();
    private static final char[] validMoves = {'w', 'W', 'a', 'A', 's', 'S', 'd', 'D'};
    private static final char[] validOptions = {'m', 'c', '?'};
    private static final int TOTAL_NUMBER_OF_MONSTERS = 3;
    private static boolean cheatActive = false;
    private static Hero theHero;
    private static Maze mazeMap;
    private static Power power;
    private static String move;

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
        displayNeighbours();

        mazeMap.displayCurrMaze(theHero.isHeroAlive());

        boolean gameOver = false;

        // Loops over until the game is over!
        while (!gameOver) {

            if (cheatActive && monsters.size() < TOTAL_NUMBER_OF_MONSTERS) {
                TextUI.gameWon();
                gameOver = true;
                continue;

            } else if (monsters.size() == 0) {
                TextUI.gameWon();
                gameOver = true;
                continue;

            } else if (!theHero.isHeroAlive()) {
                TextUI.gameLost();
                gameOver = true;
                continue;

            }

            // Checks if the user input move is a valid character and only input of length 1
            move = TextUI.inputMove();
            if (new String(validMoves).contains(move) && move.length() == 1) {
                checkHeroMove(move);
                if(theHero.isHeroAlive()) {
                    moveAliveMonsters();
                }
                displayNeighbours();
                mazeMap.displayCurrMaze(theHero.isHeroAlive());
                gameInfo();

            } else if (new String(validOptions).contains(move) && move.length() == 1) {
                checkOptions(move);

            } else {
                TextUI.invalidInput();
            }
        }
        mazeMap.revealMaze(theHero.isHeroAlive());
    }

    private static void setPositionOfPowerOnMaze() {
        Random randGen = new Random();
        Cell[][] mazeTemp = mazeMap.getMazeCells();

        int LAST_COL = 19;
        int LAST_ROW = 14;

        int range_X = LAST_COL - 1;
        int range_Y = LAST_ROW - 1;
        int randomPos_X = randGen.nextInt(range_X) + 1;
        int randomPos_Y = randGen.nextInt(range_Y) + 1;

        // Keep finding an OPEN CELL so that power is not placed on the same cell as Wall
        while (!mazeTemp[randomPos_Y][randomPos_X].getContent().equals(CellContent.EMPTY)) {
            randomPos_X = randGen.nextInt(range_X) + 1;
            randomPos_Y = randGen.nextInt(range_Y) + 1;
        }

        power = new Power(randomPos_X, randomPos_Y);

        setCurrentMoveEmpty("Power");
        mazeMap.setPowerInMaze(power);
    }

    // Set the current cell to be EMPTY AND/OR Visible (Visibility Only applies to hero!) before going to another cell!
    private static void setCurrentMoveEmpty(String inGameCharacter) {
        if (inGameCharacter.equals("Hero")) {
            int row = theHero.getHeroYPos();
            int column = theHero.getHeroXPos();
            mazeMap.setMazeCellContent(row, column, CellContent.EMPTY);
            mazeMap.setMazeCellVisible(row, column);
        } else if (inGameCharacter.equals("Power")) {
            int row = power.getPowerYPos();
            int column = power.getPowerXPos();
            mazeMap.setMazeCellContent(row, column, CellContent.EMPTY);
        }

    }

    private static void setPositionOfMonstersOnMaze() {
        for (Monster monster : monsters) {
            setPositionOfIndividualMonsterOnMaze(monster);
        }
    }

    private static void setPositionOfIndividualMonsterOnMaze(Monster monster) {
        boolean fought = false;

        if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.POWER)) {
            monster.setOccupyingMazeCellContent(CellContent.POWER);
        } else if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.MONSTER)) {
            monster.setOccupyingMazeCellContent(CellContent.MONSTER);
        } else if (mazeMap.getMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos())
                .equals(CellContent.HERO)) {
            monster.setOccupyingMazeCellContent(CellContent.HERO);
            fought = true;
        } else { // cell is empty!
            monster.setOccupyingMazeCellContent(CellContent.EMPTY);
        }
        // Always make the CellContent as Hero if they fought on the same cell - to show the result whether Hero Dead or Alive on Maze
        if(fought) {
            mazeMap.setHeroPositionInMaze(theHero);
        } else {
            mazeMap.setMonsterPositionInMaze(monster);
        }
    }

    private static void setPositionOfHeroOnMaze() {
        if (mazeMap.getMazeCellContent(theHero.getHeroYPos(), theHero.getHeroXPos())
                .equals(CellContent.POWER)) {
            theHero.incrementPowerCount();
            theHero.setOccupyingMazeCellContent(CellContent.HERO);
            setPositionOfPowerOnMaze();
        } else if (mazeMap.getMazeCellContent(theHero.getHeroYPos(), theHero.getHeroXPos()).equals(CellContent.MONSTER)) {
            Monster monster = checkWhichMonster();
            if(monster != null) {
                killHeroOrMonster(monster);
                if (!monster.isMonsterAlive()) {
                    removeDeadMonster(monster);
                    theHero.setOccupyingMazeCellContent(CellContent.HERO);
                }
            } else {
                theHero.killHero();
            }
        } else { // cell is empty!
            theHero.setOccupyingMazeCellContent(CellContent.EMPTY);
        }

        mazeMap.setHeroPositionInMaze(theHero);
    }

    private static Monster checkWhichMonster() {
        Monster monster = null;

        for (int i = 0; i < monsters.size(); i++) {
            if (theHero.getHeroYPos() == monsters.get(i).getMonsterYPos() &&
                    theHero.getHeroXPos() == monsters.get(i).getMonsterXPos()) {
                monster = monsters.get(i);
            }
        }
        return monster;
    }

    private static void removeDeadMonster(Monster monster) {
        monsters.remove(monster);
    }

    private static void checkHeroMove(String move) {
        boolean successfulMove = false;
        CellContent nextCellContent;

        while (!successfulMove) {

            switch (move) {
                case "w", "W" -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos() - 1, theHero.getHeroXPos());

                case "a", "A" -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos(), theHero.getHeroXPos() - 1);

                case "s", "S" -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos() + 1, theHero.getHeroXPos());

                case "d", "D" -> nextCellContent = mazeMap.getMazeCellContent
                        (theHero.getHeroYPos(), theHero.getHeroXPos() + 1);

                default -> {
                    move = TextUI.inputMove();
                    if(new String(validOptions).contains(move) && move.length() == 1) {
                        checkOptions(move);
                    }
                    else {
                        TextUI.invalidInput();
                    }
                    continue;
                }
            }

            if (nextCellContent == CellContent.WALL) {
                successfulMove = false;
                TextUI.invalidMove();
                move = TextUI.inputMove();
            } else if (nextCellContent == CellContent.POWER ||
                    nextCellContent == CellContent.EMPTY ||
                    nextCellContent == CellContent.MONSTER) {
                moveHero(move);
                setPositionOfHeroOnMaze();
                successfulMove = true;
            }
        }
    }

    private static void moveHero(String move) {
        setCurrentMoveEmpty("Hero");
        switch (move) {
            case "w", "W" -> theHero.moveHeroUp();
            case "a", "A" -> theHero.moveHeroLeft();
            case "s", "S" -> theHero.moveHeroDown();
            case "d", "D" -> theHero.moveHeroRight();
        }
    }

    private static void displayNeighbours() {

        // Up, Left, Down, Right
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() - 1, theHero.getHeroXPos());
        mazeMap.setMazeCellVisible(theHero.getHeroYPos(), theHero.getHeroXPos() - 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos());
        mazeMap.setMazeCellVisible(theHero.getHeroYPos(), theHero.getHeroXPos() + 1);

        // Diagonals
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos() - 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() - 1, theHero.getHeroXPos() + 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() - 1, theHero.getHeroYPos() - 1);
        mazeMap.setMazeCellVisible(theHero.getHeroYPos() + 1, theHero.getHeroXPos() + 1);

    }

    private static void moveAliveMonsters() {
        RandomDirection randomDir;
        List<String> randomDirs;
        boolean successfulMove;
        CellContent nextCellContent;

        for (int i = 0; i < monsters.size(); i++) {
            successfulMove = false;
            nextCellContent = CellContent.WALL;

            randomDir = new RandomDirection();
            randomDirs = randomDir.getRandomizedDir();

            while (!successfulMove) {
                for (String dir : randomDirs) {
                    if (!successfulMove) {
                        switch (dir) {
                            case "left" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monsters.get(i).getMonsterYPos(), monsters.get(i).getMonsterXPos() - 1);

                            case "right" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monsters.get(i).getMonsterYPos(), monsters.get(i).getMonsterXPos() + 1);

                            case "up" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monsters.get(i).getMonsterYPos() - 1, monsters.get(i).getMonsterXPos());

                            case "down" -> nextCellContent = mazeMap.getMazeCellContent(
                                    monsters.get(i).getMonsterYPos() + 1, monsters.get(i).getMonsterXPos());
                            default -> {
                                assert false : "Unknown direction!";
                            }
                        }

                        switch (nextCellContent) {
                            case WALL -> successfulMove = false;
                            case EMPTY, MONSTER, POWER -> {
                                moveIndividualMonster(monsters.get(i), dir);
                                setPositionOfIndividualMonsterOnMaze(monsters.get(i));
                                successfulMove = true;
                            }
                            case HERO -> {
                                moveIndividualMonster(monsters.get(i), dir);
                                killHeroOrMonster(monsters.get(i));

                                if (monsters.get(i).isMonsterAlive()) {
                                    setPositionOfIndividualMonsterOnMaze(monsters.get(i));
                                } else {
                                    removeDeadMonster(monsters.get(i));
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

    // Checks if more than one monster occupies the same cell
    private static int isMoreThanOne(Monster monster) {
        int count = 0;
        for(int i = 0; i < monsters.size(); i++) {
            int other_monster_row = monsters.get(i).getMonsterYPos();
            int other_monster_column = monsters.get(i).getMonsterXPos();
            if(other_monster_row == monster.getMonsterYPos() && other_monster_column == monster.getMonsterXPos()) {
                count += 1;
            }
        }
        System.out.println(count);
        return count;
    }

    // Checks if power and monster occupy same cell
    private static boolean checkPowerMonsterSameCell() {
        boolean flag = false;
        for(int i = 0; i < monsters.size(); i++) {
            int other_monster_row = monsters.get(i).getMonsterYPos();
            int other_monster_column = monsters.get(i).getMonsterXPos();
            if(other_monster_row == power.getPowerYPos() && other_monster_column == power.getPowerXPos()) {
                flag = true;
            }
        }
        return flag;
    }

    private static void moveIndividualMonster(Monster monster, String direction) {

        // Idea: Sets the current cell to either empty, monster, or power before it moves to next cell!
        // Checks if the monster thinks he is occupying a cell in which he's alone! : Checks if a cell only contains 1 monster
        if(monster.getOccupyingMazeCellContent() == CellContent.MONSTER && isMoreThanOne(monster) == 1) {

            // if the one lone monster is occupying the cell which is the same cell with the Power!
            if(checkPowerMonsterSameCell() ) {
                monster.setOccupyingMazeCellContent(CellContent.POWER);
            } else {
                monster.setOccupyingMazeCellContent(CellContent.EMPTY);
            }
        }

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
        if (numberOfPowers >= 1) {
            theHero.decrementPowerCount();
            monster.killMonster();
        } else {
            theHero.killHero();
        }
    }

    private static void checkOptions(String symbol) {
        int monstersAlive = monsters.size();
        switch (symbol) {
            case "?" -> gameInfo();
            case "m" -> {
                mazeMap.revealMaze(theHero.isHeroAlive());
                gameInfo();
            }
            case "c" -> {
                if (monstersAlive == TOTAL_NUMBER_OF_MONSTERS) {
                    cheatActive = true;
                }
            }
        }
    }

    private static void gameInfo() {
        int monstersAlive = monsters.size();

        if (!cheatActive) {
            TextUI.gameInfo(monstersAlive, theHero.getPowerCount(), monstersAlive);
        } else {
            TextUI.gameInfo(monstersAlive - 2, theHero.getPowerCount(), monstersAlive);
        }
    }
} // Main.java