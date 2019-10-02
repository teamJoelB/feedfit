/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.model;

import java.util.Date;

/**
 *
 * @author esic
 */
public class Ctache {
    private int idTache;
    private Date dateDebut;
    private Date dateFin;
    private boolean ao;
    private float valTache;

    public Ctache() {
    }
    public Ctache(int idTache, Date dateDebut, Date dateFin, boolean ao, float valTache) {
        this.idTache = idTache;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.ao = ao;
        this.valTache = valTache;
    }

    public int getIdTache() {
        return idTache;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public boolean isAo() {
        return ao;
    }
    public float getValTache() {
        return valTache;
    }
    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setAo(boolean ao) {
        this.ao = ao;
    }
    public void setValTache(float valTache) {
        this.valTache = valTache;
    }
    
    
    
    
    
}
