package org.example.sudoku.model;

import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final char type;
    private int health;
    private SVGPath ship;
    List<ShipNode> shipNodeList = new ArrayList<>();

    public Ship (char type){
        this.type = type;

        if (type == 1){
            createAircraftCarrier();
        }
        else if (type == 2){
            createSubmarine();
        }
        else if ( type == 3){
            createDestroyer();
        }
        else if (type == 4){
            createFrigate();
        }

        for (ShipNode node : shipNodeList){
            health += node.getNodeHealth();
        }
    }

    public void createAircraftCarrier(){
        String head = "";
        String body1 = "";
        String body2 = "";
        String tail = "";
        String ship = "";
        ShipNode node0 = new ShipNode(head);
        ShipNode node1 = new ShipNode(body1);
        ShipNode node2 = new ShipNode(body2);
        ShipNode node3 = new ShipNode(tail);
        shipNodeList.add(node0);
        shipNodeList.add(node1);
        shipNodeList.add(node2);
        shipNodeList.add(node3);
        this.ship.setContent(ship);
    }

    public int getHealth() {
        return health;
    }

    public char getType() {
        return type;
    }

}
