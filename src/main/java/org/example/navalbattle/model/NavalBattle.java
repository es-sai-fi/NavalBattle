package org.example.navalbattle.model;

import org.example.navalbattle.controller.GameController;

public class NavalBattle {
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Cell[][] enemyBoardAux = new Cell[10][10];
    private Ship[] playerShips = new Ship[9];
    private Ship[] enemyShips = new Ship[9];
    private GameController gameController;

    public NavalBattle(GameController gameController){
        this.gameController = gameController;
    }

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

    public void launchAttack(int row, int column){
        Cell enemyCell = enemyBoardAux[row][column];
        try{
            if(!enemyCell.isOccupied()){
                throw new AttackException("La celda no está ocupada.");
            }
            if(enemyCell.getHasBeenAttacked()){
                throw new AttackException("La celda ya ha sido atacada.");
            }
            enemyCell.getShip().receiveDamage();
            enemyCell.setHasBeenAttacked(true);
        } catch (AttackException e1){
            gameController.updateStatusLabel(e1.getMessage());
        } catch (AttackException e2){

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

    public Cell[][] getEnemyBoardAux() {
        return enemyBoardAux;
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}