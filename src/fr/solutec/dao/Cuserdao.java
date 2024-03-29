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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
            resultat.setDateConnect(rs.getString("dateconnect"));
            resultat.setHelpUser(rs.getBoolean("helpuser"));
        }
        return resultat;
    }
    
    public static void insertUser(Cuser user) throws SQLException {
        // Fonction pour ajouter un utilisateur
        
        Connection connection = Caccesdao.getConnection();
        String sql = "INSERT INTO User (nomuser, prenomuser, pseudouser, mailuser, mdpuser, poidsuser, tailleuser, ageuser, sexeuser, helpuser) values (?,?,?,?,?,?,?,?,?,?)";
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
        requete.setBoolean(10, false);
        requete.execute(); 
    }
    
    public static void majUser(Cuser user, String nom, String prenom, String mail, String mdp, float taille, float age, String sexe) throws SQLException {
        // Fonction pour mettre à jour un utilisateur 
        
        Connection connection = Caccesdao.getConnection();
        String SQL = "UPDATE user SET nomuser = ?, prenomuser = ?, mailuser = ?, mdpuser = ?, tailleuser = ?, ageuser = ?, sexeuser = ? WHERE iduser = ?";
        PreparedStatement requete = connection.prepareStatement(SQL);
        
        if (!nom.equals("")) {
            requete.setString(1, nom);
        }
        else {
            requete.setString(1, user.getNomUser());
        }
        if (!prenom.equals("")) {
            requete.setString(2, prenom);
        }
        else {
            requete.setString(1, user.getPrenomUser());
        }
        if (!mail.equals("")) {
            requete.setString(3, mail);
        }
        else {
            requete.setString(1, user.getMailUser());
        }
        if (!mdp.equals("")) {
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
        requete.setString(7, sexe);
        requete.setInt(8, user.getIdUser());
        
        requete.execute();
    }
    
        public static void insertTache(Cuser user, Date datedeb, Date datefin, boolean AO, String typetache, float valeur) throws SQLException {
        // Fonction pour ajouter une tache
        
        int idType = 0;
        Connection connection = Caccesdao.getConnection();
               
        String sqltype = "SELECT idtype FROM Type WHERE typetache = ?";
        PreparedStatement requetetype = connection.prepareStatement(sqltype);
        requetetype.setString(1, typetache);
        ResultSet rs = requetetype.executeQuery();
        if (rs.next()){
            idType = rs.getInt("idtype");
        }
        
        String sql = "INSERT INTO Tache (datedeb, datefin, ao, idtype, valtache, iduser) values (?,?,?,?,?,?)";
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, datedeb);
        requete.setDate(2, datefin);
        requete.setBoolean(3, AO);
        requete.setInt(4, idType);
        requete.setFloat(5, valeur);
        requete.setInt(6, user.getIdUser());
       
        requete.execute(); 
    }
    
    public static List<Ctache> getObjectifDay(Cuser user, Date day) throws SQLException{
        // Récupère les objectifs  de l'utilisateur en argument à la date en argument
        
        List<Ctache> result = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE datedeb = ? AND datefin = ? AND ao = false";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, day);
        requete.setDate(2, day);
        ResultSet rs = requete.executeQuery();

        while (rs.next()){
            Ctache t = new Ctache();
            t.setDateDebut(rs.getDate("datedeb"));
            t.setDateFin(rs.getDate("datefin"));
            t.setAo(rs.getBoolean("ao"));
            t.setIdTache(rs.getInt("idtache"));
            t.setValTache(rs.getFloat("valtache"));  
            result.add(t);
        }
        return result; 
    } 
    
    public static List<Ctache> getObjectifWeek(Cuser user, Date day) throws SQLException{
        //Récupère les objectifs de l'user en argument de la semaine de la date en argument
        
        List<Ctache> result = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE datedeb < ? < datefin  AND ao = false AND datedeb <> datefin";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, day);
        ResultSet rs = requete.executeQuery();
        
        while (rs.next()){
            Ctache t = new Ctache();
            t.setDateDebut(rs.getDate("datedeb"));
            t.setDateFin(rs.getDate("datefin"));
            t.setAo(rs.getBoolean("ao"));
            t.setIdTache(rs.getInt("idtache"));
            t.setValTache(rs.getFloat("valtache"));  
            result.add(t);
        }
        return result; 
    }   
    
    public static List<Ctache> getActionDay(Cuser user, Date day) throws SQLException{
        //Récupère les actions du jour
        
        List<Ctache> result = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE datedeb = ? AND datefin = ? AND ao = true";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, day);
        requete.setDate(2, day);
        ResultSet rs = requete.executeQuery();
        
        while (rs.next()){
            Ctache t = new Ctache();
            t.setDateDebut(rs.getDate("datedeb"));
            t.setDateFin(rs.getDate("datefin"));
            t.setAo(rs.getBoolean("ao"));
            t.setIdTache(rs.getInt("idtache"));
            t.setValTache(rs.getFloat("valtache"));  
            result.add(t);
        }
        return result;     
    }
    
    public static List<Ctache> getActionDone(Cuser user, Date day) throws SQLException{
        // récupère les actions passés
        
        List<Ctache> result = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE datefin < ? AND ao = true";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, day);
        ResultSet rs = requete.executeQuery();

        while (rs.next()){
            Ctache t = new Ctache();
            t.setDateDebut(rs.getDate("datedeb"));
            t.setDateFin(rs.getDate("datefin"));
            t.setAo(rs.getBoolean("ao"));
            t.setIdTache(rs.getInt("idtache"));
            t.setValTache(rs.getFloat("valtache"));  
            result.add(t);
        }
        return result; 
    }
    
    
    public static void insertPoids(Cuser user, Date day, float value) throws SQLException {
        // Fonction pour ajouter un utilisateur
        
        Connection connection = Caccesdao.getConnection();
        String sql = "INSERT INTO Poids (iduser, datepoids, valpoids) values (?,?,?)";
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setInt(1, user.getIdUser());
        requete.setDate(2, day);
        requete.setFloat(3, value);

        requete.execute(); 
    }
    
    public static float getpoids(Cuser user) throws SQLException {
        
        Connection connection = Caccesdao.getConnection();
        String sql = "SELECT poidsuser FROM User WHERE iduser = ?";
        PreparedStatement requete = connection.prepareStatement(sql);
        float pds = 0;
        
        requete.setInt(1, user.getIdUser());
        ResultSet rs = requete.executeQuery();
        
        if (rs.next()){
            pds = rs.getFloat("poidsuser");
        }
        return pds;
    }
    
    public static List<Cpoids> getAllPoids(Cuser user, Date day) throws SQLException {

        List<Cpoids> result = new ArrayList<>();
        String sql = "SELECT * FROM Poids WHERE datepoids <= ? AND iduser = ?";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setDate(1, day);
        requete.setInt(2, user.getIdUser());
        ResultSet rs = requete.executeQuery();
        System.out.println("In the function");
        while (rs.next()){
            
            System.out.println("In the while");
            
            Cpoids p = new Cpoids();
            p.setDatePoids(rs.getDate("datepoids"));
            p.setIdUser(user.getIdUser());
            p.setValPoids(rs.getFloat("valpoids"));
            result.add(p);
        }
        return result;        
    }

    public static String getTypeTache(Ctache tache) throws SQLException {
        // Récupère le type de tache de la tache
        
        String str = "";
        String sql;
        sql = "SELECT typetache FROM Type INNER JOIN Tache On Tache.idtype = Type.idtype WHERE idtache = ?";
        Connection connection = Caccesdao.getConnection();
        PreparedStatement requete = connection.prepareStatement(sql);
        
        requete.setInt(1, tache.getIdTache());
        ResultSet rs = requete.executeQuery();
        
        if (rs.next()){
            str = rs.getString("typetache");
        }
        return str;
    }
    
    public static void insertDateConnect(Cuser user) throws SQLException {
        // Insère la date actuelle dans l'utilisateur
        
        Connection connection = Caccesdao.getConnection();
        String sql = "UPDATE User SET dateconnect = ? WHERE iduser = ?";
        PreparedStatement requete = connection.prepareStatement(sql);

        java.util.Date todayDate = new java.util.Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = simpleDate.format(todayDate);

        requete.setString(1, sdate);
        requete.setInt(2, user.getIdUser());
        requete.execute();  
    }
    
    public static String getDateConnect(Cuser user) throws SQLException{

        Connection connection = Caccesdao.getConnection();
        String date = "";
        String sql = "SELECT dateconnect FROM User WHERE iduser = ?";
        
        PreparedStatement requete = connection.prepareStatement(sql);

        requete.setInt(1, user.getIdUser());
        ResultSet rs = requete.executeQuery();

        if (rs.next()) {
            date = rs.getString("dateconnect");
        }              
        return date;
    }
    
    public static void setHelpUserFalse(Cuser user) throws SQLException {
        
        Connection connection = Caccesdao.getConnection();
        String sql = "UPDATE User SET helpuser = false WHERE iduser = ?";
        PreparedStatement requete = connection.prepareStatement(sql);

        requete.setInt(2, user.getIdUser());
        requete.execute(); 
        
    }
}
