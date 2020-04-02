/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import services.UserService;
import entities.User;
import java.io.IOException;
import javafx.scene.control.Alert.AlertType;


import utils.BCrypt;
import utils.Routing;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


/**
 * FXML Controller class
 *
 * @author abder
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private Label txtetat;
    @FXML
    private JFXButton connectbutton;
    @FXML
    private VBox VBoxInfoPersonel;
    @FXML
    private VBox VBoxMdp;
    @FXML
    private JFXButton back;
    @FXML
    private JFXTextField txtEmailRecover;
    @FXML
    private JFXButton btnsendrecover;
    @FXML
    private JFXButton facebookButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        txtEmailRecover.setVisible(false);
        btnsendrecover.setVisible(false);
    }

    @FXML
    public void Back(ActionEvent event) throws IOException {
        //Stage stage = new Stage();

        Parent root =  FXMLLoader(getClass().getResource("/gui/Login.fxml"));
         
        connectbutton.getScene().setRoot(root);

    }

    @FXML
    public void SeConnecter() throws IOException, SQLException {
        if (validateInputs()) {
            UserService us = new UserService();
            String pseudo = txtUsername.getText();
            String password = txtPassword.getText();

            User u = us.searchByPseudoPass(pseudo, password);
            System.out.println(u);
            if (u != null && u.getEnabled() == 1 && BCrypt.checkpw(txtPassword.getText(), u.getPassword())) {
                User user = u;
                int user_id = u.getId();
                /*System.out.println("sooooo nice");*/
                txtetat.setText("");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Succès");
                alert.setHeaderText("Authentifié");
                alert.setContentText("Vous êtes connecté en tant que :" + u.getUsername());

                alert.showAndWait();
                
                
                AnchorPane root = getRole(u);
                

                txtPassword.getScene().setRoot(root);

            } else if (u != null && u.getEnabled() == 0) {
                txtetat.setText("Votre compte est desactivé.");

            } else {
                txtetat.setText("Identifiants incorrects.");
            }

        }
    }
    
    //YASSINE REDIRECT TO
    public AnchorPane getRole(User user) throws IOException{
        AnchorPane root = null;
        //ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_Client", "ROLE_ADMIN");
        if (user.getRoles().contains("ROLE_ADMIN")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
            root = (AnchorPane) loader.load();
        }else if(user.getRoles().contains("ROLE_Client")){
            
            
        }

        return root;
    }
    
    
         
    @FXML
    public void Inscription(ActionEvent event) throws IOException {

             
                      
                FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
                AnchorPane root = (AnchorPane) loader.load();

                btnsendrecover.getScene().setRoot(root);
   
    }

    private boolean validateInputs() throws SQLException {

        if (txtUsername.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre nom d'utilisateur");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        if (txtPassword.getText().isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez saisir votre mot de passe");
            alert1.setHeaderText("Controle de saisie");
            alert1.show();
            return false;
        }
        return true;
    }

    private Parent FXMLLoader(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
}