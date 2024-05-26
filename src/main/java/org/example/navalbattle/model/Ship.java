package org.example.navalbattle.model;

import javafx.scene.shape.SVGPath;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private final int type;
    private int health;
    private boolean alive = true;
    public Ship (int type){
        this.type = type;
        if (type == 4){
            health = 4;
        }
        else if (type == 3){
            health = 3;
        }
        else if (type == 2){
            health = 2;
        }
        else if (type == 1){
            health = 1;
        }
    }
    public int getHealth() {
        return health;
    }
    public int getType() {
        return type;
    }

    public boolean isAlive() {
        return alive;
    }

    public void receiveDamage(){
        health--;
        if (health == 0){
            alive = false;
        }
    }

}
