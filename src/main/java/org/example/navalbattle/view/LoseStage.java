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
 * Represents the lose stage (window) for the naval battle game.
 * This class handles the loading of the FXML layout for the lose view.
 */
public class LoseStage extends Stage {

    /**
     * Constructs a LoseStage and initializes the lose view by loading the FXML layout.
     *
     * @throws IOException if there is an error loading the FXML file
     */
    public LoseStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/lose-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
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
     * Returns the singleton instance of LoseStage.
     *
     * @return the singleton instance of LoseStage
     * @throws IOException if there is an error creating the instance
     */
    public static LoseStage getInstance() throws IOException {
        return LoseStage.LoseStageHolder.INSTANCE != null ?
                LoseStage.LoseStageHolder.INSTANCE :
                (LoseStage.LoseStageHolder.INSTANCE = new LoseStage());
    }

    /**
     * Deletes the singleton instance of LoseStage.
     * Closes the stage and sets the instance to null.
     */
    public static void deleteInstance() {
        LoseStage.LoseStageHolder.INSTANCE.close();
        LoseStage.LoseStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of LoseStage.
     */
    private static class LoseStageHolder {
        private static LoseStage INSTANCE;
    }
}
