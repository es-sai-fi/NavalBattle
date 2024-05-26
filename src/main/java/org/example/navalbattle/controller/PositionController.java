package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;

import java.net.URL;
import java.util.ResourceBundle;

public class PositionController implements Initializable {
    @FXML
    GridPane playerBoard;
    private NavalBattle navalBattle = new NavalBattle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createPlayerShips();
    }

    public void createPlayerBoard(){
        Cell[][] playerBoardAux = new Cell[10][10];
        Ship[] playerShips = new Ship[9];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column, false, navalBattle);
                playerBoard.add(cell, column, row);
                playerBoardAux[row][column] = cell;
            }
        }
        navalBattle.setPlayerBoardAux(playerBoardAux);
    }

     public void createPlayerShips(){
         Ship[] playerShips = new Ship[10];
         int[] shipTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
         for (int i = 0; i < 10; i++) {
             Ship ship = new Ship(i);
             playerShips[i] = ship;
         }
         navalBattle.setPlayerShips(playerShips);
     }
}
