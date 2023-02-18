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
import java.util.Date;

/**
 *
 * @author ITU
 */
public class RemiseQuantitecommande {
        String idQuantiteCommande;
        int pourcentageRemisequantitecommande;

    public RemiseQuantitecommande() {
    }

    public RemiseQuantitecommande(String idQuantiteCommande, int pourcentageRemisequantitecommande) {
        this.idQuantiteCommande = idQuantiteCommande;
        this.pourcentageRemisequantitecommande = pourcentageRemisequantitecommande;
    }

    public String getIdQuantiteCommande() {
        return idQuantiteCommande;
    }

    public int getPourcentageRemisequantitecommande() {
        return pourcentageRemisequantitecommande;
    }

    public void setIdQuantiteCommande(String idQuantiteCommande) {
        this.idQuantiteCommande = idQuantiteCommande;
    }

    public void setPourcentageRemisequantitecommande(int pourcentageRemisequantitecommande) {
        this.pourcentageRemisequantitecommande = pourcentageRemisequantitecommande;
    }
    
    public void insertRemiseQuantitecommande(Connection connection) throws Exception {
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
            String sql = "INSERT INTO remiseQuantitecommande VALUES ('"+getIdQuantiteCommande()+"',"+getPourcentageRemisequantitecommande()+")";
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
    
    public static void main(String [] args) throws Exception {
            RemiseQuantitecommande r = new  RemiseQuantitecommande("CMD1",20);
            
            r.insertRemiseQuantitecommande(null);

      }
}
