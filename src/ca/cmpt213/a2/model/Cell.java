package ca.cmpt213.a2.model;

/**
 * A class for holding information of maze cells.
 *
 * @author Mark Angelo Monroy (Student ID: 301326143, SFU ID: mamonroy@sfu.sfu)
 * @author Kash Khodabakhshi (Student ID: 301203001, SFU ID: kkhodaba@sfu.ca)
 */
public class Cell {
    private CellContent content = CellContent.WALL;
    private Boolean isExplored = false;
    private Boolean isInvisible = true;

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        switch (content) {
            case EMPTY -> this.content = CellContent.EMPTY;
            case WALL -> this.content = CellContent.WALL;
            case HERO -> this.content = CellContent.HERO;
            case MONSTER -> this.content = CellContent.MONSTER;
            case POWER -> this.content = CellContent.POWER;
            default -> {
                assert false : "Unknown cell content!";
            }
        }
    }

    public Boolean getVisibility() {
        return isInvisible;
    }

    public void setVisibility(Boolean visibility) {
        isInvisible = visibility;
    }

    public Boolean getExplored() {
        return isExplored;
    }

    public void setExplored() {
        isExplored = true;
    }
} // Cell.java