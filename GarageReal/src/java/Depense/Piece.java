/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Depense;

import connexion.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import jdbc.BddObject;

/**
 *
 * @author ITU
 */
public class Piece  extends BddObject{
        String idPiece;
        String nomPiece;
        double prixAchat;

    public Piece() {
    }

    public Piece(String idPiece, String nomPiece, double prixAchat) {
        this.idPiece = idPiece;
        this.nomPiece = nomPiece;
        this.prixAchat = prixAchat;
    }

    public String getIdPiece() {
        return idPiece;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public double getPrixAchat() {
        return prixAchat;
    }

    public void setIdPiece(String idPiece) {
        this.idPiece = idPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }
    
    public double getPourcentage() throws Exception {
        if((this.prixAchat>0) && (this.prixAchat<2000)){
            return 10.0;
        }
        else if((this.prixAchat>=2000) && (this.prixAchat<5000)){
            return 20.0;
        }
        
        else if((this.prixAchat>=5000) && (this.prixAchat<=15000)){
            return 30.0;
        }
      return 50;   
    }
    
    public double getPrixVente() throws Exception{
        return this.getPourcentage()*(this.getPrixAchat())/100;
    }
    
    public double getBenefice() throws Exception{
        return ((this.getPrixAchat()-this.getPrixVente()));
    }
    
    public static Piece[] select( Connection connection ,String idPiece) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        //TotalSalaire salaire = null;

        try {
            if (connection == null) {
               
                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();
            }
            connection = new Connect().getConnectionPostGresql();
            
            String sql = "SELECT * FROM Piece WHERE idPiece='"+idPiece+"'";
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();
            Vector<Piece> v = new Vector<Piece>();
            
            while(resultSet.next()){
                String idP = resultSet.getString(1);
                String nom = resultSet.getString(2);
                double prix = resultSet.getDouble(3);
                
                Piece p = new Piece(idP, nom, prix);
                v.add(p);
               /* v.add(materiel);
                v.add(cout);*/
            }
               if(v.size() == 0) throw new Exception("Vector vide");
               Piece[] rep = new Piece[v.size()];
            for (int i = 0; i < v.size(); i++) {
                rep[i] = (Piece) v.get(i);
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
        return new Piece[0];
    }
            
     public static void main(String [] args) throws Exception {
            try{
               Piece g = new Piece();
            
            //String nom = "genre";
                BddObject [] resultSelect = g.select(null); 
            //String [] colonnes =g.getAllColumnInTable(null, "genre");
            /*for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }*/
            //g2.insertSpec(null);

            for (BddObject bddObject : resultSelect) {
               Piece p = (Piece) bddObject;
               // p.desc(); 
                System.out.println("La prix du vente de "+p.getNomPiece()+"est:"+p.getPrixAchat());
                System.out.println("");
               System.out.println("La prix du vente de "+p.getNomPiece()+"est:"+p.getPrixVente());
                System.out.println("");
                System.out.println("La benefice de "+p.getNomPiece()+"est:"+p.getBenefice());
                // System.out.println("Pourcentage:"+p.getNomPiece()+"est:"+p.getPourcentage());
            }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
}
