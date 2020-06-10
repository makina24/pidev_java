/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import entities.Chauffeur;
import entities.LigneTransport;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ChauffeurService;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class GestionChauffeursController implements Initializable {
   

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private  JFXTextField txtNumTel ;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtSalaire;
    @FXML
    private JFXDatePicker txtHireDate;
    @FXML
    private JFXComboBox< String> ligneCombo;
    @FXML
    private JFXButton ajouterCBoutton;
    @FXML
    private TableView<Chauffeur> tableChauffeur;
      List<Chauffeur> listChauffeur;
    ObservableList<Chauffeur> listViewChauffeur;
    @FXML
    private TableColumn<?, ?> nomColumn;
    @FXML
    private TableColumn<?, ?> PrenomColumn;
    @FXML
    private TableColumn<?, ?> numTelColumn;
    @FXML
    private TableColumn<?, ?> emailColumn;
    @FXML
    private TableColumn<?, ?> salaireColumn;
    @FXML
    private TableColumn<?, ?> hireDateColumn;
    @FXML
    private TableColumn<?, ?> ligneColumn;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXButton resetBoutton;
    @FXML
    private JFXButton saveBoutton;
    @FXML
    private JFXButton supprimerBoutton;
    @FXML
    private JFXButton modifierBoutton;
    @FXML
    private JFXButton actualiserBoutton;
    @FXML
    private JFXButton backBoutton;
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ChauffeurService us = new ChauffeurService();
        
 Connection con = Connexion.getInstance().getConnection();
 try{
            String sql="select station from ligne_transport";
            Statement ste = con.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            ligneCombo.getItems().addAll(rs.getString("station")); 
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          try {
            initCol();
            loadData();
        } catch (Exception e) {
        }
   
    }  
    
    
    
 private void initCol() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numTelColumn.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        salaireColumn.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
       ligneColumn.setCellValueFactory(new PropertyValueFactory<>("lignec_id"));

        

    }  
 
 
  private void loadData() {

     
        try {
            ChauffeurService sr = new ChauffeurService();
            listChauffeur = new ArrayList<>();
            listChauffeur = sr.getAllChauffeurs();
            System.err.println(listChauffeur);
            listViewChauffeur = FXCollections.observableArrayList(listChauffeur);
            tableChauffeur.setItems(listViewChauffeur);
            initCol();
        } catch (Exception e) {
        }

    }
  
    private boolean validateInputs() {
        if ((txtNom.getText().isEmpty()) || (txtPrenom.getText().isEmpty())
                || (txtEmail.getText().isEmpty()) || (txtNumTel.getText().isEmpty())
                || (txtSalaire.getText().isEmpty())
                 ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Veillez remplir tout les champs");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        
        } else if (!(validate(txtEmail.getText()))) {
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
    private void ajouterChauffeur(ActionEvent event) {
        
         
        ChauffeurService sr = new ChauffeurService();
        //String image = "";
     

        //if (radioHomme.isSelected()) {
           // valueRadio = "Homme";
       // } else if (radioFemme.isSelected()) {
         //   valueRadio = "Femme";
        //}

        if (validateInputs()) {
            

           
            Chauffeur ch;
            String a = txtNom.getText();
            String b =txtPrenom.getText();
             String c =(txtNumTel.getText());
             String d =txtNumTel.getText();
             String e = txtSalaire.getText();
             String f = txtHireDate.getValue().toString();
              String j = ligneCombo.getValue();
             

            
            ch = new Chauffeur(a,b,c,d,e,f,j);
            ChauffeurService us = new ChauffeurService();
          
            try{
            sr.ajouterChauffeur(ch);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
          
          
            }catch (SQLException ex){
                             Logger.getLogger(RegistrationController.class.getName()).log(Level.SEVERE, null, ex);

            }
       

    }

    }

    @FXML
    private void reset(ActionEvent event) {
          txtNom.setText("");
             txtPrenom.setText("");
                txtEmail.setText("");
                   txtNumTel.setText("");
                      txtHireDate.setValue(LocalDate.MAX);
          
    }

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void supprimerChauffeur(ActionEvent event) throws SQLException {
               
if (tableChauffeur.getSelectionModel().getSelectedItem()==null){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Vous devez selectionner l'utilisateur à supprimer");
            alert1.setHeaderText(null);
            alert1.show();
        } else {
        
          ButtonType ok = new ButtonType("oui",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("non", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulez vous vraiment supprimer cet user? ",ok,cancel);

        alert.setTitle("Suppresion");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get()== ok) {  
        ChauffeurService us = new ChauffeurService();
            ObservableList<Chauffeur> EventSelected,allEvents;
               allEvents = tableChauffeur.getItems();

            EventSelected = tableChauffeur.getSelectionModel().getSelectedItems();
            
             us.SupprimerChauffeur(EventSelected.get(0).getNom());
             EventSelected.forEach(listViewChauffeur::removeAll);

                }

        }
    }

    @FXML
    private void modifierChauffeur(ActionEvent event) {
    }

    @FXML
    private void actualiser(ActionEvent event) {
        
        this.listViewChauffeur.clear();
        loadData();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
        
        
              backBoutton.getScene().getWindow().hide();
        Stage rb = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/GestionTransport.fxml"));
        Scene scene = new Scene(root);
        rb.setScene(scene);
        rb.show();
        rb.setResizable(false);
    }
    
}
