/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import com.jfoenix.controls.JFXTextField;
import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.PlatService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class supmodifController implements Initializable {
    public ObservableList<Plat> data = FXCollections.observableArrayList();
    List<Plat> listPlat;
    ObservableList<Plat> listViewPlat;
    private String path;
    

    @FXML
    private TableView<Plat> tableauPlat;
    @FXML
    private TableColumn<?, ?> nomP;
    @FXML
    private TableColumn<?, ?> descriptionP;
    @FXML
    private TableColumn<?, ?> dateP;
    @FXML
    private TableColumn<?, ?> photoplatp;
    @FXML
    private Button modifierBoutton;
    @FXML
    private Button supprimerBoutton;
    @FXML
    private Button boutonretour;
    @FXML
    private Button boutonactualiser;
    @FXML
    private ImageView imgnom131;
    @FXML
    private ImageView imgnom132;
    @FXML
    private ImageView imgnom133;
    @FXML
    private ImageView imgnom134;
    @FXML
    private ImageView imgnom135;
    @FXML
    private Button ajoutplat;
    private Object pst;
    PlatService ps = new PlatService();
    @FXML
    private Button listetickett;
    @FXML
    private Button propo;
    @FXML
    private Button acceuill;
    @FXML
    private JFXTextField searchField;
   ObservableList<Plat> listViewPlats ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            initCol();
            loadData();
        } catch (Exception e) {
        }
         
         
                 FilteredList<Plat> filteredData = new FilteredList<>(listViewPlat, e -> true);

       
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(a -> {
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();

                if (a.getNomPlat().toLowerCase().contains(lowerCaseFilter)) {
                    return true; 
                } else  if (a.getPhotoPlat().toLowerCase().contains(lowerCaseFilter)) {
                    return true ; 
                }else if  (a.getDescription().toLowerCase().contains(lowerCaseFilter)){
                    return true ;
                }else if (a.getDate().toString().contains(lowerCaseFilter)){
                    return true ;
              
                }else
                return false; 
            });
        });

         
        SortedList<Plat> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableauPlat.comparatorProperty());
        tableauPlat.setItems(sortedData);
  
         
    }    

    @FXML
    private void modifierP(ActionEvent event) {
    }

    @FXML
    private void supprimerP(ActionEvent event) throws SQLException {
        if (tableauPlat.getSelectionModel().getSelectedItem()==null){
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Vous devez selectionner le plat à supprimer");
            alert1.setHeaderText(null);
            alert1.show();
        } else { ButtonType ok = new ButtonType("oui",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("non", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,"Voulez vous vraiment supprimer ce plat? ",ok,cancel);

        alert.setTitle("Suppresion");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get()== ok) {  
        
         PlatService ps = new PlatService();
            ObservableList<Plat> EventSelected,allEvents;
               allEvents = tableauPlat.getItems();

            EventSelected = tableauPlat.getSelectionModel().getSelectedItems();
                       ps.SupprimePlat(EventSelected.get(0).getId());
                        EventSelected.forEach(listViewPlat::removeAll);
        }
        }
    }
    


    @FXML
    private void retourP(ActionEvent event) {
    }

    @FXML
    private void actualiserp(ActionEvent event) {
    }

    @FXML
    private void imagenom(MouseEvent event) {
    }

    @FXML
    private void ajouterplatbtn(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/ajoutplat.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    private void initCol() {
        nomP.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateP.setCellValueFactory(new PropertyValueFactory<>("date"));
        photoplatp.setCellValueFactory(new PropertyValueFactory<>("PhotoPlat"));
        
        
     }
    private void loadData() {

       
        try {
            PlatService  ps = new PlatService();
            listPlat = new ArrayList<>();
            listPlat = ps.getAllPlats();
            System.err.println(listPlat);
            listViewPlat = FXCollections.observableArrayList(listPlat);
            tableauPlat.setItems(listViewPlat);
            initCol();
        } catch (SQLException e) {
        }

    }

    @FXML
    private void listeticketbouton(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/gestionticket.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void propobtn(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/affichemsg.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void acceuillbtn(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/cantineback.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}
