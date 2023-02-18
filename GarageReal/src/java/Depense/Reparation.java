/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Depense;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.sql.*;
import java.util.Vector;

import connexion.Connect;
import jdbc.*;

/**
 *
 * @author ITU
 */
public class Reparation extends BddObject{
    String idReparation;
    String reparation;
    double prix;

    public Reparation() {
    }

    public Reparation(String idReparation, String reparation, double prix) {
        this.idReparation = idReparation;
        this.reparation = reparation;
        this.prix = prix;
    }

    public Reparation(String reparation, double prix) {
        this.reparation = reparation;
        this.prix = prix;
    }
    
    public String getIdReparation() {
        return idReparation;
    }

    public String getReparation() {
        return reparation;
    }

    public double getPrix() {
        return prix;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setReparation(String reparation) {
        this.reparation = reparation;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    

   
    public static Reparation [] bddListToReparation(BddObject [] l) throws Exception {
        Reparation [] le = new Reparation[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Reparation) l[i];
        }
        return le;
    }
    
    /////PAGINATION///////////////////
    /*public Reparation[] getPaginatedReparations(int page, int nombre) {



    //miget  any am base de donnee  
    Reparation[] allReparations = getAllReparation();
    //Reparation rep = new Reparation();
    //BddObject [] resultSelect = g.select(null); 


    //micalcule ny depart
    int depart = (page - 1) * nombre;
    
    // ra ngeza le depart de miverina zero par defaut (controlle kely fa normalement tokony tsy misy page miotra ny atsofoka ao)
    if (depart > allReparations.length) {
        return new Reparation[0];
    }
    //micaclcule fin
    int fin = Math.min(debut +nombre, allReparations.length);

    //initialisation
    Reparation[] paginatedReparations = new Reparation[fin - debut];

    //remplissage
    for (int i = debut; i < fin; i++) {
        paginatedReparations[i - debut] = allReparations[i];
    }
    return paginatedReparations;
}
    
    public static Reparation[] getAllReparation(String nomTable) throws Exception {
        if (nomTable == null) {nomTable = "reparation";}

        Connect c = new Connect(); //classe namboarina
    
        Connection connection = c.getConnectionPostGresql();

        Statement stat = connection.createStatement();

        ResultSet resultSet = stat.executeQuery("select * from "+nomTable);

        Vector repVect = new Vector();

        while(resultSet.next()){

            int id = resultSet.getInt(1);
            String reparation = resultSet.getString(2);
            double salaire = resultSet.getDouble(6);
            String numDept = resultSet.getString(8);
            int manager = resultSet.getInt(4);

            Employe e = new Employe(numero,nom,salaire,numDept,manager,this);

            repVect.add(e);
        }

        connection.close();

        Employe[] rep = vectorToEmp(repVect);

        etablirSousJacent(rep);
        etablirManager(rep);

        le = rep;

        return le;
    }*/
    
    public void insertRep(Connection connection) throws Exception {
        //Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }
            
            connection = new Connect().getConnectionPostGresql();
            // Préparation de la requête d'insertion
          
            //String emp = "EMP";
            String sql = "INSERT INTO Reparation VALUES ('REP'||nextval('" +this.getClass().getSimpleName()+"seq'),'"+getReparation()+"',"+getPrix()+")";
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
            Reparation g = new Reparation("Remplacement des freins",4000);
            //Genre g2 = new Genre("dada");
            //String nom = "genre";
            //BddObject [] resultSelect = g.select(null); 
            //String [] colonnes =g.getAllColumnInTable(null, "genre");
            /*for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }*/
            g.insertRep(null);

            /*for (BddObject bddObject : resultSelect) {
                Reparation p = (Reparation) bddObject;
                //p.desc(); 
                System.out.println(p.getReparation());
            }*/
         }

}
