/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Plat;
import entities.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import utils.Connexion;

/**
 *
 * @author mahmoud ennouri
 */
public class TicketService {
     
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
            query = "INSERT INTO  `ticket`( `nomPlat`, `photoPlat`, `iduser`,`idPlat`, `date` ) VALUES (?,?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
           
                
                    pst.setString(1, t.getNomPlat());
                    pst.setString(2, "");

                    pst.setInt(3, t.getIdUser());
                    Plat p = null ;
                    pst.setInt(4,p.getId() );
                   
                    pst.setDate(5,t.getDate ());


                    pst.executeUpdate();
    }   

    
    
}
