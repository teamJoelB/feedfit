/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.model;

/**
 *
 * @author esic
 */
public class Cuser {
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String pseudoUser;
    private String mailUser;
    private String mdpUser;
    private float poidsUser;
    private float tailleUser;
    private float ageUser;
    private String sexeUser;

    public Cuser() {
    }
    public Cuser(String nomUser, String prenomUser, String pseudoUser, String mailUser, String mdpUser, float poidsUser, float tailleUser, float ageUser, String sexeUser) {
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.pseudoUser = pseudoUser;
        this.mailUser = mailUser;
        this.mdpUser = mdpUser;
        this.poidsUser = poidsUser;
        this.tailleUser = tailleUser;
        this.ageUser = ageUser;
        this.sexeUser = sexeUser;
    }

    public int getIdUser() {
        return idUser;
    }
    public String getNomUser() {
        return nomUser;
    }
    public String getPrenomUser() {
        return prenomUser;
    }
    public String getPseudoUser() {
        return pseudoUser;
    }
    public String getMailUser() {
        return mailUser;
    }
    public String getMdpUser() {
        return mdpUser;
    }
    public float getPoidsUser() {
        return poidsUser;
    }
    public float getTailleUser() {
        return tailleUser;
    }
    public float getAgeUser() {
        return ageUser;
    }
    public String getSexeUser() {
        return sexeUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }
    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }
    public void setPseudoUser(String pseudoUser) {
        this.pseudoUser = pseudoUser;
    }
    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }
    public void setMdpUser(String mdpUser) {
        this.mdpUser = mdpUser;
    }
    public void setPoidsUser(float poidsUser) {
        this.poidsUser = poidsUser;
    }
    public void setTailleUser(float tailleUser) {
        this.tailleUser = tailleUser;
    }
    public void setAgeUser(float ageUser) {
        this.ageUser = ageUser;
    }
    public void setSexeUser(String sexeUser) {
        this.sexeUser = sexeUser;
    }

}
