package org.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShipDrawing extends Pane {
    private int x, y, width, height;
    private String type;
    private Color color;
    private Rectangle rectangle;
    private Group boatGroup;

    private double initialMouseX, initialMouseY;
    private double initialTranslateX, initialTranslateY;

    public ShipDrawing(int x, int y, int width, int height, String type) {
        boatGroup = new Group();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = determinarColor(type);
        this.rectangle = new Rectangle(width, height, color);
        boatGroup.getChildren().add(rectangle);
        this.getChildren().add(boatGroup);

        this.setOnMousePressed(this::onMousePressed);
        this.setOnMouseDragged(this::onMouseDragged);
        this.setOnMouseReleased(this::onMouseReleased);

        draw();
    }

    public Group getBoatGroup() {
        return boatGroup;
    }

    private Color determinarColor(String type) {
        switch (type) {
            case "aircraftCarrier":
                return Color.GRAY;
            case "submarine":
                return Color.BLUE;
            case "destroyer":
                return Color.GREEN;
            case "frigate":
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

    public void setPosition(int x, int y) {
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void rotate() {
        double temp = this.width;
        this.width = this.height;
        this.height = (int) temp;

        rectangle.setWidth(width);
        rectangle.setHeight(height);

        this.setRotate(this.getRotate() + 90);
    }

    private void onMousePressed(MouseEvent event) {
        initialMouseX = event.getSceneX();
        initialMouseY = event.getSceneY();
        initialTranslateX = this.getTranslateX();
        initialTranslateY = this.getTranslateY();
    }

    private void onMouseDragged(MouseEvent event) {
        double offsetX = event.getSceneX() - initialMouseX;
        double offsetY = event.getSceneY() - initialMouseY;
        this.setTranslateX(initialTranslateX + offsetX);
        this.setTranslateY(initialTranslateY + offsetY);
    }

    private void onMouseReleased(MouseEvent event) {
        // El método onMouseReleased se manejará en el PositionController
    }
}
