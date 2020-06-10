/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class gestionticketControllers implements Initializable {

    @FXML
    private TextField txtnomplat;
    @FXML
    private DatePicker txtdate;
    @FXML
    private TableView<?> tableaut;
    @FXML
    private TableColumn<?, ?> idt;
    @FXML
    private TableColumn<?, ?> nomplatt;
    @FXML
    private TableColumn<?, ?> datet;
    @FXML
    private Button boutonajouterticket;
    @FXML
    private Button boutonsupprimer;
    @FXML
    private Button boutonactualiser;
    @FXML
    private Button boutonretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutt(ActionEvent event) {
        Date date = null;
            

            Ticket t= new Ticket("",txtnomplat.getText(), date);
            TicketService ps = new TicketService();
             
          
            try{
            ps.ajouterTicket(t);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Operation effectuée avec succés !");
            alert.show();
          
          
            }catch(SQLException ex){
                             Logger.getLogger(supmodifController.class.getName()).log(Level.SEVERE, null, ex);

            
            }
    }

    @FXML
    private void supprimert(ActionEvent event) {
        /*
        Ticket t = (Ticket) tableaut.getSelectionModel().getSelectedItem();
     System.out.println(t);
     TicketService ps = new TicketService();
     tableaut.getItems().removeAll(tableaut.getSelectionModel().getSelectedItem());
     try{
            ps.SupprimeTicket(int id)
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.setContentText("Plat supprimé");
            alert.show();
          
          
            }catch (SQLException ex){
                             Logger.getLogger(GestionplatController.class.getName()).log(Level.SEVERE, null, ex);
       
        
    }
*/
    }

    @FXML
    private void actualisert(ActionEvent event) {
    }

    @FXML
    private void retourt(ActionEvent event) {
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
    

