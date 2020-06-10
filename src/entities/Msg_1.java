/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mahmoud ennouri
 */
public class Msg_1 {
    private int id_msg;
    private String contenu;
    private String statut;
    private int idUser ;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    public Msg_1(){
    }
    
    
    public Msg_1(int id_msg,String contenu,String statut){
        this.id_msg=id_msg;
        this.contenu=contenu;
        this.statut=statut;
    }
    
    public Msg_1(String contenu,String statut ){
       
        this.contenu=contenu;
        this.statut=statut;
    
    
}
      public Msg_1(String contenu,String statut, int idUser ){
       
        this.contenu=contenu;
        this.statut=statut;
        this.idUser = idUser;

    
}

    public Msg_1(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_msg() {
        return id_msg;
    }

    public void setId_msg(int id_msg) {
        this.id_msg = id_msg;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Msg{" + "id_msg=" + id_msg + ", contenu=" + contenu + ", statut=" + statut + '}';
    }
    
   
    
    
}

