package org.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShipDrawing extends Pane {
    private int width, height;
    private int type;
    private Color color;
    private Rectangle rectangle;
    private Group boatGroup;

    public ShipDrawing(int width, int height, int type) {
        boatGroup = new Group();
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = determinarColor(type);
        this.rectangle = new Rectangle(width, height, color);
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
    }

    public void rotate() {
        double temp = this.width;
        this.width = this.height;
        this.height = (int) temp;

        rectangle.setWidth(width);
        rectangle.setHeight(height);

        this.setRotate(this.getRotate() + 90);
    }
}
