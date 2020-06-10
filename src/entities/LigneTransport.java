/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author abder
 */
public class LigneTransport {
    
    private int id ;
    private String HeureDepart ; 
    private String station ; 
    private String vehicule ; 
    private String tarif ; 
    private String chauffeur_id ;
    String capacite ; 
    private int nbPlaces ; 
    

public LigneTransport(){


}
public LigneTransport(int id ,String HeureDepart,String station,String vehicule ,String tarif,String chauffeur_id,String capacite, int nbPlaces){
    
    this.id = id ; 
    this.HeureDepart=HeureDepart;
    this.capacite=capacite;
    this.station=station;
    this.vehicule=vehicule;
    this.tarif=tarif;
    this.chauffeur_id=chauffeur_id;
    this.nbPlaces=nbPlaces;
    
}
public LigneTransport(String HeureDepart,String station,String vehicule ,String tarif,String chauffeur_id,String capacite){
    
   
    this.HeureDepart=HeureDepart;
    this.capacite=capacite;
    this.station=station;
    this.vehicule=vehicule;
    this.tarif=tarif;
    this.chauffeur_id=chauffeur_id;
   
    
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeureDepart() {
        return HeureDepart;
    }

    public void setHeureDepart(String HeureDepart) {
        this.HeureDepart = HeureDepart;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    public String getTarif() {
        return tarif;
    }

    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    public String getChauffeur_id() {
        return chauffeur_id;
    }

    public void setChauffeur_id(String chauffeur_id) {
        this.chauffeur_id = chauffeur_id;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    @Override
    public String toString() {
        return "LigneTransport{" + "id=" + id + ", HeureDepart=" + HeureDepart + ", station=" + station + ", vehicule=" + vehicule + ", tarif=" + tarif + ", chauffeur_id=" + chauffeur_id + ", capacite=" + capacite + ", nbPlaces=" + nbPlaces + '}';
    }
























}