/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

import utils.Connexion;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import gescluuub.model.Mail;
import gescluuub.model.club;
import gescluuub.model.demandeadhesion;
import gescluuub.services.crudclub;

import gescluuub.services.cruddemande;

/**
 * FXML Controller class
 *
 * @author benour
 */
public class DemandelistController implements Initializable {

    /**
     * Initializes the controller class.
     */
    demandeadhesion Aem;
    private TextField idD;
     @FXML private TableView<demandeadhesion> dem;
    private TableColumn<demandeadhesion, Integer> id;
    @FXML private TableColumn<demandeadhesion, String>  dureInsc;
    @FXML private TableColumn<demandeadhesion, String>  modePayment;
    @FXML private TableColumn<demandeadhesion, Integer>  etatDemande;
    @FXML private TableColumn<demandeadhesion, Integer>  numParent;
    @FXML private TableColumn<demandeadhesion, String> ancienClub;
    @FXML private TableColumn<demandeadhesion, String> objectif;
    @FXML private TableColumn<demandeadhesion, Integer> clubId;
    public ObservableList<demandeadhesion> data2 = FXCollections.observableArrayList();
    ObservableList<String> listeTypeRecherche = FXCollections.observableArrayList("Tout", "durée d'inscription", "mode payment", "num parent", "ancien club", "objectif", "etatDemande","email","nomclub");
    @FXML
    private TableColumn<demandeadhesion, String> nomClub;
    @FXML
    private TableColumn<demandeadhesion, Button> accepter;
    @FXML
    private TableColumn<demandeadhesion, String> email;
    @FXML
    private Label controlid;
    @FXML
    private Button delD;
    demandeadhesion deman;
    @FXML
    private HBox hbox;
    @FXML
    private ComboBox<String> traitement;
    @FXML
    private TextField recherch;
    private static ComboBox<String> typeEtat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cruddemande cs = new cruddemande();
        data2 = cs.afficherdemande();
        traitement.setItems(listeTypeRecherche);
        traitement.setValue("Tout");
   //id.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("id"));
   dureInsc.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("dureInsc"));
   modePayment.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("modePayment"));
    etatDemande.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("etatDemande"));
    numParent.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("numParent"));
   ancienClub.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("ancienClub"));
   objectif.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("objectif"));
   clubId.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Integer>("clubId"));
      nomClub.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("nomclub"));
            email.setCellValueFactory(new PropertyValueFactory<demandeadhesion, String>("email"));
    accepter.setCellValueFactory(new PropertyValueFactory<demandeadhesion, Button>("accepter"));

   dem.setItems(data2);
   
   refreshTable();
   getSelected();
    Callback<TableColumn<demandeadhesion, Integer>, TableCell<demandeadhesion, Integer>> cellFactoryEtatD
                = //
                new Callback<TableColumn<demandeadhesion, Integer>, TableCell<demandeadhesion, Integer>>() {
            @Override
            public TableCell call(final TableColumn<demandeadhesion, Integer> param) {
                final TableCell<demandeadhesion, Integer> cell = new TableCell<demandeadhesion, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {

                            ImageView imagev;
                            if (item == 0) {
                                imagev = new ImageView(new Image("/gescluuub/images/nontraite.png"));
                                imagev.setFitHeight(70);
                                imagev.setFitWidth(70);
                                setGraphic(imagev);

                            }
                            else  {
                                imagev = new ImageView(new Image("/gescluuub/images/traite.png"));
                                imagev.setFitHeight(70);
                                imagev.setFitWidth(70);
                                setGraphic(imagev);
                            }

                        }
                    }
                };
                return cell;
            }
        };

        etatDemande.setCellFactory(cellFactoryEtatD);
   
        // TODO
    }    
    
      public void refreshTable () 
    {
        
            data2.clear();
            Connection connexion;
                   connexion=Connexion.getInstance().getConnection();
            String query = "select * from demandeadhesion";
            PreparedStatement pst;
        try {
            pst = connexion.prepareStatement(query);
               ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                   demandeadhesion d=new demandeadhesion();
              
               d.setId(rs.getInt(1));
               d.setDureInsc(rs.getString(2));
               d.setModePayment(rs.getString(3));
               d.setEtatDemande(rs.getInt(4));
               d.setNumParent(rs.getInt(5));
               d.setAncienClub(rs.getString(6));
               d.setObjectif(rs.getString(7));
               d.setClubId(rs.getInt(8));
               d.setNomclub(rs.getString(9));
               d.setEmail(rs.getString(10));
               Button accepter = new Button ("accepter");
               d.setAccepter(accepter);
               if (d.getEtatDemande() == 1) {accepter.setDisable(true);}
              
                              accepter.setOnAction(s -> {
                           if (accepter == d.getAccepter())
                           {  int a = d.getId();
            
                    String reqe = "UPDATE demandeadhesion set etatDemande = 1 where id = ? ";
                    try {
                        PreparedStatement pstm = connexion.prepareStatement(reqe);
                            pstm.setInt(1, a);
                          
                            pstm.executeUpdate();
                            
                             System.out.println("succes");
                        Mail.send(d.getEmail(), "Demande d'adhésion Acceptée","Cher Parent, \n On vous informe "
                                + "que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée", "benourtest@gmail.com", "Esprit123");
                            // Mail.send(reqe, reqe, reqe, query, reqe);
                                  SMS f = new SMS();
       f.SendSMS("elitekids","19041998Kef","Cher Parent, \n On vous informe que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée ","216"+Integer.toString(d.getNumParent()), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                        refreshTable();
                    } catch (SQLException ex) {
                        Logger.getLogger(cruddemande.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                           }

                    
                      
        
        });
                data2.add(d);
                dem.setItems(data2);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DemandelistController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
     private void getSelected() {
        dem.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                deman = dem.getItems().get(dem.getSelectionModel().getSelectedIndex());
                if (deman == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            
            delD.setDisable(true);
        } else {
                    
                delD.setDisable(false); 
               // idD.setText(deman.getEmail());
          
            }
           }   
        });
        

      

    } 
    @FXML
      public void deletedemande(ActionEvent event)throws IOException{      
       int st = 0;
       String sql = " DELETE FROM demandeadhesion WHERE id=?";
        try{
        Integer id=deman.getId();
        Connection ds=Connexion.getInstance().getConnection();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setInt(1, id);
        st = stat.executeUpdate();
        if(st== 1){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("supprimer une demande!");
        alert.setHeaderText("information");
        alert.setContentText("demande bien supprimer! ");
        alert.showAndWait();
        refreshTable();
        
     
     } else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("supprimer une demande!");
        alert.setHeaderText("information");
        alert.setContentText("demande non supprimer! ");
        alert.showAndWait();}
        
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        
        
    }
       public void getdemande(ActionEvent event) throws IOException, ParseException{
           if (testid()==true && !id.getText().equals(""))
           {String sid = idD.getText();
        int id = Integer.parseInt(sid);
        club cl = crudclub.getclubId(id);
           }
        
    }

    private boolean testid( ) {
        int nbNonChar = 0;
        for (int i = 0; i < idD.getText().trim().length(); i++) {
            char ch = idD.getText().charAt(i);
            
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        
        
       if (nbNonChar == 0 && idD.getText().trim().length() >= 1) {
            
            controlid.setVisible(false);
            idD.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");

            
            return true;
        }
      
        
        
           if (nbNonChar != 0){
           
            controlid.setText("Veuillez taper des numéros uniquement");
            controlid.setVisible(true);
            idD.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
            
            return false;
           }
        


        
       return false;
    }

    @FXML
     public void list() {
        ArrayList arrayList = null;
        cruddemande crud = new cruddemande();
        if (typeEtat == null) {
            typeEtat = new ComboBox<String>();
             typeEtat.getItems().setAll("Non traitée", "Traitée");
            typeEtat.setValue("Traitée");
        }
        typeEtat.setOnAction(e -> list());
         if (traitement.getValue().toString().equals("etatDemande") && hbox.getChildren().contains(recherch) ) {
           
            hbox.getChildren().remove(recherch);
            hbox.getChildren().add(typeEtat);
        }  else if (!traitement.getValue().toString().equals("etatDemande") && !hbox.getChildren().contains(recherch)) {
            hbox.getChildren().remove(typeEtat);
            hbox.getChildren().add(recherch);
        }
         if (traitement.getValue().toString().equals("etatDemande"))
         {
        arrayList = (ArrayList) crud.recherchedemande(traitement.getValue().toString(), typeEtat.getValue().toString());
        refreshTable();

         }
         else{
         arrayList = (ArrayList) crud.recherchedemande(traitement.getValue().toString(), recherch.getText());
        refreshTable();

         }
        
     
            //arrayList = (ArrayList) produitService.afficherReclamation();
        
 
        ObservableList oc = FXCollections.observableArrayList(arrayList);
        dem.setItems(oc);

    }

   

}
