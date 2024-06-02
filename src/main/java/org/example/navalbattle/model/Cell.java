package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.example.navalbattle.controller.GameController;

import java.io.Serializable;

public class Cell extends Pane  implements Serializable {
    private int row;
    private int column;
    private Ship ship;
    private boolean hasBeenAttacked = false;
    private boolean occupied;

    public Cell (int row, int column){
        this.row = row;
        this.column = column;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip(){
        return ship;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean hasBeenAttacked() {
        return hasBeenAttacked;
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}