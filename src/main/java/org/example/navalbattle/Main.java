package org.example.navalbattle;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.navalbattle.model.ShipPanel;
import org.example.navalbattle.view.WelcomeStage;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        WelcomeStage.getInstance();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Barcos en Java 2D con Grids");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Ajustar el tamaño de la ventana
            frame.add(new ShipPanel());
            frame.setVisible(true);
        });
    }
}