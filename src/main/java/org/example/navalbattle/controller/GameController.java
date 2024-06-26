/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
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

/**
 * Controller class for handling the game's logic and interactions.
 * This class is responsible for initializing the game boards, handling user actions,
 * and managing the game state.
 */
public class GameController {
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

    /**
     * Initializes the game controller with the given naval battle instance.
     * Sets up the enemy and player boards and serializes the game state.
     *
     * @param navalBattle the naval battle instance
     * @throws IOException if an I/O error occurs during serialization
     */
    public void initialize(NavalBattle navalBattle) throws IOException {
        this.navalBattle = navalBattle;
        createEnemyBoard();
        this.navalBattle.arrangeEnemyBoard(enemyShipDrawings, continueGame, playerBoard, enemyBoard, this);
        this.navalBattle.arrangePlayerBoard(continueGame, playerBoard);
        NavalBattleSerialize.serialize(this.navalBattle);
    }

    /**
     * Continues the game by deserializing the saved game state and arranging the boards.
     *
     * @return true if the game state was successfully loaded, false otherwise
     * @throws IOException if an I/O error occurs during deserialization
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public boolean continueGame() throws IOException, ClassNotFoundException {
        try {
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

    /**
     * Creates and sets up the enemy board with clickable cells that handle attack events.
     */
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

    /**
     * Handles the action of showing the enemy board when the corresponding button is clicked.
     *
     * @param actionEvent the action event triggered by clicking the button
     */
    @FXML
    void onShowEnemyBoardButtonClick(ActionEvent actionEvent) {
        for (ShipDrawing enemyShipDrawing : enemyShipDrawings) {
            enemyShipDrawing.setVisible(true);
        }
        showEnemyBoardImage.setVisible(false);
        showEnemyBoardButton.setVisible(false);
    }

    /**
     * Sets the player ships drawing data.
     *
     * @param playerShipsDrawingData the list of player ship drawing data
     */
    public void setPlayerShipsDrawingData(List<ShipDrawingData> playerShipsDrawingData) {
        this.playerShipsDrawingData = playerShipsDrawingData;
    }

    /**
     * Handles the winning scenario by deleting the save file, disabling the boards, and displaying the win stage.
     *
     * @throws IOException if an I/O error occurs during file deletion
     */
    public void win() throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if (serializedNavalBattle.delete()) {
            System.out.println("Save file deleted.");
        }
        playerBoard.setDisable(true);
        enemyBoard.setDisable(true);
        WinStage.getInstance();
    }

    /**
     * Handles the losing scenario by deleting the save file, disabling the boards, and displaying the lose stage.
     *
     * @throws IOException if an I/O error occurs during file deletion
     */
    public void lose() throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if (serializedNavalBattle.delete()) {
            System.out.println("Save file deleted.");
        }
        playerBoard.setDisable(true);
        enemyBoard.setDisable(true);
        LoseStage.getInstance();
    }
}
