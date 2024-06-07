/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.navalbattle.view.GameStage;
import org.example.navalbattle.view.PositionStage;
import org.example.navalbattle.view.WelcomeStage;

import java.io.File;
import java.io.IOException;

/**
 * Controller class for handling the welcome screen actions in the Naval Battle game.
 * This class manages the initial user interactions, such as starting a new game or continuing an existing one.
 * It handles button clicks to navigate to the appropriate stages of the game.

 */
public class WelcomeController {

    @FXML
    private Button continueButton;

    @FXML
    private Button playButton;

    /**
     * Handles the action of continuing a previously saved game.
     * If a saved game is found, it loads the game state and navigates to the game stage.
     * If no saved game is found, it prints an error message.
     *
     * @param event the action event triggered by clicking the continue button
     * @throws IOException if an I/O error occurs during loading the game stage
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    @FXML
    void onContinueButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        GameController gameController = GameStage.getInstance().getGameController();
        boolean continueSucceded = gameController.continueGame();
        if (continueSucceded) {
            WelcomeStage.deleteInstance();
        } else {
            GameStage.deleteInstance();
            System.out.println("An error has occurred: There's no save to load from.");
        }
    }

    /**
     * Handles the action of starting a new game.
     * Deletes any existing saved game file and navigates to the position stage for setting up a new game.
     *
     * @param event the action event triggered by clicking the play button
     * @throws IOException if an I/O error occurs during deleting the save file or loading the position stage
     */
    @FXML
    void onPlayButtonClick(ActionEvent event) throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if (serializedNavalBattle.delete()) {
            System.out.println("Save file deleted.");
        }
        PositionStage.getInstance();
        WelcomeStage.deleteInstance();
    }
}
