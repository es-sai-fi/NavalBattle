package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.navalbattle.controller.GameController;

import java.io.IOException;

public class HelpStage extends Stage {

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
        Scene scene = new Scene(root, 600, 600);
        getIcons().add(
                new Image(
                        String.valueOf(getClass().getResource("/org/example/navalbattle/images/favicon.png"))));
        setScene(scene);
        setResizable(false);
        show();
    }

    public static HelpStage getInstance() throws IOException {
        return  HelpStage.HelpStageHolder.INSTANCE != null ?
                HelpStage.HelpStageHolder.INSTANCE :
                (HelpStage.HelpStageHolder.INSTANCE = new HelpStage());
    }

    public static void deleteInstance() {
        HelpStage.HelpStageHolder.INSTANCE.close();
        HelpStage.HelpStageHolder.INSTANCE = null;
    }

    private static class HelpStageHolder {
        private static HelpStage INSTANCE;
    }
}
