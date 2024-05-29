package org.example.navalbattle.model;

public class AttackException extends Exception {
    public AttackException() {
        super();
    }

    public AttackException(String message) {
        super(message);
    }

    public AttackException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttackException(Throwable cause) {
        super(cause);
    }
}
