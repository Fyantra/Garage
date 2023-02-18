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
public class SpecialiteEmployer extends BddObject{
    String id;
    String idEmployer;
    String idSpecialite;

    public SpecialiteEmployer(String id, String idEmploye, String idSpecialite) throws Exception {
        this.id = id;
        this.idEmployer = idEmploye;
        this.idSpecialite = idSpecialite;
    }

    public SpecialiteEmployer(String idEmploye, String idSpecialite) throws Exception {
        this.idEmployer = idEmploye;
        this.idSpecialite = idSpecialite;
    }

    public SpecialiteEmployer() throws Exception {
    }

    public String getIdEmployer() {
        return this.idEmployer;
    }

    public void setIdEmployer(String idEmploye) {
        this.idEmployer = idEmploye;
    }

    public String getIdSpecialite() {
        return this.idSpecialite;
    }

    public void setIdSpecialite(String idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void insertSpecEmp(Connection connection) throws Exception {
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
            String sql = "INSERT INTO SpecialiteEmployer VALUES ('"+getIdEmployer()+"','"+getIdSpecialite()+"')";
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
            //Specialite g = new Specialite();
            SpecialiteEmployer g2 = new SpecialiteEmployer("5","1");
            
            g2.insertSpecEmp(null);

         }catch(Exception e){
             System.out.println(e.getMessage());
         }
    }

}
