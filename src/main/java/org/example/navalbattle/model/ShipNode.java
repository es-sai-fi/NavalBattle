package org.example.sudoku.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;

public class ShipNode {
    private SVGPath sp = new SVGPath();
    private final int nodeHealth = 1;

    public ShipNode (String SVGPathContent){
        sp.setContent(SVGPathContent);
        sp.setFill(Color.BLUE);
        sp.setStroke(Color.BLACK);
    }

    public int getNodeHealth() {
        return nodeHealth;
    }
}
