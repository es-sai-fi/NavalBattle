package org.example.navalbattle.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NavalBattle {
    private Cell[][] playerBoardAux;
    private Cell[][] enemyBoardAux;
    private List<Ship> playerShips = new ArrayList<>();
    private List<Ship> enemyShips = new ArrayList<>();
    private Random random = new Random();
    private GridPane playerBoard;
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
                Image image = new Image(getClass().getResourceAsStream("/org/example/navalbattle/images/fail.png"));
                ImageView imageView = new ImageView(image);
                enemyBoard.add(imageView, column, row);
                enemyCell.setHasBeenAttacked(true);
                gameController.getMissImage().setVisible(true);
            }
            else{
                gameController.getMissImage().setVisible(false);
                enemyCell.getShip().receiveDamage();
                Image image = new Image(getClass().getResourceAsStream("/org/example/navalbattle/images/hit.png"));
                ImageView imageView = new ImageView(image);
                enemyBoard.add(imageView, column, row);
                enemyCell.setHasBeenAttacked(true);
                gameController.getHitImage().setVisible(true);
                checkWinState();
            }
            do {
                int randomRow = random.nextInt(10);
                int randomColumn = random.nextInt(10);
                Cell playerCell = playerBoardAux[randomRow][randomColumn];
                if(!playerCell.getHasBeenAttacked()){
                    validAttack = true;
                    if(playerCell.isOccupied()){
                        Image image = new Image(getClass().getResourceAsStream("/org/example/navalbattle/images/hit.png"));
                        ImageView imageView = new ImageView(image);
                        playerCell.getShip().receiveDamage();
                        playerBoard.add(imageView, randomColumn, randomRow);
                        playerCell.setHasBeenAttacked(true);
                        checkLoseState();
                    }
                    else{
                        Image image = new Image(getClass().getResourceAsStream("/org/example/navalbattle/images/fail.png"));
                        ImageView imageView = new ImageView(image);
                        playerBoard.add(imageView, randomColumn, randomRow);
                        playerCell.setHasBeenAttacked(true);
                    }
                }
            }while(!validAttack);
        } catch (NavalBattleException e1) {
            System.out.println("An error has occurred: " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println("An error has occurred: " + e2.getMessage());
        }
    }

    private void checkWinState() throws IOException {
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

    private void checkLoseState() throws IOException {
        boolean lose = true;
        for(Ship playerShip: playerShips){
            if(playerShip.isAlive()){
                lose = false;
                break;
            }
        }
        if(lose){
            gameController.win();
        }
    }

    public void arrangeEnemyBoard(GridPane enemyBoard, List<ShipDrawing> enemyShipDrawings) {
        createEnemyShipDrawings(enemyShipDrawings);
        for (ShipDrawing enemyShipDrawing : enemyShipDrawings) {
            boolean canBePlaced = true;
            boolean boatPlaced = false;
            while(!boatPlaced) {
                int randomRow = random.nextInt(10);
                int randomColumn = random.nextInt(10);
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
                            if(finalRow <= 10){
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
                            if(finalColumn <= 10){
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