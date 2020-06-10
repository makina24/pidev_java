/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gescluuub.model;

import javafx.scene.control.Button;

/**
 *
 * @author benour
 */
public class demandeadhesion {
    private int id;
    private String dureInsc;
    private String modePayment;
    private int numParent;
    private String ancienClub;
    private String objectif;
    private int clubId;
    private int etatDemande;
    private String nomclub;
    Button accepter;
    private String email;
    
    
    public demandeadhesion(){
super();
}
    public demandeadhesion(int id, String dureInsc, String modePayment, int etatDemande, int numParent,String ancienClub,String objectif, int clubId, String nomclub,Button accepter,String email){
super();
this.id=id;
this.dureInsc=dureInsc;
this.modePayment=modePayment;
this.etatDemande=etatDemande;
this.numParent=numParent;
this.ancienClub=ancienClub;
this.objectif=objectif;
this.clubId=clubId;
this.nomclub = nomclub;
this.accepter = accepter;
this.email= email;
}

    public demandeadhesion(String dureInsc, String modePayment, int numParent, String ancienClub, String objectif, String nomclub,String email) {
        this.dureInsc = dureInsc;
        this.modePayment = modePayment;
        this.numParent = numParent;
        this.ancienClub = ancienClub;
        this.objectif = objectif;
        this.nomclub = nomclub;
        this.email =email;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDureInsc() {
        return dureInsc;
    }

    public void setDureInsc(String dureInsc) {
        this.dureInsc = dureInsc;
    }

    public String getModePayment() {
        return modePayment;
    }

    public void setModePayment(String modePayment) {
        this.modePayment = modePayment;
    }

    public int getNumParent() {
        return numParent;
    }

    public void setNumParent(int numParent) {
        this.numParent = numParent;
    }

    public String getAncienClub() {
        return ancienClub;
    }

    public void setAncienClub(String ancienClub) {
        this.ancienClub = ancienClub;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getEtatDemande() {
        return etatDemande;
    }

    public void setEtatDemande(int etatDemande) {
        this.etatDemande = etatDemande;
    }

    public String getNomclub() {
        return nomclub;
    }

    public void setNomclub(String nomclub) {
        this.nomclub = nomclub;
    }

    public Button getAccepter() {
        return accepter;
    }

    public void setAccepter(Button accepter) {
        this.accepter = accepter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    
}
