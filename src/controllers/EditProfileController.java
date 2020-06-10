/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controllers.RegistrationController.validate;
import entities.Users;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import services.UserService;
import utils.BCrypt;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class EditProfileController implements Initializable {
    
          ObservableList<String> RoleList = FXCollections.observableArrayList("ROLE_USER", "ROLE_ADMIN");

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtPseudo;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtCfPassword;
    @FXML
    private Text Role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
              txtNom.setText(LoginController.userConnected.getNom());
       txtPrenom.setText(LoginController.userConnected.getPrenom());
       txtEmail.setText(LoginController.userConnected.getEmail());
      
       txtPseudo.setText(LoginController.userConnected.getUsername());
       Role.setText(LoginController.userConnected.getRoles());
    }    

    @FXML
    private void modifieruser(ActionEvent event) throws SQLException {
        
        
             if (validateInputs()) {
                 UserService sr = new UserService();

                Users user=new Users(txtPseudo.getText(), txtEmail.getText(), BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt()),  LoginController.userConnected.getRoles(), txtNom.getText(), txtPrenom.getText());
                sr.ModifierClient(user, LoginController.userConnected.getId());
        
       LoginController.userConnected=sr.getUserById(LoginController.userConnected.getId());
       
       System.out.println(LoginController.userConnected.getId());
       System.out.println(user);
                 
             }
    }
    
    
     
    private boolean validateInputs() {
        if ((txtNom.getText().isEmpty()) || (txtPrenom.getText().isEmpty())
                || (txtEmail.getText().isEmpty()) 
                 || (txtPseudo.getText().isEmpty()) || (txtPassword.getText().isEmpty())
                ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        } else if (!(txtCfPassword.getText().equals(txtPassword.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre mot de passe");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } else if (!(validate(txtEmail.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        } 
        
        else if (!BCrypt.checkpw(txtPassword.getText(), LoginController.userConnected.getPassword())) {
           Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Mot de passe incorrect");
            alert2.setHeaderText(null);
            alert2.show();
            return false; 
        }
        return true;
    }
    
    
}
