package org.example.navalbattle.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavalBattle implements Serializable {
    private Cell[][] playerBoardAux;
    private Cell[][] enemyBoardAux;
    private List<Ship> playerShips = new ArrayList<>();
    private List<Ship> enemyShips = new ArrayList<>();
    private List<ShipDrawingData> playerShipsDrawingData;
    private List<ShipDrawingData> enemyShipsDrawingData = new ArrayList<>();
    private final String hitImagePath = String.valueOf(getClass().getResource("/org/example/navalbattle/images/hit.png"));
    private final String failImagePath = String.valueOf(getClass().getResource("/org/example/navalbattle/images/fail.png"));

    public void launchAttack(int row, int column, GridPane playerBoard, GridPane enemyBoard, GameController gameController) {
        Cell enemyCell = enemyBoardAux[row][column];

        try {
            if (enemyCell.hasBeenAttacked()) {
                throw new NavalBattleException("La celda ya ha sido atacada.");
            } else {
                handlePlayerAttack(enemyCell, row, column, enemyBoard, gameController);
                handleEnemyAttack(playerBoard, gameController);
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handlePlayerAttack(Cell enemyCell, int row, int column, GridPane enemyBoard, GameController gameController) throws IOException {
        if (!enemyCell.isOccupied()) {
            ImageView imageView = new ImageView(new Image(failImagePath));
            enemyBoard.add(imageView, column, row);
        } else {
            enemyCell.getShip().receiveDamage();
            ImageView imageView = new ImageView(new Image(hitImagePath));
            enemyBoard.add(imageView, column, row);
        }
        enemyCell.toFront();
        enemyCell.setHasBeenAttacked(true);
    }

    private void handleEnemyAttack(GridPane playerBoard, GameController gameController) throws IOException {
        boolean validAttack = false;
        Random random = new Random();
        do {
            int randomRow = random.nextInt(10);
            int randomColumn = random.nextInt(10);
            Cell playerCell = playerBoardAux[randomRow][randomColumn];
            if (!playerCell.hasBeenAttacked()) {
                validAttack = true;
                if (playerCell.isOccupied()) {
                    ImageView imageView = new ImageView(new Image(hitImagePath));
                    playerCell.getShip().receiveDamage();
                    playerBoard.add(imageView, randomColumn, randomRow);
                    checkLoseState(gameController);
                } else {
                    ImageView imageView = new ImageView(new Image(failImagePath));
                    playerBoard.add(imageView, randomColumn, randomRow);
                }
                playerCell.setHasBeenAttacked(true);
                NavalBattleSerialize.serialize(this);
                checkWinState(gameController);
                checkLoseState(gameController);
            }
        } while (!validAttack);
    }

    private void checkWinState(GameController gameController) throws IOException {
        boolean win = true;
        for(Ship enemyShip: enemyShips){
            if(enemyShip.isAlive()){
                win = false;
                break;
            }
        }
        if(win){
            gameController.win();
        }
    }

    private void checkLoseState(GameController gameController) throws IOException {
        boolean lose = true;
        for(Ship playerShip: playerShips){
            if(playerShip.isAlive()){
                lose = false;
                break;
            }
        }
        if(lose){
            gameController.lose();
        }
    }

    public void arrangePlayerBoard(boolean continueGame, GridPane playerBoard){
        for(ShipDrawingData playerShipsDrawingData: playerShipsDrawingData){
            int row = playerShipsDrawingData.getRow();
            int column = playerShipsDrawingData.getColumn();
            int type = playerShipsDrawingData.getType();
            boolean isVertical = playerShipsDrawingData.isVertical();
            ShipDrawing shipDrawing = new ShipDrawing(type, isVertical);
            playerBoard.add(shipDrawing, column, row);
        }
        if(continueGame){
            resumePlayerBoard(playerBoard);
        }
    }

    public void resumePlayerBoard(GridPane playerBoard){
        for(int row = 0; row < 10; row++){
            for(int column = 0; column < 10; column++){
                Cell playerCell = playerBoardAux[row][column];
                if(playerCell.hasBeenAttacked()){
                    if(playerCell.isOccupied()){
                        ImageView imageView = new ImageView(new Image(hitImagePath));
                        playerBoard.add(imageView, column, row);
                    }
                    else{
                        ImageView imageView = new ImageView(new Image(failImagePath));
                        playerBoard.add(imageView, column, row);
                    }
                    playerBoard.add(playerCell, column, row);
                    playerCell.toFront();
                }
            }
        }
    }

    public void arrangeEnemyBoard(List<ShipDrawing> enemyShipDrawings, boolean continueGame, GridPane playerBoard, GridPane enemyBoard, GameController gameController) {
        Random random = new Random();
        if(continueGame){
            for(ShipDrawingData enemyShipDrawingData: enemyShipsDrawingData){
                int row = enemyShipDrawingData.getRow();
                int column = enemyShipDrawingData.getColumn();
                int type = enemyShipDrawingData.getType();
                boolean isVertical = enemyShipDrawingData.isVertical();
                ShipDrawing shipDrawing = new ShipDrawing(type, isVertical);
                enemyShipDrawings.add(shipDrawing);
                shipDrawing.setVisible(false);
                enemyBoard.add(shipDrawing, column, row);
            }
            for(int row = 0; row < 10; row++){
                for(int column = 0; column < 10; column++){
                    Cell enemyCell = enemyBoardAux[row][column];
                    enemyCell.setOnMouseClicked(event -> {
                        this.launchAttack(enemyCell.getRow(), enemyCell.getColumn(), playerBoard, enemyBoard, gameController);
                    });
                    if(enemyCell.hasBeenAttacked()){
                        if(enemyCell.isOccupied()){
                            ImageView imageView = new ImageView(new Image(hitImagePath));
                            enemyBoard.add(imageView, column, row);
                        }
                        else{
                            ImageView imageView = new ImageView(new Image(failImagePath));
                            enemyBoard.add(imageView, column, row);
                        }
                    }
                    enemyBoard.add(enemyCell, column, row);
                    enemyCell.toFront();
                }
            }
        }
        else{
            createEnemyShipDrawings(enemyShipDrawings);
            for (ShipDrawing enemyShipDrawing : enemyShipDrawings) {
                boolean boatPlaced = false;
                while (!boatPlaced) {
                    int randomRow = random.nextInt(10);
                    int randomColumn = random.nextInt(10);
                    if (canPlaceShip(enemyShipDrawing, randomRow, randomColumn)) {
                        placeShip(enemyShipDrawing, enemyBoard, randomRow, randomColumn);
                        boatPlaced = true;
                    }
                }
            }
        }
    }

    private boolean canPlaceShip(ShipDrawing shipDrawing, int startRow, int startColumn) {
        int shipSize = shipDrawing.getType();
        boolean isVertical = shipDrawing.isVertical();

        for (int i = 0; i < shipSize; i++) {
            int row = isVertical ? startRow + i : startRow;
            int column = isVertical ? startColumn : startColumn + i;

            if (row > 9 || column > 9 || enemyBoardAux[row][column].isOccupied()) {
                return false;
            }
        }
        return true;
    }

    private void placeShip(ShipDrawing shipDrawing, GridPane enemyBoard, int startRow, int startColumn) {
        int shipSize = shipDrawing.getType();
        boolean isVertical = shipDrawing.isVertical();
        Ship ship = new Ship(shipSize);

        for (int i = 0; i < shipSize; i++) {
            int row = isVertical ? startRow + i : startRow;
            int column = isVertical ? startColumn : startColumn + i;

            Cell cell = enemyBoardAux[row][column];
            cell.setShip(ship);
            cell.setOccupied(true);
        }

        enemyShips.add(ship);
        enemyBoard.add(shipDrawing, startColumn, startRow);
        shipDrawing.toBack();
        enemyShipsDrawingData.add(new ShipDrawingData(startRow, startColumn, shipSize, isVertical));
    }

    private void createEnemyShipDrawings(List<ShipDrawing> enemyShipDrawings) {
        Random random = new Random();
        int[] enemyShipDrawingTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int enemyShipDrawingType : enemyShipDrawingTypes){
            boolean isVertical = random.nextBoolean();
            ShipDrawing enemyShipDrawing = new ShipDrawing(enemyShipDrawingType, isVertical);
            enemyShipDrawing.setVisible(false);
            enemyShipDrawings.add(enemyShipDrawing);
        }
    }

    public void setPlayerShipsDrawingData(List<ShipDrawingData> playerShipsDrawingData) {
        this.playerShipsDrawingData = playerShipsDrawingData;
    }

    public List<ShipDrawingData> getPlayerShipsDrawingData() {
        return playerShipsDrawingData;
    }

    public void setPlayerShips(List<Ship> playerShips) {
        this.playerShips = playerShips;
    }

    public void setPlayerBoardAux(Cell[][] playerBoardAux) {
        this.playerBoardAux = playerBoardAux;
    }

    public void setEnemyBoardAux(Cell[][] enemyBoardAux) {
        this.enemyBoardAux = enemyBoardAux;
    }
}