package org.example.navalbattle.model;

public class NavalBattle {
    private Cell[][] player1BoardAux = new Cell[10][10];
    private Cell[][] player2BoardAux = new Cell[10][10];
    private Ship[] player1Ships = new Ship[9];
    private Ship[] player2Ships = new Ship[9];

    public NavalBattle (Cell[][] player1BoardAux, Cell[][] player2BoardAux, Ship[] player1Ships, Ship[] player2Ships){
        this.player1BoardAux = player1BoardAux;
        this.player2BoardAux = player2BoardAux;
        this.player1Ships = player1Ships;
        this.player2Ships = player2Ships;
    }
}
