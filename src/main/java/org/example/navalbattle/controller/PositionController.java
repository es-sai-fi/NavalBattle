/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.example.navalbattle.model.*;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.HelpStage;
import org.example.navalbattle.view.PositionStage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

/**
 * Controller class for handling the positioning of ships in the game.
 * This class is responsible for initializing the player board, handling user input for ship placement,
 * and managing the game state before starting the actual game.
 */
public class PositionController implements Initializable {
    @FXML
    private GridPane playerBoard;
    @FXML
    private HBox shipsBox;
    @FXML
    private TextField addRowTextField;
    @FXML
    private TextField addColumnTextField;
    int indexBoatToAdd = 0;
    private Cell[][] playerBoardAux = new Cell[10][10];
    private ShipDrawing[] shipDrawings = new ShipDrawing[10];
    private List<Ship> playerShips = new ArrayList<>();
    private List<ShipDrawingData> playerShipsDrawingData = new ArrayList<>();
    NavalBattle navalBattle = new NavalBattle();

    /**
     * Handles the action of adding a ship to the player board.
     *
     * @param actionEvent the action event triggered by clicking the add ship button
     */
    @FXML
    void onAddShipButtonClick(ActionEvent actionEvent) {
        try {
            char rowInput = addRowTextField.getText().charAt(0);
            char columnInput = addColumnTextField.getText().charAt(0);
            if (addRowTextField.getText().isEmpty() || addColumnTextField.getText().isEmpty()) {
                throw new NavalBattleException("No input has been done to either text field.");
            }
            if (!isDigit(rowInput) || !isDigit(columnInput)) {
                throw new NavalBattleException("Input is not a number");
            }
            int row = getNumericValue(rowInput);
            int column = getNumericValue(columnInput);
            if (row < 0 || row > 9 || column < 0 || column > 9) {
                throw new IndexOutOfBoundsException("Row or column is out of bounds.");
            }
            if (playerBoardAux[row][column].isOccupied()) {
                throw new NavalBattleException("Cell is already occupied.");
            }
            ShipDrawing shipDrawing = shipDrawings[indexBoatToAdd];
            int shipDrawingType = shipDrawing.getType();
            if (shipDrawingType == 1) {
                Cell cell = playerBoardAux[row][column];
                Ship ship = new Ship(1);
                playerShips.add(ship);
                playerBoard.add(shipDrawing, column, row);
                shipDrawing.setHasBeenPlaced(true);
                cell.setShip(ship);
                cell.setOccupied(true);
                addRowTextField.setText("");
                addColumnTextField.setText("");
                playerShipsDrawingData.add(new ShipDrawingData(row, column, 1, shipDrawing.isVertical()));
            } else {
                Ship ship = new Ship(shipDrawingType);
                int type = shipDrawing.getType();
                int finalRow = row + type;
                int finalColumn = column + type;
                if (shipDrawing.isVertical()) {
                    for (int i = row; i < finalRow; i++) {
                        Cell cell = playerBoardAux[i][column];
                        if (cell.isOccupied()) {
                            throw new NavalBattleException("Ship can't be placed in said position.");
                        }
                    }
                    if (finalRow <= 10) {
                        for (int i = row; i < finalRow; i++) {
                            Cell cell = playerBoardAux[i][column];
                            cell.setShip(ship);
                            cell.setOccupied(true);
                        }
                    } else {
                        throw new IndexOutOfBoundsException("Ship doesn't fit in said position.");
                    }
                } else {
                    for (int j = column; j < finalColumn; j++) {
                        Cell cell = playerBoardAux[row][j];
                        if (cell.isOccupied()) {
                            throw new NavalBattleException("Ship can't be placed in said position.");
                        }
                    }
                    if (finalColumn <= 10) {
                        for (int j = column; j < finalColumn; j++) {
                            Cell cell = playerBoardAux[row][j];
                            cell.setShip(ship);
                            cell.setOccupied(true);
                        }
                    } else {
                        throw new IndexOutOfBoundsException("Ship doesn't fit in said position.");
                    }
                }
                playerBoard.add(shipDrawing, column, row);
                playerShips.add(ship);
                shipDrawing.setHasBeenPlaced(true);
                addRowTextField.setText("");
                addColumnTextField.setText("");
                playerShipsDrawingData.add(new ShipDrawingData(row, column, type, shipDrawing.isVertical()));
            }
            updateNextBoatIndex();

        } catch (NavalBattleException e1) {
            System.out.println("An error has occurred: " + e1.getMessage());
            addRowTextField.setText("");
            addColumnTextField.setText("");
        } catch (IndexOutOfBoundsException e2) {
            System.out.println("An error has occurred: " + e2.getMessage());
            addRowTextField.setText("");
            addColumnTextField.setText("");
        }
    }

    /**
     * Updates the index of the next boat to add.
     */
    private void updateNextBoatIndex() {
        for (int i = 0; i < shipDrawings.length; i++) {
            if (!shipDrawings[i].hasBeenPlaced()) {
                indexBoatToAdd = i;
                return;
            }
        }
    }

    /**
     * Handles the action of showing the help stage.
     *
     * @param actionEvent the action event triggered by clicking the help button
     * @throws IOException if an I/O error occurs during loading the help stage
     */
    @FXML
    void onHelpButtonClick(ActionEvent actionEvent) throws IOException {
        HelpStage helpStage = new HelpStage();
        helpStage.show();
    }

    /**
     * Handles the event of a key being pressed in the text fields for row and column input.
     *
     * @param keyEvent the key event triggered by pressing a key in the text field
     */
    @FXML
    void onAddLetterKeyPressed(KeyEvent keyEvent) {
        TextField textField = (TextField) keyEvent.getSource();
        textField.setEditable(true);
        try {
            if (textField.getText().length() >= 1) {
                textField.setEditable(false);
                textField.setText("");
                throw new NavalBattleException("More than 1 digit has been input");
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * Initializes the position controller.
     * Sets up the player board and the ship drawings.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createShipDrawings();
    }

    /**
     * Creates the ship drawings and adds them to the ships box.
     */
    public void createShipDrawings() {
        shipDrawings[0] = new ShipDrawing(4, true);
        shipDrawings[1] = new ShipDrawing(3, true);
        shipDrawings[2] = new ShipDrawing(3, true);
        shipDrawings[3] = new ShipDrawing(2, true);
        shipDrawings[4] = new ShipDrawing(2, true);
        shipDrawings[5] = new ShipDrawing(2, true);
        shipDrawings[6] = new ShipDrawing(1, true);
        shipDrawings[7] = new ShipDrawing(1, true);
        shipDrawings[8] = new ShipDrawing(1, true);
        shipDrawings[9] = new ShipDrawing(1, true);
        for (int shipDrawingIndex = 0; shipDrawingIndex < 10; shipDrawingIndex++) {
            int finalShipDrawingIndex = shipDrawingIndex;
            shipDrawings[shipDrawingIndex].setOnMouseClicked(event -> {
                indexBoatToAdd = finalShipDrawingIndex;
            });
            shipsBox.getChildren().add(shipDrawings[finalShipDrawingIndex]);
        }
    }

    /**
     * Creates the player board with cells.
     */
    public void createPlayerBoard() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
                playerBoardAux[row][column] = cell;
            }
        }
    }

    /**
     * Handles the action of rotating a ship drawing.
     *
     * @param actionEvent the action event triggered by clicking the rotate button
     */
    @FXML
    void onRotateButtonClick(ActionEvent actionEvent) {
        ShipDrawing shipDrawing = shipDrawings[indexBoatToAdd];
        try {
            if (indexBoatToAdd != -1 && !(shipDrawing.getType() == 1)) {
                shipDrawing.rotate();
            } else {
                throw new NavalBattleException("A boat has not been clicked.");
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    /**
     * Handles the action of clearing the player board and ship drawings.
     *
     * @param actionEvent the action event triggered by clicking the clear button
     */
    @FXML
    void onClearButtonClick(ActionEvent actionEvent) {
        shipsBox.getChildren().clear();
        playerBoard.getChildren().clear();
        playerBoardAux = new Cell[10][10];
        playerShips.clear();
        Arrays.fill(shipDrawings, null);
        indexBoatToAdd = 0;
        addColumnTextField.setText("");
        addRowTextField.setText("");
        createPlayerBoard();
        createShipDrawings();
    }

    /**
     * Handles the action of starting the game.
     *
     * @param actionEvent the action event triggered by clicking the start button
     * @throws IOException if an I/O error occurs during loading the game stage
     */
    @FXML
    void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        boolean canContinue = true;
        for (ShipDrawing shipDrawing : shipDrawings) {
            if (!shipDrawing.hasBeenPlaced()) {
                canContinue = false;
                break;
            }
        }
        try {
            if (canContinue) {
                GameStage gameStage = GameStage.getInstance();
                GameController gameController = gameStage.getGameController();
                navalBattle.setPlayerShipsDrawingData(playerShipsDrawingData);
                navalBattle.setPlayerBoardAux(playerBoardAux);
                navalBattle.setPlayerShips(playerShips);
                gameController.setPlayerShipsDrawingData(playerShipsDrawingData);
                gameController.initialize(navalBattle);
                PositionStage.deleteInstance();
            } else {
                throw new NavalBattleException("All boats have not been placed.");
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }
}
