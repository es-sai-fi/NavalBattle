package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.navalbattle.controller.PositionController;

import java.io.IOException;

public class PositionStage extends Stage {
    private PositionController positionController;

    public PositionStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/position-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }
        positionController = loader.getController();
        setTitle("Batalla Naval");
        Scene scene = new Scene(root, 816, 1020);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    public PositionController getPositionController(){
        return positionController;
    }

    public static PositionStage getInstance() throws IOException {
        return  PlayerStageHolder.INSTANCE != null ?
                PlayerStageHolder.INSTANCE :
                (PlayerStageHolder.INSTANCE = new PositionStage());
    }

    public static void deleteInstance() {
        PlayerStageHolder.INSTANCE.close();
        PlayerStageHolder.INSTANCE = null;
    }

    private static class PlayerStageHolder {
        private static PositionStage INSTANCE;
    }
}