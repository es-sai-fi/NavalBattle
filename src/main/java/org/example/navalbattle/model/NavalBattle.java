package org.example.navalbattle.model;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavalBattle {
    private Cell[][] playerBoardAux = new Cell[10][10];
    private Cell[][] enemyBoardAux = new Cell[10][10];
    private List<Ship> playerShips = new ArrayList<>();
    private List<Ship> enemyShips = new ArrayList<>();
    private Random random = new Random();
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

    public void launchAttack(int row, int column){
        boolean validAttack = false;
        boolean win = true;
        boolean lose = true;
        Cell enemyCell = enemyBoardAux[row][column];
        try{
            if(enemyCell.getHasBeenAttacked()){
                gameController.getHitImage().setVisible(false);
                gameController.getMissImage().setVisible(false);
                throw new NavalBattleException("La celda ya ha sido atacada.");
            }
            if(!enemyCell.isOccupied()){
                gameController.getHitImage().setVisible(false);
                enemyCell.toFront();
                enemyCell.getImageView().toFront();
                enemyCell.setImage("/org/example/navalbattle/images/fail.png");
                enemyCell.setHasBeenAttacked(true);
                gameController.getMissImage().setVisible(true);
            }
            else{
                gameController.getMissImage().setVisible(false);
                enemyCell.toFront();
                enemyCell.getShip().receiveDamage();
                enemyCell.getImageView().toFront();
                enemyCell.setImage("/org/example/navalbattle/images/hit.png");
                enemyCell.setHasBeenAttacked(true);
                gameController.getHitImage().setVisible(true);
                for(Ship enemyShip: enemyShips){
                    if (enemyShip.isAlive()) {
                        win = false;
                        break;
                    }
                }
                if(win){
                    gameController.win();
                }
                else{
                    do {
                        int randomRow = random.nextInt(10);
                        int randomColumn = random.nextInt(10);
                        Cell playerCell = playerBoardAux[randomRow][randomColumn];
                        if(!playerCell.getHasBeenAttacked()){
                            validAttack = true;
                            if(playerCell.isOccupied()){
                                playerCell.toFront();
                                playerCell.getShip().receiveDamage();
                                playerCell.setImage("/org/example/navalbattle/images/hit.png");
                                playerCell.setHasBeenAttacked(true);
                            }
                            else{
                                playerCell.toFront();
                                playerCell.setImage("/org/example/navalbattle/images/fail.png");
                                playerCell.setHasBeenAttacked(true);
                            }
                        }
                    }while(!validAttack);
                    for (Ship playerShip: playerShips){
                        if (playerShip.isAlive()) {
                            lose = false;
                            break;
                        }
                    }
                    if(lose){
                        gameController.lose();
                    }
                }
            }
        } catch (NavalBattleException e1) {
            System.out.println("An error has occurred: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("An error has occurred: " + e2.getMessage());
        }
    }

    public void arrangeEnemyBoard(GridPane enemyBoard, List<ShipDrawing> enemyShipDrawings) {
        createEnemyShipDrawings(enemyShipDrawings);
        for (ShipDrawing enemyShipDrawing : enemyShipDrawings) {
            boolean canBePlaced = true;
            boolean boatPlaced = false;
            while(!boatPlaced) {
                int randomRow = random.nextInt(9);
                int randomColumn = random.nextInt(9);
                Cell enemyCell = enemyBoardAux[randomRow][randomColumn];
                if (!enemyCell.isOccupied()) {
                    int enemyShipDrawingType = enemyShipDrawing.getType();
                    if (enemyShipDrawingType == 1) {
                        boatPlaced = true;
                        Ship ship = new Ship(1);
                        enemyShips.add(ship);
                        enemyBoard.add(enemyShipDrawing, randomColumn, randomRow);
                        enemyShipDrawing.toBack();
                        enemyCell.setShip(ship);
                        enemyCell.setOccupied(true);
                        enemyShipsDrawingData.add(new ShipDrawingData(randomRow, randomColumn, 1, enemyShipDrawing.isVertical()));
                    } else {
                        Ship ship = new Ship(enemyShipDrawingType);
                        int finalRow = randomRow + enemyShipDrawingType;
                        int finalColumn = randomColumn + enemyShipDrawingType;
                        if (enemyShipDrawing.isVertical()) {
                            if (finalRow <= 9) {
                                for (int i = randomRow; i < finalRow; i++) {
                                    Cell cell = enemyBoardAux[i][randomColumn];
                                    if (cell.isOccupied()) {
                                        canBePlaced = false;
                                        break;
                                    }
                                }
                                if(canBePlaced) {
                                    for (int i = randomRow; i < finalRow; i++) {
                                        Cell cell = enemyBoardAux[i][randomColumn];
                                        cell.setShip(ship);
                                        cell.setOccupied(true);
                                    }
                                    boatPlaced = true;
                                    enemyBoard.add(enemyShipDrawing, randomColumn, randomRow);
                                    enemyShipDrawing.toBack();
                                    enemyShipsDrawingData.add(new ShipDrawingData(randomRow, randomColumn, enemyShipDrawingType, enemyShipDrawing.isVertical()));
                                }
                            }
                        } else {
                            if(finalColumn <= 9) {
                                for (int j = randomColumn; j < finalColumn; j++) {
                                    Cell cell = enemyBoardAux[randomRow][j];
                                    if (cell.isOccupied()) {
                                        canBePlaced = false;
                                        break;
                                    }
                                }
                                if (canBePlaced) {
                                    for (int j = randomColumn; j < finalColumn; j++) {
                                        Cell cell = enemyBoardAux[randomRow][j];
                                        cell.setShip(ship);
                                        cell.setOccupied(true);
                                    }
                                    boatPlaced = true;
                                    enemyBoard.add(enemyShipDrawing, randomColumn, randomRow);
                                    enemyShipDrawing.toBack();
                                    enemyShipsDrawingData.add(new ShipDrawingData(randomRow, randomColumn, enemyShipDrawingType, enemyShipDrawing.isVertical()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void createEnemyShipDrawings(List<ShipDrawing> enemyShipDrawings) {
        int[] enemyShipDrawingTypes = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
        for (int enemyShipDrawingType : enemyShipDrawingTypes){
            boolean isVertical = random.nextInt(10)+1 <= 5;
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
}