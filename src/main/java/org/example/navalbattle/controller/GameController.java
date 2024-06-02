package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.*;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.LoseStage;
import org.example.navalbattle.view.WinStage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController{
    @FXML
    private GridPane playerBoard;
    @FXML
    private GridPane enemyBoard;
    @FXML
    private Button showEnemyBoardButton;
    @FXML
    private ImageView showEnemyBoardImage;
    private boolean continueGame = false;
    private List<ShipDrawingData> playerShipsDrawingData = new ArrayList<>();
    private List<ShipDrawing> enemyShipDrawings = new ArrayList<>();
    private NavalBattle navalBattle;

    public void initialize(NavalBattle navalBattle) throws IOException {
        this.navalBattle = navalBattle;
        createEnemyBoard();
        this.navalBattle.arrangeEnemyBoard(enemyShipDrawings, continueGame, playerBoard, enemyBoard, this);
        this.navalBattle.arrangePlayerBoard(continueGame, playerBoard);
        NavalBattleSerialize.serialize(this.navalBattle);
    }

    public boolean continueGame() throws IOException, ClassNotFoundException {
        try{
            navalBattle = (NavalBattle) NavalBattleSerialize.deserialize("navalBattle.nvl");
            continueGame = true;
            navalBattle.arrangePlayerBoard(continueGame, playerBoard);
            navalBattle.arrangeEnemyBoard(enemyShipDrawings, continueGame, playerBoard, enemyBoard, this);
            return true;
        } catch (FileNotFoundException e1) {
            return false;
        } catch (IOException | ClassNotFoundException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void createEnemyBoard() {
        Cell[][] enemyBoardAux = new Cell[10][10];
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                cell.setOnMouseClicked(event -> {
                    navalBattle.launchAttack(cell.getRow(), cell.getColumn(), playerBoard, enemyBoard, this);
                });
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
        showEnemyBoardImage.setVisible(false);
        showEnemyBoardButton.setVisible(false);
    }

    public void setPlayerShipsDrawingData(List<ShipDrawingData> playerShipsDrawingData) {
        this.playerShipsDrawingData = playerShipsDrawingData;
    }

    public void win() throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if(serializedNavalBattle.delete()){
            System.out.println("Save file deleted.");
        }
        WinStage winStage = new WinStage();
        winStage.show();
        GameStage.deleteInstance();
    }

    public void lose() throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if(serializedNavalBattle.delete()){
            System.out.println("Save file deleted.");
        }
        LoseStage loseStage = new LoseStage();
        loseStage.show();
        GameStage.deleteInstance();
    }

    public void setPlayerBoard(GridPane playerBoard) {
        this.playerBoard = playerBoard;
    }
}