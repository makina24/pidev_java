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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class HomeController implements Initializable {

    @FXML
    private JFXButton msgbutton;

     public static int messagerie = 0;
    @FXML
    private JFXButton profilebutton;
    @FXML
    private JFXButton deconnectbutton;
    @FXML
    private Text connected;
    @FXML
    private Text NomPrenom;
    @FXML
    private AnchorPane menupane;
    @FXML
    private AnchorPane content;
    @FXML
    private JFXButton connexiobutton;
    @FXML
    private JFXButton dashbordBout;
    @FXML
    private JFXButton paiementBoutton;
    @FXML
    private JFXButton retourBoutton;
   
     
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
            if (LoginController.userConnected != null) {
                menupane.getChildren().remove(connexiobutton);
                NomPrenom.setText("" + LoginController.userConnected.getNom() + " " + LoginController.userConnected.getPrenom());
                if (LoginController.userConnected .getRoles().contains("ROLE_ADMIN")) {
                     profilebutton.setVisible(true);
                     msgbutton.setVisible(true);
                     dashbordBout.setVisible(true);
                     deconnectbutton.setVisible(true);
                     connexiobutton.setVisible(false);
                      paiementBoutton.setVisible(false);

                }else if ((LoginController.userConnected .getRoles().contains("ROLE_USER") )){
                              profilebutton.setVisible(true);
                            msgbutton.setVisible(true);
                menupane.getChildren().remove(dashbordBout);
                         deconnectbutton.setVisible(true);
                        connexiobutton.setVisible(false);
                       paiementBoutton.setVisible(true);
                      dashbordBout.setVisible(false);

                       


                }  
                     
             
            } else {
              

                NomPrenom.setVisible(false);
                connected.setVisible(false);
                menupane.getChildren().remove(profilebutton);
                menupane.getChildren().remove(deconnectbutton);

               
                menupane.getChildren().remove(msgbutton);
                 menupane.getChildren().remove(dashbordBout);
                 menupane.getChildren().remove(paiementBoutton);

                 

                


            }

         
    }
         
    
    
    
    @FXML
    private void ShowMsg(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Messagerie.fxml"));

        content.getChildren().setAll(pane);
        
        
    }

    @FXML
    private void Afficherprofile(ActionEvent event) throws IOException {
        
       
       
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Profile.fxml"));

        content.getChildren().setAll(pane);
        
        
    }

   

   

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        LoginController.userConnected = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
        AnchorPane root = (AnchorPane) loader.load();

        menupane.getScene().setRoot(root);
    }

    @FXML
    private void Connexion(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));

        content.getChildren().setAll(pane);
    }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
         dashbordBout.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/BackAdmin.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
        
        
    }

    @FXML
    private void paiement(ActionEvent event) throws IOException {
        
               paiementBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/Paiement.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
               retourBoutton.getScene().getWindow().hide();
        Stage s = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gesenfant/Views/front.fxml"));
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.show();
        
        
    }
    
    
    
   
  


   
}
