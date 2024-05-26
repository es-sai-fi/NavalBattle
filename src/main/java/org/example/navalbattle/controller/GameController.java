package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.view.LoseStage;
import org.example.navalbattle.view.WinStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    Label missLabel;
    @FXML
    Label hitLabel;
    @FXML
    Label statusLabel;
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane enemyBoard;
    Stage stage;
    private boolean playerTurn = true;
    private NavalBattle navalBattle = new NavalBattle(this);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = (Stage) missLabel.getScene().getWindow();
        createEnemyBoard();
        createEnemyShips();
        arrangeEnemyBoard();
        missLabel.setVisible(false);
        hitLabel.setVisible(false);
    }

    public void createEnemyBoard() {
        Cell[][] enemyBoardAux = new Cell[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column, navalBattle);
                enemyBoard.add(cell, column, row);
                enemyBoardAux[row][column] = cell;
            }
        }
        navalBattle.setEnemyBoardAux(enemyBoardAux);
    }

    public void arrangeEnemyBoard(){
        int randomInt = (int) (Math.random() * 3);
        switch (randomInt){
            case 1:
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
                enemyBoardAux[][].setShip(enemyShips[6]);
                //Frigate 2
                enemyBoardAux[][].setShip(enemyShips[7]);
                //Frigate 3
                enemyBoardAux[][].setShip(enemyShips[8]);
                //Frigate 4
                enemyBoardAux[][].setShip(enemyShips[9]);
            case 3:
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
                enemyBoardAux[][].setShip(enemyShips[6]);
                //Frigate 2
                enemyBoardAux[][].setShip(enemyShips[7]);
                //Frigate 3
                enemyBoardAux[][].setShip(enemyShips[8]);
                //Frigate 4
                enemyBoardAux[][].setShip(enemyShips[9]);
        }
        /*
        for (Ship enemyShip : enemyShips){
            if (enemyShip.getType() == 1){
                boolean validPlacement = false;
                do{
                    randomX = (int) (Math.random() * 10);
                    randomY = (int) (Math.random() * 10);
                    if (!enemyBoardAux[randomX][randomY].isOccupied()){
                        validPlacement = true;
                        enemyBoardAux[randomX][randomY].setShip(enemyShip);

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
                                if (!enemyBoardAux[i][randomY].isOccupied()) {
                                    validPlacement = false;
                                }
                            }
                            if (validPlacement) {
                                for (int i = 0; i < enemyShip.getType(); i++){
                                    enemyBoardAux[i][randomY].setShip(enemyShip);
                                }
                            }
                        }
                    }
                    while (!validPlacement);
                }
                else{

                }
            }
        }*/
    }

    public void updateStatusLabel(String message, int type){
        switch(type){
            case 1:
                statusLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
            case 2:
                statusLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
        }
        statusLabel.setText(message);
    }

    public void createEnemyShips() {
        Ship[] enemyShips = new Ship[10];
        int[] shipTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int i = 0; i < 10; i++) {
            Ship ship = new Ship(i);
            enemyShips[i] = ship;
        }
        navalBattle.setEnemyShips(enemyShips);
    }

    public void win() throws IOException {
        WinStage winStage = new WinStage();
        winStage.show();
        stage.close();
    }

    public void lose() throws IOException {
        LoseStage loseStage = new LoseStage();
        loseStage.show();
        stage.close();
    }


    public void setNavalBattle(NavalBattle navalBattle) {
        this.navalBattle = navalBattle;
    }

}