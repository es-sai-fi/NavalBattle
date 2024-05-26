package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class Cell extends Node {
    private StackPane stackPane;
    private Rectangle rectangle;
    private ImageView imageView;
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean hasBeenAttacked = false;
    private ShipNode shipNode;

    public Cell (int row, int column){
        Rectangle rectangle = new Rectangle(50,50);
        rectangle.setStyle("-fx-background-color: blue");
        ImageView imageView = new ImageView();
        StackPane stackPane = new StackPane(rectangle, imageView);
        this.row = row;
        this.column = column;
    }

    public void setShipNode(ShipNode shipNode) {
        this.shipNode = shipNode;
        isOccupied = true;
    }

    public ShipNode getShipNode() {
        return shipNode;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

}
