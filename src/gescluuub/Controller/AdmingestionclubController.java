/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class AdmingestionclubController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane rootpp;
    @FXML
   public void gestionclub(ActionEvent event) throws IOException{
FXMLLoader loader=new FXMLLoader(getClass().getResource("/gescluuub/view/Home.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gobacka(ActionEvent event)  throws IOException{
FXMLLoader loader=new FXMLLoader(getClass().getResource("/gesenfant/Views/back.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
}

    @FXML
    private void demandes(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/gescluuub/view/HomeDemande.fxml"));    
              Parent root = (Parent) loader.load();
              Scene scene = new Scene(root);
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    
                        
                        stage.setScene(scene);
        stage.show();
    }
    
}
