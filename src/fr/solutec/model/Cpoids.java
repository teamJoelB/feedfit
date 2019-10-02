/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.model;

import java.sql.Date;

/**
 *
 * @author esic
 */
public class Cpoids {
    
    private int idUser; 
    private Date datePoids;
    private float valPoids;

    public Cpoids() {
    }

    public Cpoids(int idPoids, Date datePoids, float valPoids) {
        this.idUser = idPoids;
        this.datePoids = datePoids;
        this.valPoids = valPoids;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getDatePoids() {
        return datePoids;
    }

    public void setDatePoids(Date datePoids) {
        this.datePoids = datePoids;
    }

    public float getValPoids() {
        return valPoids;
    }

    public void setValPoids(float valPoids) {
        this.valPoids = valPoids;
    }
}
