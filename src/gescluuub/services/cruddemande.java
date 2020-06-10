/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.services;

import utils.Connexion;
import com.teknikindustries.bulksms.SMS;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import gescluuub.model.Mail;
import gescluuub.model.club;
import gescluuub.model.demandeadhesion;

/**
 *
 * @author benour
 */
public class cruddemande {
    
            public ObservableList<demandeadhesion> data2 = FXCollections.observableArrayList();

    public ObservableList afficherdemande(){
         try {
             data2.clear();
            Connection ds=Connexion.getInstance().getConnection();
  String sql= " select * from demandeadhesion ";
   PreparedStatement stat = ds.prepareStatement(sql);
   ResultSet rs = stat.executeQuery();
            while(rs.next())
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
                        PreparedStatement pstm = ds.prepareStatement(reqe);
                            pstm.setInt(1, a);
                          
                            pstm.executeUpdate();
                            
                             System.out.println("succes");
                              Mail.send(d.getEmail(), "Demande d'adhésion Acceptée","Cher Parent, \n On vous informe "
                                + "que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée", "benourtest@gmail.com", "Esprit123");
                            
                              SMS f = new SMS();
       f.SendSMS("elitekids","19041998Kef","Cher Parent, \n On vous informe que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée ","216"+Integer.toString(d.getNumParent()), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                       
                             
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(cruddemande.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                           }

                    
                      
        
        });
                    data2.add(d);
                 
                    }   
        } catch (SQLException ex) {
            Logger.getLogger(crudclub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data2;
    }
    
        public void savedemande(demandeadhesion cl) throws SQLException{
        
        String sql = "INSERT INTO demandeadhesion (dureInsc,modePayment,numParent,ancienClub,objectif,nomclub,email) VALUES(?,?,?,?,?,?,?)";
        String req = "UPDATE demandeadhesion set clubId =(select id from club where nomclub like ? ) where nomclub like ? ";
        Connection ds=Connexion.getInstance().getConnection();
        PreparedStatement pst = ds.prepareStatement(req);
        PreparedStatement stat = ds.prepareStatement(sql);
        stat.setString(1, cl.getDureInsc());
        stat.setString(2, cl.getModePayment());
        stat.setInt(3, cl.getNumParent());
        stat.setString(4, cl.getAncienClub());
        stat.setString(5, cl.getObjectif());
        stat.setString(6, cl.getNomclub());
        stat.setString(7, cl.getEmail());
        pst.setString(1, cl.getNomclub());
        pst.setString(2, cl.getNomclub());
        
        stat.executeUpdate();
        pst.executeUpdate();
        //ds.close();
      
        
        
        
        
        
    
    }
        public static int deleted(int id){
        int st = 0;
        try{
        String sql = " DELETE FROM demandeadhesion WHERE id=?";
        Connection ds=Connexion.getInstance().getConnection();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setInt(1, id);
        st = stat.executeUpdate();
        //ds.close();
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        return 0;
        
    
    }
          public List<demandeadhesion> recherchedemande(String type, String valeur) {
        List<demandeadhesion> myList = new ArrayList<demandeadhesion>();
        String requete = null;
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            if (type.equals("durée d'inscription")) {
                requete = "SELECT * from demandeadhesion where dureInsc like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("mode payment")) {
                requete = "SELECT * from demandeadhesion where modePayment like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("etatDemande") && valeur.equals("Traitée")) {
                requete = "SELECT * from demandeadhesion where etatDemande=1 "; //MAJUSCULE NON OBLIGATOIRE 
            }  else if (type.equals("etatDemande") && valeur.equals("Non traitée")) {
                requete = "SELECT * from demandeadhesion where etatDemande=0 "; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("num parent")) {
                requete = "SELECT * from demandeadhesion where numParent like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }  else if (type.equals("ancien club")) {
                requete = "SELECT * from demandeadhesion where ancienClub like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("objectif")) {
                requete = "SELECT * from demandeadhesion where objectif like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("email")) {
                requete = "SELECT * from demandeadhesion where email like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            }else if (type.equals("nom club")) {
                requete = "SELECT * from demandeadhesion where nomclub like '%" + valeur + "%'"; //MAJUSCULE NON OBLIGATOIRE 
            } else if (type.equals("Tout")) {
                requete = "SELECT * from demandeadhesion where dureInsc like '%" + valeur + "%' or modePayment like '%" + valeur + "%' or nomclub like '%" + valeur + "%' or numParent like '%" + valeur + "%' or ancienClub like '%" + valeur + "%'  or objectif like '%" + valeur + "%' or email like '%" + valeur + "%' "; //MAJUSCULE NON OBLIGATOIRE 
            }

            Statement pst = Connexion.getInstance().getConnection().prepareStatement(requete); // import java.sql.Statement
            ResultSet rs = pst.executeQuery(requete);
           while(rs.next())
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
                        PreparedStatement pstm = Connexion.getInstance().getConnection().prepareStatement(reqe);
                            pstm.setInt(1, a);
                          
                            pstm.executeUpdate();
                            
                             System.out.println("succes");
                              Mail.send(d.getEmail(), "Demande d'adhésion Acceptée","Cher Parent, \n On vous informe "
                                + "que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée", "benourtest@gmail.com", "Esprit123");
                            
                              SMS f = new SMS();
       f.SendSMS("elitekids","19041998Kef","Cher Parent, \n On vous informe que votre demande d'adhésion de votre enfant dans le club: "+d.getNomclub()+" a été acceptée ","216"+Integer.toString(d.getNumParent()), "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
                       
                             
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(cruddemande.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                           }

                    
                      
        
        });
                myList.add(d);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;

    }
    
}
