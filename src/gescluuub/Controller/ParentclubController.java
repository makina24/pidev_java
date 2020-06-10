/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import gescluuub.model.club;
import gescluuub.services.crudclub;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class ParentclubController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableView<club> club;
   // @FXML private TableColumn<club, Integer> id;
    @FXML private TableColumn<club, String> nomclub;
    @FXML private TableColumn<club, String> description;
    @FXML private TableColumn<club, String> horraire;
    @FXML private TableColumn<club, Integer> tarif;
    @FXML private TableColumn<club, String> image;
    @FXML private TableColumn<club, Integer> capacite;
     public ObservableList<club> data = FXCollections.observableArrayList();
    @FXML
   public void envdemande() {
       try{
           Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gescluuub/view/ajouterDemande.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Envoyer une Demande pour rejoindre un Club");
         stage.show();
        } catch(Exception e){
        e.printStackTrace();
        }
       
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        crudclub cs = new crudclub();
        data = cs.afficherclub();
   //id.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
   nomclub.setCellValueFactory(new PropertyValueFactory<club, String>("nomclub"));
   description.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
   horraire.setCellValueFactory(new PropertyValueFactory<club, String>("horraire"));
   tarif.setCellValueFactory(new PropertyValueFactory<club, Integer>("tarif"));
   image.setCellValueFactory(new PropertyValueFactory<club, String>("image"));
   capacite.setCellValueFactory(new PropertyValueFactory<club, Integer>("capacite"));
   club.setItems(data);
    
    }    

    @FXML
    private void backb(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gesenfant/Views/front.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
    }
    
}
