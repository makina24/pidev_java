/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class GestionTransportController implements Initializable {

    @FXML
    private JFXButton chauffeurBoutton;
    @FXML
    private JFXButton ligneBouttoun;
    @FXML
    private JFXButton backBoutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void GestionChauffeur(ActionEvent event) throws IOException {
             chauffeurBoutton.getScene().getWindow().hide();
        Stage c = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionChauffeurs.fxml"));
        Scene scene = new Scene(root);
        c.setScene(scene);
        c.show();
        c.setResizable(false);
    }

    @FXML
    private void GestionLigne(ActionEvent event) throws IOException {
             ligneBouttoun.getScene().getWindow().hide();
        Stage l = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionLigneTransport.fxml"));
        Scene scene = new Scene(root);
        l.setScene(scene);
        l.show();
        l.setResizable(false);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
        
              backBoutton.getScene().getWindow().hide();
        Stage rb = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/BackAdmin.fxml"));
        Scene scene = new Scene(root);
        rb.setScene(scene);
        rb.show();
        rb.setResizable(false);
    }
    
}
