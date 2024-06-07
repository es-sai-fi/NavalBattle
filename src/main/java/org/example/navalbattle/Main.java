/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */package org.example.navalbattle;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.IOException;

/**
 * The main class for the Naval Battle game application.
 * This class launches the JavaFX application.
 */
public class Main extends Application {

    /**
     * The main entry point for all JavaFX applications.
     * This method is called after the system is ready for the application to begin running.
     *
     * @param primaryStage the primary stage for this application, onto which the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be primary stages.
     * @throws IOException if there is an error loading the WelcomeStage.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }

    /**
     * The main method for launching the application.
     * This method is required for compatibility with environments that do not support direct JavaFX application launching.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
