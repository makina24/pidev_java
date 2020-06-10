/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.DatePicker;

/**
 *
 * @author mahmoud ennouri
 */
public class Plat {

    public static int Selected_id;

    public static Object getScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void add(Plat c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
     private int id;
    private String nomPlat;
    private String photoPlat;
    private Date date;
    private String description;
    private int idUser;

    public static int getSelected_id() {
        return Selected_id;
    }

    public static void setSelected_id(int Selected_id) {
        Plat.Selected_id = Selected_id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
  
    
public  Plat(){
}
public  Plat(int id, String nomPlat, String photoPlat, String description, Date date){
   this.id=id;
   this.nomPlat=nomPlat;
   this.photoPlat=photoPlat;
   this.description=description;
   
   
}    

public  Plat( String nomPlat, String photoPlat, String description, Date date, int idUser ){
   this.nomPlat=nomPlat;
   this.photoPlat=photoPlat;
   this.description=description;
   this.idUser=idUser;
    this.date=date;
   
}    
    
public  Plat(String nomPlat, String photoPlat, String description, Date date){
   this.nomPlat=nomPlat;
   this.photoPlat=photoPlat;
   this.description=description;
  this.date=date;
          
   
}
    
public  Plat(String nomPlat, String description, Date date){
   this.nomPlat=nomPlat;
   this.description=description;
  this.date=date;
          
   
}

    public Plat(String nomPlat, String description, Integer date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plat(Date date, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plat(String date, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plat(String nomPlat, String date, String description, String photoPlat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plat(String nomPlat, String date, String description) {
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

    public void setNomPlat(String nomPlat) {
        this.nomPlat = nomPlat;
    }

    public String getPhotoPlat() {
        return photoPlat;
    }

    public void setPhotoPlat(String photoPlat) {
        this.photoPlat = photoPlat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    

    @Override
    public String toString() {
        return "plats{" + "id=" + id + ", nomPlat=" + nomPlat + ", photoPlat=" + photoPlat + ", date=" + date + ", description=" + description + '}';
    }

  

   
    

    







}
    
    
    
