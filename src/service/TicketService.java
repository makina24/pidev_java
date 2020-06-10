/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Plat;
import entities.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.Connexion;

/**
 *
 * @author mahmoud ennouri
 */

public class TicketService {

    public static void compter(Ticket ticket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public TicketService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }

   
   
      
     public void ajouterTicket(Ticket t) throws SQLException {
    
      
                String query;
            query = "INSERT INTO  `ticket`( `nomPlat`, `photoPlat`, `id_user`,`id_Plat`, `date` ) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
         
           
                
                    pst.setString(1, t.getNomPlat());
                    pst.setString(2, "");

                    pst.setInt(3, t.getIdUser());
                 
                    pst.setInt(4,t.getIdPlat() );
                   
                    pst.setDate(5,t.getDate ());


                    pst.executeUpdate();
    }   
     
    
        public List<Ticket> getAllTickets() throws SQLException {
               ArrayList<Ticket> a = new ArrayList<Ticket>();

          
        String req = "SELECT id,nomPlat,id_plat,id_user,date,photoPlat FROM ticket ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          Ticket p = new Ticket();

            p.setId(rs.getInt("id"));
            p.setNomPlat(rs.getString("NomPlat"));
              p.setIdPlat(rs.getInt("id_plat"));
             p.setIdUser(rs.getInt("id_user"));

            p.setDate(rs.getDate("date"));
            p.setPhotoPlat(rs.getString("photoPlat"));

            

          
            
             a.add(p);

            
        }
        return a;
    }
         
        
         
        
    

    
    public void SupprimeTicket(int id   ) throws SQLException {

      
        String query ="Delete from ticket WHERE  id =? ";
           
       
            PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1, id);

             pst.executeUpdate();

}
    
    
    
    
}