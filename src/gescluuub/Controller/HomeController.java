/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import gescluuub.model.club;
import gescluuub.services.crudclub;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author benour
 */

public class HomeController implements Initializable {
    @FXML
    private AnchorPane rootp;
    @FXML
    private Label controlnom;
    @FXML
    private Label controldesc;
    @FXML
    private Label controlcap;
    @FXML
    private Label controlimg;
    @FXML
    private Label controltarif;
    @FXML
    private Label controlhoraire;
    @FXML
    private JFXButton retourBouton;
    
    
    @FXML
   public void statshow(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/gescluuub/view/Fstatclub.fxml"));
rootp.getChildren().setAll(pane);
}
    @FXML
   public void ajouterclub(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/gescluuub/view/clubD.fxml"));
rootp.getChildren().setAll(pane);
}
   public void modif(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/gescluuub/view/updateDel.fxml"));
rootp.getChildren().setAll(pane);
}

public void quitter(ActionEvent event){
System.exit(0);
}
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    private void back(MouseEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gescluuub/view/Admingestionclub.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         retourBouton.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gescluuub/view/Admingestionclub.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
    }
    
}
