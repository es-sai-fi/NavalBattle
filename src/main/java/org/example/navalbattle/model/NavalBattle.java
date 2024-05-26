package org.example.navalbattle.model;

import org.example.navalbattle.controller.GameController;

import java.io.IOException;
import java.util.Random;

public class NavalBattle {
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Cell[][] enemyBoardAux = new Cell[10][10];
    private Ship[] playerShips = new Ship[9];
    private Ship[] enemyShips = new Ship[9];
    private Random random = new Random();
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
        boolean validAttack = false;
        boolean win = true;
        boolean lose = true;
        Cell enemyCell = enemyBoardAux[row][column];
        try{
            if(enemyCell.getHasBeenAttacked()){
                throw new AttackException("La celda ya ha sido atacada.");
            }
            if(!enemyCell.isOccupied()){
                enemyCell.setImage("/org/example/navalbattle/images/fail.png");
            }
            else{
                enemyCell.getShip().receiveDamage();
                enemyCell.setImage("/org/example/navalbattle/images/hit.png");
                enemyCell.setHasBeenAttacked(true);
                gameController.updateStatusLabel("¡Ataque exitoso!", 1);
                for(Ship enemyShip: enemyShips){
                    if (enemyShip.isAlive()){
                        win = false;
                    }
                }
                if(win){
                    gameController.win();
                }
                else{
                    do {
                        int randomRow = random.nextInt(10);
                        int randomColumn = random.nextInt(10);
                        Cell playerCell = playerBoardAux[randomRow][randomColumn];
                        if(!playerCell.getHasBeenAttacked()){
                            validAttack = true;
                            if(playerCell.isOccupied()){
                                playerCell.getShip().receiveDamage();
                                playerCell.setImage("/org/example/navalbattle/images/hit.png");
                            }
                            else{
                                playerCell.setImage("/org/example/navalbattle/images/fail.png");
                            }
                        }
                    }while(!validAttack);
                    for (Ship playerShip: playerShips){
                        if (playerShip.isAlive()){
                            lose = false;
                        }
                    }
                    if(lose){
                        gameController.lose();
                    }
                }
            }
        } catch (AttackException e) {
            gameController.updateStatusLabel(e.getMessage(), 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void arrangeEnemyBoard(){
        int randomInt = random.nextInt(3);
        switch (randomInt){
            case 0:
                //Aircraft Carrier
                enemyBoardAux[7][6].setShip(enemyShips[0]);
                enemyBoardAux[7][7].setShip(enemyShips[0]);
                enemyBoardAux[7][8].setShip(enemyShips[0]);
                enemyBoardAux[7][9].setShip(enemyShips[0]);
                //Submarine 1
                enemyBoardAux[1][5].setShip(enemyShips[1]);
                enemyBoardAux[2][5].setShip(enemyShips[1]);
                enemyBoardAux[3][5].setShip(enemyShips[1]);
                //Submarine 2
                enemyBoardAux[2][6].setShip(enemyShips[2]);
                enemyBoardAux[3][6].setShip(enemyShips[2]);
                enemyBoardAux[4][6].setShip(enemyShips[2]);
                //Destroyer 1
                enemyBoardAux[5][0].setShip(enemyShips[3]);
                enemyBoardAux[6][0].setShip(enemyShips[3]);
                //Destroyer 2
                enemyBoardAux[6][1].setShip(enemyShips[4]);
                enemyBoardAux[7][1].setShip(enemyShips[4]);
                //Destroyer 3
                enemyBoardAux[1][0].setShip(enemyShips[5]);
                enemyBoardAux[1][1].setShip(enemyShips[5]);
                //Frigate 1
                enemyBoardAux[0][6].setShip(enemyShips[6]);
                //Frigate 2
                enemyBoardAux[1][9].setShip(enemyShips[7]);
                //Frigate 3
                enemyBoardAux[5][3].setShip(enemyShips[8]);
                //Frigate 4
                enemyBoardAux[5][4].setShip(enemyShips[9]);
            case 1:
                //Aircraft Carrier
                enemyBoardAux[4][0].setShip(enemyShips[0]);
                enemyBoardAux[5][0].setShip(enemyShips[0]);
                enemyBoardAux[6][0].setShip(enemyShips[0]);
                enemyBoardAux[7][0].setShip(enemyShips[0]);
                //Submarine 1
                enemyBoardAux[8][3].setShip(enemyShips[1]);
                enemyBoardAux[8][4].setShip(enemyShips[1]);
                enemyBoardAux[8][5].setShip(enemyShips[1]);
                //Submarine 2
                enemyBoardAux[3][9].setShip(enemyShips[2]);
                enemyBoardAux[4][9].setShip(enemyShips[2]);
                enemyBoardAux[5][9].setShip(enemyShips[2]);
                //Destroyer 1
                enemyBoardAux[5][5].setShip(enemyShips[3]);
                enemyBoardAux[6][5].setShip(enemyShips[3]);
                //Destroyer 2
                enemyBoardAux[6][6].setShip(enemyShips[4]);
                enemyBoardAux[7][6].setShip(enemyShips[4]);
                //Destroyer 3
                enemyBoardAux[4][3].setShip(enemyShips[5]);
                enemyBoardAux[5][3].setShip(enemyShips[5]);
                //Frigate 1
                enemyBoardAux[0][1].setShip(enemyShips[6]);
                //Frigate 2
                enemyBoardAux[0][3].setShip(enemyShips[7]);
                //Frigate 3
                enemyBoardAux[1][3].setShip(enemyShips[8]);
                //Frigate 4
                enemyBoardAux[0][9].setShip(enemyShips[9]);
            case 2:
                //Aircraft Carrier
                enemyBoardAux[][].setShip(enemyShips[0]);
                enemyBoardAux[][].setShip(enemyShips[0]);
                enemyBoardAux[][].setShip(enemyShips[0]);
                enemyBoardAux[][].setShip(enemyShips[0]);
                //Submarine 1
                enemyBoardAux[][].setShip(enemyShips[1]);
                enemyBoardAux[][].setShip(enemyShips[1]);
                enemyBoardAux[][].setShip(enemyShips[1]);
                //Submarine 2
                enemyBoardAux[][].setShip(enemyShips[2]);
                enemyBoardAux[][].setShip(enemyShips[2]);
                enemyBoardAux[][].setShip(enemyShips[2]);
                //Destroyer 1
                enemyBoardAux[][].setShip(enemyShips[3]);
                enemyBoardAux[][].setShip(enemyShips[3]);
                //Destroyer 2
                enemyBoardAux[][].setShip(enemyShips[4]);
                enemyBoardAux[][].setShip(enemyShips[4]);
                //Destroyer 3
                enemyBoardAux[][].setShip(enemyShips[5]);
                enemyBoardAux[][].setShip(enemyShips[5]);
                //Frigate 1
                enemyBoardAux[0][0].setShip(enemyShips[6]);
                //Frigate 2
                enemyBoardAux[0][9].setShip(enemyShips[7]);
                //Frigate 3
                enemyBoardAux[9][0].setShip(enemyShips[8]);
                //Frigate 4
                enemyBoardAux[9][9].setShip(enemyShips[9]);
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