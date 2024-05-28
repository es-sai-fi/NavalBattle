package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.model.Cell;
import org.example.navalbattle.model.InputException;
import org.example.navalbattle.model.Ship;
import org.example.navalbattle.model.ShipDrawing;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Character.isDigit;

public class PositionController implements Initializable {
    @FXML
    GridPane playerBoard;
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField addRowTextField;
    @FXML
    TextField addColumnTextField;
    ShipDrawing[] shipDrawings = new ShipDrawing[10];
    int boatIndexToAdd;
    int boatTypeToAdd;
    boolean boatClicked;
    Cell[][] playerBoardAux = new Cell[10][10];
    List<Ship> playerShips = new ArrayList<>();
    @FXML
    void onAddShipButtonClick(ActionEvent actionEvent){
        try{
            char rowInput = addRowTextField.getText().charAt(0);
            char columnInput = addColumnTextField.getText().charAt(0);
            if(!isDigit(rowInput) || !isDigit(columnInput)){
                throw new InputException("Input is not a number");
            }
            if(!boatClicked){
                throw new InputException("A boat has not been selected");
            }
            int row = (int) rowInput;
            int column = (int) columnInput;
            if (playerBoardAux[row][column].isOccupied()){
                throw new InputException("Cell is already occupied.");
            }
            ShipDrawing shipDrawing = shipDrawings[boatIndexToAdd];
            if(shipDrawing.getType() == 1){
                Ship ship = new Ship(1);
                playerShips.add(ship);
                playerBoardAux.add(shipDrawing, column, row);
                shipDrawing.setHasBeenPlaced(true);
                playerBoardAux[row][column].setShip(ship);
                boatClicked = false;
            }
            else{
                if (shipDrawing.isVertical()){
                    for (int j = column-shipDrawing.getType(); j < )
                }
                playerBoard.add(shipDrawings[boatIndexToAdd], column, row);
                else{

                }
            }


        } catch (InputException e1){
            System.out.println("Se ha generado un error: " + e1.getMessage());
        } catch (IndexOutOfBoundsException e2){
            System.out.println("Se ha generado un error: " + e2.getMessage());
            addRowTextField.setText("");
            addColumnTextField.setText("");
            shipDrawings[boatIndexToAdd].setHasBeenClicked(false);
        } catch (StringIndexOutOfBoundsException e3){
            System.out.println("Se ha generado un error: " + e3.getMessage());
            shipDrawings[boatIndexToAdd].setHasBeenClicked(false);
        }
    }

    @FXML
    void onAddLetterKeyPressed(KeyEvent keyEvent){
        TextField textField = (TextField) keyEvent.getSource();
        textField.setEditable(true);
        if(textField.getText().length() > 1){
            textField.setEditable(false);
            textField.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createPlayerBoard();
        createPlayerShips();
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

    public void createPlayerShips(){
        for (int i = 0; i < 10; i++) {
            Ship ship = new Ship(i);
            playerShips[i] = ship;
        }
    }
    public Cell[][] getPlayerBoardAux(){
        return playerBoardAux;
   }

    public Ship[] getPlayerShips() {
        return playerShips;
    }
}



