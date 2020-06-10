/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static controllers.LoginController.userConnected;
import entities.Msg;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.MsgServices;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class NewMsgController implements Initializable {

    @FXML
    private Label Objet;
    @FXML
    private JFXTextField Destinataire;
    @FXML
    private JFXTextArea Contenu;
    @FXML
    private JFXButton AddButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add(ActionEvent event) throws SQLException, IOException {
          if (validateInputs()) {
            UserService us = new UserService();
            MsgServices ms = new MsgServices();
            if (us.getUserByEmail(Destinataire.getText().toString()) == null) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Information Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText("Destinataire introuvable");
                            alert.showAndWait();
                            
            } else {
                Msg m = new Msg();
                m.setBody(Contenu.getText());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Using DateFormat format method we can create a string 
// representation of a date with the defined format.
                String reportDate = df.format(new java.util.Date().getTime());
                m.setDateEnvoi(reportDate);
                m.setEmetteur_id(LoginController.userConnected.getId());
                m.setSubject(Objet.getText());
                m.setRecepteur_id(us.getUserByEmail(Destinataire.getText().toString()).getId());
                m.setLu(0);
                m.setPiece("");
                ms.AjouterMsg(m);
                HomeController.messagerie = 1;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
                AnchorPane root = (AnchorPane) loader.load();

                Contenu.getScene().setRoot(root);
            }

        }
    }
     public static final Pattern VALID_EMAIL_ADDRESS_REGEX
            = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    private boolean validateInputs() {
        if (Destinataire.getText().length() == 0 || Objet.getText().length() == 0
                || Contenu.getText().length() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("tous les champs doivent etre remplis");
            alert.showAndWait();
            return false;
        } else if (!(validate(Destinataire.getText()))) {
            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Erreur");
            alert2.setContentText("Veillez vérifier votre email");
            alert2.setHeaderText(null);
            alert2.show();
            return false;
        }

        return true;
    }
    
}
