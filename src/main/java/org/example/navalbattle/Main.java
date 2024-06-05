package org.example.navalbattle;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.IOException;

public class Main extends Application {
    @Override
public void start(Stage primaryStage) throws IOException {
    WelcomeStage.getInstance();
}

public static void main(String[] args) {
    launch(args);
}
}