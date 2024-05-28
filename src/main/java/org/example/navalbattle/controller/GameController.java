package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.view.LoseStage;
import org.example.navalbattle.view.WinStage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    Label missLabel;
    @FXML
    Label hitLabel;
    @FXML
    Label statusLabel;
    @FXML
    GridPane playerBoard;
    @FXML
    GridPane enemyBoard;
    Stage stage;
    private NavalBattle navalBattle = new NavalBattle(this);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage = (Stage) missLabel.getScene().getWindow();
        createEnemyBoard();
        createEnemyShips();
        navalBattle.arrangeEnemyBoard();
        missLabel.setVisible(false);
        hitLabel.setVisible(false);
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

    public void updateStatusLabel(String message, int type){
        switch(type){
            case 1:
                statusLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: green;");
            case 2:
                statusLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: red;");
        }
        statusLabel.setText(message);
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
        stage.close();
    }

    public void lose() throws IOException {
        LoseStage loseStage = new LoseStage();
        loseStage.show();
        stage.close();
    }


    public void setNavalBattle(NavalBattle navalBattle) {
        this.navalBattle = navalBattle;
    }

}