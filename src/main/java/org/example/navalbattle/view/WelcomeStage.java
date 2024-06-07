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
import org.example.navalbattle.controller.WelcomeController;

import java.io.IOException;

/**
 * Represents the welcome stage (window) for the naval battle game.
 * This class handles the loading of the FXML layout for the welcome view.
 */
public class WelcomeStage extends Stage {
    private WelcomeController welcomeController;

    /**
     * Constructs a WelcomeStage and initializes the welcome view by loading the FXML layout.
     *
     * @throws IOException if there is an error loading the FXML file
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/welcome-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }
        welcomeController = loader.getController();
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
     * Returns the singleton instance of WelcomeStage.
     *
     * @return the singleton instance of WelcomeStage
     * @throws IOException if there is an error creating the instance
     */
    public static WelcomeStage getInstance() throws IOException {
        return WelcomeStage.WelcomeStageHolder.INSTANCE != null ?
                WelcomeStage.WelcomeStageHolder.INSTANCE :
                (WelcomeStage.WelcomeStageHolder.INSTANCE = new WelcomeStage());
    }

    /**
     * Deletes the singleton instance of WelcomeStage.
     * Closes the stage and sets the instance to null.
     */
    public static void deleteInstance() {
        WelcomeStage.WelcomeStageHolder.INSTANCE.close();
        WelcomeStage.WelcomeStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of WelcomeStage.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }
}
