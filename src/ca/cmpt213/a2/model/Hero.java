package ca.cmpt213.a2.model;

/**
 * A class for holding information of game hero.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.sfu)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Hero {
    private static int powerCount = 0;
    private final Position heroPosition;
    private CellContent occupyingMazeCellContent = CellContent.EMPTY;
    private boolean isAlive = true;

    public Hero(int xPos, int yPos) {
        heroPosition = new Position(xPos, yPos);
    }

    public int getPowerCount() {
        return powerCount;
    }

    public void incrementPowerCount() {
        powerCount++;
    }

    public void decrementPowerCount() {
        powerCount--;
    }

    public void moveHeroLeft() {
        heroPosition.moveLeft();
    }

    public void moveHeroRight() {
        heroPosition.moveRight();
    }

    public void moveHeroUp() {
        heroPosition.moveUp();
    }

    public void moveHeroDown() {
        heroPosition.moveDown();
    }

    public int getHeroXPos() {
        return heroPosition.getXPosition();
    }

    public int getHeroYPos() {
        return heroPosition.getYPosition();
    }

    public boolean isHeroAlive() {
        return isAlive;
    }

    public void killHero() {
        isAlive = false;
    }

    public void setOccupyingMazeCellContent(CellContent occupyingMazeCellContent) {
        this.occupyingMazeCellContent = occupyingMazeCellContent;
    }
} // Hero.java