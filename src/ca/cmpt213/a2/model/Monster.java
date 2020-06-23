package ca.cmpt213.a2.model;

public class Monster {
    private Position monsterPosition;
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

    public boolean getMonsterLifeStatus() {
        return isAlive;
    }

    public void killMonster() {
        isAlive = false;
    }
} // Monster.java
