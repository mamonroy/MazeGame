package ca.cmpt213.a2.model;

import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Maze {
    private static final int MAZE_WIDTH = 20;
    private static final int MAZE_HEIGHT = 15;
    private static final int FIRST_ROW = 0;
    private static final int FIRST_COL = 0;
    private static final int LAST_ROW = MAZE_HEIGHT - 1;
    private static final int LAST_COL = MAZE_WIDTH - 1;

    private static final int HERO_INITIAL_ROW = FIRST_ROW + 1;
    private static final int HERO_INITIAL_COL = FIRST_COL + 1;

    private static final int NUM_INNER_WALLS_TO_BE_REMOVED = 30;

    // A maze with 20 columns (20 cells wide) and 15 rows (15 cells tall)
    // Maze rows are visualized and counted from top to bottom, left to right
    private static final Cell[][] mazeCells = new Cell[MAZE_HEIGHT][MAZE_WIDTH];

    public Maze() {

        for (int i = FIRST_ROW; i < MAZE_HEIGHT; i++) {
            for (int j = FIRST_COL; j < MAZE_WIDTH; j++) {
                mazeCells[i][j] = new Cell();
            }
        }

        setBorderWallsAsExplored();
        generateMaze(HERO_INITIAL_ROW, HERO_INITIAL_COL); // using iterative backtracker algorithm
        clearWallsAtCorners(); // where the hero and monsters spawn
        removeUnwantedInnerWalls(); // to allow multiple possible paths
        setInvisibleCells();
    }

    public static int getMazeWidth() {
        return MAZE_WIDTH;
    }

    public static int getMazeHeight() {
        return MAZE_HEIGHT;
    }

    private void setBorderWallsAsExplored() {

        for (int row = FIRST_ROW; row < MAZE_HEIGHT; row++) {
            mazeCells[row][FIRST_COL].setExplored();
            mazeCells[row][LAST_COL].setExplored();
        }

        for (int col = FIRST_COL; col < MAZE_WIDTH; col++) {
            mazeCells[FIRST_ROW][col].setExplored();
            mazeCells[LAST_ROW][col].setExplored();
        }
    }

    private void setInvisibleCells() {

        for (int i = 1; i < MAZE_HEIGHT - 1; i++) {
            for (int j = 1; j < MAZE_WIDTH - 1; j++) {
                mazeCells[i][j].setVisible(false);
            }
        }
    }

    private void setCellAsExplored(int rowNum, int colNum) {
        mazeCells[rowNum][colNum].setExplored();
    }

    private void generateMaze(int rowNum, int colNum) {
        setCellContentEmpty(rowNum, colNum);
        setCellAsExplored(rowNum, colNum);

        iterativeBacktracker(rowNum, colNum);
    }

    private void setCellContentEmpty(int rowNum, int colNum) {
        mazeCells[rowNum][colNum].setContent(CellContent.EMPTY);
    }

    // Algorithm Source: https://en.wikipedia.org/wiki/Maze_generation_algorithm
    private void iterativeBacktracker(int rowNum, int colNum) {
        Stack<Integer> cellIndexStack = new Stack<>();
        cellIndexStack.push(rowNum);
        cellIndexStack.push(colNum);

        RandomDirection randomDir;
        List<String> randomDirs;

        int currentRowNum;
        int currentColNum;

        while (!cellIndexStack.isEmpty()) {
            currentColNum = cellIndexStack.pop();
            currentRowNum = cellIndexStack.pop();

            randomDir = new RandomDirection();
            randomDirs = randomDir.getRandomizedDir();

            for (String dir : randomDirs) {
                switch (dir) {
                    case "left" -> {
                        if (currentColNum - 2 >= FIRST_COL) { // Make sure we're not at the left wall boundary
                            // Remember that the neighbouring cell is 2 cells away, in-between walls don't count!
                            if (!mazeCells[currentRowNum][currentColNum - 2].getExplored()) {
                                // Remove the wall between current cell and its unexplored neighbour
                                setCellContentEmpty(currentRowNum, currentColNum - 1);
                                // Set the cell where wall used to be as explored!
                                setCellAsExplored(currentRowNum, currentColNum - 1);

                                // Set the previously unexplored neighbour as empty and explored!
                                setCellContentEmpty(currentRowNum, currentColNum - 2);
                                setCellAsExplored(currentRowNum, currentColNum - 2);

                                // Push the coordinates of the neighbour onto stack!
                                cellIndexStack.push(currentRowNum);
                                cellIndexStack.push(currentColNum - 2);
                            }
                        }
                    }
                    case "right" -> {
                        if (currentColNum + 2 < MAZE_WIDTH) { // Make sure we're not at the right wall boundary
                            // Remember that the neighbouring cell is 2 cells away, in-between walls don't count!
                            if (!mazeCells[currentRowNum][currentColNum + 2].getExplored()) {
                                // Remove the wall between current cell and its unexplored neighbour
                                setCellContentEmpty(currentRowNum, currentColNum + 1);
                                // Set the cell where wall used to be as explored!
                                setCellAsExplored(currentRowNum, currentColNum + 1);

                                // Set the previously unexplored neighbour as empty and explored!
                                setCellContentEmpty(currentRowNum, currentColNum + 2);
                                setCellAsExplored(currentRowNum, currentColNum + 2);

                                // Push the coordinates of the neighbour onto stack!
                                cellIndexStack.push(currentRowNum);
                                cellIndexStack.push(currentColNum + 2);
                            }
                        }
                    }
                    case "up" -> {
                        if (currentRowNum - 2 >= FIRST_ROW) { // Make sure we're not at the top wall boundary
                            // Remember that the neighbouring cell is 2 cells away, in-between walls don't count!
                            if (!mazeCells[currentRowNum - 2][currentColNum].getExplored()) {
                                // Remove the wall between current cell and its unexplored neighbour
                                setCellContentEmpty(currentRowNum - 1, currentColNum);
                                // Set the cell where wall used to be as explored!
                                setCellAsExplored(currentRowNum - 1, currentColNum);

                                // Set the previously unexplored neighbour as empty and explored!
                                setCellContentEmpty(currentRowNum - 2, currentColNum);
                                setCellAsExplored(currentRowNum - 2, currentColNum);

                                // Push the coordinates of the neighbour onto stack!
                                cellIndexStack.push(currentRowNum - 2);
                                cellIndexStack.push(currentColNum);
                            }
                        }
                    }
                    case "down" -> {
                        if (currentRowNum + 2 < MAZE_HEIGHT) { // Make sure we're not at the bottom wall boundary
                            // Remember that the neighbouring cell is 2 cells away, in-between walls don't count!
                            if (!mazeCells[currentRowNum + 2][currentColNum].getExplored()) {
                                // Remove the wall between current cell and its unexplored neighbour
                                setCellContentEmpty(currentRowNum + 1, currentColNum);
                                // Set the cell where wall used to be as explored!
                                setCellAsExplored(currentRowNum + 1, currentColNum);

                                // Set the previously unexplored neighbour as empty and explored!
                                setCellContentEmpty(currentRowNum + 2, currentColNum);
                                setCellAsExplored(currentRowNum + 2, currentColNum);

                                // Push the coordinates of the neighbour onto stack!
                                cellIndexStack.push(currentRowNum + 2);
                                cellIndexStack.push(currentColNum);
                            }
                        }
                    }
                    default -> {
                        assert false : "Unknown direction!";
                    }
                }
            }
        }
    }

    private void clearWallsAtCorners() {
        // Already done in maze generation process
        // Included here for code completeness!
        setCellContentEmpty(FIRST_ROW + 1, FIRST_COL + 1); // Hero spawns here!

        setCellContentEmpty(FIRST_ROW + 1, LAST_COL - 1); // First monster spawns here!
        setCellContentEmpty(LAST_ROW - 1, FIRST_COL + 1); // Second monster spawns here!
        setCellContentEmpty(LAST_ROW - 1, LAST_COL - 1); // Third monster spawns here!
    }

    private void removeUnwantedInnerWalls() {
        // Source: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
        Random rand = new Random();
        int wallCellRowToBeRemoved;
        int wallCellColToBeRemoved;
        int numWallsToBeRemoved = NUM_INNER_WALLS_TO_BE_REMOVED;

        while (numWallsToBeRemoved > 0) {
            wallCellRowToBeRemoved = rand.nextInt(LAST_ROW - 2) + 1;
            wallCellColToBeRemoved = rand.nextInt(LAST_COL - 2) + 1;
            if (mazeCells[wallCellRowToBeRemoved][wallCellColToBeRemoved].getContent().equals(CellContent.WALL) &&
                    checkTwoByTwoConstraint(wallCellRowToBeRemoved, wallCellColToBeRemoved)) {
                setCellContentEmpty(wallCellRowToBeRemoved, wallCellColToBeRemoved);
                numWallsToBeRemoved--;
            }
        }
    }

    private boolean checkTwoByTwoConstraint(int rowNum, int colNum) {
        return checkUpRight(rowNum, colNum) &&
                checkUpLeft(rowNum, colNum) &&
                checkDownRight(rowNum, colNum) &&
                checkDownLeft(rowNum, colNum);
    }

    private boolean checkUpRight(int rowNum, int colNum) {
        return mazeCells[rowNum - 1][colNum].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum - 1][colNum + 1].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum][colNum + 1].getContent().equals(CellContent.WALL);
    }

    private boolean checkUpLeft(int rowNum, int colNum) {
        return mazeCells[rowNum - 1][colNum].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum - 1][colNum - 1].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum][colNum - 1].getContent().equals(CellContent.WALL);
    }

    private boolean checkDownRight(int rowNum, int colNum) {
        return mazeCells[rowNum + 1][colNum].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum + 1][colNum + 1].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum][colNum + 1].getContent().equals(CellContent.WALL);
    }

    private boolean checkDownLeft(int rowNum, int colNum) {
        return mazeCells[rowNum + 1][colNum].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum + 1][colNum - 1].getContent().equals(CellContent.WALL) ||
                mazeCells[rowNum][colNum - 1].getContent().equals(CellContent.WALL);
    }

    public CellContent getMazeCellContent(int rowNum, int colNum) {
        return mazeCells[rowNum][colNum].getContent();
    }

    public void setMazeCellContent(int rowNum, int colNum, CellContent content) {
        mazeCells[rowNum][colNum].setContent(content);
    }

    public Cell[][] getMazeCells() {
        return mazeCells;
    }

    public void putHeroPosition(Hero hero) {
        setMazeCellContent(hero.getHeroYPos(), hero.getHeroYPos(), CellContent.HERO);
    }

    public void putMonsterPosition(Monster monster) {
        setMazeCellContent(monster.getMonsterYPos(), monster.getMonsterXPos(), CellContent.MONSTER);
    }

    public void putPowerRandomly() {

        Random randGen = new Random();
        int range_X = 18;
        int range_Y = 13;
        int randomPos_X = randGen.nextInt(range_X) + 1;
        int randomPos_Y = randGen.nextInt(range_Y) + 1;
        Power pow = new Power(randomPos_X, randomPos_Y);
        int row = pow.getPowerYPos();
        int column = pow.getPowerXPos();

        // Keep finding an OPEN CELL so that power is not placed on the same cell as hero/monster/wall
        while(mazeCells[row][column].getContent() != CellContent.EMPTY &&
                mazeCells[row][column].getContent() != CellContent.MONSTER) {

            randomPos_X = randGen.nextInt(range_X) + 1;
            randomPos_Y = randGen.nextInt(range_Y) + 1;
            pow = new Power(randomPos_X, randomPos_Y);
            row = pow.getPowerYPos();
            column = pow.getPowerXPos();
        }

        setMazeCellContent(row,column, CellContent.POWER);
    }

    public void displayCurrMaze() {

        for (int y = 0; y < MAZE_HEIGHT; y ++) {
            for (int x = 0; x < MAZE_WIDTH; x++) {
                if (mazeCells[y][x].getContent() == CellContent.EMPTY && mazeCells[y][x].getVisibility() == true) {
                    System.out.printf("%s", " ");
                } else if (mazeCells[y][x].getContent() == CellContent.WALL && mazeCells[y][x].getVisibility() == true) {
                    System.out.printf("%s", "#");
                } else if (mazeCells[y][x].getContent() == CellContent.HERO) {
                    System.out.printf("%s", "@");
                } else if (mazeCells[y][x].getContent() == CellContent.MONSTER) {
                    System.out.printf("%s", "!");
                } else if (mazeCells[y][x].getContent() == CellContent.POWER) {
                    System.out.printf("%s", "$");
                } else {
                    System.out.printf("%s", ".");
                }
            }
            System.out.printf("\n");
        }
    }

    public void revealMaze() {

        for (int y = 0; y < MAZE_HEIGHT; y ++) {
            for (int x = 0; x < MAZE_WIDTH; x++) {
                if (mazeCells[y][x].getContent() == CellContent.EMPTY) {
                    System.out.printf("%s", " ");
                } else if (mazeCells[y][x].getContent() == CellContent.WALL) {
                    System.out.printf("%s", "#");
                } else if (mazeCells[y][x].getContent() == CellContent.HERO) {
                    System.out.printf("%s", "@");
                } else if (mazeCells[y][x].getContent() == CellContent.MONSTER) {
                    System.out.printf("%s", "!");
                } else{
                    System.out.printf("%s", "$");
                }
            }
            System.out.printf("\n");
        }
    }
} // Maze.java
