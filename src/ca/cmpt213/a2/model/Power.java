package ca.cmpt213.a2.model;

public class Power {

    private Position powerPosition;

    public Power(int xPos, int yPos) {
        powerPosition = new Position(xPos,yPos);
    }

    public int getPowerXPos() {
        return powerPosition.getXPosition();
    }

    public int getPowerYPos() {
        return powerPosition.getYPosition();
    }

} // Power.java