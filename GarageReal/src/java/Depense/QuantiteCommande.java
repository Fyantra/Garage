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
import personne.SpecialiteEmployer;

/**
 *
 * @author ITU
 */
public class QuantiteCommande {
        String idQuantitecommande;
        String idReparation;
        String idCommande;
        
        public QuantiteCommande (){
            
        }

    public QuantiteCommande(String idCommande,String idReparation) {
        this.idReparation = idReparation;
        this.idCommande = idCommande;
    }   

    public QuantiteCommande(String idQuantitecommande, String idCommande,String idReparation) {
        this.idQuantitecommande = idQuantitecommande;
        this.idReparation = idReparation;
        this.idCommande = idCommande;
    }

    public void setIdQuantitecommande(String idQuantitecommande) {
        this.idQuantitecommande = idQuantitecommande;
    }

    public String getIdQuantitecommande() {
        return idQuantitecommande;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }
        
     public void insertQuantCommande(Connection connection) throws Exception {
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
            String sql = "INSERT INTO QuantiteCommande (idCommande,idReparation) VALUES ('"+getIdCommande()+"','"+getIdReparation()+"')";   //tsy mila REP sy CMD satria zvtra getene ef misy
            //(nextval('quantitecommandeseq')

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
         try{
           
            QuantiteCommande g2 = new QuantiteCommande("CMD1","REP3");
            
            g2.insertQuantCommande(null);

         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
}
