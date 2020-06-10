/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.LigneTransport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import utils.Connexion;

/**
 *
 * @author abder
 */
public class LigneTransportService {
        Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public LigneTransportService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    public void ajouterChauffeur(LigneTransport ligne) throws SQLException {
    
      
                String query;
            query = "INSERT INTO `ligne_transport`( `heureDepart`, `station`, `vehicule`, `tarif`, `chauffeur_id`, `capacite`, `nbPlaces`) VALUES (?,?,?,?,?,?,?)";
                 
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, ligne.getHeureDepart());
                    pst.setString(2, ligne.getStation());
                    pst.setString(3,ligne.getVehicule());
                    pst.setString(4,ligne.getTarif());
                    pst.setString(5,ligne.getChauffeur_id());
                    pst.setString(6,  ligne.getCapacite());
                    pst.setInt(7,  ligne.getNbPlaces());

                  
                    pst.executeUpdate();
    }
    
    
    
        public List<LigneTransport> getAllLignes() throws SQLException {
                List<LigneTransport> myList = new ArrayList<LigneTransport>();

          
        String req = "SELECT * FROM `ligne_transport`  ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          LigneTransport l= new LigneTransport();
      
            l.setHeureDepart(rs.getString("HeureDepart"));
            l.setStation(rs.getString("station"));
            l.setVehicule(rs.getString("vehicule"));
            l.setTarif(rs.getString("tarif"));
            l.setChauffeur_id(rs.getString("chauffeur_id"));
            l.setCapacite(rs.getString("capacite"));
            l.setNbPlaces(rs.getInt("nbPlaces"));
           
           
             myList.add(l);

            
        }
        return myList;
    }
   
    
         
      public void ModifierUser( LigneTransport ligne , int id   ) throws SQLException {
          
          
         String query ="UPDATE `ligne_transport` SET `heureDepart`=?,`station`=?,`vehicule`=?,`tarif`=?,`chauffeur_id`=?,`capacite`=?,`nbPlaces`=? WHERE id = ?";

               PreparedStatement  pst =con.prepareStatement(query);

                  
                    pst.setString(1, ligne.getHeureDepart());
                    pst.setString(2, ligne.getStation());
                    pst.setString(3,ligne.getVehicule());
                    pst.setString(4,ligne.getTarif());
                    pst.setString(5,ligne.getChauffeur_id());
                    pst.setString(6,  ligne.getCapacite());
                    pst.setInt(7,  ligne.getNbPlaces());
                    pst.executeUpdate();
              
              
               Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }   
        
      public void SupprimerLigne(int id) throws SQLException {

      String query ="Delete from ligne_transport WHERE  id =? ";
            PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1, id);

             pst.executeUpdate();
             
        
           }
      
       
      
          public  LigneTransport getLigneTransportById(int id) throws SQLException {
        LigneTransport l= null;
        String req = "SELECT * FROM `ligne_transport` WHERE id = ?";                      
                
        
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            l = new LigneTransport();

            l.setHeureDepart(rs.getString("HeureDepart"));
            l.setStation(rs.getString("station"));
            l.setVehicule(rs.getString("vehicule"));
            l.setTarif(rs.getString("tarif"));
            l.setChauffeur_id(rs.getString("chauffeur_id"));
            l.setCapacite(rs.getString("capacite"));
            l.setNbPlaces(rs.getInt("nbPlaces"));
          

            System.out.println("Ligne trouvé !");
        }
        return l;
    }
    
    
}
