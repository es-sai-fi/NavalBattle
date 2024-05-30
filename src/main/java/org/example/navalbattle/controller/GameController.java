package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.navalbattle.model.*;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.LoseStage;
import org.example.navalbattle.view.WinStage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController{
    @FXML
    private ImageView missImage;
    @FXML
    private ImageView hitImage;
    @FXML
    private GridPane playerBoard;
    @FXML
    private GridPane enemyBoard;
    private List<ShipDrawingData> shipDrawingDataList = new ArrayList<>();
    Stage stage;
    ShipDrawing[] enemyShipDrawings = new ShipDrawing[10];
    private NavalBattle navalBattle;

    public void initialize(NavalBattle navalBattle) {
        missImage.setVisible(false);
        hitImage.setVisible(false);
        setNavalBattle(navalBattle);
        createPlayerBoard();
        createEnemyBoard();
        createEnemyShips();
    }

    public void createPlayerBoard(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
            }
        }
        for(ShipDrawingData shipDrawingData: shipDrawingDataList){
            int row = shipDrawingData.getRow();
            int column = shipDrawingData.getColumn();
            int type = shipDrawingData.getType();
            boolean isVertical = shipDrawingData.isVertical();
            ShipDrawing shipDrawing = new ShipDrawing(type, isVertical);
            playerBoard.add(shipDrawing, column, row);
            shipDrawing.toFront();
        }
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
        navalBattle.arrangeEnemyBoard();
        switch (navalBattle.getArrangeInt()){
            case 0:
                // Aircraft Carrier (Horizontal)
                ShipDrawing aircraftCarrier = new ShipDrawing(4, false);
                enemyBoard.add(aircraftCarrier, 6, 7);

                // Submarine 1 (Vertical)
                ShipDrawing submarine1 = new ShipDrawing(3, true);
                enemyBoard.add(submarine1, 5, 1);

                // Submarine 2 (Vertical)
                ShipDrawing submarine2 = new ShipDrawing(3, true);
                enemyBoard.add(submarine2, 6, 2);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer1 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer1, 0, 5);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer2 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer2, 1, 6);

                // Destroyer 3 (Horizontal)
                ShipDrawing destroyer3 = new ShipDrawing(2, false);
                enemyBoard.add(destroyer3, 0, 1);

                // Frigate 1
                ShipDrawing frigate1 = new ShipDrawing(1, true);
                enemyBoard.add(frigate1, 6, 0);

                // Frigate 2
                ShipDrawing frigate2 = new ShipDrawing(1, true);
                enemyBoard.add(frigate2, 9, 1);

                // Frigate 3
                ShipDrawing frigate3 = new ShipDrawing(1, true);
                enemyBoard.add(frigate3, 3, 5);

                // Frigate 4
                ShipDrawing frigate4 = new ShipDrawing(1, true);
                enemyBoard.add(frigate4, 4, 5);
                aircraftCarrier.setVisible(false);
                submarine1.setVisible(false);
                submarine2.setVisible(false);
                destroyer1.setVisible(false);
                destroyer2.setVisible(false);
                destroyer3.setVisible(false);
                frigate1.setVisible(false);
                frigate2.setVisible(false);
                frigate3.setVisible(false);
                frigate4.setVisible(false);
                enemyShipDrawings[0] = aircraftCarrier;
                enemyShipDrawings[1] = submarine1;
                enemyShipDrawings[2] = submarine2;
                enemyShipDrawings[3] = destroyer1;
                enemyShipDrawings[4] = destroyer2;
                enemyShipDrawings[5] = destroyer3;
                enemyShipDrawings[6] = frigate1;
                enemyShipDrawings[7] = frigate2;
                enemyShipDrawings[8] = frigate3;
                enemyShipDrawings[9] = frigate4;
                break;

            case 1:
                // Aircraft Carrier (Vertical)
                ShipDrawing aircraftCarrier1 = new ShipDrawing(200, true);
                enemyBoard.add(aircraftCarrier1, 0, 4);

                // Submarine 1 (Horizontal)
                ShipDrawing submarine11 = new ShipDrawing(3, false);
                enemyBoard.add(submarine11, 3, 8);

                // Submarine 2 (Vertical)
                ShipDrawing submarine22 = new ShipDrawing(3, true);
                enemyBoard.add(submarine22, 9, 3);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer11 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer11, 5, 5);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer22 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer22, 6, 6);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer33 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer33, 3, 4);

                // Frigate 1
                ShipDrawing frigate11 = new ShipDrawing(1, true);
                enemyBoard.add(frigate11, 1, 0);

                // Frigate 2
                ShipDrawing frigate22 = new ShipDrawing(1, true);
                enemyBoard.add(frigate22, 3, 0);

                // Frigate 3
                ShipDrawing frigate33 = new ShipDrawing(1, true);
                enemyBoard.add(frigate33, 3, 1);

                // Frigate 4
                ShipDrawing frigate44 = new ShipDrawing(1, true);
                enemyBoard.add(frigate44, 9, 0);

                aircraftCarrier1.setVisible(false);
                submarine11.setVisible(false);
                submarine22.setVisible(false);
                destroyer11.setVisible(false);
                destroyer22.setVisible(false);
                destroyer33.setVisible(false);
                frigate11.setVisible(false);
                frigate22.setVisible(false);
                frigate33.setVisible(false);
                frigate44.setVisible(false);
                enemyShipDrawings[0] = aircraftCarrier1;
                enemyShipDrawings[1] = submarine11;
                enemyShipDrawings[2] = submarine22;
                enemyShipDrawings[3] = destroyer11;
                enemyShipDrawings[4] = destroyer22;
                enemyShipDrawings[5] = destroyer33;
                enemyShipDrawings[6] = frigate11;
                enemyShipDrawings[7] = frigate22;
                enemyShipDrawings[8] = frigate33;
                enemyShipDrawings[9] = frigate44;
                break;

            case 2:
                // Aircraft Carrier (Horizontal)
                ShipDrawing aircraftCarrier2 = new ShipDrawing(4,false);
                enemyBoard.add(aircraftCarrier2, 1, 8);

                // Submarine 1 (Vertical)
                ShipDrawing submarine12 = new ShipDrawing(3, true);
                enemyBoard.add(submarine12, 0, 2);

                // Submarine 2 (Horizontal)
                ShipDrawing submarine23 = new ShipDrawing(3, true);
                enemyBoard.add(submarine23, 4, 2);

                // Destroyer 1 (Horizontal)
                ShipDrawing destroyer12 = new ShipDrawing(2,false);
                enemyBoard.add(destroyer12, 0, 8);

                // Destroyer 2 (Horizontal)
                ShipDrawing destroyer23 = new ShipDrawing(2, false);
                enemyBoard.add(destroyer23, 5, 8);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer34 = new ShipDrawing(2, true);
                enemyBoard.add(destroyer34, 1, 0);

                // Frigate 1
                ShipDrawing frigate12 = new ShipDrawing(1, true);
                enemyBoard.add(frigate12, 0, 6);

                // Frigate 2
                ShipDrawing frigate23 = new ShipDrawing(1, true);
                enemyBoard.add(frigate23, 2, 5);

                // Frigate 3
                ShipDrawing frigate34 = new ShipDrawing(1, true);
                enemyBoard.add(frigate34, 6, 5);

                // Frigate 4
                ShipDrawing frigate45 = new ShipDrawing(1, true);
                enemyBoard.add(frigate45, 9, 9);

                aircraftCarrier2.setVisible(false);
                submarine12.setVisible(false);
                submarine23.setVisible(false);
                destroyer12.setVisible(false);
                destroyer23.setVisible(false);
                destroyer34.setVisible(false);
                frigate12.setVisible(false);
                frigate23.setVisible(false);
                frigate34.setVisible(false);
                frigate45.setVisible(false);
                enemyShipDrawings[0] = aircraftCarrier2;
                enemyShipDrawings[1] = submarine12;
                enemyShipDrawings[2] = submarine23;
                enemyShipDrawings[3] = destroyer12;
                enemyShipDrawings[4] = destroyer23;
                enemyShipDrawings[5] = destroyer34;
                enemyShipDrawings[6] = frigate12;
                enemyShipDrawings[7] = frigate23;
                enemyShipDrawings[8] = frigate34;
                enemyShipDrawings[9] = frigate45;
                break;
        }
    }

    @FXML
    void onShowEnemyBoardButtonClick(ActionEvent actionEvent){
        for(ShipDrawing enemyShipDrawing : enemyShipDrawings){
            enemyShipDrawing.setVisible(true);
        }
    }

    public void setShipDrawingDataList(List<ShipDrawingData> shipDrawingDataList) {
        this.shipDrawingDataList = shipDrawingDataList;
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
}