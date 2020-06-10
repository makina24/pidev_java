/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import entities.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class RegistrationController implements Initializable {

        ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_USER", "ROLE_ADMIN");

    @FXML
    private JFXTextField txtusername;
    @FXML
    private JFXTextField txtnom;
    @FXML
    private JFXTextField txtprenom;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXButton sinscrireboutton;
    @FXML
    private ComboBox<String> rolecombo;
    @FXML
    private JFXPasswordField txtpassword;
    @FXML
    private JFXPasswordField cfpassword;
    @FXML
    private JFXButton loginBoutton;
   

    /**
     * Initializes the controller class.txtpassword
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {





       
          rolecombo.setItems(RoleList);
           
                             
}
    
    @FXML
    public void login(ActionEvent ae2) throws IOException{
             
        sinscrireboutton.getScene().getWindow().hide();
        
        Stage login = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }
        
       
    
    private boolean validateInputs() {
        if ((txtusername.getText().isEmpty()) || (txtnom.getText().isEmpty())
                || (txtprenom.getText().isEmpty()) || (txtemail.getText().isEmpty())
                || (txtpassword.getText().isEmpty())
                 ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if (!(cfpassword.getText().equals(txtpassword.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(txtemail.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } 
        return true;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
    
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
    
    @FXML
    
    private void signUp (ActionEvent event) throws SQLException, IOException, InterruptedException {
        
   
        
    
        UserService sr = new UserService();
        //String image = "";
     

        //if (radioHomme.isSelected()) {
           // valueRadio = "Homme";
       // } else if (radioFemme.isSelected()) {
         //   valueRadio = "Femme";
        //}

        if (validateInputs()) {
            
            

            

           
            Users u= new Users(txtusername.getText(), txtemail.getText(), txtpassword.getText(), rolecombo.getValue().toString(), txtnom.getText(), txtprenom.getText());
            UserService us = new UserService();
               txtusername.setText("");
                      
            String tit = "Inscription réussite";
            String message = "Bienvenue a Elite Jardin d'enfant !!";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(2));
          
            try{
            sr.ajouterUser(u);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
          
          
            }catch (SQLException ex){
                             Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);

            }
       


          
          
                  
                   
          
                
                
           
           
             }

        }

    }


