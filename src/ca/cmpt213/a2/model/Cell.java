package ca.cmpt213.a2.model;

public class Cell {
    private CellContent content = CellContent.WALL;
    private Boolean isExplored = false;

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        switch (content) {
            case WALL -> this.content = CellContent.WALL;
            case HERO -> this.content = CellContent.HERO;
            case MONSTER -> this.content = CellContent.MONSTER;
            case POWER -> this.content = CellContent.POWER;
            default -> { assert false; }
        }
    }

    public Boolean getExplored() {
        return isExplored;
    }

    public void setExplored(Boolean explored) {
        isExplored = explored;
    }
}
