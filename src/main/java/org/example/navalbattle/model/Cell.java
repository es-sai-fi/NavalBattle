package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Cell extends Pane {
    private int row;
    private int column;
    private Ship ship;
    private boolean hasBeenAttacked = false;
    private boolean occupied;

    public Cell (int row, int column, NavalBattle navalBattle){
        this.setOnMouseClicked(event -> {
            navalBattle.launchAttack(row, column);
        });
        this.row = row;
        this.column = column;
    }

    public Cell(int row, int column) {
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

    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }
}