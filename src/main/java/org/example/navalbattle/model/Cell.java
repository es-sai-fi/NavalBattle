package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Cell extends Node {
    private StackPane stackPane;
    private Rectangle rectangle;
    private ImageView imageView;
    private int row;
    private int column;
    private Ship ship;
    private boolean hasBeenAttacked = false;
    private boolean occupied;
    private boolean leftNeighbor;
    private boolean rightNeighbor;
    private boolean upNeighbor;
    private boolean downNeighbor;

    public Cell (int row, int column, boolean enemyCell, NavalBattle navalBattle){
        Rectangle rectangle = new Rectangle(50,50);
        rectangle.setStyle("-fx-background-color: blue");
        ImageView imageView = new ImageView();
        StackPane stackPane = new StackPane(rectangle, imageView);
        this.row = row;
        this.column = column;

        if(enemyCell){

        }
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip(){
        return ship;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRightNeighbor(boolean rightNeighbor) {
        this.rightNeighbor = rightNeighbor;
    }

    public void setLeftNeighbor(boolean leftNeighbor) {
        this.leftNeighbor = leftNeighbor;
    }

    public void setDownNeighbor(boolean downNeighbor) {
        this.downNeighbor = downNeighbor;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void setUpNeighbor(boolean upNeighbor) {
        this.upNeighbor = upNeighbor;
    }

    public boolean hasRightNeighbor(){
        return rightNeighbor;
    }

    public boolean hasLeftNeighbor(){
        return leftNeighbor;
    }

    public boolean hasUpNeighbor(){
        return upNeighbor;
    }

    public boolean hasDownNeighbor(){
        return downNeighbor;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}
