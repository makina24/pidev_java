/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controllers.Scene2Controller;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Genuine Coder
 */
public class Scene1Controller implements Initializable {

    @FXML
    private TextField inputField;
    @FXML
    private Button actionBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionBtn.setOnAction(event -> {
            loadSceneAndSendMessage();
        });
    }
    
    public void receiveMessage(String message){
        
    }

    private void loadSceneAndSendMessage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/scene2.fxml"));
            Parent root = loader.load();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("votre message a été envoyé !");
            alert.show();
            //Get controller of scene2
            Scene2Controller scene2Controller = loader.getController();
            scene2Controller.transferMessage(inputField.getText());
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ELITE");
          
        } catch (IOException ex) {
            System.err.println(ex);
            
    }
        }
 }
