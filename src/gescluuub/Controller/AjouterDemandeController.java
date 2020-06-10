/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

import controllers.LoginController;
import entities.Users;
import utils.Connexion;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import gescluuub.model.demandeadhesion;
import gescluuub.services.cruddemande;
import gescluuub.model.club;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class AjouterDemandeController implements Initializable {

    
    @FXML private ComboBox<String> dureetxt;
    private ObservableList<String> list = FXCollections.observableArrayList("semestre","trimestre","année");
    @FXML
    private TextField modetxt;
    @FXML
    private TextField numtxt;
    @FXML
    private TextField ancientxt;
    @FXML
    private TextField objectiftxt;
    @FXML
    private ComboBox<?> clubcombo;
    @FXML
    private Button envoyer;
    
    final ObservableList options = FXCollections.observableArrayList();
  

    @FXML
    private TextField emailtxt;
    @FXML
    private Label controldur;
    @FXML
    private Label controlmode;
    @FXML
    private Label controlnum;
    @FXML
    private Label controlanc;
    @FXML
    private Label controlobj;
    @FXML
    private Label controlclub;
    @FXML
    private Label controlemail;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String[] possibleWords ={"cash","carte-Edinar","carte-bancaire"};
        TextFields.bindAutoCompletion(modetxt, possibleWords);
        dureetxt.setItems(list);
                clubcombo.setItems(options);
                fillComboBoxClub();
     
       


    }    

    @FXML
    private void envoyerD(ActionEvent event) throws SQLException {
        if (testSaisie() && !dureetxt.getSelectionModel().isEmpty() && !modetxt.getText().equals("") && !numtxt.getText().equals("") && !ancientxt.getText().equals("") && !objectiftxt.getText().equals("") && !clubcombo.getSelectionModel().isEmpty() )
        {  cruddemande c= new cruddemande();
        Users u =   LoginController.userConnected ;
        demandeadhesion a = new demandeadhesion(dureetxt.getValue(), modetxt.getText(), Integer.parseInt(numtxt.getText()), ancientxt.getText(), objectiftxt.getText(), clubcombo.getValue().toString(),u.getEmail().toString());
        c.savedemande(a);
         Image img = new Image("/gescluuub/images/valide1.png");
     
        System.out.println("demande ajouté avec succés");
        Notifications n = Notifications.create()
                    .title("Succes")
                    .text("Demande envoyée avec succes")
                    .graphic(new ImageView(img))
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
        n.darkStyle();
            n.show();
        System.out.println("demande ajouté avec succés");}
    }
    
         public void fillComboBoxClub() 
    {
        String requete ="select nomclub from club ";
           Connection connexion=Connexion.getInstance().getConnection();
        PreparedStatement pstm;
        try {
            pstm = connexion.prepareStatement(requete);
             ResultSet rs = pstm.executeQuery();
        while(rs.next())
        {
            
            options.add(rs.getString("nomclub"));
        }
        pstm.close();
        rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterDemandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        

    
    }

    @FXML
    private boolean testmode( ) {
           int nbNonChar = 0;
      

        if (nbNonChar == 0 && modetxt.getText().trim().length() >= 1) {
            
            controlmode.setVisible(false);
            modetxt.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            return true;
        } else {
            controlmode.setText("veuillez remplir ce champs");
            controlmode.setVisible(true);
            modetxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    }

    @FXML
    private boolean testnum( ) {
          int nbNonChar = 0;
        for (int i = 0; i < numtxt.getText().trim().length(); i++) {
            char ch = numtxt.getText().charAt(i);
            
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        
        
       if (nbNonChar == 0 && numtxt.getText().trim().length() >= 1) {
            
            controlnum.setVisible(false);
            numtxt.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");

            
            return true;
        }
      
        
        else {
           if (nbNonChar != 0){
           
            controlnum.setText("Veuillez taper des numéros uniquement");
            controlnum.setVisible(true);
            numtxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
            
            return false;
           }
           else if (numtxt.getText().trim().length() >= 0)
           {
              controlnum.setText("Veuillez remplir ce champs");
              controlnum.setVisible(true);
            numtxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
           
                       return false;
}


        }
       return false;
    }

    @FXML
    private boolean testancien( ) {
          int nbNonChar = 0;
      

        if (nbNonChar == 0 && ancientxt.getText().trim().length() >= 1) {
            //img.setImage(new Image("Images/tick.png"));
            controlanc.setVisible(false);
            ancientxt.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            //img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            controlanc.setText("veuillez remplir ce champs");
            //img.setVisible(false);
            controlanc.setVisible(true);
            ancientxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    }

    @FXML
    private boolean testobj( ) {
              int nbNonChar = 0;
      

        if (nbNonChar == 0 && objectiftxt.getText().trim().length() >= 1) {
            //img.setImage(new Image("Images/tick.png"));
            controlobj.setVisible(false);
            objectiftxt.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            //img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            controlobj.setText("veuillez remplir ce champs");
            //img.setVisible(false);
            controlobj.setVisible(true);
            objectiftxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    }

    @FXML
    private boolean testclub( ) {
            if (!clubcombo.getSelectionModel().isEmpty()){
            
          clubcombo.setStyle("-fx-focus-color: green ;-fx-text-box-border: green ;");

            controlclub.setVisible(false);
            
            return true;
        }
        else {
            controlclub.setText("Veuillez selectionner le club choisi");
                    controlclub.setVisible(true);
         clubcombo.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
        }
        
    }

    
    

    @FXML
    private boolean testduree( ) {
          if (!dureetxt.getSelectionModel().isEmpty()){
            
          dureetxt.setStyle("-fx-focus-color: green ;-fx-text-box-border: green ;");

            controldur.setVisible(false);
            
            return true;
        }
        else {
            controldur.setText("Veuillez selectionner le club choisi");
                    controldur.setVisible(true);
         dureetxt.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
        }
    }
         private Boolean testSaisie() {
       String erreur = "";
        
        
        if (!testancien()) {
            erreur = erreur + ("Veuillez verifier le champs du ancien club \n");
        }
         if (!testclub()) {
            erreur = erreur + ("Veuillez verifier le champs du club \n");
        }
          if (!testduree()) {
            erreur = erreur + ("Veuillez verifier le champs de la duree \n");
        }
         
            if (!testmode()) {
            erreur = erreur + ("Veuillez verifier le champs de la mode de paiement \n");
        }
               if (!testnum()) {
            erreur = erreur + ("Veuillez verifier le champs de numero parent \n");
        }
                     if (!testobj()) {
            erreur = erreur + ("Veuillez verifier le champs de l'objectif \n");
        }
       
        
        

        if (!testancien()|| !testclub()|| !testduree()|| !testmode()|| !testnum() || !testobj()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("Warning");
        alert.setContentText(erreur);
        alert.showAndWait();
       
        }
        return testancien();
    
    
}

    
}
