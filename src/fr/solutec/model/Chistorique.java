/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.solutec.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author esic
 */
public class Chistorique {
    
    /* à utiliser ailleurs dans le programme :
        Ajouter une ligne au log :
    Historique.getUnique().ajoutLog("Consultation du solde");
        Obtenir le log pour l'afficher :
    String info = Historique.getUnique().getLog();
    */

    private static Chistorique unique;	// On crée un objet de cette même classe juste pour pouvoir le manipuler plus bas, dans cette classe.
    private String log;


    private Chistorique() {		// Constructeur privé
            log = new String();
    }


    public static Chistorique getUnique() {
            if(unique == null) {
                    unique = new Chistorique();
            }
            return unique;
    }

    public void ajoutLog(String log) {      // Ajout de la date actuelle dans log.
            // Date du système
            Date date = new Date();
            // Format => a ce format
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH'h'mm");
            // Rajout date
            this.log += "**" + dateFormat.format(date) + "**" + log + "\n";
    }

    public String getLog() {
            return log;
    }
        
}
