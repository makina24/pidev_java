/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Plat;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import service.PlatService;

/**
 * FXML Controller class
 *
 * @author mahmoud ennouri
 */
public class updateController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtDescription;
    @FXML
    private Button modifierBoutton;
    @FXML
    private Button boutonretour;
    @FXML
    private Button boutonrechercher;
    @FXML
    private TextField datep;
    @FXML
    private Button importerbouton;
    @FXML
    private TextField txtrecherche;
    @FXML
    private ImageView photoview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierP(ActionEvent event) {
    }

    @FXML
    private void retourP(ActionEvent event) {
    }

    @FXML
    private void rechercherp(ActionEvent event) throws IOException, ParseException{
        String snom = txtrecherche.getText();
        int nomPlat = Integer.parseInt(snom);
        //Plat p = PlatService.findByNom(snom);
        //txtNom.setText(p.getNomPlat());
       // txtDescription.setText(p.getDescription());
        
      
        
    }

    @FXML
    private void importerbouton(ActionEvent event) {
    }
    
}
