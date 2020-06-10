/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Msg_1;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.MsgService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class affichemsgController implements Initializable {

    @FXML
    private TableView<Msg_1> tableaumsg;
    @FXML
    private TableColumn<Msg_1, ?> proposition;
    List<Msg_1> listMsg;
    ObservableList<Msg_1> listViewMsg;
    public ObservableList<Msg_1> data = FXCollections.observableArrayList();
    @FXML
    private Button acceuilbtn;
    @FXML
    private Button mdfsuppbtn;
    @FXML
    private Button listetickets;
    @FXML
    private Button ajoutplat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initCol();
            loadData();
        } catch (Exception e) {
        
        
        }
        // TODO
    }  
    private void loadData() {

       
        try {
            MsgService  ps = new MsgService();
            listMsg = new ArrayList<>();
            listMsg = ps.getAllMsg();
            System.err.println(listMsg);
            listViewMsg = FXCollections.observableArrayList(listMsg);
            tableaumsg.setItems(listViewMsg);
            initCol();
        } catch (SQLException e) {
        }
    }

   

    @FXML
    private void supprimermsg(ActionEvent event) {
    }

    private void initCol() {
        proposition.setCellValueFactory(new PropertyValueFactory<>("contenu"));
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
    private void acceuilbtn(ActionEvent event) {
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

    @FXML
    private void mdfsuppbtn(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/gestionplat.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void listetickets(ActionEvent event) {
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
    private void ajouterplatbtn(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/ajoutmsg.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void retourmsg(ActionEvent event) {
    }

    
    
}
