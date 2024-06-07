/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

import java.io.Serializable;

/**
 * Represents a ship in the naval battle game.
 * The ship has a type, health, and alive status.
 */
public class Ship implements Serializable {
    private final int type;
    private int health;
    private boolean alive = true;

    /**
     * Constructs a ship with the specified type.
     * The type determines the initial health of the ship.
     *
     * @param type the type of the ship, which also determines its initial health and length
     */
    public Ship(int type) {
        this.type = type;
        this.health = type;
    }

    /**
     * Returns the current health of the ship.
     *
     * @return the current health of the ship
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns the type of the ship.
     * The type determines the initial health and length of the ship.
     *
     * @return the type of the ship
     */
    public int getType() {
        return type;
    }

    /**
     * Checks if the ship is still alive.
     * A ship is alive if its health is greater than 0.
     *
     * @return {@code true} if the ship is alive, {@code false} otherwise
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Applies damage to the ship, decreasing its health by 1.
     * If the ship's health reaches 0, the ship is considered dead.
     */
    public void receiveDamage() {
        health--;
        if (health == 0) {
            alive = false;
        }
    }

    /**
     * Returns the length of the ship.
     * The length is assumed to be equal to the type of the ship.
     *
     * @return the length of the ship
     */
    public int getLength() {
        return type; // Assuming that the type is equal to the length
    }
}
