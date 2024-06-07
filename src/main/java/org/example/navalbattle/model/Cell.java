/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.example.navalbattle.controller.GameController;

import java.io.Serializable;

/**
 * Represents a single cell in the game board grid.
 * This class holds the state of the cell including its position, whether it has been attacked, and whether it is occupied by a ship.
 */
public class Cell extends Pane implements Serializable {
    private int row;
    private int column;
    private Ship ship;
    private boolean hasBeenAttacked = false;
    private boolean occupied;

    /**
     * Constructs a Cell with the specified row and column.
     *
     * @param row the row index of the cell
     * @param column the column index of the cell
     */
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Sets the ship occupying this cell.
     *
     * @param ship the ship to set
     */
    public void setShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Gets the ship occupying this cell.
     *
     * @return the ship occupying this cell
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Checks if the cell is occupied by a ship.
     *
     * @return true if the cell is occupied, false otherwise
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Sets whether the cell is occupied by a ship.
     *
     * @param occupied true if the cell is occupied, false otherwise
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Checks if the cell has been attacked.
     *
     * @return true if the cell has been attacked, false otherwise
     */
    public boolean hasBeenAttacked() {
        return hasBeenAttacked;
    }

    /**
     * Sets whether the cell has been attacked.
     *
     * @param hasBeenAttacked true if the cell has been attacked, false otherwise
     */
    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }

    /**
     * Gets the column index of the cell.
     *
     * @return the column index of the cell
     */
    public int getColumn() {
        return column;
    }

    /**
     * Gets the row index of the cell.
     *
     * @return the row index of the cell
     */
    public int getRow() {
        return row;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
