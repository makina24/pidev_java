/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import utils.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;
import gescluuub.model.club;

/**
 * FXML Controller class
 *
 * @author benour
 */


public class FstatclubController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private BarChart<String, Integer> barchart;
@FXML
    private Button btnshow;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showstat();
    }    
    @FXML
    private void showstat(){
    
   String sql ="Select nomclub, count(*) from demandeadhesion group by nomclub";
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
    
}
