/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facture;

/**
 *
 * @author ITU
 */
public class TotalCommande {
        String idCommande;
        double coutTotal;

    public TotalCommande() {
    }

    public TotalCommande(String idCommande, double coutTotal) {
        this.idCommande = idCommande;
        this.coutTotal = coutTotal;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }
        
        
}
