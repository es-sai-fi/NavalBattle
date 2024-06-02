package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoseStage extends Stage {

    public LoseStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/navalbattle/lose-view.fxml"));
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

    public static LoseStage getInstance() throws IOException {
        return  LoseStage.LoseStageHolder.INSTANCE != null ?
                LoseStage.LoseStageHolder.INSTANCE :
                (LoseStage.LoseStageHolder.INSTANCE = new LoseStage());
    }

    public static void deleteInstance() {
        LoseStage.LoseStageHolder.INSTANCE.close();
        LoseStage.LoseStageHolder.INSTANCE = null;
    }

    private static class LoseStageHolder {
        private static LoseStage INSTANCE;
    }
}