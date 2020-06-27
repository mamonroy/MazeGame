package ca.cmpt213.a2.model;

/**
 * A class for holding information of hero power.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.ca)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Power {
    private final Position powerPosition;

    public Power(int xPos, int yPos) {
        powerPosition = new Position(xPos, yPos);
    }

    public int getPowerXPos() {
        return powerPosition.getXPosition();
    }

    public int getPowerYPos() {
        return powerPosition.getYPosition();
    }
} // Power.java