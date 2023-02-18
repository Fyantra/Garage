/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Remise;

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
public class RemiseCommande {
    String idCommande;
    int pourcentageRemisecommande;

    public RemiseCommande() {
    }

    public RemiseCommande(String idCommande, int pourcentageRemisecommande) {
        this.idCommande = idCommande;
        this.pourcentageRemisecommande = pourcentageRemisecommande;
    }

    public String getidCommande() {
        return idCommande;
    }

    public int getpourcentageRemisecommande() {
        return pourcentageRemisecommande;
    }

    public void setidCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public void setpourcentageRemisecommande(int pourcentageRemisecommande) {
        this.pourcentageRemisecommande = pourcentageRemisecommande;
    }
    
    public void insertRemisecommande(Connection connection) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            connection = new Connect().getConnectionPostGresql();
            
            //Date date = new Date();
            // Préparation de la requête d'insertion
          
            //String emp = "EMP";
            String sql = "INSERT INTO remisecommande VALUES ('"+getidCommande()+"',"+getpourcentageRemisecommande()+")";
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
}
