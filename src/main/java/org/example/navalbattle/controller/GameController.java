package org.example.navalbattle.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
            Cell cell = (Cell) getNodeFromGridPane(playerBoard, column, row);
            cell.setShipDrawing(shipDrawing);
            shipDrawing.toFront();
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            Integer columnIndex = GridPane.getColumnIndex(node);
            Integer rowIndex = GridPane.getRowIndex(node);

            if (columnIndex == null)
                columnIndex = 0;
            if (rowIndex == null)
                rowIndex = 0;

            if (columnIndex == col && rowIndex == row) {
                return node;
            }
        }
        return null;
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
        switch (navalBattle.getArrangeInt()) {
            case 0:
                Cell cell;
                // Aircraft Carrier (Horizontal)
                ShipDrawing aircraftCarrier = new ShipDrawing(4, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 6, 7);
                cell.setShipDrawing(aircraftCarrier);

                // Submarine 1 (Vertical)
                ShipDrawing submarine1 = new ShipDrawing(3, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 5, 1);
                cell.setShipDrawing(submarine1);

                // Submarine 2 (Vertical)
                ShipDrawing submarine2 = new ShipDrawing(3, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 6, 2);
                cell.setShipDrawing(submarine2);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer1 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 5);
                cell.setShipDrawing(destroyer1);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer2 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 1, 6);
                cell.setShipDrawing(destroyer2);

                // Destroyer 3 (Horizontal)
                ShipDrawing destroyer3 = new ShipDrawing(2, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 1);
                cell.setShipDrawing(destroyer3);

                // Frigate 1
                ShipDrawing frigate1 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 6, 0);
                cell.setShipDrawing(frigate1);

                // Frigate 2
                ShipDrawing frigate2 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 9, 1);
                cell.setShipDrawing(frigate2);

                // Frigate 3
                ShipDrawing frigate3 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 3, 5);
                cell.setShipDrawing(frigate3);

                // Frigate 4
                ShipDrawing frigate4 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 4, 5);
                cell.setShipDrawing(frigate4);

                aircraftCarrier.setOpacity(0);
                submarine1.setOpacity(0);
                submarine2.setOpacity(0);
                destroyer1.setOpacity(0);
                destroyer2.setOpacity(0);
                destroyer3.setOpacity(0);
                frigate1.setOpacity(0);
                frigate2.setOpacity(0);
                frigate3.setOpacity(0);
                frigate4.setOpacity(0);
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
                ShipDrawing aircraftCarrier1 = new ShipDrawing(4, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 4);
                cell.setShipDrawing(aircraftCarrier1);

                // Submarine 1 (Horizontal)
                ShipDrawing submarine11 = new ShipDrawing(3, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 3, 8);
                cell.setShipDrawing(submarine11);

                // Submarine 2 (Vertical)
                ShipDrawing submarine22 = new ShipDrawing(3, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 9, 3);
                cell.setShipDrawing(submarine22);

                // Destroyer 1 (Vertical)
                ShipDrawing destroyer11 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 5, 5);
                cell.setShipDrawing(destroyer11);

                // Destroyer 2 (Vertical)
                ShipDrawing destroyer22 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 6, 6);
                cell.setShipDrawing(destroyer22);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer33 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 3, 4);
                cell.setShipDrawing(destroyer33);

                // Frigate 1
                ShipDrawing frigate11 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 1, 0);
                cell.setShipDrawing(frigate11);

                // Frigate 2
                ShipDrawing frigate22 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 3, 0);
                cell.setShipDrawing(frigate22);

                // Frigate 3
                ShipDrawing frigate33 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 3, 1);
                cell.setShipDrawing(frigate33);

                // Frigate 4
                ShipDrawing frigate44 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 9, 0);
                cell.setShipDrawing(frigate44);

                aircraftCarrier1.setOpacity(0);
                submarine11.setOpacity(0);
                submarine22.setOpacity(0);
                destroyer11.setOpacity(0);
                destroyer22.setOpacity(0);
                destroyer33.setOpacity(0);
                frigate11.setOpacity(0);
                frigate22.setOpacity(0);
                frigate33.setOpacity(0);
                frigate44.setOpacity(0);
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
                ShipDrawing aircraftCarrier2 = new ShipDrawing(4, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 1, 8);
                cell.setShipDrawing(aircraftCarrier2);

                // Submarine 1 (Vertical)
                ShipDrawing submarine12 = new ShipDrawing(3, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 2);
                cell.setShipDrawing(submarine12);

                // Submarine 2 (Horizontal)
                ShipDrawing submarine23 = new ShipDrawing(3, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 4, 2);
                cell.setShipDrawing(submarine23);

                // Destroyer 1 (Horizontal)
                ShipDrawing destroyer12 = new ShipDrawing(2, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 8);
                cell.setShipDrawing(destroyer12);

                // Destroyer 2 (Horizontal)
                ShipDrawing destroyer23 = new ShipDrawing(2, false);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 5, 8);
                cell.setShipDrawing(destroyer23);

                // Destroyer 3 (Vertical)
                ShipDrawing destroyer34 = new ShipDrawing(2, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 1, 0);
                cell.setShipDrawing(destroyer34);

                // Frigate 1
                ShipDrawing frigate12 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 0, 6);
                cell.setShipDrawing(frigate12);

                // Frigate 2
                ShipDrawing frigate23 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 2, 5);
                cell.setShipDrawing(frigate23);

                // Frigate 3
                ShipDrawing frigate34 = new ShipDrawing(1, true);
                cell = (Cell) getNodeFromGridPane(enemyBoard, 6, 5);
                cell.setShipDrawing(frigate34);
                break;
        }
    }

    @FXML
    void onShowEnemyBoardButtonClick(ActionEvent actionEvent){
        for(ShipDrawing enemyShipDrawing : enemyShipDrawings){
            enemyShipDrawing.setOpacity(100);
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