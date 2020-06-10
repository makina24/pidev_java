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
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class BackController implements Initializable {

    @FXML
    private Button r;
    @FXML
    private Button gesenf;
    @FXML
    private Button gesclub;
    @FXML
    private Button gesres;
    @FXML
    private Button gesreclamation;
    @FXML
    private Button gesevenement;
    @FXML
    private Button gesutil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gesenf.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gesenfant/Views/Acceuil.fxml"));
                gesenf.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        gesres.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gui/cantineback.fxml"));
                gesres.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        gesutil.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gui/BackAdmin.fxml"));
                gesutil.getScene().setRoot(root);
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
    private void gesReclamation(ActionEvent event) {
         try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gesreclamation/views/HomeReclamation.fxml"));
                gesutil.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }

    @FXML
    private void gestclub(ActionEvent event) {
        try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("/gescluuub/view/Admingestionclub.fxml"));
                gesutil.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}
