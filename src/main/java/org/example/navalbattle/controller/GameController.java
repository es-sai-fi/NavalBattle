package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.model.ShipNode;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane enemyBoard;
    @FXML
    VBox shipsBox;
    private boolean playerTurn = true;
    private NavalBattle navalBattle;
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Cell[][] enemyBoardAux = new Cell[10][10];
    private Ship[] playerShips = new Ship[9];
    private Ship[] enemyShips = new Ship[9];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createEnemyBoard();
        createEnemyShips();
    }

    public void createEnemyBoard() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                cell.getRectangle().setOnMouseClicked(mouseEvent -> {
                    if (playerTurn){
                        launchAttack(cell.getRow(), cell.getColumn());
                    }
                });
                enemyBoard.add(cell, column, row);
                enemyBoardAux[row][column] = cell;
            }
        }
    }

    public void launchAttack(int row, int column){
        Cell cell = enemyBoardAux[row][column];
        if (cell.isOccupied()){
            cell.
        }
    }

    public void arrangeEnemyBoard(){
        int randomX;
        int randomY;
        for (Ship enemyShip : enemyShips){
            if (enemyShip.getType() == 1){
                boolean validPlacement = false;
                do{
                    randomX = (int) (Math.random() * 10);
                    randomY = (int) (Math.random() * 10);
                    if (enemyBoardAux[randomX][randomY].getShipNode() == null){
                        validPlacement = true;
                        enemyBoardAux[randomX][randomY].setShipNode(enemyShip.getShipNodeList().get(0));

                    }
                }
                while (!validPlacement);
            }
            else{
                boolean validPlacement = true;
                boolean horizontal = Math.random() < 0.50;
                if(horizontal){
                    do{
                        randomX = (int) (Math.random() * 10);
                        randomY = (int) (Math.random() * 10);

                        if (randomX == 0) {
                            for (int i = 0; i < enemyShip.getType(); i++) {
                                if (!(enemyBoardAux[i][randomY].getShipNode() == null)) {
                                    validPlacement = false;
                                }
                            }
                            if (validPlacement) {
                                for (int i = 0; i < enemyShip.getType(); i++){
                                    for (ShipNode shipNode : enemyShip.getShipNodeList()) {
                                        enemyBoardAux[i][randomY].setShipNode(shipNode);
                                    }
                                }
                            }
                        }
                    }
                    while (!validPlacement);
                }
                else{

                }
            }
        }
    }

    public void createEnemyShips() {
        int[] shipTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < 10; i++) {
            Ship ship = new Ship(i);
            enemyShips[i] = ship;
        }
    }

    public void setPlayerBoardAux(Cell[][] playerBoardAux) {
        this.playerBoardAux = playerBoardAux;
    }

    public void setPlayerShips(Ship[] playerShips) {
        this.playerShips = playerShips;
    }
}