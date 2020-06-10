/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Msg_1;
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
public class MsgService {
    public static Msg_1 getId_msg(int id_msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     Connection con = Connexion.getInstance().getConnection();
    private Statement stmt;

    public MsgService() {
        try {
            if (con != null) {
                stmt = con.createStatement();
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }
    
    
        public void AjouterMsg(Msg_1 c) throws SQLException {

        String req = "INSERT INTO  `msgm`(`contenu`, `statut`,`id_user`) VALUES (?,?,?)";
        PreparedStatement pre = con.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
     
       
        pre.setString(1, c.getContenu());
        pre.setString(2, c.getContenu());
        pre.setInt(3, c.getIdUser());

        pre.executeUpdate();
        

        
    }

        public List<Msg_1> getAllMsg() throws SQLException {
               List<Msg_1> myList = new ArrayList<Msg_1>();

          
        String req = "SELECT * FROM  msgm ";
        PreparedStatement pre = con.prepareStatement(req);
      
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
          Msg_1 m = new Msg_1();

            m.setId_msg(rs.getInt("msg_id"));
            m.setContenu(rs.getString("contenu"));
            m.setStatut(rs.getString("statut"));
            m.setIdUser(rs.getInt("id_user"));

          
            
             myList.add(m);

            
        }
        return myList;
    }
        public void SupprimerMsg(int id ) throws SQLException {

       
        String query ="Delete from msgm WHERE  id_msg =? ";
            PreparedStatement pst =con.prepareStatement(query);
                    pst.setInt(1, id);

             pst.executeUpdate(); 
        
        
        }


    
}
