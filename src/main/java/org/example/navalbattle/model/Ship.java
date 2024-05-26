package org.example.navalbattle.model;

import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final int type;
    private int health;
    List<ShipNode> shipNodeList = new ArrayList<>();

    public Ship (int type){
        this.type = type;

        for (int node = 0; node < type; node++){
            ShipNode shipNode = new ShipNode(this);
            shipNodeList.add(shipNode);
        }

        for (ShipNode node : shipNodeList){
            health += node.getNodeHealth();
        }
    }

    public List<ShipNode> getShipNodeList() {
        return shipNodeList;
    }

    public int getHealth() {
        return health;
    }

    public int getType() {
        return type;
    }

}
