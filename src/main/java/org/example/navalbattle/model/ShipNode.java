package org.example.navalbattle.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class ShipNode {
    private Ship ship;
    private final int nodeHealth = 1;

    public ShipNode(Ship ship) {
        this.ship = ship;
    }

    public int getNodeHealth() {
        return nodeHealth;
    }
}
