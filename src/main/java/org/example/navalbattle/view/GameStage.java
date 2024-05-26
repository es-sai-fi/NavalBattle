package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;

public class GameStage extends Stage {
    private GameController gameController;

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/game-view.fxml"));
        Parent root = loader.load();
        gameController = loader.getController();
        setTitle("Batalla Naval");
        Scene scene = new Scene(root, 600, 600);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
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
