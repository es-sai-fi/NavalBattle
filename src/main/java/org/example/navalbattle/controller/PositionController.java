package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.model.ShipDrawing;

import java.net.URL;
import java.util.ResourceBundle;

public class    PositionController implements Initializable {
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
        placeShipsOnBoard();
    }

    public void createPlayerBoard() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
                playerBoardAux[row][column] = cell;
            }
        }
    }

    public void createPlayerShips() {
        int[] shipLengths = {5, 4, 3, 3, 2, 2, 2, 1, 1, 1}; // Ejemplo de longitudes de barcos
        for (int i = 0; i < shipLengths.length; i++) {
            Ship ship = new Ship(shipLengths[i]);
            playerShips[i] = ship;
        }
    }

    public void placeShipsOnBoard() {
        String[] shipTypes = {"aircraftCarrier", "destroyer", "submarine", "frigate", "patrolBoat"}; // Ejemplo de tipos de barcos
        for (int i = 0; i < playerShips.length; i++) {
            if (playerShips[i] != null) {
                // Ajusta la posición inicial de los barcos en el tablero
                int initialX = i;
                int initialY = 0;

                ShipDrawing shipDrawing = new ShipDrawing(initialX, initialY, playerShips[i].getLength() * 30, 30, shipTypes[i % shipTypes.length]);
                shipDrawing.setOnMouseClicked(this::rotateShip);
                playerBoard.add(shipDrawing, initialX, initialY); // Ajusta la posición según necesites
            }
        }
    }

    public void rotateShip(MouseEvent event) {
        ShipDrawing shipDrawing = (ShipDrawing) event.getSource();
        shipDrawing.rotate();
    }

    public Cell[][] getPlayerBoardAux() {
        return playerBoardAux;
    }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}
