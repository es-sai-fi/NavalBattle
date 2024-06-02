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

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button continueButton;

    @FXML
    private Button playButton;
    @FXML
    void onContinueButtonClick(ActionEvent event) throws IOException {
        GameController gameStageController = GameStage.getInstance().getGameController();
        WelcomeStage.deleteInstance();
    }

    @FXML
    void onPlayButtonClick(ActionEvent event) throws IOException {
        PositionController positionController = PositionStage.getInstance().getPositionController();
        WelcomeStage.deleteInstance();
    }

}
