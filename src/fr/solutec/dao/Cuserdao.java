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
}
