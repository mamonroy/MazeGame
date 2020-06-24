package ca.cmpt213.a2.model;

public class Hero {
    private static int powerCount = 0;
    private final Position heroPosition = new Position(1, 1);
    private boolean isAlive = true;


    public static int getPowerCount() {
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

    public boolean getHeroLifeStatus() {
        return isAlive;
    }

    public void killHero() {
        isAlive = false;
    }
} // Hero.java
