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

    private List<ShipDrawingData> shipDrawingDataList = new ArrayList<>();

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
                addRowTextField.setText("");
                addColumnTextField.setText("");
                shipDrawingDataList.add(new ShipDrawingData(row, column, 1, shipDrawing.isVertical()));
            }
            else{
                Ship ship = new Ship(shipDrawingType);
                int type = shipDrawing.getType();
                int finalRow = row + type;
                int finalColumn = column + type;
                if (shipDrawing.isVertical()){
                    if(finalRow <= 9) {
                        for (int i = row; i < row + type; i++) {
                            Cell cell = playerBoardAux[i][column];
                            cell.setShip(ship);
                            cell.setOccupied(true);
                        }
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
                shipDrawingDataList.add(new ShipDrawingData(row, column, type, shipDrawing.isVertical()));
            }
            updateNextBoatIndex();

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

    private void updateNextBoatIndex() {
        for (int i = 0; i < shipDrawings.length; i++) {
            if (!shipDrawings[i].hasBeenPlaced()) {
                indexBoatToAdd = i;
                return;
            }
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
        for(int shipDrawingIndex = 0; shipDrawingIndex < 10; shipDrawingIndex++){
            int finalShipDrawingIndex = shipDrawingIndex;
            shipDrawings[shipDrawingIndex].setOnMouseClicked(event -> {
                indexBoatToAdd = finalShipDrawingIndex;
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
            if (indexBoatToAdd != -1 && !(shipDrawing.getType() == 1)) {
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
        indexBoatToAdd = 0;
        addColumnTextField.setText("");
        addRowTextField.setText("");
        createPlayerBoard();
        createShipDrawings();
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
                navalBattle.setShipDrawingDataList(shipDrawingDataList);
                navalBattle.setPlayerBoardAux(playerBoardAux);
                navalBattle.setPlayerShips(playerShips);
                gameController.setShipDrawingDataList(shipDrawingDataList);
                gameController.initialize(navalBattle);
                PositionStage.deleteInstance();
            } else {
                throw new NavalBattleException("All boats have not been placed.");
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        }
    }

    public List<ShipDrawingData> getShipDrawingDataList() {
        return shipDrawingDataList;
    }
}