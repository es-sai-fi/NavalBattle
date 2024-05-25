package org.example.sudoku.model;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Cell {
    private StackPane stackPane;
    private Button button;
    private ImageView imageView;
    private char xCoordinate;
    private char yCoordinate;
    private ShipNode shipNode;

    public Cell (StackPane stackPane, Button button, ImageView imageView, char xCoordinate, char yCoordinate, ShipNode shipNode){
        this.stackPane = stackPane;
        this.button = button;
        this.imageView = imageView;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.shipNode = shipNode;
    }
}
