/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import connexion.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import jdbc.BddObject;

/**
 *
 * @author ITU
 */
public class Specialite extends BddObject{
    String idSpecialite;
    String specialite;
    double salaire;


    public Specialite() throws Exception {
        //setPrimaryKey(idSpecialite);
    }

    public Specialite(String idSpecialite, String specialite, double salaire) throws Exception {
        this.idSpecialite = idSpecialite;
        this.specialite = specialite;
        this.salaire = salaire;
        //etPrimaryKey(idSpecialite);
    }

    public Specialite(String specialite, double salaire) {
        this.specialite = specialite;
        this.salaire = salaire;
    }
    
    

    public static Specialite [] bddListToSpecialite(BddObject [] l) throws Exception {
        Specialite [] le = new Specialite[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Specialite) l[i];
        }
        return le;
    }

    public String getIdSpecialite() {
        return idSpecialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setIdSpecialite(String idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
    
    public void insertSpec(Connection connection) throws Exception {
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
            String sql = "INSERT INTO specialite VALUES ('SPE'||nextval('" +this.getClass().getSimpleName()+"seq'),'"+getSpecialite()+"',"+getSalaire()+")";
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
            try{
            Specialite g = new Specialite();
            Specialite g2 = new Specialite("dada",2000);
            //String nom = "genre";
            BddObject [] resultSelect = g.select(null); 
            //String [] colonnes =g.getAllColumnInTable(null, "genre");
            /*for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }*/
            //g2.insertSpec(null);

            for (BddObject bddObject : resultSelect) {
                Specialite p = (Specialite) bddObject;
                p.desc(); 
                //System.out.println(p.getGenre());
            }
         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }

}
