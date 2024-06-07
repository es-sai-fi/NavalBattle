/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

/**
 * Represents custom exceptions specific to the Naval Battle game.
 * This exception can be used to signal various game-specific error conditions.
 */
public class NavalBattleException extends Exception {

    /**
     * Constructs a new NavalBattleException with {@code null} as its detail message.
     */
    public NavalBattleException() {
        super();
    }

    /**
     * Constructs a new NavalBattleException with the specified detail message.
     *
     * @param message the detail message
     */
    public NavalBattleException(String message) {
        super(message);
    }

    /**
     * Constructs a new NavalBattleException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public NavalBattleException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new NavalBattleException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public NavalBattleException(Throwable cause) {
        super(cause);
    }
}
