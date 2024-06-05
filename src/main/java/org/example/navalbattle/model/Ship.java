package org.example.navalbattle.model;

import java.io.Serializable;

public class Ship implements Serializable {
    private final int type;
    private int health;
    private boolean alive = true;

    public Ship(int type) {
        this.type = type;
        health = type;
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

    public void receiveDamage() {
        health--;
        if (health == 0) {
            alive = false;
        }
    }

    // Añadido para obtener la longitud del barco
    public int getLength() {
        return type; // Asumiendo que el tipo es igual a la longitud
    }
}
