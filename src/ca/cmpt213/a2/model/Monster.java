package ca.cmpt213.a2.model;

/**
 * A class for holding information of game monster(s).
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.sfu)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Monster {
    private final Position monsterPosition;
    private CellContent occupyingMazeCellContent = CellContent.EMPTY;
    private boolean isAlive = true;

    public Monster(int xPos, int yPos) {
        monsterPosition = new Position(xPos, yPos);
    }

    public void moveMonsterLeft() {
        monsterPosition.moveLeft();
    }

    public void moveMonsterRight() {
        monsterPosition.moveRight();
    }

    public void moveMonsterUp() {
        monsterPosition.moveUp();
    }

    public void moveMonsterDown() {
        monsterPosition.moveDown();
    }

    public int getMonsterXPos() {
        return monsterPosition.getXPosition();
    }

    public int getMonsterYPos() {
        return monsterPosition.getYPosition();
    }

    public CellContent getOccupyingMazeCellContent() {
        return occupyingMazeCellContent;
    }

    public void setOccupyingMazeCellContent(CellContent occupyingMazeCellContent) {
        this.occupyingMazeCellContent = occupyingMazeCellContent;
    }

    public boolean isMonsterAlive() {
        return isAlive;
    }

    public void killMonster() {
        isAlive = false;
    }
} // Monster.java
