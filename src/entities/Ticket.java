/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author mahmoud ennouri
 */
public class Ticket {
   private int id;
   private String nomPlat;
   private int idPlat;
   private String photoPlat;
   private Date date;
   private int idUser ; 

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

   


 public Ticket() {
    
}
   
  
   public Ticket(int id,String nomPlat,String photoPlat,int idPlat,int idUser, Date date ){
       this.id=id;
       this.nomPlat=nomPlat;
       this.photoPlat=photoPlat;
       this.idPlat=idPlat;
       this.idUser=idUser;
       this.date=date;
       
       
   }
   public  Ticket(String nomPlat,String photoPlat, Date date ){
       
       this.nomPlat=nomPlat;
       this.photoPlat=photoPlat;
       this.date=date;
   }
 
    public  Ticket(String nomPlat, Date date ){
       
       this.nomPlat=nomPlat;
       this.date=date;
   }
     public  Ticket(String nomPlat, Date date, int idUser  ){
       
       this.nomPlat=nomPlat;
       this.date=date;
       this.idUser=idUser;

   }

    public Ticket(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomPlat() {
        return nomPlat;
    }

    public int getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(int idPlat) {
        this.idPlat = idPlat;
    }

 
    public String getPhotoPlat() {
        return photoPlat;
    }

    public void setPhotoPlat(String photoPlat) {
        this.photoPlat = photoPlat;
    }
    

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", nomPlat=" + nomPlat + ", idPlat=" + idPlat + ", idUser=" + idUser + ", photoPlat=" + photoPlat + ", date=" + date + '}';
    }

    public List<String[]> load(File file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   



    

  

  
   
   
}

    

