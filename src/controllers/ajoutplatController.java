/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Plat;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.PlatService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class ajoutplatController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private DatePicker dateplat;
    @FXML
    private Button ajoutBoutton;
    @FXML
    private ImageView imageview;
    @FXML
    private Button image;
    @FXML
    private ImageView imgnom;
    @FXML
    private ImageView imgnom1;
    @FXML
    private ImageView imgnom11;
    @FXML
    private ImageView imgnom12;
    @FXML
    private ImageView imgnom13;
    File selectedFile;
    private String path;
    @FXML
    private Button acceuilbtn;
    @FXML
    private Button mdfsuppbtn;
    @FXML
    private Button listetickets;
    @FXML
    private Button propo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageview.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        imageview.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        imageview.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });

        imageview.setImage(new Image("file:C:\\Users\\X\\Documents\\NetBeansProjects\\Bon_paln\\src\\Images\\drag-drop.gif"));
        // TODO
    }    

    @FXML
    private void ajoutP(ActionEvent event) {
        if ( validateInputs()){
        
            Date date = Date.valueOf(dateplat.getValue());
            
           


            Plat p= new Plat(txtNom.getText(),"", txtDescription.getText(), date,LoginController.userConnected.getId());
            PlatService ps = new PlatService();
             
          
            try{
            ps.ajouterPlat(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.setContentText("Operation effectuée avec succée !");
            alert.show();
          
          
            }catch (SQLException ex){
                             Logger.getLogger(supmodifController.class.getName()).log(Level.SEVERE, null, ex);

            }
    }
        
    }
     public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }
    
    private boolean validateInputs() {
        if ((txtNom.getText().isEmpty()) || (txtDescription.getText().isEmpty())
               
                 ) {
            Alert alert1 = new Alert(Alert.AlertType.WARNING);
            alert1.setTitle("Erreur");
            alert1.setContentText("inserer champ manquant");
            alert1.setHeaderText(null);
            alert1.show();
            return false;
        }
        return true;
    }

    

    @FXML
    private void image(ActionEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            imageview.setImage(new Image(selectedFile.toURI().toURL().toString()));
            imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            image.setText(path);

        }
          
    }
    

    @FXML
    private void imagenom(MouseEvent event) {
    }

    private void retourP(ActionEvent event) {
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
    private void listetickets(ActionEvent event) {
        try {
        javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("/gui/gestionticket.fxml"));
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
