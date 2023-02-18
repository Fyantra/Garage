/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Depense;

import connexion.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import jdbc.BddObject;

/**
 *
 * @author ITU
 */
public class Commande  extends BddObject {
        String idCommande;
        String idClient;
        Date datecommande;
        
        public Commande() throws Exception {
        }

    public Commande(String idCommande, String idClient, Date commande) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.datecommande = commande;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public Commande(String idClient, Date commande) {
        this.idClient = idClient;
        this.datecommande = commande;
    }

    public String getIdClient() {
        return idClient;
    }

    public Date getCommande() {
        return datecommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    /*public void setCommande(String commande) {        //tsy milaa datyyyy
        this.commande = commande;
    }*/
    
        

       public static Commande [] bddListToCommande(BddObject [] l) throws Exception {
        Commande [] le = new Commande[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Commande) l[i];
        }
        return le;
    }
    
    public void insertCommande(Connection connection) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            connection = new Connect().getConnectionPostGresql();
            
            Date date = new Date();
            // Préparation de la requête d'insertion
          
            //String emp = "EMP";
            String sql = "INSERT INTO commande VALUES ('CMD'||nextval('" +this.getClass().getSimpleName()+"seq'),'"+getIdClient()+"','"+date+"')";
            //(nextval('Employerseq')

            statement = connection.prepareStatement(sql);

            System.out.println(sql);
            statement.executeUpdate();
        } catch (Exception e) { 
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }
    
    public static Commande getNewCommande() throws Exception {
         Commande iniemp=new Commande();
        
        BddObject[] listecomm=iniemp.select(null);
        int taille=listecomm.length;
        Commande commande=(Commande) listecomm[taille-1];
        
        return commande;
    }
    
    public static void main(String [] args) throws Exception {
            Commande g = new Commande();
            
            g.insertCommande(null);

      }
}


