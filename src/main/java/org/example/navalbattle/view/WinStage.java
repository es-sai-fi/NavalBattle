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

import java.io.IOException;

/**
 * This class represents the stage displayed when the player wins the Naval Battle game.
 */
public class WinStage extends Stage {

    /**
     * Constructs a new WinStage.
     * This constructor loads the FXML file and sets up the stage with the appropriate scene and properties.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public WinStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/win-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }
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
     * Retrieves the single instance of WinStage.
     * If the instance does not exist, it will be created.
     *
     * @return the single instance of WinStage.
     * @throws IOException if the FXML file cannot be loaded during the instance creation.
     */
    public static WinStage getInstance() throws IOException {
        return WinStage.WinStageHolder.INSTANCE != null ?
                WinStage.WinStageHolder.INSTANCE :
                (WinStage.WinStageHolder.INSTANCE = new WinStage());
    }

    /**
     * Deletes the current instance of WinStage by closing the stage and setting the instance to null.
     */
    public static void deleteInstance() {
        WinStageHolder.INSTANCE.close();
        WinStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the WinStage instance.
     * This ensures that the instance is lazily initialized.
     */
    private static class WinStageHolder {
        private static WinStage INSTANCE;
    }
}
