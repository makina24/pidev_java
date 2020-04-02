/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import utils.BCrypt;
import utils.Connexion;

/**
 *
 * @author abder
 */
public class UserService {
    
  Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public UserService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterUser(User user) throws SQLException {
    
      
                String query;
            query = "INSERT INTO fos_user ( `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `ligne_transport_id`, `valid`)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                 
        PreparedStatement pst = con.prepareStatement(query);
        String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
           
                
                    pst.setString(1, user.getUsername());
                    pst.setString(2, user.getUsername());
                    pst.setString(3,user.getEmail());
                    pst.setString(4,user.getEmail());
                    pst.setString(5,"1");
                    pst.setString(6,null);
                    pst.setString(7,password);
                    pst.setString(8,null);
                    pst.setString(9,null);
                    pst.setString(10,null);
                    pst.setString(11,user.getRoles());
                    pst.setString(12,user.getNom());
                    pst.setString(13,user.getPrenom());
                    pst.setString(14,null);
                    pst.setString(15,null);

                    



                    pst.executeUpdate();
    }
    
     
        public List<User> getAllUsers() throws SQLException {
                List<User> myList = new ArrayList<User>();

          
        String req = "SELECT id,username,email,roles,prenom,nom,password FROM fos_user ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          User u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setPrenom(rs.getString("prenom"));
            u.setNom(rs.getString("nom"));
            u.setPassword(rs.getString("password"));
           
           
             myList.add(u);

            
        }
        return myList;
    }
        
        
      public void ModifierUser(User u) throws SQLException {
          
           String query ="UPDATE fos_user set nom=?,prenom=?,username=?,email=?,password=?,roles=? WHERE id= ?";
                   String password = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());

             
         PreparedStatement  pst =con.prepareStatement(query);
             pst.setString(1,u.getNom());
             pst.setString(2,u.getPrenom());
             pst.setString(3,u.getUsername());
             pst.setString(4,u.getEmail());
             pst.setString(5,password);
             pst.setString(6,u.getRoles());
             
              pst.executeUpdate();
              
              
               Alert alertSucc = new Alert(Alert.AlertType.CONFIRMATION);
        alertSucc.setTitle("Succés");
        alertSucc.setContentText("Opération effectuer avec succés");
        alertSucc.setHeaderText(null);
        alertSucc.show();
    }
      
      
          
      
      public void SupprimerUser(User u ) throws SQLException {

      
        String query ="Delete from fos_user WHERE  id =? ";
            PreparedStatement pst =con.prepareStatement(query);
             pst.executeUpdate();

        
           }
      
      
    public User getUserById(int id) throws SQLException {
        User u = null;
        String req = "SELECT * FROM `fos_user` WHERE id = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, id);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setNom(rs.getString("nom"));
           
            u.setPrenom(rs.getString("prenom"));
          
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));

            System.out.println("Utilisateur trouvé !");
        }
        return u;
    }
    
    
     public User searchByPseudoPass(String pseudo, String password) throws SQLException {
        User u = null;
        String req = "SELECT * FROM `fos_user` WHERE ((username = ? OR email = ?))";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, pseudo);
        pre.setString(2, pseudo);
        //pre.setString(3, password);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new User();
            u = UserService.this.getUserById(rs.getInt("id"));
            if (BCrypt.checkpw(u.getPassword(), BCrypt.hashpw(password, BCrypt.gensalt())) == true) {
                return u;

            }
         
        }
        return u;
    }
           
   
    public void changePassword(String password, String email) throws SQLException
    {
        String req = "UPDATE `fos_user` SET password = ?  WHERE email = ?";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setString(1,password);
        
        
        ste.setString(2, email);
        ste.executeUpdate();
    }
           
    
 
    public User getUserByEmail(String email) throws SQLException {
        User u = null;
        String req = "SELECT * FROM `fos_user` WHERE email = ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, email);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setNom(rs.getString("nom"));
         
            u.setPrenom(rs.getString("prenom"));
            
            u.setPassword(rs.getString("password"));
            u.setEnabled(rs.getInt("enabled"));

            System.out.println("Utilisateur trouvé !");
        }
        return u;
    }
    
    
        public List<User> getUserbyRole(String role) throws SQLException {
                List<User> myList = new ArrayList<User>();

          
        String req = "SELECT * FROM `fos_user` WHERE roles LIKE ?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, "%"+role+"%");
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          User u = new User();

            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            u.setNom(rs.getString("nom"));
           
            u.setPrenom(rs.getString("prenom"));
            u.setPassword(rs.getString("password"));
           
                                myList.add(u);

            
        }
        return myList;
    }
        
        
        
         public void UserSetEnable(int id) throws SQLException {
        
        
        String req = "UPDATE `fos_user` SET enabled = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setInt(1,1);
        st.setInt(2,id);
 
        st.executeUpdate();

    }
      
         
          
          public void UserSetDisable(int id) throws SQLException {
        
        
        String req = "UPDATE `fos_user` SET enabled = ? WHERE id = ?";
        PreparedStatement st = con.prepareStatement(req);
        
        st.setInt(1,0);
        st.setInt(2,id);
 
        st.executeUpdate();

    }
    
    
    
    
    
    
    
}
