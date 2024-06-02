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

public class WelcomeController {

    @FXML
    private Button continueButton;

    @FXML
    private Button playButton;
    @FXML
    void onContinueButtonClick(ActionEvent event) throws IOException, ClassNotFoundException {
        GameController gameController = GameStage.getInstance().getGameController();
        boolean continueSucceded = gameController.continueGame();
        if(continueSucceded){
            WelcomeStage.deleteInstance();
        }
        else{
            GameStage.deleteInstance();
            System.out.println("An error has occurred: There's no save to load from.");
        }
    }

    @FXML
    void onPlayButtonClick(ActionEvent event) throws IOException {
        File serializedNavalBattle = new File("A:\\IntelliJProyectos\\NavalBattle\\navalBattle.nvl");
        if(serializedNavalBattle.delete()){
            System.out.println("Save file deleted.");
        }
        PositionController positionController = PositionStage.getInstance().getPositionController();
        WelcomeStage.deleteInstance();
    }

}
