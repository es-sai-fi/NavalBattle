package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    HBox hBox;
    @FXML
    TextField addRowTextField;
    @FXML
    TextField addColumnTextField;
    ShipDrawing[] shipDrawings = new ShipDrawing[10];
    boolean boatClicked;
    int indexBoatToAdd;
    Cell[][] playerBoardAux = new Cell[10][10];
    List<Ship> playerShips = new ArrayList<>();
    @FXML
    void onAddShipButtonClick(ActionEvent actionEvent){
        try{
            char rowInput = addRowTextField.getText().charAt(0);
            char columnInput = addColumnTextField.getText().charAt(0);
            if (addRowTextField.getText().isEmpty() || addColumnTextField.getText().isEmpty()) {
                throw new InputException("No input has been done to either text field.");
            }
            if(!isDigit(rowInput) || !isDigit(columnInput)){
                throw new InputException("Input is not a number");
            }
            if(!boatClicked){
                throw new InputException("A boat has not been selected");
            }
            int row = getNumericValue(rowInput);
            int column = getNumericValue(columnInput);
            if (row < 0 || row > 9 || column < 0 || column > 9){
                throw new IndexOutOfBoundsException("Row or column is out of bounds.");
            }
            if (playerBoardAux[row][column].isOccupied()){
                throw new InputException("Cell is already occupied.");
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
            }


        } catch (InputException e1){
            System.out.println("Se ha generado un error: " + e1.getMessage());
        } catch (IndexOutOfBoundsException e2){
            System.out.println("Se ha generado un error: " + e2.getMessage());
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
        addRowTextField.setEditable(true);
        if(addRowTextField.getText().length() >= 1){
            addRowTextField.setEditable(false);
            addRowTextField.setText("");
            System.out.println("ingrese solo 1 letra");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createShipDrawings();
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
            hBox.getChildren().add(shipDrawings[finalShipDrawingIndex]);
        }
    }

    @FXML
    void onRotateButtonClick(ActionEvent actionEvent) {
        ShipDrawing shipDrawing = shipDrawings[indexBoatToAdd];
        if (boatClicked && shipDrawing != null) {
            shipDrawing.rotate();
        }
    }

    @FXML
    void onClearButtonClick(ActionEvent actionEvent){
        GridPane newGridPane = new GridPane();
        playerBoard.getChildren().clear();
        hBox.getChildren().clear();

        playerBoardAux = new Cell[10][10];
        playerShips.clear();
        Arrays.fill(shipDrawings, null);
        boatClicked = false;
        addColumnTextField.setText("");
        addRowTextField.setText("");

        playerBoard = newGridPane;
        createPlayerBoard();
        createShipDrawings();
    }

    @FXML
    void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        boolean canContinue = true;
        for (ShipDrawing shipDrawing : shipDrawings){
            if(!shipDrawing.hasBeenPlaced()){
                canContinue = false;
                break;
            }
        }
        try{
            if(canContinue){
                GameStage gameStage = new GameStage();
                GameController gameController = gameStage.getGameController();
                NavalBattle navalBattle = new NavalBattle(gameController);
                navalBattle.setPlayerBoardAux(playerBoardAux);
                navalBattle.setPlayerShips(playerShips);
                gameController.setNavalBattle(navalBattle);
                PositionStage.deleteInstance();
            }
            else{
                throw new AttackException("All boats have not been placed.");
            }
        } catch (AttackException e){
            System.out.println("Se ha generado un error: " + e.getMessage());
        }
    }

    public Cell[][] getPlayerBoardAux(){
        return playerBoardAux;
   }

    public List<Ship> getPlayerShips() {
        return playerShips;
    }
}
