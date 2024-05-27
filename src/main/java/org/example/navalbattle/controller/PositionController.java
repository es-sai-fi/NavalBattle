package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
        ShipDrawing aircraftCarrier = new ShipDrawing(50, 50, 200, 50, "aircraftCarrier");
        ShipDrawing submarine1 = new ShipDrawing(50, 150, 150, 50, "submarine");
        ShipDrawing submarine2 = new ShipDrawing(250, 150, 150, 50, "submarine");
        ShipDrawing destroyer1 = new ShipDrawing(50, 250, 100, 50, "destroyer");
        ShipDrawing destroyer2 = new ShipDrawing(200, 250, 100, 50, "destroyer");
        ShipDrawing destroyer3 = new ShipDrawing(350, 250, 100, 50, "destroyer");
        ShipDrawing frigate1 = new ShipDrawing(50, 350, 50, 50, "frigate");
        ShipDrawing frigate2 = new ShipDrawing(150, 350, 50, 50, "frigate");
        ShipDrawing frigate3 = new ShipDrawing(250, 350, 50, 50, "frigate");
        ShipDrawing frigate4 = new ShipDrawing(350, 350, 50, 50, "frigate");
        anchorPane.getChildren().addAll(aircraftCarrier, submarine1, submarine2, destroyer1, destroyer2, destroyer3, frigate1, frigate2, frigate3, frigate4);
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



