package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.model.ShipDrawing;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.LoseStage;
import org.example.navalbattle.view.WinStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    ImageView missImage;
    @FXML
    ImageView hitImage;
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane enemyBoard;
    Stage stage;
    private NavalBattle navalBattle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        missImage.setVisible(false);
        hitImage.setVisible(false);
        createEnemyBoard();
        createEnemyShips();
        navalBattle.arrangeEnemyBoard();
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
        switch (navalBattle.getArrangeInt()){
            case 0:
                // Aircraft Carrier (Horizontal)
                ShipDrawing aircraftCarrier = new ShipDrawing(200, 50, 4, false);
                enemyBoard.add(aircraftCarrier, 6, 7);

                // Submarine 1 (Vertical)
                ShipDrawing submarine1 = new ShipDrawing(150, 50, 3);
                enemyBoard.add(submarine1, 5, 1);

                // Submarine 2 (Vertical)
                ShipDrawing submarine2 = new ShipDrawing(150, 50, 3);
                enemyBoard.add(submarine2, 6, 2);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer1 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer1, 0, 5);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer2 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer2, 1, 6);

                // Destroyer 3 (Horizontal)
                ShipDrawing destroyer3 = new ShipDrawing(100, 50, 2, false);
                enemyBoard.add(destroyer3, 0, 1);

                // Frigate 1
                ShipDrawing frigate1 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate1, 6, 0);

                // Frigate 2
                ShipDrawing frigate2 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate2, 9, 1);

                // Frigate 3
                ShipDrawing frigate3 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate3, 3, 5);

                // Frigate 4
                ShipDrawing frigate4 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate4, 4, 5);
                break;

            case 1:
                // Aircraft Carrier (Vertical)
                ShipDrawing aircraftCarrier1 = new ShipDrawing(200, 50, 4);
                enemyBoard.add(aircraftCarrier1, 0, 4);

                // Submarine 1 (Horizontal)
                ShipDrawing submarine11 = new ShipDrawing(150, 50, 3, false);
                enemyBoard.add(submarine11, 3, 8);

                // Submarine 2 (Vertical)
                ShipDrawing submarine22 = new ShipDrawing(150, 50, 3);
                enemyBoard.add(submarine22, 9, 3);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer11 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer11, 5, 5);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer22 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer22, 6, 6);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer33 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer33, 3, 4);

                // Frigate 1
                ShipDrawing frigate11 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate11, 1, 0);

                // Frigate 2
                ShipDrawing frigate22 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate22, 3, 0);

                // Frigate 3
                ShipDrawing frigate33 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate33, 3, 1);

                // Frigate 4
                ShipDrawing frigate44 = new ShipDrawing(50, 50, 1, false);
                enemyBoard.add(frigate44, 9, 0);
                break;

            case 2:
                // Aircraft Carrier (Horizontal)
                ShipDrawing aircraftCarrier2 = new ShipDrawing(200, 50, 4, false);
                enemyBoard.add(aircraftCarrier2, 1, 8);

                // Submarine 1 (Vertical)
                ShipDrawing submarine12 = new ShipDrawing(150, 50, 3);
                enemyBoard.add(submarine12, 0, 2);

                // Submarine 2 (Horizontal)
                ShipDrawing submarine23 = new ShipDrawing(150, 50, 3, false);
                enemyBoard.add(submarine23, 4, 2);

                // Destroyer 1 (Horizontal)
                ShipDrawing destroyer12 = new ShipDrawing(100, 50, 2, false);
                enemyBoard.add(destroyer12, 0, 8);

                // Destroyer 2 (Horizontal)
                ShipDrawing destroyer23 = new ShipDrawing(100, 50, 2, false);
                enemyBoard.add(destroyer23, 5, 8);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer34 = new ShipDrawing(100, 50, 2);
                enemyBoard.add(destroyer34, 1, 0);

                // Frigate 1
                ShipDrawing frigate12 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate12, 0, 6);

                // Frigate 2
                ShipDrawing frigate23 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate23, 2, 5);

                // Frigate 3
                ShipDrawing frigate34 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate34, 6, 5);

                // Frigate 4
                ShipDrawing frigate45 = new ShipDrawing(50, 50, 1);
                enemyBoard.add(frigate45, 9, 9);
                break;
        }
        navalBattle.setEnemyBoardAux(enemyBoardAux);
    }

    public GridPane getPlayerBoard() {
        return playerBoard;
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
        GameStage.deleteInstance();
    }

    public void lose() throws IOException {
        LoseStage loseStage = new LoseStage();
        loseStage.show();
        GameStage.deleteInstance();
    }

    public ImageView getHitImage() {
        return hitImage;
    }

    public ImageView getMissImage() {
        return missImage;
    }

    public void setNavalBattle(NavalBattle navalBattle) {
        this.navalBattle = navalBattle;
    }

    public void setPlayerBoard(GridPane playerBoard) {
        this.playerBoard = playerBoard;
    }
}