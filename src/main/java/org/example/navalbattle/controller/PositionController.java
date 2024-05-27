package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.model.ShipDrawing;

import java.net.URL;
import java.util.ResourceBundle;

public class PositionController implements Initializable {
    @FXML
    GridPane playerBoard;
    @FXML
    AnchorPane anchorPane;
    Cell[][] playerBoardAux = new Cell[10][10];
    Ship[] playerShips = new Ship[10];

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
    public Cell[][] getPlayerBoardAux(){
        return playerBoardAux;
   }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}



