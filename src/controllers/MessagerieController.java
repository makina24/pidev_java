/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entities.Msg;
import entities.Users;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import services.MsgServices;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class MessagerieController implements Initializable {

    @FXML
    private JFXButton NewButton;
    @FXML
    private JFXButton sendButton;
    @FXML
    private JFXButton ReceiveButton;
    @FXML
    private Pane content2;
    @FXML
    private JFXListView<Msg> listmsg;
    MsgServices ms = new MsgServices();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affiche();
        
       try {
           
            ReceiveButton.setText("Boite de reception ("+ms.GetCountReceived(LoginController.userConnected.getId())+")");
            sendButton.setText("Message envoyés ("+ms.GetCountSended(LoginController.userConnected.getId()));
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void affiche() {
        List<Msg> lm = new ArrayList<Msg>();
        
          try {
            lm = ms.GetAllMsgByRecepteur(LoginController.userConnected.getId());
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
                        if(e.getLu()==0)
                {
                        setFont(Font.font("Berlin Sans FB Demi Bold", FontWeight.BOLD, 12));
                }
                        else
                {
                        setFont(Font.font("Berlin Sans FB Demi Bold", 12));

                }
                        // setAlignment(Pos.CENTER);
                    }
                }
            };
            return cell;
        });
        listmsg.setItems(items);
        content2.getChildren().setAll(listmsg);
    }

    @FXML
    private void New(ActionEvent event) throws IOException {
        
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/NewMsg.fxml"));

        content2.getChildren().setAll(pane);
    }

    @FXML
    private void sended(ActionEvent event) throws IOException {
        
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/SendedMsg.fxml"));

        content2.getChildren().setAll(pane);
    }

    @FXML
    private void Receive(ActionEvent event) {
        
         affiche();
    }
    
    
   @FXML
    private void test(MouseEvent event) throws IOException {
         MsgServices ms=new MsgServices();
        try {
            
            ms.ModifierMsg(listmsg.getSelectionModel().getSelectedItem().getId(), 1);
        } catch (SQLException ex) {
            Logger.getLogger(ResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResponseController.id = listmsg.getSelectionModel().getSelectedItem().getId();
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/Response.fxml"));
        content2.getChildren().setAll(pane);
    }
    
    
    
}
