/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class FrontController implements Initializable {

    @FXML
    private Button r;
    @FXML
    private Button inscription;
    @FXML
    private Button club;
    @FXML
    private Button restaurant;
    @FXML
    private Button reclamation;
    @FXML
    private Button evenement;
    @FXML
    private Button profile;
    @FXML
    private Label idconnecte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        inscription.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gesenfant/Views/Inscription.fxml"));
                inscription.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        restaurant.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gui/cantinefront.fxml"));
                restaurant.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        profile.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
                inscription.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
         r.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
                r.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
    }    

    @FXML
    private void envoiReclamation(ActionEvent event) {
           try {
                
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/gesreclamation/views/ajoutReclamation.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                     gesreclamation.controllers.AjoutReclamationController ajout = loader.getController();
                        ajout.myFunction3(idconnecte.getText());
                        
                        stage.setScene(scene);
        stage.show();
           } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
      public void myFunction2 (String text){
    idconnecte.setText(text);
    idconnecte.setVisible(false);
}

    @FXML
    private void listclub(ActionEvent event) throws IOException {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("/gescluuub/view/Parentclub.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
    }
    

}
