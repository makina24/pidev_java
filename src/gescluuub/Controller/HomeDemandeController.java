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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class HomeDemandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootp;
    @FXML
    private JFXButton retourBouton;
    
    
    @FXML
   public void statshow(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/gescluuub/view/Fstatclub.fxml"));
rootp.getChildren().setAll(pane);
}
   @FXML
   public void consulter(ActionEvent event) throws IOException{
AnchorPane pane = FXMLLoader.load(getClass().getResource("/gescluuub/view/Demandelist.fxml"));
rootp.getChildren().setAll(pane);
}
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
