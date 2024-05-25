package org.example.hangedman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.hangedman.model.SecretWord;
import org.example.hangedman.view.GameStage;
import org.example.hangedman.view.alert.AlertBox;

import java.io.IOException;

import static java.lang.Character.isLetter;

public class WelcomeController {


    @FXML
    private ImageView hangedMan;
    @FXML
    private TextField secretWordTextField;

    @FXML
    void onWelcomeButtonClick(ActionEvent event) throws IOException {
        String word = secretWordTextField.getText().toLowerCase();
        Stage stage = (Stage) secretWordTextField.getScene().getWindow();
        int wordLength = word.length();
        if (wordLength == 0){
            AlertBox alert = new AlertBox();
            alert.showMessage("¡Error!", "No se ha ingresado una palabra.", "No se ha detectado una palabra, por favor ingrese una que contenga solo letras y no contenga espacios.", stage);
        }
        else{
            boolean wordIsValid = true;
            for(int i = 0; i < wordLength; i++){
                if(!isLetter(word.charAt(i))){
                    wordIsValid = false;
                    AlertBox alert = new AlertBox();
                    alert.showMessage("¡Error!", "Palabra no válida.", "La palabra ingresada no es válida, por favor ingresar solo letras y/o no utilizar espacio.", stage);
                    break;
                }
            }
            if (wordIsValid){
                SecretWord secretWord = new SecretWord(word);
                GameController gameController = GameStage.getInstance().getGameController();
                gameController.setSecretWord(secretWord);
                gameController.createTextFields();

                stage.close();
            }
        }
    }
}
