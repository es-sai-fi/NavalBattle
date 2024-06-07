/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

import java.io.Serializable;

/**
 * Represents the data associated with the drawing of a ship in the naval battle game.
 * This data includes the ship's position, type, and orientation.
 */
public class ShipDrawingData implements Serializable {
    public int row;
    public int column;
    public int type;
    public boolean isVertical;

    /**
     * Constructs a ShipDrawingData with the specified position, type, and orientation.
     *
     * @param row       the row position of the ship
     * @param column    the column position of the ship
     * @param type      the type of the ship
     * @param isVertical whether the ship is oriented vertically
     */
    public ShipDrawingData(int row, int column, int type, boolean isVertical) {
        this.row = row;
        this.column = column;
        this.type = type;
        this.isVertical = isVertical;
    }

    /**
     * Checks if the ship is oriented vertically.
     *
     * @return {@code true} if the ship is vertical, {@code false} otherwise
     */
    public boolean isVertical() {
        return isVertical;
    }

    /**
     * Returns the column position of the ship.
     *
     * @return the column position of the ship
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the row position of the ship.
     *
     * @return the row position of the ship
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the type of the ship.
     * The type determines the size and other characteristics of the ship.
     *
     * @return the type of the ship
     */
    public int getType() {
        return type;
    }
}
