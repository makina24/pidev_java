/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import entities.Plat;
import entities.Ticket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import service.PlatService;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class listeTicketbackController implements Initializable {
    private AnchorPane id_affichage_Forum;

    @FXML
    private Button boutonsupprimer;
    @FXML
    private TableColumn<?, ?> nomt;
    @FXML
    private TableColumn<?, ?> datet;
    @FXML
    private TableColumn<?, ?> idt;
    List<Ticket> listTicket;
    ObservableList<Ticket> listViewTicket;
    @FXML
    private TableView<Ticket> tableaut;
    @FXML
    private Button boutonpdf;
    @FXML
    private Button acceuilbtn;
    @FXML
    private Button ajoutplat;
    @FXML
    private Button mdfsuppbtn;
    @FXML
    private Button propo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TicketService st = new TicketService();

         try {
            initialiser();
            loadData();
        } catch (Exception e) {
        }    
    }    

    
    private void initialiser() {
        nomt.setCellValueFactory(new PropertyValueFactory<>("nomPlat"));
        datet.setCellValueFactory(new PropertyValueFactory<>("date"));
        idt.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        

    }  
     
     private void loadData() throws SQLException {

      
        try {
            TicketService  ps = new TicketService();
            listTicket = new ArrayList<>();
            listTicket = ps.getAllTickets();
            System.err.println(listTicket);
            listViewTicket = FXCollections.observableArrayList(listTicket);
            tableaut.setItems(listViewTicket);
            initialiser();
        } catch (SQLException e) {
        }
     
   
     }
    
     
    
    @FXML
    private void spprimert(ActionEvent event) throws SQLException {
         
         TicketService ps = new TicketService();
            ObservableList<Ticket> EventSelected,allEvents;
               allEvents = tableaut.getItems();

            EventSelected = tableaut.getSelectionModel().getSelectedItems();
                       ps.SupprimeTicket(EventSelected.get(0).getId());
                        EventSelected.forEach(listViewTicket::removeAll);
    }

    @FXML
    private void pdfp(ActionEvent event) {
        
        System.out.println("To Printer!");
   PrinterJob job=PrinterJob.createPrinterJob();
     if(job!=null){
         Window PrimaryStage = null;
       Window primary = null;
       
         job.showPrintDialog(PrimaryStage);
         Node root=this.tableaut;
         job.printPage(root);
         job.endJob();
         
         job.endJob();
     }
    }
    private EventHandler<MouseEvent> OpenEventHandler(Ticket ticket, int index) {
		return event -> {
			
                   try {
                       TicketService.compter(ticket);
                       Ticket ticket_à_ouvrir = ticket;

				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Detail_Sujet.fxml"));
				id_affichage_Forum.getChildren().clear();
				id_affichage_Forum.getChildren().add(newLoadedPane);
                                
			} catch (IOException ex) {
				Logger.getLogger(listeTicketbackController.class.getName()).log(Level.SEVERE, null, ex);
			}
		};
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
    private void ajouterplatbtn(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/ajoutplat.fxml"));
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
    private void propobtn(ActionEvent event) {
         try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/affichemsg.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 }
    


    
