package org.example.navalbattle.model;

public class ShipDrawingData {
    public int row;
    public int column;
    public int type;
    public boolean isVertical;

    public ShipDrawingData(int row, int column, int type, boolean isVertical) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.isVertical = isVertical;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getType() {
        return type;
    }
}


