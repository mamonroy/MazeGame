package ca.cmpt213.a2.model;

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

    public void setVisible(Boolean visible) {
        isInvisible = visible;
    }

    public Boolean getVisibility() {
        return isInvisible;
    }

    public Boolean getExplored() {
        return isExplored;
    }

    public void setExplored() {
        isExplored = true;
    }

//    public void setBordersUnexplored() {
//        isExplored = false;
//    }
} // Cell.java