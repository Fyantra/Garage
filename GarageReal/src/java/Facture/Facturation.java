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
public class Facturation extends BddObject{
        String idquantitecommande;
        String  idreparation;
        String  reparation;
        double prix;        //le prix a payerrrrrrrrrrrrr
        String nomClient;

    public Facturation() {
    }

    public Facturation(String idquantitecommande, String idreparation, String reparation, double prix, String nomClient) {
        this.idquantitecommande = idquantitecommande;
        this.idreparation = idreparation;
        this.reparation = reparation;
        this.prix = prix;
        this.nomClient = nomClient;
    }
    
    

    public String getIdquantitecommande() {
        return idquantitecommande;
    }

    public String getIdreparation() {
        return idreparation;
    }

    public String getReparation() {
        return reparation;
    }

    public double getPrix() {
        return prix;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setIdquantitecommande(String idquantitecommande) {
        this.idquantitecommande = idquantitecommande;
    }

    public void setIdreparation(String idreparation) {
        this.idreparation = idreparation;
    }

    public void setReparation(String reparation) {
        this.reparation = reparation;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
        
        public static Facturation [] select( Connection connection ,String idquantitecommande) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        //TotalSalaire salaire = null;

        try {
            if (connection == null) {
               
                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();
            }
            connection = new Connect().getConnectionPostGresql();
            
            String sql = "SELECT * FROM facturation WHERE idquantitecommande='"+idquantitecommande+"'";
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();
            Vector<Facturation> v = new Vector<Facturation>();
            
            while(resultSet.next()){
                String idQ = resultSet.getString("idquantitecommande");
                String idRep = resultSet.getString("idreparation");
                String reparation = resultSet.getString("reparation");
                double prix = resultSet.getDouble("prix");
                String nomClient = resultSet.getString("nomclient");
                
                Facturation f = new Facturation(idQ, idRep, reparation, prix, nomClient);
                v.add(f);
               /* v.add(materiel);
                v.add(cout);*/
            }
               if(v.size() == 0) throw new Exception("Vector vide");
               Facturation[] rep = new Facturation[v.size()];
            for (int i = 0; i < v.size(); i++) {
                rep[i] = (Facturation) v.get(i);
            }
               return rep;
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
        return new Facturation[0];
    }
      
        public static void main(String [] args) throws Exception {
            try{
               Facturation g = new  Facturation();
            
            //String nom = "genre";
                Facturation [] resultSelect = g.select(null,"QCM2"); 
            //String [] colonnes =g.getAllColumnInTable(null, "genre");
            /*for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }*/
            //g2.insertSpec(null);

            for (Facturation bddObject : resultSelect) {
               Facturation p = (Facturation) bddObject;
                p.desc(); 
                
                // System.out.println("Pourcentage:"+p.getNomPiece()+"est:"+p.getPourcentage());
            }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
}
