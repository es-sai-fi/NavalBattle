package org.example.navalbattle.model;

public class Player {
    private int id;
    private boolean itsTurn = true;

    public Player(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
