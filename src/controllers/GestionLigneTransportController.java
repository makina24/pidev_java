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
import entities.LigneTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ChauffeurService;
import services.LigneTransportService;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class GestionLigneTransportController implements Initializable {

    @FXML
    private JFXTextField txtStation;
    @FXML
    private JFXTextField txtVehicule;
    @FXML
    private JFXTextField txtTarif;
    @FXML
    private JFXTextField txtCapacite;
    @FXML
    private TableColumn<?, ?> stationColumn;
    @FXML
    private TableColumn<?, ?> vehiculeColumn;
    @FXML
    private TableColumn<?, ?> chauffeurColumn;
    @FXML
    private TableColumn<?, ?> heureDepartColumn;
    @FXML
    private TableColumn<?, ?> tarifColumn;
    @FXML
    private TableColumn<?, ?> capaciteColumn;
    @FXML
    private TableColumn<?, ?> nbPlacesColumn;
    @FXML
    private JFXDatePicker dateDepart;
    @FXML
    private JFXButton ajouterBoutton;
    @FXML
    private JFXButton resetBoutton;
    @FXML
    private JFXButton modifierBoutton;
    @FXML
    private JFXButton supprimerBoutton;
    @FXML
    private JFXButton actualiserBoutton;
    @FXML
    private JFXComboBox<String> comboChauffeur;
      List< LigneTransport> listLigne;
    ObservableList<LigneTransport> listViewLigne;
    @FXML
    private TableView<LigneTransport> tableLigne;
    @FXML
    private JFXButton backBoutton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               
 Connection con = Connexion.getInstance().getConnection();
 try{
            String sql="select nom from Chauffeur";
            Statement ste = con.createStatement();
            ResultSet rs=ste.executeQuery(sql);
            while(rs.next())
        {
            comboChauffeur.getItems().addAll(rs.getString("nom")); 
            
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
        stationColumn.setCellValueFactory(new PropertyValueFactory<>("station"));
        vehiculeColumn.setCellValueFactory(new PropertyValueFactory<>("vehicule"));
        chauffeurColumn.setCellValueFactory(new PropertyValueFactory<>("chauffeur_id"));
        heureDepartColumn.setCellValueFactory(new PropertyValueFactory<>("HeureDepart"));
        tarifColumn.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        capaciteColumn.setCellValueFactory(new PropertyValueFactory<>("capacite"));
       nbPlacesColumn.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));

        

    }  
 
 
  private void loadData() {

     
        try {
            LigneTransportService sr = new LigneTransportService();
            listLigne = new ArrayList<>();
            listLigne = sr.getAllLignes();
            System.err.println(listLigne);
            listViewLigne = FXCollections.observableArrayList(listLigne);
            tableLigne.setItems(listViewLigne);
            initCol();
        } catch (Exception e) {
        }

    }

    @FXML
    private void ajouterLigne(ActionEvent event) {
    }

    @FXML
    private void reset(ActionEvent event) {
    }

    @FXML
    private void modifierLigne(ActionEvent event) {
    }

    @FXML
    private void supprimerLigne(ActionEvent event) {
    }

    @FXML
    private void actualiser(ActionEvent event) {
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
