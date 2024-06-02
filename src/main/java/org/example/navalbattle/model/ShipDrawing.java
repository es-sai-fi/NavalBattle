package org.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

public class ShipDrawing extends Pane implements Serializable {
    private int width, height;
    private int type;
    private Color color;
    private Rectangle rectangle;
    private Group boatGroup;
    private boolean hasBeenPlaced;
    private boolean isVertical = true;

    public ShipDrawing(int type, boolean isVertical) {
        boatGroup = new Group();
        this.isVertical = isVertical;
        this.type = type;
        width = 50;
        height = 50 * type;
        this.rectangle = new Rectangle(width, height, color);
        this.type = type;
        this.color = determinarColor(type);
        if(!isVertical){
            rotate();
        }
        boatGroup.getChildren().add(rectangle);
        this.getChildren().add(boatGroup);
        draw();
    }

    public Group getBoatGroup() {
        return boatGroup;
    }

    private Color determinarColor(int type) {
        switch (type) {
            case 4:
                return Color.GRAY;
            case 3:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 1:
                return Color.RED;
            default:
                return Color.BLACK;
        }
    }

    private void draw() {
        rectangle.setFill(color);
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(15);

        // Añadir detalles adicionales
        addDetails();
    }

    private void addDetails() {
        // Añadir ventanas como círculos
        int windowCount = width / 20;
        for (int i = 0; i < windowCount; i++) {
            Circle window = new Circle(5, Color.WHITE);
            window.setStroke(Color.BLACK);
            window.setCenterX((i * 20) + 10);
            window.setCenterY(height / 4);
            boatGroup.getChildren().add(window);
        }
    }

    public void rotate() {
        if(!hasBeenPlaced){
            int temp = width;
            width = height;
            height = temp;
            rectangle.setWidth(width);
            rectangle.setHeight(height);
        }
    }

    public boolean hasBeenPlaced() {
        return hasBeenPlaced;
    }

    public void setHasBeenPlaced(boolean hasBeenPlaced) {
        this.hasBeenPlaced = hasBeenPlaced;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public int getType() {
        return type;
    }
}