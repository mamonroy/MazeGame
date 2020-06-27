package ca.cmpt213.a2.model;

/**
 * A class for holding 2D position information of
 * game characters (Hero/Monsters) and power in the maze.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.ca)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Position {
    private static final int MAZE_BORDER_LIMIT_LEFT = 0;
    private static final int MAZE_BORDER_LIMIT_RIGHT = 18;
    private static final int MAZE_BORDER_LIMIT_TOP = 0;
    private static final int MAZE_BORDER_LIMIT_BOTTOM = 13;

    private int xPosition; // corresponds to column number in the maze
    private int yPosition; // corresponds to row number in the maze

    public Position(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void moveLeft() {
        if (xPosition > MAZE_BORDER_LIMIT_LEFT) {
            xPosition--;
        }
    }

    public void moveRight() {
        if (xPosition < MAZE_BORDER_LIMIT_RIGHT) {
            xPosition++;
        }
    }

    public void moveUp() {
        if (yPosition > MAZE_BORDER_LIMIT_TOP) {
            yPosition--;
        }
    }

    public void moveDown() {
        if (yPosition < MAZE_BORDER_LIMIT_BOTTOM) {
            yPosition++;
        }
    }
} // Position.java