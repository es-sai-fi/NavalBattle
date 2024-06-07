/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.effect.DropShadow;

import java.io.Serializable;

/**
 * Represents the visual representation of a ship in the naval battle game.
 * The ship drawing includes graphical details such as color, shape, and additional visual elements.
 */
public class ShipDrawing extends Pane implements Serializable {
    private int width, height;
    private int type;
    private Color color;
    private Rectangle rectangle;
    private Group boatGroup;
    private boolean hasBeenPlaced;
    private boolean isVertical;

    /**
     * Constructs a ShipDrawing with the specified type and orientation.
     * The type determines the size and details of the ship.
     *
     * @param type       the type of the ship, which determines its size and details
     * @param isVertical whether the ship is oriented vertically or horizontally
     */
    public ShipDrawing(int type, boolean isVertical) {
        boatGroup = new Group();
        this.isVertical = isVertical;
        this.type = type;
        width = 50;
        height = 50 * type;
        this.color = determinarColor(type);
        this.rectangle = new Rectangle(width, height);
        if (!isVertical) {
            int temp = width;
            width = height;
            height = temp;
        }
        boatGroup.getChildren().add(rectangle);
        this.getChildren().add(boatGroup);
        draw();
    }

    /**
     * Returns the group of graphical elements representing the boat.
     *
     * @return the group of graphical elements representing the boat
     */
    public Group getBoatGroup() {
        return boatGroup;
    }

    /**
     * Determines the color of the ship based on its type.
     *
     * @param type the type of the ship
     * @return the color corresponding to the ship type
     */
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

    /**
     * Draws the ship by setting its graphical properties and adding details.
     */
    private void draw() {
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        rectangle.setFill(createGradient(color));
        rectangle.setStroke(Color.BLACK);
        rectangle.setArcWidth(15);
        rectangle.setArcHeight(15);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.rgb(50, 50, 50, 0.7));
        rectangle.setEffect(dropShadow);

        boatGroup.getChildren().clear();
        boatGroup.getChildren().add(rectangle);

        addDetails();
    }

    /**
     * Creates a gradient based on the base color for the ship.
     *
     * @param baseColor the base color of the ship
     * @return a LinearGradient for the ship's color
     */
    private LinearGradient createGradient(Color baseColor) {
        return new LinearGradient(0, 0, 1, 1, true, null,
                new Stop(0, baseColor.brighter()),
                new Stop(1, baseColor.darker()));
    }

    /**
     * Adds visual details to the ship based on its type.
     */
    private void addDetails() {
        if (type != 4) {
            addEllipses();
        }

        switch (type) {
            case 1:
                addCircles(Color.GREEN);
                break;
            case 2:
                addCircles(Color.BLUE);
                addMastAndSail();
                break;
            case 3:
                addCircles(Color.ORANGE);
                addMastAndSail();
                break;
            case 4:
                addTriangles();
                addTriangles();
                break;
            default:
                addCircles(Color.BLACK);
                break;
        }
    }

    /**
     * Adds circular windows to the ship.
     *
     * @param strokeColor the color of the window's border
     */
    private void addCircles(Color strokeColor) {
        int windowCount = Math.max(1, type);
        if (type == 2) {
            windowCount--;
        }
        for (int i = 0; i < windowCount; i++) {
            Circle window = new Circle(5, Color.WHITE);
            window.setStroke(strokeColor);
            window.setStrokeWidth(2);
            window.setEffect(new DropShadow(2, Color.BLACK));
            if (isVertical) {
                window.setCenterX(width / 2);
                window.setCenterY((i * height / windowCount) + (height / (2 * windowCount)));
            } else {
                window.setCenterX((i * width / windowCount) + (width / (2 * windowCount)));
                window.setCenterY(height / 2);
            }
            boatGroup.getChildren().add(window);
        }
    }

    /**
     * Adds elliptical windows to the ship.
     */
    private void addEllipses() {
        int ellipseCount = Math.max(1, type);
        if (type == 2) {
            ellipseCount--;
        }
        for (int i = 0; i < ellipseCount; i++) {
            Ellipse window = new Ellipse(10, 5);
            window.setFill(Color.WHITE);
            window.setStroke(Color.BLACK);
            window.setStrokeWidth(2);
            window.setEffect(new DropShadow(2, Color.BLACK));
            if (isVertical) {
                window.setCenterX(width / 2);
                window.setCenterY((i * height / ellipseCount) + (height / (2 * ellipseCount)));
            } else {
                window.setCenterX((i * width / ellipseCount) + (width / (2 * ellipseCount)));
                window.setCenterY(height / 2);
            }
            boatGroup.getChildren().add(window);
        }
    }

    /**
     * Adds triangular details to the ship.
     */
    private void addTriangles() {
        int triangleCount = Math.max(1, type);
        for (int i = 0; i < triangleCount; i++) {
            Polygon triangle = new Polygon();
            triangle.getPoints().addAll(
                    0.0, 0.0,
                    10.0, 0.0,
                    5.0, 10.0
            );
            triangle.setFill(Color.WHITE);
            triangle.setStroke(Color.BLACK);
            triangle.setStrokeWidth(2);
            triangle.setEffect(new DropShadow(2, Color.BLACK));
            if (isVertical) {
                triangle.setLayoutX(width / 2 - 5);
                triangle.setLayoutY((i * height / triangleCount) + (height / (2 * triangleCount)) - 5);
            } else {
                triangle.setLayoutX((i * width / triangleCount) + (width / (2 * triangleCount)) - 5);
                triangle.setLayoutY(height / 2 - 5);
            }
            boatGroup.getChildren().add(triangle);
        }
        if (type == 4) {
            for (int i = 0; i < triangleCount; i++) {
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(
                        0.0, 0.0,
                        10.0, 0.0,
                        5.0, 10.0
                );
                triangle.setFill(Color.WHITE);
                triangle.setStroke(Color.BLACK);
                triangle.setStrokeWidth(2);
                triangle.setEffect(new DropShadow(2, Color.BLACK));
                if (isVertical) {
                    triangle.setLayoutX(width / 2 - 5);
                    triangle.setLayoutY((i * height / triangleCount) + (height / (2 * triangleCount)) - 5 + 15);
                } else {
                    triangle.setLayoutX((i * width / triangleCount) + (width / (2 * triangleCount)) - 5 + 15);
                    triangle.setLayoutY(height / 2 - 5);
                }
                boatGroup.getChildren().add(triangle);
            }
        }
    }

    /**
     * Adds a mast and sail to the ship.
     */
    private void addMastAndSail() {
        Line mast = new Line();
        mast.setStroke(Color.BROWN);
        mast.setStrokeWidth(2);
        mast.setEffect(new DropShadow(2, Color.BLACK));
        Ellipse sail = new Ellipse();
        sail.setFill(Color.WHITE);
        sail.setStroke(Color.BLACK);
        sail.setEffect(new DropShadow(2, Color.BLACK));

        mast.setStartX(width / 2);
        mast.setStartY(height - height / 4);
        mast.setEndX(width / 2);
        mast.setEndY(height / 4);

        sail.setRadiusX(width / 4);
        sail.setRadiusY(height / 8);
        sail.setCenterX(width / 2);
        sail.setCenterY(height / 8);

        boatGroup.getChildren().addAll(mast, sail);
    }

    /**
     * Rotates the ship 90 degrees, swapping its width and height.
     * The ship can only be rotated if it has not been placed.
     */
    public void rotate() {
        if (!hasBeenPlaced) {
            int temp = width;
            width = height;
            height = temp;
            isVertical = !isVertical;
            draw();
        }
    }

    /**
     * Checks if the ship has been placed on the board.
     *
     * @return {@code true} if the ship has been placed, {@code false} otherwise
     */
    public boolean hasBeenPlaced() {
        return hasBeenPlaced;
    }

    /**
     * Sets the placement status of the ship.
     *
     * @param hasBeenPlaced {@code true} if the ship has been placed, {@code false} otherwise
     */
    public void setHasBeenPlaced(boolean hasBeenPlaced) {
        this.hasBeenPlaced = hasBeenPlaced;
    }

    /**
     * Checks if the ship is oriented vertically.
     *
     * @return {@code true} if the ship is vertical, {@code false} otherwise
     */
    public boolean isVertical() {
        return isVertical;
    }

    /**
     * Returns the type of the ship.
     * The type determines the size and details of the ship.
     *
     * @return the type of the ship
     */
    public int getType() {
        return type;
    }
}
