/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXListView;
import entities.Msg;
import entities.Users;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.text.Font;
import services.MsgServices;
import services.UserService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class SendedMsgController implements Initializable {

    @FXML
    private JFXListView<Msg> listmsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
          affiche();
        
    }    
    
   
public void affiche() {
    Users user = new Users() ;
        MsgServices ms = new MsgServices();
        List<Msg> lm = new ArrayList<Msg>();
        try {
            lm = ms.GetAllMsgByEmeteur(LoginController.userConnected.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Msg> items = FXCollections.observableArrayList(lm);

        listmsg.setCellFactory((ListView<Msg> arg0) -> {
            ListCell<Msg> cell = new ListCell<Msg>() {
                @Override
                protected void updateItem(Msg e, boolean btl) {
                    super.updateItem(e, btl);
                    if (e != null) {
                        UserService us = new UserService();
                        Users u = new Users();
                        try {
                            u = us.getUserById(e.getEmetteur_id());
                        } catch (SQLException ex) {
                            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        setText(u.getEmail() + "     Object : " + e.getSubject() + "     " + e.getDateEnvoi());
                       
                        setFont(Font.font("Berlin Sans FB Demi Bold", 16));
                               
          
               
                        // setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        listmsg.setItems(items);
    }    
    
}