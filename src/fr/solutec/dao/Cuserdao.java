/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.dao;

import fr.solutec.model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author esic
 */
public class Cuserdao {
    
    public static Cuser getByPseudoPass(String pseudoUser, String mdpUser)throws SQLException {
        // fonction de récupération d'un user dans la bd à partir de son pseudo et mdp
        Cuser resultat = null;
        
        Connection connection = Caccesdao.getConnection();
        
        String sql = "SELECT * FROM User WHERE  pseudouser = ? AND mdpuser = ?";
        PreparedStatement requete = connection.prepareStatement(sql);
        requete.setString(1, pseudoUser);
        requete.setString(2, mdpUser);
        ResultSet rs = requete.executeQuery();
        
        if (rs.next()) {
            resultat = new Cuser();
            resultat.setIdUser(rs.getInt("iduser"));
            resultat.setNomUser(rs.getString("nomuser"));
            resultat.setPrenomUser(rs.getString("prenomuser"));
            resultat.setPseudoUser(rs.getString("pseudouser"));
            resultat.setMailUser(rs.getString("mailuser"));
            resultat.setPoidsUser(rs.getFloat("poidsuser"));
            resultat.setTailleUser(rs.getFloat("tailleuser"));
            resultat.setAgeUser(rs.getInt("ageuser"));
            resultat.setSexeUser(rs.getString("sexeuser"));
        }
        return resultat;
    }
    
    public static void insertUser(Cuser user) throws SQLException {
        // Fonction pour ajouter un utilisateur
        
        Connection connection = Caccesdao.getConnection();
        String sql = "INSERT INTO User (nomuser, prenomuser, pseudouser, mailuser, mdpuser, poidsuser, tailleuser, ageuser, sexeuser) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement requete = connection.prepareStatement(sql);
        requete.setString(1, user.getNomUser());
        requete.setString(2, user.getPrenomUser());
        requete.setString(3, user.getPseudoUser());
        requete.setString(4, user.getMailUser());
        requete.setString(5, user.getMdpUser());
        requete.setFloat(6, user.getPoidsUser());
        requete.setFloat(7, user.getTailleUser());
        requete.setFloat(8, user.getAgeUser());
        requete.setString(9, user.getSexeUser());
        requete.execute(); 
    }
    
    public static void majUser(Cuser user, String nom, String prenom, String mail, String mdp, float taille, float age, String sexe) throws SQLException {
        // Fonction pour mettre à jour un utilisateur 
        
        Connection connection = Caccesdao.getConnection();
        String SQL = "UPDATE user SET nomuser = ?, prenomuser = ?, mailuser = ?, mdpuser = ?, tailleuser = ?, ageuser = ?, sexeuser = ? WHERE iduser = ?";
        PreparedStatement requete = connection.prepareStatement(SQL);
        
        if (!nom.equals(null)) {
            requete.setString(1, nom);
        }
        else {
            requete.setString(1, user.getNomUser());
        }
        if (!prenom.equals(null)) {
            requete.setString(2, prenom);
        }
        else {
            requete.setString(1, user.getPrenomUser());
        }
        if (!mail.equals(null)) {
            requete.setString(3, mail);
        }
        else {
            requete.setString(1, user.getMailUser());
        }
        if (!mdp.equals(null)) {
            requete.setString(4, mdp);
        }
        else {
            requete.setString(1, user.getMdpUser());
        }
        if (taille == 0.0) { 
            requete.setFloat(5, user.getTailleUser());
        }
        else {
            requete.setFloat(5, taille);
        }
        if (age == 0.0) {
            requete.setFloat(6, user.getAgeUser());
        }
        else {
            requete.setFloat(6, age);
        }
        if (!sexe.equals(null)) {
            requete.setString(7, sexe);
        }
        else{
            requete.setString(7, user.getSexeUser());
        }
        requete.setInt(8, user.getIdUser());
        
        requete.execute();
    }
    
        public static void insertTache(Cuser user, Date datedeb, Date datefin, boolean HQ, boolean AO, String typretache, float valeur) throws SQLException {
        // Fonction pour ajouter une tache
        
        int idType = 0;
        Connection connection = Caccesdao.getConnection();
               
        String sqltype = "SELECT idtype FROM Type WHERE typetache = ?";
        PreparedStatement requetetype = connection.prepareStatement(sqltype);
        ResultSet rs = requetetype.executeQuery(sqltype);
        if (rs.next()){
            idType = rs.getInt("idtype");
        }
        
        String sql = "INSERT INTO Tache (datedeb, datefin, hq, ao, idtype, valtache, iduser) values (?,?,?,?,?,?,?)";
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, datedeb);
        requete.setDate(2, datefin);
        requete.setBoolean(3, HQ);
        requete.setBoolean(4, AO);
        requete.setInt(5, idType);
        requete.setFloat(6, valeur);
        requete.setInt(6, user.getIdUser());
       
        requete.execute(); 
    }
    
}
