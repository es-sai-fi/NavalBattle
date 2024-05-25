package org.example.sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.sudoku.controller.GameController;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/sudoku/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Ventana Sudoku");
        Scene scene = new Scene(root, 600, 600);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/sudoku/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    public GameController getGameController(){
        return gameController;
    }

    public static GameStage getInstance() throws IOException{
        return GameStageHolder.INSTANCE = new GameStage();
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
}
