/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.dao;

import fr.solutec.model.*;
import java.sql.Connection;
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
    
    
    
}
