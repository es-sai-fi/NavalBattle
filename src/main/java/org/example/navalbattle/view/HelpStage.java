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
 * Represents the help stage (window) for the naval battle game.
 * This class handles the loading of the FXML layout for the help view.
 */
public class HelpStage extends Stage {

    /**
     * Constructs a HelpStage and initializes the help view by loading the FXML layout.
     *
     * @throws IOException if there is an error loading the FXML file
     */
    public HelpStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/help-view.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Re-throwing the caught IOException
            throw new IOException("Error while loading FXML file", e);
        }
        setTitle("Ayuda");
        Scene scene = new Scene(root);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    /**
     * Returns the singleton instance of HelpStage.
     *
     * @return the singleton instance of HelpStage
     * @throws IOException if there is an error creating the instance
     */
    public static HelpStage getInstance() throws IOException {
        return HelpStage.HelpStageHolder.INSTANCE != null ?
                HelpStage.HelpStageHolder.INSTANCE :
                (HelpStage.HelpStageHolder.INSTANCE = new HelpStage());
    }

    /**
     * Deletes the singleton instance of HelpStage.
     * Closes the stage and sets the instance to null.
     */
    public static void deleteInstance() {
        HelpStage.HelpStageHolder.INSTANCE.close();
        HelpStage.HelpStageHolder.INSTANCE = null;
    }

    /**
     * Holder class for the singleton instance of HelpStage.
     */
    private static class HelpStageHolder {
        private static HelpStage INSTANCE;
    }
}
