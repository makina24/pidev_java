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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Routing;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ProfileController implements Initializable {
    
     ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_USER", "ROLE_ADMIN");

    @FXML
    private AnchorPane profilecontent;
    public static int afficherprofile=0;
    public static int afficherPaiement = 0 ;
  
    @FXML
    private JFXButton paiementBoutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if (afficherprofile!=0) {
            AnchorPane pane1;
         try {
               pane1 = FXMLLoader.load(getClass().getResource("/gui/EditProfile.fxml"));
                               profilecontent.getChildren().setAll(pane1);

           } catch (IOException ex) {
               Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
           }
           afficherprofile=0;
        }
        
        if (afficherPaiement!=0) {
            AnchorPane pane1;
           try {
               pane1 = FXMLLoader.load(getClass().getResource("/gui/ShowDemande.fxml"));
                               profilecontent.getChildren().setAll(pane1);

           } catch (IOException ex) {
               Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
           }
           afficherPaiement=0;
        }
      

    }    

    @FXML
    private void Afficherprofile(ActionEvent event) throws IOException {
           AnchorPane pane=FXMLLoader.load(getClass().getResource("/gui/Profile.fxml"));
                AnchorPane pane1=FXMLLoader.load(getClass().getResource("/gui/EditProfile.fxml"));

                profilecontent.getChildren().setAll(pane1);
        
        
    }

    @FXML
    private void Paiement(ActionEvent event) throws IOException {
           paiementBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/Paiement.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
        
    }

    
}
