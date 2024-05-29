package org.example.navalbattle.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import org.example.navalbattle.model.*;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.HelpStage;
import org.example.navalbattle.view.PositionStage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

public class PositionController implements Initializable {
    @FXML
    GridPane playerBoard;
    @FXML
    HBox shipsBox;
    @FXML
    TextField addRowTextField;
    @FXML
    TextField addColumnTextField;
    boolean boatClicked;
    int indexBoatToAdd;
    private Cell[][] playerBoardAux = new Cell[10][10];
    private ShipDrawing[] shipDrawings = new ShipDrawing[10];
    private List<Ship> playerShips = new ArrayList<>();
    NavalBattle navalBattle = new NavalBattle();
    @FXML
    void onAddShipButtonClick(ActionEvent actionEvent){
        try{
            char rowInput = addRowTextField.getText().charAt(0);
            char columnInput = addColumnTextField.getText().charAt(0);
            if (addRowTextField.getText().isEmpty() || addColumnTextField.getText().isEmpty()) {
                throw new NavalBattleException("No input has been done to either text field.");
            }
            if(!isDigit(rowInput) || !isDigit(columnInput)){
                throw new NavalBattleException("Input is not a number");
            }
            if(!boatClicked){
                throw new NavalBattleException("A boat has not been selected");
            }
            int row = getNumericValue(rowInput);
            int column = getNumericValue(columnInput);
            if (row < 0 || row > 9 || column < 0 || column > 9){
                throw new IndexOutOfBoundsException("Row or column is out of bounds.");
            }
            if (playerBoardAux[row][column].isOccupied()){
                throw new NavalBattleException("Cell is already occupied.");
            }
            ShipDrawing shipDrawing = shipDrawings[indexBoatToAdd];
            int shipDrawingType = shipDrawing.getType();
            if(shipDrawingType == 1){
                Cell cell = playerBoardAux[row][column];
                Ship ship = new Ship(1);
                playerShips.add(ship);
                playerBoard.add(shipDrawing, column, row);
                shipDrawing.setHasBeenPlaced(true);
                cell.setShip(ship);
                cell.setOccupied(true);
                boatClicked = false;
                addRowTextField.setText("");
                addColumnTextField.setText("");
            }
            else{
                Ship ship = new Ship(shipDrawingType);
                int type = shipDrawing.getType();
                int finalRow = row + type;
                int finalColumn = row + type;
                if (shipDrawing.isVertical()){
                    if(finalRow <= 9) {
                        for (int i = row; i < row + type; i++) {
                            Cell cell = playerBoardAux[i][column];
                            cell.setShip(ship);
                            cell.setOccupied(true);
                        }
                        boatClicked = false;
                    }
                    else{
                        throw new IndexOutOfBoundsException("Ship doesn't fit in said position.");
                    }
                }
                else{
                    if(finalColumn <= 9) {
                        for (int j = column; j < column + type; j++) {
                            Cell cell = playerBoardAux[row][j];
                            cell.setShip(ship);
                            cell.setOccupied(true);
                        }
                        boatClicked = false;
                    }
                    else{
                        throw new IndexOutOfBoundsException("Ship doesn't fit in said position.");
                    }
                }
                playerBoard.add(shipDrawing, column, row);
                playerShips.add(ship);
                shipDrawing.setHasBeenPlaced(true);
                addRowTextField.setText("");
                addColumnTextField.setText("");
            }


        } catch (NavalBattleException e1){
            System.out.println("An error has occurred: " + e1.getMessage());
            addRowTextField.setText("");
            addColumnTextField.setText("");
        } catch (IndexOutOfBoundsException e2){
            System.out.println("An error has occurred: " + e2.getMessage());
            addRowTextField.setText("");
            addColumnTextField.setText("");
        }
    }

    @FXML
    void onHelpButtonClick(ActionEvent actionEvent) throws IOException{
        HelpStage helpStage = new HelpStage();
        helpStage.show();
    }

    @FXML
    void onAddLetterKeyPressed(KeyEvent keyEvent){
        TextField textField = (TextField) keyEvent.getSource();
        textField.setEditable(true);
        try{
            if(textField.getText().length() >= 1){
                textField.setEditable(false);
                textField.setText("");
                throw new NavalBattleException("More than 1 digit has been input");
            }
        } catch (NavalBattleException e){
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createShipDrawings();
    }

    public void createShipDrawings(){
        shipDrawings[0] = new ShipDrawing(50, 200, 4);
        shipDrawings[1] = new ShipDrawing(50, 150, 3);
        shipDrawings[2] = new ShipDrawing(50, 150, 3);
        shipDrawings[3] = new ShipDrawing(50, 100, 2);
        shipDrawings[4] = new ShipDrawing(50, 100, 2);
        shipDrawings[5] = new ShipDrawing(50, 100, 2);
        shipDrawings[6] = new ShipDrawing(50, 50, 1);
        shipDrawings[7] = new ShipDrawing(50, 50, 1);
        shipDrawings[8] = new ShipDrawing(50, 50, 1);
        shipDrawings[9] = new ShipDrawing(50, 50, 1);
        for(int shipDrawingIndex = 0; shipDrawingIndex < 10; shipDrawingIndex++){
            int finalShipDrawingIndex = shipDrawingIndex;
            shipDrawings[shipDrawingIndex].setOnMouseClicked(event -> {
                indexBoatToAdd = finalShipDrawingIndex;
                boatClicked = true;
            });
            shipsBox.getChildren().add(shipDrawings[finalShipDrawingIndex]);
        }
    }

    public void createPlayerBoard(){
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell cell = new Cell(row, column);
                playerBoard.add(cell, column, row);
                playerBoardAux[row][column] = cell;
            }
        }
    }

    @FXML
    void onRotateButtonClick(ActionEvent actionEvent) {
        ShipDrawing shipDrawing = shipDrawings[indexBoatToAdd];
        try{
            if (boatClicked) {
                shipDrawing.rotate();
            }
            else{
                throw new NavalBattleException("A boat has not been clicked.");
            }
        } catch(NavalBattleException e){
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    @FXML
    void onClearButtonClick(ActionEvent actionEvent){
        shipsBox.getChildren().clear();
        playerBoard.getChildren().clear();
        playerBoardAux = new Cell[10][10];
        playerShips.clear();
        Arrays.fill(shipDrawings, null);
        boatClicked = false;
        addColumnTextField.setText("");
        addRowTextField.setText("");
        createPlayerBoard();
        createShipDrawings();
    }

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
                GameController gameController = GameStage.getInstance().getGameController();
                navalBattle.setGameController(gameController);
                navalBattle.setPlayerBoardAux(playerBoardAux);
                navalBattle.setPlayerShips(playerShips);

                // Clear current playerBoard in GameController to avoid duplicates
                gameController.getPlayerBoard().getChildren().clear();

                // Transfer nodes from playerBoard in PositionStage to playerBoard in GameController
                for (Node node : playerBoard.getChildren()) {
                    Integer colIndex = GridPane.getColumnIndex(node);
                    Integer rowIndex = GridPane.getRowIndex(node);
                    gameController.getPlayerBoard().add(node, colIndex != null ? colIndex : 0, rowIndex != null ? rowIndex : 0);
                }

                gameController.setNavalBattle(navalBattle);
                PositionStage.deleteInstance();
            } else {
                throw new NavalBattleException("All boats have not been placed.");
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }
}