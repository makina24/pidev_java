/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.qrcode.WriterException;
import com.jfoenix.controls.JFXButton;

import entities.Plat;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class impticketController implements Initializable {

    @FXML
    private TextArea ticket;
    @FXML
    private TextField txtplat;
    @FXML
    private TextField txtdate;
    @FXML
    private Button boutonimprimer;
    @FXML
    private AnchorPane tickett;
    
    public static Plat selectedplat ;
    @FXML
    private ImageView imgvwGeneratedBarcode;
    @FXML
    private JFXButton retourBouton;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
   
    @FXML
    private void imprimerbtn (ActionEvent event) {
        
        
       
        System.out.println("To Printer!");
   PrinterJob job=PrinterJob.createPrinterJob();
     if(job!=null){
       Window PrimaryStage = null;
       Window primary = null;
       
         job.showPrintDialog(PrimaryStage);
         Node root=this.tickett;
         job.printPage(root);
         job.endJob();
         
         job.endJob();
     }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        
         retourBouton.getScene().getWindow().hide();
        Stage signup = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/gui/platfront.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

   

  
}