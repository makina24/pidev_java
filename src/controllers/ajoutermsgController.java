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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.MsgService;
import service.PlatService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class ajoutermsgController implements Initializable {

   
    @FXML
    private Button ajouter;
    @FXML
    private Button retour;
    
    private TableColumn<Msg_1, ?> proposition ;
    List<Msg_1> listMsg;
    ObservableList<Msg_1> listViewMsg;
    private TableView<Msg_1> tableaumsg;
    @FXML
    private TextArea txtmsg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
    private void ajoutermsg(ActionEvent event) {
        if ( validateInputs()){
            Msg_1 m= new Msg_1(txtmsg.getText(),"",LoginController.userConnected.getId());
            MsgService ms = new MsgService();
             
          
            try{
            ms.AjouterMsg(m);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.setContentText("votre proposition a été ajouté !");
            alert.show();
             String tit = "on va traiter votre demande ";
            String message = "votre proposition a été ajouté ";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(2));
          
          
            }catch (SQLException ex){
              Logger.getLogger(supmodifController.class.getName()).log(Level.SEVERE, null, ex);

            }
    }
      
    }
    

    @FXML
    private void retourmsg(ActionEvent event) {
        
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/cantinefront.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
        
    }
    private boolean validateInputs() {
        if ((txtmsg.getText().isEmpty())) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("Ecrivez votre proposition");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
        return true;

}
     private void initCol() {
        proposition.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
     }

    
        
}
