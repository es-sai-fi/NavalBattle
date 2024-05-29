package org.example.navalbattle.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.NavalBattle;
import org.example.navalbattle.model.Ship;
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
        navalBattle.setEnemyBoardAux(enemyBoardAux);
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