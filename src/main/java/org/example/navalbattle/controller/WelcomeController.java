package org.example.navalbattle.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.example.navalbattle.view.GameStage;

import java.io.IOException;

public class WelcomeController {


    @FXML
    private ImageView hangedMan;
    @FXML
    private TextField secretWordTextField;

    @FXML
    void onWelcomeButtonClick(ActionEvent event) throws IOException {
        GameController gameController = GameStage.getInstance().getGameController();
    }
}
