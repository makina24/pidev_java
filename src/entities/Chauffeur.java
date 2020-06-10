/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author abder
 */
public class Chauffeur {
    
      private int id ; 
        private String nom ;
        String prenom ;
        String numTel ; 
        String email ;
        String salaire ;
        String hireDate ; 
        String lignec_id ; 


 public Chauffeur(){
        
    }
 
   public Chauffeur(int id,String nom,String prenom,String numTel,String email,String salaire,String hireDate,String lignec_id){
        this.id= id ;
        this.nom=nom;
        this.prenom=prenom;
        this.numTel=numTel;
        this.email=email;
        this.salaire=salaire;
        this.hireDate=hireDate;
        this.lignec_id=lignec_id;
    }
    public Chauffeur(String nom,String prenom,String numTel,String email,String salaire){
         this.id= id ;
        this.nom=nom;
        this.prenom=prenom;
        this.numTel=numTel;
        this.email=email;
        this.salaire=salaire;
        
    }
    
   public Chauffeur(String nom,String prenom,String numTel,String email,String salaire,String hireDate,String lignec_id){
       
        this.nom=nom;
        this.prenom=prenom;
        this.numTel=numTel;
        this.email=email;
        this.salaire=salaire;
        this.hireDate=hireDate;
        this.lignec_id=lignec_id;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getLignec_id() {
        return lignec_id;
    }

    public void setLignec_id(String lignec_id) {
        this.lignec_id = lignec_id;
    }

    @Override
    public String toString() {
        return "Chauffeur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numTel=" + numTel + ", email=" + email + ", salaire=" + salaire + ", hireDate=" + hireDate + ", lignec_id=" + lignec_id + '}';
    }



























}