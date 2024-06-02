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
    private Random random = new Random();
    private GridPane playerBoard;
    private final String hitImagePath = String.valueOf(getClass().getResource("/org/example/navalbattle/images/hit.png"));
    private final String failImagePath = String.valueOf(getClass().getResource("/org/example/navalbattle/images/fail.png"));
    private GridPane enemyBoard;
    private GameController gameController;
    private List<ShipDrawingData> playerShipsDrawingData;
    private List<ShipDrawingData> enemyShipsDrawingData = new ArrayList<>();

    public void updateOccupation() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                Cell playerCell = playerBoardAux[row][column];
                Cell enemyCell = enemyBoardAux[row][column];
                if (!(playerCell.getShip() == null)) {
                    playerCell.setOccupied(true);
                }
                if (!(enemyCell.getShip() == null)) {
                    enemyCell.setOccupied(true);
                }
            }
        }
    }

    public void launchAttack(int row, int column) {
        Cell enemyCell = enemyBoardAux[row][column];

        try {
            if (enemyCell.getHasBeenAttacked()) {
                throw new NavalBattleException("La celda ya ha sido atacada.");
            } else {
                handlePlayerAttack(enemyCell, row, column);
                handleEnemyAttack();
            }
        } catch (NavalBattleException e) {
            System.out.println("An error has occurred: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handlePlayerAttack(Cell enemyCell, int row, int column) throws IOException {
        if (!enemyCell.isOccupied()) {
            ImageView imageView = new ImageView(new Image(failImagePath));
            enemyBoard.add(imageView, column, row);
        } else {
            enemyCell.getShip().receiveDamage();
            ImageView imageView = new ImageView(new Image(hitImagePath));
            enemyBoard.add(imageView, column, row);
            checkWinState();
        }
        enemyCell.toFront();
        enemyCell.setHasBeenAttacked(true);
    }

    private void handleEnemyAttack() throws IOException {
        boolean validAttack = false;
        do {
            int randomRow = random.nextInt(10);
            int randomColumn = random.nextInt(10);
            Cell playerCell = playerBoardAux[randomRow][randomColumn];
            if (!playerCell.getHasBeenAttacked()) {
                validAttack = true;
                if (playerCell.isOccupied()) {
                    ImageView imageView = new ImageView(new Image(hitImagePath));
                    playerCell.getShip().receiveDamage();
                    playerBoard.add(imageView, randomColumn, randomRow);
                    checkLoseState();
                } else {
                    ImageView imageView = new ImageView(new Image(failImagePath));
                    playerBoard.add(imageView, randomColumn, randomRow);
                }
                playerCell.setHasBeenAttacked(true);
            }
        } while (!validAttack);
    }

    private void checkWinState() throws IOException {
        boolean win = true;
        int index = 0;
        System.out.println("\n");
        for(Ship enemyShip: enemyShips){
            System.out.println("Ship " + index + " is: " + (enemyShip.isAlive() ? "alive" : "dead"));
            index++;
        }
        System.out.println("\n");
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

    private void checkLoseState() throws IOException {
        boolean lose = true;
        int index = 0;
        System.out.println("\n");
        for(Ship playerShip: playerShips){
            System.out.println("Ship " + index + " is: " + (playerShip.isAlive() ? "alive" : "dead"));
            index++;
        }
        System.out.println("\n");
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

    public void arrangeEnemyBoard(List<ShipDrawing> enemyShipDrawings) {
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

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setPlayerBoard(GridPane playerBoard) {
        this.playerBoard = playerBoard;
    }

    public void setEnemyBoard(GridPane enemyBoard) {
        this.enemyBoard = enemyBoard;
    }
}