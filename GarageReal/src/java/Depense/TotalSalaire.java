/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Depense;

import connexion.Connect;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;
import jdbc.BddObject;
import personne.Specialite;

/**
 *
 * @author Fy
 */
public class TotalSalaire {
    String idReparation;
    String materiel;
    double coutReparation;

    public TotalSalaire() {
    }

    public TotalSalaire(String idReparation, String materiel, double coutReparation) {
        this.idReparation = idReparation;
        this.materiel = materiel;
        this.coutReparation = coutReparation;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public String getMateriel() {
        return materiel;
    }

    public double getCoutReparation() {
        return coutReparation;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public void setCoutReparation(double coutReparation) {
        this.coutReparation = coutReparation;
    }
    
    public static TotalSalaire[] select( Connection connection ,String idReparation) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        //TotalSalaire salaire = null;

        try {
            if (connection == null) {
               
                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();
            }
            connection = new Connect().getConnectionPostGresql();
            
            String sql = "SELECT * FROM totalsalaire WHERE idReparation='"+idReparation+"'";
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();
            Vector<TotalSalaire> v = new Vector<TotalSalaire>();
            
            while(resultSet.next()){
                String idRep = resultSet.getString(1);
                String materiel = resultSet.getString(2);
                double cout = resultSet.getDouble(3);
                
                TotalSalaire totsalaire = new TotalSalaire(idRep, materiel, cout);
                v.add(totsalaire);
               /* v.add(materiel);
                v.add(cout);*/
            }
               if(v.size() == 0) throw new Exception("Vector vide");
               TotalSalaire[] rep = new TotalSalaire[v.size()];
            for (int i = 0; i < v.size(); i++) {
                rep[i] = (TotalSalaire) v.get(i);
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
        return new TotalSalaire[0];
    }
    
    /*public BddObject [] vectToBddObjects(Vector v) throws Exception {
        if(v.size() == 0) throw new Exception("Vector null de size 0 BddObject.java vectToBddObjects");

        BddObject[] rep = new BddObject[v.size()];
        for (int i = 0; i < v.size(); i++) {
            rep[i] = (BddObject) v.get(i);
        }

        return rep;
    }*/
    
    public void desc() throws Exception {
        System.out.println(getClass().getSimpleName()+" : ");
        Field[] lf = getClass().getDeclaredFields();
        for (Field field : lf) {
            System.out.print("| "+field.getName()+" |");   
        }
        System.out.println();
        for (Field field : lf) {
            field.setAccessible(true);
            System.out.print("| "+field.get(this).toString()+" |");   
        }
        System.out.println();
    }
    
    public static void main(String [] args) throws Exception {
            try{
            TotalSalaire g = new TotalSalaire();
           
            TotalSalaire [] resultSelect = g.select(null,"REP1"); 

            for (TotalSalaire bddObject : resultSelect) {
               //TotalSalaire p =  bddObject;
                bddObject.desc(); 
                //System.out.println(p.getGenre());
            }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }
}
