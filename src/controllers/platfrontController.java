/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import entities.Plat;
import entities.Ticket;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import service.PlatService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class platfrontController implements Initializable {

    @FXML
    private TableView<Plat> tableauplatp;
    @FXML
    private TableColumn<?, ?> nomplatp;
    @FXML
    private TableColumn<?, ?> descriptionp;
    @FXML
    private TableColumn<?, ?> datep;
    @FXML
    private Button boutonajouterticket;
    @FXML
    private Button boutonretour;
    ObservableList<Plat> listViewPlat;
                List<Plat> listPlat;
                
   Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public platfrontController() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
try{
    initCol();
     loadData();
} catch (Exception e){
}
    
    }    
   

    @FXML
    private void ajoutertickett(ActionEvent event) throws SQLException {
        
        
        if (tableauplatp.getSelectionModel().getSelectedItem()!= null){
             Plat pl = tableauplatp.getSelectionModel().getSelectedItem();
        
       String a = tableauplatp.getSelectionModel().getSelectedItem().getNomPlat();
       Date b = tableauplatp.getSelectionModel().getSelectedItem().getDate();
       Ticket t = new Ticket (a,b,LoginController.userConnected.getId());
       ajouterrTicket(t);
      
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.setContentText("Ticket Ajoutée avec succés");
            alert.show();
             String tit = "Ticket Ajouté avec succés";
            String message = "Bon Appetit , enjoy your meal !! ";
            NotificationType notification = NotificationType.SUCCESS;
    
            TrayNotification tray = new TrayNotification(tit, message, notification);          
            tray.setAnimationType(AnimationType.POPUP);
            tray.showAndDismiss(javafx.util.Duration.seconds(2));
            
        }
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/impticket.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }
          
     }
      
     public void ajouterTicket(Ticket t) throws SQLException {
    
                String query;
            query = "INSERT INTO  `ticket`( `nomPlat`, `photoPlat`, `id_user`,`id_plat`, `date` ) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, t.getNomPlat());
                    pst.setString(2, "");

                    pst.setInt(3, t.getIdUser());
                    Plat p = null ;
                    pst.setInt(4,t.getIdPlat() );
                   
                    pst.setDate(5,t.getDate ());


                    pst.executeUpdate();
    }   
     

   
        
     public void ajouterrTicket(Ticket t) throws SQLException {
    
      
                String query;
            query = "INSERT INTO  `ticket`( `nomPlat`, `photoPlat`, `id_user`,`id_plat`, `date` ) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, t.getNomPlat());
                    pst.setString(2, "");

                    pst.setInt(3, t.getIdUser());
                    Plat p = null ;
                    pst.setInt(4,t.getIdPlat() );
                   
                    pst.setDate(5,t.getDate ());


                    pst.executeUpdate();
    }   
      
       
       

        
    
   private void initCol() {
        nomplatp.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
        descriptionp.setCellValueFactory(new PropertyValueFactory<>("description"));
        datep.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        

    } 
     private void loadData() {

       
        try {
            PlatService  ps = new PlatService();
            listPlat = new ArrayList<>();
            listPlat = ps.getAllPlats();
            System.err.println(listPlat);
            listViewPlat = FXCollections.observableArrayList(listPlat);
            tableauplatp.setItems(listViewPlat);
            initCol();
        } catch (SQLException e) {
        }

    }
  
    @FXML
    private void retourt(ActionEvent event) {
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

    private void buttonprop(ActionEvent event) {
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

}
