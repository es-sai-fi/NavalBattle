package org.example.navalbattle.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ShipDrawing extends Pane {
    private int x, y, width, height;
    private String type;
    private Color color;
    private Rectangle rectangle;

    public ShipDrawing(int x, int y, int width, int height, String type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.color = determinarColor(type);
        this.rectangle = new Rectangle(width, height, color);
        this.getChildren().add(rectangle);
        setPosition(x, y);
        draw();
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
}


