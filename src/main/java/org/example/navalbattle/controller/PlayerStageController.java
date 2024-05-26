package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.Ship;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerStageController implements Initializable {
    @FXML
    GridPane playerBoard;
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Ship[] playerShips = new Ship[9];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createPlayerShips();
    }

    public void createPlayerBoard(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
                playerBoardAux[row][column] = cell;
            }
        }
    }

     public void createPlayerShips(){
         int[] shipTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
         for (int i = 0; i < 10; i++) {
             Ship ship = new Ship(i);
             playerShips[i] = ship;
         }
     }
}
