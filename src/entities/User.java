/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author abder
 */
public class User  {
 
  private int id ;
  private  String username ; 
  private String username_canonical ;
  private String email ;
  private String email_canonical ;
  private int enabled ; 
  private String salt ;
  private String password ; 
  private Date last_login ;
 private String confirmation_token ;
  private Date password_requested_at ;
  private String  roles ;
  private String nom,prenom ; 
  private String valid ;
  
    public User(String cyrine, String jlassi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.username_canonical, other.username_canonical)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.email_canonical, other.email_canonical)) {
            return false;
        }
        if (!Objects.equals(this.salt, other.salt)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.confirmation_token, other.confirmation_token)) {
            return false;
        }
        if (!Objects.equals(this.roles, other.roles)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.valid, other.valid)) {
            return false;
        }
        if (!Objects.equals(this.last_login, other.last_login)) {
            return false;
        }
        if (!Objects.equals(this.password_requested_at, other.password_requested_at)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", nom=" + nom + ", prenom=" + prenom + ", valid=" + valid + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getValid() {
        return valid;
    }
  
  
  
  public User(){
      
      
  }
  
  public User (int id ,String nom , String prenom,String username,String email,String password,String roles){
      this.id= id ;
      this.nom= nom ;
      this.prenom=prenom;
      this.username=username;
      this.email=email;
      this.password=password;
      this.roles=roles;
      
  }
 
      
  
      
      
  
  
    public User (int id ,String nom , String prenom,String username,String email,String password,String roles,String username_canonical,String email_canonical,int enabled,String salt,Date last_login,String confirmation_token,Date password_requested_at,String valid ){
      this.id= id ;
      this.nom= nom ;
      this.prenom=prenom;
      this.username=username;
      this.email=email;
      this.password=password;
      this.roles=roles;
      this.username_canonical=username_canonical;
      this.email_canonical=email_canonical;
      this.enabled=enabled ;
      this.salt=salt ;
      this.last_login=last_login;
      this.confirmation_token=confirmation_token;
      this.password_requested_at=password_requested_at;
      this.valid=valid;
        
      
  }
    
     
    
     public User (String username,String email , String password,String roles,String nom ,String prenom){
     this.username=username;
     this.email=email;
      this.password=password;
      this.roles=roles;
      this.nom= nom ;
      this.prenom=prenom;
  
     }
  
  
    

   
    
}
