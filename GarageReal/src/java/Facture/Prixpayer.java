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
public class Prixpayer {
        String idCommande;
        double coutPaye;

    public Prixpayer() {
    }

    public Prixpayer(String idCommande, double coutPaye) {
        this.idCommande = idCommande;
        this.coutPaye = coutPaye;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public double getCoutPaye() {
        return coutPaye;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setCoutPaye(double coutPaye) {
        this.coutPaye = coutPaye;
    }
        
        
}
