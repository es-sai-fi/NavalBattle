package org.example.navalbattle.model;

public class NavalBattle {
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Cell[][] enemyBoardAux = new Cell[10][10];
    private Ship[] playerShips = new Ship[9];
    private Ship[] enemyShips = new Ship[9];

    public void updateOccupation() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell playerCell = playerBoardAux[row][column];
                Cell enemyCell = enemyBoardAux[row][column];
                if (!(playerCell.getShip() == null)) {
                    playerCell.setOccupied(true);
                }
                if (!(enemyCell.getShip() == null)) {
                    enemyCell.setOccupied(true);
                }
            }
        }
    }

    public void setPlayerShips(Ship[] playerShips) {
        this.playerShips = playerShips;
    }

    public void setPlayerBoardAux(Cell[][] playerBoardAux) {
        this.playerBoardAux = playerBoardAux;
    }

    public void setEnemyBoardAux(Cell[][] enemyBoardAux) {
        this.enemyBoardAux = enemyBoardAux;
    }

    public void setEnemyShips(Ship[] enemyShips) {
        this.enemyShips = enemyShips;
    }

    public Cell[][] getPlayerBoardAux() {
        return playerBoardAux;
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}