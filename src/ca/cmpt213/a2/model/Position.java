package ca.cmpt213.a2.model;

public class Position {
    private static final int MAZE_BORDER_LIMIT_LEFT = 0;
    private static final int MAZE_BORDER_LIMIT_RIGHT = 18;
    private static final int MAZE_BORDER_LIMIT_TOP = 0;
    private static final int MAZE_BORDER_LIMIT_BOTTOM = 13;

    private int xPosition;
    private int yPosition;

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
