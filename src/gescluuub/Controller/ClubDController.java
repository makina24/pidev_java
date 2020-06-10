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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
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
public class ClubDController implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML private TableView<club> club;
    //@FXML private TableColumn<club, Integer> id;
    @FXML private TableColumn<club, String> nomclub;
    @FXML private TableColumn<club, String> description;
    @FXML private TableColumn<club, String> horraire;
    @FXML private TableColumn<club, Integer> tarif;
    @FXML private TableColumn<club, String> image;
    @FXML private TableColumn<club, Integer> capacite;
    @FXML private TextField nomclubx;
    @FXML private TextArea descriptionx;
    @FXML private ComboBox<String> horrairex;
    private ObservableList<String> list = FXCollections.observableArrayList("matin","soir","nuit");
    @FXML private TextField tarifx;
    @FXML private TextField imagex;
    @FXML private TextField capacitex;
   public ObservableList<club> data = FXCollections.observableArrayList();
   @FXML
    private TextField search;
    
    club Aem;
    @FXML
    private Label controlnom;
    @FXML
    private Label controldesc;
    @FXML
    private Label controlcap;
    @FXML
    private Label controlimg;
    @FXML
    private Label controltarif;
    @FXML
    private Label controlhoraire;
    @FXML
    private Button modifierc;
    @FXML
    private Button del;
   
    @FXML
   public void vider(){
    nomclubx.clear();
    descriptionx.clear();
    horrairex.setValue(null);
    imagex.clear();
    tarifx.clear();
    capacitex.clear();
    }
    @FXML
    public void updateclub(ActionEvent event){
        String sql = " UPDATE club SET nomclub=?, description=?, horraire=?, tarif=?, image=?, capacite=? WHERE id=?";
        
        try{
            String nomclub = nomclubx.getText();
        String description = descriptionx.getText();
        String image = imagex.getText();
        Integer tarif= Integer.valueOf(tarifx.getText());
        Integer capacite= Integer.valueOf(capacitex.getText());
       String horraire = horrairex.getValue();
       Integer id=Aem.getId();
        Connection ds=Connexion.getInstance().getConnection();
                   

        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        
        stat.setString(1, nomclub);
        stat.setString(2, description);
        stat.setString(3, horraire);
        stat.setInt(4, tarif);
        stat.setString(5, image);
        stat.setInt(6, capacite);
        stat.setInt(7, id);

     int  st = stat.executeUpdate();
     if(st== 1){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("modifier un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club bien Modifier! ");
        alert.showAndWait();
        refreshTableclub();
     
     } else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Modifier un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non modifier! ");
        alert.showAndWait();}
        }catch (SQLException ex) {
                        Logger.getLogger(ClubDController.class.getName()).log(Level.SEVERE, null, ex);
                    }
      
    }
    
    private void getSelected() {
        club.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Aem = club.getItems().get(club.getSelectionModel().getSelectedIndex());
                if (Aem == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            modifierc.setDisable(true);
    //        delete.setDisable(true);
        } else {
                    modifierc.setDisable(false);
                
                nomclubx.setText(Aem.getNomclub());
          tarifx.setText(Integer.toString(Aem.getTarif()));
          capacitex.setText(Integer.toString(Aem.getCapacite()));
          imagex.setText(Aem.getImage());
          descriptionx.setText(Aem.getDescription());
	  horrairex.getSelectionModel().select(Aem.getHorraire());
            }
           }   
        });
    }
    
    @FXML
   public void ajoutclub(ActionEvent event) throws IOException{
       if (testSaisie() && !nomclubx.getText().equals("") && !tarifx.getText().equals("") && !capacitex.getText().equals("") && !imagex.getText().equals("") && !descriptionx.getText().equals("") && !horrairex.getSelectionModel().isEmpty())
        {
        String nomclub = nomclubx.getText();
        String description = descriptionx.getText();
        String image = imagex.getText();
        club clb = new club();
        clb.setNomclub(nomclub);
        clb.setDescription(description);
        clb.setHorraire(horrairex.getValue());
        clb.setTarif(Integer.parseInt(tarifx.getText()));
        clb.setImage(image);
        clb.setCapacite(Integer.parseInt(capacitex.getText()));
        
        int status = crudclub.save(clb);
        if (status>0){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club bien ajouter! ");
        
        alert.showAndWait();
        refreshTableclub();
        
        }
        
        
        else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non ajouter! ");
        alert.showAndWait();
        }
        }
        
        
    }
   @FXML
      public void deletec(ActionEvent event)throws IOException{      
       int st = 0;
       String sql = " DELETE FROM club WHERE id=?";
        try{
        Integer id=Aem.getId();
        Connection ds=Connexion.getInstance().getConnection();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setInt(1, id);
        st = stat.executeUpdate();
        if(st== 1){
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("supprimer un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club bien supprimer! ");
        alert.showAndWait();
        vider();
        refreshTableclub();
     
     } else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("supprimer un club!");
        alert.setHeaderText("information");
        alert.setContentText("Club non supprimer! ");
        alert.showAndWait();}
        
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        
        
    }
      
    @FXML
     public void refreshTableclub () 
    {
        
            data.clear();
            Connection connexion;
                   connexion=Connexion.getInstance().getConnection();
            String query = "select * from club";
            PreparedStatement pst;
        try {
            pst = connexion.prepareStatement(query);
               ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                   club e=new club();
               e.setId(rs.getInt(1));
                       e.setNomclub(rs.getString(2));
                       e.setDescription(rs.getString(3));
                       e.setHorraire(rs.getString(4));
                    e.setTarif(rs.getInt(5));
                    e.setImage(rs.getString(6));
                    e.setCapacite(rs.getInt(7));
                    data.add(e);
                club.setItems(data);
                
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(DemandelistController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    
   @FXML
    public void chercher() {
        FilteredList<club> filteredData = new FilteredList<>(data, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (aff.getNomclub().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<club> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(club.comparatorProperty());
        club.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horrairex.setItems(list);
  crudclub cs = new crudclub();
        data = cs.afficherclub();
   //id.setCellValueFactory(new PropertyValueFactory<club, Integer>("id"));
   nomclub.setCellValueFactory(new PropertyValueFactory<club, String>("nomclub"));
   description.setCellValueFactory(new PropertyValueFactory<club, String>("description"));
   horraire.setCellValueFactory(new PropertyValueFactory<club, String>("horraire"));
   tarif.setCellValueFactory(new PropertyValueFactory<club, Integer>("tarif"));
   image.setCellValueFactory(new PropertyValueFactory<club, String>("image"));
   capacite.setCellValueFactory(new PropertyValueFactory<club, Integer>("capacite"));
   club.setItems(data);
   refreshTableclub();
    chercher();
    getSelected();
      
   
    }    

    @FXML
    private boolean nomtest() {
          int nbNonChar = 0;
      

        if (nbNonChar == 0 && nomclubx.getText().trim().length() >= 1) {
            //img.setImage(new Image("Images/tick.png"));
            controlnom.setVisible(false);
            nomclubx.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            //img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            controlnom.setText("veuillez remplir ce champs");
            //img.setVisible(false);
            controlnom.setVisible(true);
            nomclubx.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    
    }
     private Boolean testSaisie() {
       String erreur = "";
        
        
        if (!nomtest()) {
            erreur = erreur + ("Veuillez verifier le champs du nom club \n");
        }
         if (!tariftest()) {
            erreur = erreur + ("Veuillez verifier le champs du tarif \n");
        }
          if (!captest()) {
            erreur = erreur + ("Veuillez verifier le champs de la capacité \n");
        }
           if (!imgtest()) {
            erreur = erreur + ("Veuillez verifier le champs de l'image \n");
        }
            if (!desctest()) {
            erreur = erreur + ("Veuillez verifier le champs de la description \n");
        }
               if (!testhoraire()) {
            erreur = erreur + ("Veuillez verifier le champs de l'horaire \n");
        }
       
        
        

        if (!nomtest() || !tariftest() || !captest() || !imgtest() || !desctest() || !testhoraire()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ajouter un club!");
        alert.setHeaderText("Warning");
        alert.setContentText(erreur);
        alert.showAndWait();
       
        }
        return nomtest();
    
    
}

    @FXML
    private boolean tariftest() {
           int nbNonChar = 0;
        for (int i = 0; i < tarifx.getText().trim().length(); i++) {
            char ch = tarifx.getText().charAt(i);
            
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        
        
       if (nbNonChar == 0 && tarifx.getText().trim().length() >= 1) {
            
            controltarif.setVisible(false);
            tarifx.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            return true;
        }
      
        
        else {
           if (nbNonChar != 0){
           
            controltarif.setText("Veuillez taper des numéros uniquement");
            controltarif.setVisible(true);
            tarifx.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
            
            return false;
           }
           else if (tarifx.getText().trim().length() >= 0)
           {
              controltarif.setText("Veuillez remplir ce champs");
              controltarif.setVisible(true);
            tarifx.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

           
                       return false;
}


        }
       return false;
        
    }

    @FXML
    private boolean captest() {
            int nbNonChar = 0;
        for (int i = 0; i < capacitex.getText().trim().length(); i++) {
            char ch = capacitex.getText().charAt(i);
            
            if (Character.isLetter(ch)) {
                nbNonChar++;
            }
        }
        
        
       if (nbNonChar == 0 && capacitex.getText().trim().length() >= 1) {
            
            controlcap.setVisible(false);
            capacitex.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");

            
            return true;
        }
      
        
        else {
           if (nbNonChar != 0){
           
            controlcap.setText("Veuillez taper des numéros uniquement");
            controlcap.setVisible(true);
            capacitex.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
            
            return false;
           }
           else if (capacitex.getText().trim().length() >= 0)
           {
              controlcap.setText("Veuillez remplir ce champs");
              controlcap.setVisible(true);
            capacitex.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");
           
                       return false;
}


        }
       return false;
        
    }

    @FXML
    private boolean imgtest( ) {
          int nbNonChar = 0;
      

        if (nbNonChar == 0 && imagex.getText().trim().length() >= 1) {
            //img.setImage(new Image("Images/tick.png"));
            controlimg.setVisible(false);
            imagex.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            //img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            controlimg.setText("veuillez remplir ce champs");
            //img.setVisible(false);
            controlimg.setVisible(true);
            imagex.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    }

    @FXML
    private boolean desctest( ) {
          int nbNonChar = 0;
      

        if (nbNonChar == 0 && descriptionx.getText().trim().length() >= 1) {
            //img.setImage(new Image("Images/tick.png"));
            controldesc.setVisible(false);
            descriptionx.setStyle("-fx-text-box-border: green ;-fx-focus-color: green ;");
            
            //img.setVisible(true);
            return true;
        } else {
            //nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            controldesc.setText("veuillez remplir ce champs");
            //img.setVisible(false);
            controldesc.setVisible(true);
            descriptionx.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
    }
    }

    @FXML
    private boolean testhoraire( ) {
           if (!horrairex.getSelectionModel().isEmpty()){
            
          horrairex.setStyle("-fx-focus-color: green ;-fx-text-box-border: green ;");

            controlhoraire.setVisible(false);
            
            return true;
        }
        else {
            controlhoraire.setText("Veuillez selectionner l'horaire");
                    controlhoraire.setVisible(true);
         horrairex.setStyle("-fx-focus-color: red ;-fx-text-box-border: red ;");

            return false;
        }
    }
}