/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import entities.Msg;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import services.MsgServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ResponseController implements Initializable {

    @FXML
    private JFXTextArea newcontent;
    @FXML
    private JFXButton ResponseId;
    @FXML
    private JFXTextArea BodyId;
    @FXML
    private JFXTextArea SubjectId;
     public static int id=0;
            MsgServices ms=new MsgServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Msg u=null;
        try {
            u=ms.getMsgById(id);
        } catch (SQLException ex) {
            Logger.getLogger(ResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SubjectId.setText(u.getSubject());
        BodyId.setText(u.getBody());
        
    }    

    @FXML
    private void Response(ActionEvent event) throws IOException, SQLException {
        
            Msg u=null;
        try {
            u=ms.getMsgById(id);
        } catch (SQLException ex) {
            Logger.getLogger(ResponseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Msg m=new Msg();
        m.setBody(newcontent.getText());
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

// Using DateFormat format method we can create a string 
// representation of a date with the defined format.
        String reportDate = df.format(new java.util.Date().getTime());
        m.setDateEnvoi(reportDate);
        m.setEmetteur_id(u.getRecepteur_id());
        m.setSubject(u.getSubject());
        m.setRecepteur_id(u.getEmetteur_id());
        m.setLu(0);
        m.setPiece("");
        ms.AjouterMsg(m);
         HomeController.messagerie=1;
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Home.fxml"));
        AnchorPane root = (AnchorPane) loader.load();
       
        newcontent.getScene().setRoot(root);
    }
    
    }
    

