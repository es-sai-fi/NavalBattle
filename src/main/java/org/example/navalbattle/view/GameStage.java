/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;

/**
 * Represents the main stage (window) for the naval battle game.
 * This class handles the loading of the FXML layout and initializes the game controller.
 */
public class GameStage extends Stage {
    private final GameController gameController;

    /**
     * Constructs a GameStage and initializes the game view by loading the FXML layout.
     *
     * @throws IOException if there is an error loading the FXML file
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/game-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }
        gameController = loader.getController();
        setTitle("Batalla Naval");
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * Returns the game controller associated with this stage.
     *
     * @return the game controller
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Returns the singleton instance of GameStage.
     *
     * @return the singleton instance of GameStage
     * @throws IOException if there is an error creating the instance
     */
    public static GameStage getInstance() throws IOException {
        return GameStage.GameStageHolder.INSTANCE != null ?
                GameStage.GameStageHolder.INSTANCE :
                (GameStage.GameStageHolder.INSTANCE = new GameStage());
    }

    /**
     * Deletes the singleton instance of GameStage.
     * Closes the stage and sets the instance to null.
     */
    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of GameStage.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }
}
