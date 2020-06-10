/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gesenfant.Views;
import controllers.LoginController;
import entities.Users;
import gesenfant.Entities.Enfant;
import gesenfant.Entities.User;
import gesenfant.Service.ClasseService;
import gesenfant.Service.EnfantService;
import utils.Connexion;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
 
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
 


/**
 * FXML Controller class
 *
 * @author elbaz
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private TextField age;
    @FXML
    private TextField nationalite;
    @FXML
    private TextField smedical;
    @FXML
    private Button valider;
    @FXML
    private Button web;
    @FXML
    private Button play;
    @FXML
    private Button pause;
    @FXML
    private Button stop;
    @FXML
    private Button shutdown;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MediaPlayer mediaplayer;
        
       Media musicFile= new Media("file:///C:/Users/abder/Downloads/abc.mp3");
        mediaplayer= new MediaPlayer(musicFile);
        mediaplayer.setAutoPlay(false);
        mediaplayer.setVolume(0.1);
        
        
        
            ObservableList<String> sexeList= FXCollections.observableArrayList("Homme","Femme");
            sexe.setItems(sexeList);
            sexe.getSelectionModel().select("Sexe");
    



          
            
           
        valider.setOnAction(e->{
          Users u =   LoginController.userConnected ;
          //Local(String nom, String adresse, float prix,float surface,int capacite)  
        Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Error");
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);

                alert.setTitle("Success");
                
                 
         String n= nom.getText();
         String p=prenom.getText();
         String a= age.getText();
         String s = sexe.getSelectionModel().getSelectedItem().toString();
         String nat= nationalite.getText();
         String m= smedical.getText();
         int us=u.getId();

         String ch="";
         Boolean ok =true;
         int pr = -1;
         int test=Integer.parseInt(a);
         if(!n.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un nom valide!\n";
             ok=false;
         }
         if(!p.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un prenom valide!\n";
             ok=false;
         }
         if(!nat.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer une nationalité valide!\n";
             ok=false;
         }
         if(!m.matches("^[a-zA-Z]+$")){
             ch+="Vous devez entrer un status medical valide!\n";
             ok=false;
         }
         if(!a.matches("\\d+")||a.length()==0||test>5||test==0)
         {
             ch += "Vous devez entrer un age valide!\n";
                ok=false;
         }
         else pr = Integer.parseInt(a);
         if(ok==true){
             Enfant enfant = new Enfant(n, p, s,pr,nat,m,us);
             EnfantService es = new EnfantService();
             try {
                es.ajouterEnfant(enfant);
                ch+="Ajout effectué avec success!\n";
                alert1.setContentText(ch);
                alert1.show();
                 
             } catch (SQLException ex) {
                 Logger.getLogger(AjouterEnfantController.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         else {
             alert.setContentText(ch);
             alert.show();
         }
        });
        web.setOnAction(e->{
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
 
// Initialize browser
WebDriver driver=new ChromeDriver();
 
// Open facebook
driver.get("http://localhost/ELITE/web/app_dev.php/");
 
// Maximize browser
 

            
             });
        play.setOnAction(e->{
            mediaplayer.play();
        });
         pause.setOnAction(e->{
            mediaplayer.pause();
        });
          stop.setOnAction(e->{
           mediaplayer.stop();
        });
           shutdown.setOnAction(e->{
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("front.fxml"));
                shutdown.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }); 
      
    }           
   
        
    
}
