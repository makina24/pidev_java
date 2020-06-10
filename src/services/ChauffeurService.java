/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Chauffeur;
import entities.LigneTransport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import utils.Connexion;

/**
 *
 * @author abder
 */
public class ChauffeurService {
    
    
     Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public ChauffeurService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterChauffeur(Chauffeur chauffeur) throws SQLException {
    
      
                String query;
            query = "INSERT INTO `chauffeur`( `nom`, `prenom`, `numTel`, `email`, `salaire`, `hireDate`, `lignec_id`) VALUES (?,?,?,?,?,?,?)";
                 
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, chauffeur.getNom());
                    pst.setString(2, chauffeur.getPrenom());
                    pst.setString(3,chauffeur.getNumTel());
                    pst.setString(4,chauffeur.getEmail());
                    pst.setString(5,chauffeur.getSalaire());
                    pst.setString(6,  chauffeur.getHireDate());
                    
                    pst.setString(7,chauffeur.getLignec_id());
                 
                    



                    pst.executeUpdate();
    }
    
    
    
        public List<Chauffeur> getAllChauffeurs() throws SQLException {
                List<Chauffeur> myList = new ArrayList<Chauffeur>();

          
        String req = "SELECT * FROM `chauffeur`  ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          Chauffeur c = new Chauffeur();

      
            c.setNom(rs.getString("Nom"));
            c.setPrenom(rs.getString("Prenom"));
            c.setNumTel(rs.getString("NumTel"));
            c.setEmail(rs.getString("Email"));
            c.setSalaire(rs.getString("Salaire"));
            c.setHireDate(rs.getString("HireDate"));
            c.setLignec_id(rs.getString("lignec_id"));
           
           
             myList.add(c);

            
        }
        return myList;
    }
   
    
         
      public void ModifierUser( Chauffeur chauffeur , int id   ) throws SQLException {
          
          
         String query ="UPDATE `chauffeur` SET `nom`=?,`prenom`=?,`numTel`=?,`email`=?,`salaire`=?,`hireDate`=?,`lignec_id`=? WHERE id = ?";

               PreparedStatement  pst =con.prepareStatement(query);

                    pst.setString(1, chauffeur.getNom());
                    pst.setString(2, chauffeur.getPrenom());
                    pst.setString(3,chauffeur.getNumTel());
                    pst.setString(4,chauffeur.getEmail());
                    pst.setString(5,chauffeur.getSalaire());
                    pst.setString(6, chauffeur.getHireDate());
                    pst.setString(7,chauffeur.getLignec_id());
                  
                    pst.executeUpdate();
              
              
               Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }   
        
      public void SupprimerChauffeur(String nom) throws SQLException {

      String query ="Delete from chauffeur WHERE  nom =? ";
            PreparedStatement pst =con.prepareStatement(query);
                    pst.setString(1, nom);

             pst.executeUpdate();
      
      
      } 
      
          public  Chauffeur getChauffeurById(int id) throws SQLException {
        Chauffeur ch = null;
        String req = "SELECT * FROM `chauffeur` WHERE id = ?";                      
                
        
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            ch = new Chauffeur();

            ch.setNom(rs.getString("Nom"));
            ch.setPrenom(rs.getString("Prenom"));
            ch.setNumTel(rs.getString("NumTel"));
            ch.setEmail(rs.getString("Email"));
            ch.setSalaire(rs.getString("Salaire"));
            ch.setHireDate(rs.getString("HireDate"));
            ch.setLignec_id(rs.getString("lignec_id"));
       
          

            System.out.println("Chauffeur trouvé !");
        }
        return ch;
    }
    
        
        
        
        
}
