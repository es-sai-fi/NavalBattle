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
import org.example.navalbattle.controller.PositionController;

import java.io.IOException;

/**
 * Represents the position stage (window) for the naval battle game.
 * This class handles the loading of the FXML layout for the position view.
 */
public class PositionStage extends Stage {
    private PositionController positionController;

    /**
     * Constructs a PositionStage and initializes the position view by loading the FXML layout.
     *
     * @throws IOException if there is an error loading the FXML file
     */
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
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * Returns the position controller associated with this stage.
     *
     * @return the position controller
     */
    public PositionController getPositionController() {
        return positionController;
    }

    /**
     * Returns the singleton instance of PositionStage.
     *
     * @return the singleton instance of PositionStage
     * @throws IOException if there is an error creating the instance
     */
    public static PositionStage getInstance() throws IOException {
        return PlayerStageHolder.INSTANCE != null ?
                PlayerStageHolder.INSTANCE :
                (PlayerStageHolder.INSTANCE = new PositionStage());
    }

    /**
     * Deletes the singleton instance of PositionStage.
     * Closes the stage and sets the instance to null.
     */
    public static void deleteInstance() {
        PlayerStageHolder.INSTANCE.close();
        PlayerStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of PositionStage.
     */
    private static class PlayerStageHolder {
        private static PositionStage INSTANCE;
    }
}
