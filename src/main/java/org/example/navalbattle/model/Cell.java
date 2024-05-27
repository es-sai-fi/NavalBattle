package org.example.navalbattle.model;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import org.example.navalbattle.controller.GameController;

public class Cell extends Pane {
    private StackPane stackPane;
    private Rectangle rectangle;
    private ImageView imageView;
    private int row;
    private int column;
    private Ship ship;
    private boolean hasBeenAttacked = false;
    private boolean occupied;

    public Cell (int row, int column, NavalBattle navalBattle){
        rectangle = new Rectangle(50,50);
        rectangle.setOnMouseClicked(event -> {
            navalBattle.launchAttack(row, column);
        });
        rectangle.setStyle("-fx-background-color: blue");
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        stackPane = new StackPane(rectangle, imageView);
        this.row = row;
        this.column = column;
    }
    public Cell (int row, int column){
        rectangle = new Rectangle(50,50);
        rectangle.setStyle("-fx-background-color: blue");
        imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
        stackPane = new StackPane(rectangle, imageView);
        this.row = row;
        this.column = column;
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

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean getHasBeenAttacked() {
        return hasBeenAttacked;
    }
    public void setImage(String url){
        imageView.setImage(new Image(String.valueOf(getClass().getResource(url))));
    }
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    public void setHasBeenAttacked(boolean hasBeenAttacked) {
        this.hasBeenAttacked = hasBeenAttacked;
    }
}
