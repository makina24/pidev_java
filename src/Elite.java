
import entities.User;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import services.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author abder
 */
public class Elite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        
  
         
        UserService us = new UserService();
        //us.ModifierUser(u);
        
        //us.getUserByEmail("abderrahim.makina@esprit.tn");
        //us.getUserById(1);
         //User u = new User( "asmaa", "abir","ffff","admin","gggg","jojojoj");
       // us.ajouterUser(u);

        System.out.print(  us.getAllUsers() );
       // ps.ajouterPersonne2(p);
       // System.out.println(ps.getAllPersonnes());
    }

    
}