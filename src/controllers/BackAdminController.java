/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class BackAdminController implements Initializable {

    @FXML
    private JFXButton gestionu;
    Users u ;
    @FXML
    private JFXButton transportBouton;
    @FXML
    private Button retourBoutton;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        


        
        
                        
    }
    @FXML
    private void gestionusers(ActionEvent event) throws IOException {
   gestionu.getScene().getWindow().hide();
        Stage a = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionUsers.fxml"));
               Scene scene = new Scene(root);
               a.setScene(scene);
                a.show();
               a.setResizable(false);




    
}

    @FXML
    private void gestionTransport(ActionEvent event) throws IOException {
        
           transportBouton.getScene().getWindow().hide();
        Stage transport = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionTransport.fxml"));
        Scene scene = new Scene(root);
        transport.setScene(scene);
        transport.show();
        transport.setResizable(false);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
               retourBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gesenfant/Views/back.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    
     
}