package org.example.navalbattle.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class WinStage extends Stage {


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

    public static WinStage getInstance() throws IOException {
        return  WinStage.WinStageHolder.INSTANCE != null ?
                WinStage.WinStageHolder.INSTANCE :
                (WinStage.WinStageHolder.INSTANCE = new WinStage());
    }

    public static void deleteInstance() {
        WinStageHolder.INSTANCE.close();
        WinStageHolder.INSTANCE = null;
    }

    private static class WinStageHolder {
        private static WinStage INSTANCE;
    }
}
