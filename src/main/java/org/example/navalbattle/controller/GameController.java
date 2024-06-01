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
    private List<ShipDrawingData> playerShipsDrawingData = new ArrayList<>();
    private List<ShipDrawing> enemyShipDrawings = new ArrayList<>();
    private NavalBattle navalBattle;

    public void initialize(NavalBattle navalBattle) {
        missImage.setVisible(false);
        hitImage.setVisible(false);
        this.navalBattle = navalBattle;
        createEnemyBoard();
        this.navalBattle.arrangeEnemyBoard(enemyBoard, enemyShipDrawings);
        createPlayerBoard();
    }

    public void createPlayerBoard(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
            }
        }
        for(ShipDrawingData playerShipsDrawingData: playerShipsDrawingData){
            int row = playerShipsDrawingData.getRow();
            int column = playerShipsDrawingData.getColumn();
            int type = playerShipsDrawingData.getType();
            boolean isVertical = playerShipsDrawingData.isVertical();
            ShipDrawing shipDrawing = new ShipDrawing(type, isVertical);
            playerBoard.add(shipDrawing, column, row);
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
    }

    @FXML
    void onShowEnemyBoardButtonClick(ActionEvent actionEvent){
        for(ShipDrawing enemyShipDrawing : enemyShipDrawings){
            enemyShipDrawing.setVisible(true);
        }
    }

    public void setPlayerShipsDrawingData(List<ShipDrawingData> playerShipsDrawingData) {
        this.playerShipsDrawingData = playerShipsDrawingData;
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
}