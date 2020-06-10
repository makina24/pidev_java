/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Plat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import static jdk.nashorn.internal.runtime.Debug.id;
import utils.Connexion;

/**
 *
 * @author mahmoud ennouri
 */
public class PlatService {

    public static Plat getId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public PlatService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
  /*public ArrayList<Plat> recherchePlat(String rech) throws SQLException {
PlatService p  =new PlatService();
        ArrayList<Plat> off = new ArrayList<>();
String req = "SELECT * FROM plats where nomPlat Like '%"+rech+"%'  ";     
        Statement stm = p.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                      java.util.Date d1 =rst.getDate("Date"); 
                      java.util.Date d2 =rst.getDate("DATEF_EVENT"); 
                      Instant instant = Instant.ofEpochMilli(d1.getTime());
                      Instant instant1 = Instant.ofEpochMilli(d2.getTime());
                      LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();
                      LocalDate date1 = instant1.atZone(ZoneId.systemDefault()).toLocalDate();
                      
   Plat   per=new Plat(rst.getString(2),rst.getString(3),rst.getString(4),date,date1,rst.getString(7),rst.getString(9),rst.getInt(10));
  

            off.add(per);
        }
        return off;
    }

    private Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    
  */
        
     public void ajouterPlat(Plat p) throws SQLException {
    
      
                String query;
            query = "INSERT INTO  `plats`( `nomPlat`, `photoPlat`, `description`, `date`) VALUES (?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, p.getNomPlat());
                    pst.setString(2, p.getPhotoPlat());

                    pst.setString(3, p.getDescription());
                   
                    pst.setDate(4,p.getDate ());


                    pst.executeUpdate();
    }   
     
    
        public List<Plat> getAllPlats() throws SQLException {
               List<Plat> myList = new ArrayList<Plat>();

          
        String req = "SELECT id,nomPlat,photoPlat,description,date FROM plats ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          Plat p = new Plat();

            p.setId(rs.getInt("id"));
            p.setNomPlat(rs.getString("NomPlat"));
            p.setPhotoPlat(rs.getString("PhotoPlat"));
            p.setDescription(rs.getString("Description"));
            p.setDate(rs.getDate("Date"));
            
             myList.add(p);

            
        }
        return myList;
    }
        
        public static Plat getNomPlat(String nomPlat){
        Plat cl = new Plat();
        try{
        String sql = "SELECT * FROM plats WHERE nomPlat=?";  
        Connection ds=Connexion.getInstance().getConnection();
        PreparedStatement stat;
        stat = ds.prepareStatement(sql);
        stat.setString(1, nomPlat);
        ResultSet rst = stat.executeQuery();
        if(rst.next()){
        cl.setId(rst.getInt(1));
        cl.setNomPlat(rst.getString(2));
        cl.setDescription(rst.getString(3));
        cl.setPhotoPlat(rst.getString(4));
        cl.setDate(rst.getDate(5));
        
        }
        }catch(SQLException e){
        e.printStackTrace();
        }
        return cl;
}
        
        
        
        
        
        public ArrayList<Plat> findByNom(String nom) {
               ArrayList<Plat> psr = new ArrayList<>();
        try {
            Connection ds=Connexion.getInstance().getConnection();
            String sql = "select * from plats where nomPlat =?";
             PreparedStatement stat = ds.prepareStatement(sql);
            stat.setString(1, nom);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                       Plat p = new Plat();
        p.setNomPlat(rs.getString(1));
        p.setDescription(rs.getString(2));
        p.setPhotoPlat(rs.getString(3));
        p.setDate(rs.getDate(4));
        
                
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psr;  
    }
         
        public ObservableList<Plat>recherche(String nomPlat) {
        
        String requete = "SELECT * FROM  plats where nomPlat = '"+nomPlat+"' " ;
        PreparedStatement pst;
        ObservableList<Plat> cours= FXCollections.observableArrayList();
            

        try {
            pst = con.prepareStatement(requete);      

            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                
            int id=rs.getInt(1);
               String date =rs.getString("date");
              String description =rs.getString("description");
              
              
               Plat c =new Plat (nomPlat,date,description); 
           cours.add(c);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(PlatService.class.getName()).log(Level.SEVERE, null, ex);
        }
       return cours;
          
    }
        
        
       
      public void ModifierPlat(Plat p ) throws SQLException {
                
          
           String query ="UPDATE plats set nomPlat=?,photoPlat=?,description=?,date=? WHERE id= ?";
                

             
         PreparedStatement  pst =con.prepareStatement(query);
             pst.setString(1,p.getNomPlat());
             pst.setString(2,p.getDescription());
             pst.setString(3,p.getPhotoPlat());
             pst.setDate(4,p.getDate());
      
              pst.executeUpdate();
              
              
               Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
     
    }
      

    

        
     
      public void SupprimePlat(int id ) throws SQLException {

       
        String query ="Delete from plats WHERE  id =? ";
            PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1, id);

             pst.executeUpdate();


        
       
      }

}
    
