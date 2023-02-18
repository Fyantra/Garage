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
public class PrixRestant extends BddObject {
        String idCommande;
        double coutrestant;

    public PrixRestant() {
    }

    public PrixRestant(String idCommande, double coutrestant) {
        this.idCommande = idCommande;
        this.coutrestant = coutrestant;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public double getCoutrestant() {
        return coutrestant;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setCoutrestant(double coutrestant) {
        this.coutrestant = coutrestant;
    }
        
        
}
