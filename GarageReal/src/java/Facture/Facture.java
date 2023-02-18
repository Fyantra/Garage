/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facture;

import connexion.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import jdbc.BddObject;
import Depense.*;

/**
 *
 * @author ITU
 */
public class Facture {
    String idFacture;
    String idCommande;
    int identifiant;

    public Facture() {
    }

    public Facture(String idFacture, String idCommande, int identifiant) {
        this.idFacture = idFacture;
        this.idCommande = idCommande;
        this.identifiant = identifiant;
    }

    public String getIdFacture() {
        return idFacture;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public void setIdFacture(String idFacture) {
        this.idFacture = idFacture;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    
    
}
