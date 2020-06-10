/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.Connexion;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class StatCantineController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private JFXButton retourBoutton;
 
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        showstat();
    }    


    private void showstat(){
    
   String sql ="Select nomPlat, count(*) from ticket group by nomPlat";
   XYChart.Series<String, Integer> series=new XYChart.Series<>();
   try{
   Connection ds=Connexion.getInstance().getConnection();
   PreparedStatement stat = ds.prepareStatement(sql);
   ResultSet rs = stat.executeQuery();
   while(rs.next()){
   series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
   }
   barchart.getData().add(series);
   
   }catch(Exception e){}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
               retourBoutton.getScene().getWindow().hide();
        Stage signup = new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("/gui/cantineback.fxml"));
        Scene scene = new Scene(root);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

}