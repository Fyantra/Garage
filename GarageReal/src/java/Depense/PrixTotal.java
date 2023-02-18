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
import java.util.Date;

/**
 *
 * @author Fy
 */
public class PrixTotal {
    String idReparation;
    String materiel;
    double cout;

    public PrixTotal() {
    }

    public PrixTotal(String idReparation, String materiel, double cout) {
        this.idReparation = idReparation;
        this.materiel = materiel;
        this.cout = cout;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public String getMateriel() {
        return materiel;
    }

    public double getCout() {
        return cout;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }
    
    public static PrixTotal select(String idReparation) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        PrixTotal prix = null;

        try {
            connection = new Connect().getConnectionPostGresql();
            String sql = "SELECT * FROM PrixTotal WHERE idReparation="+idReparation;
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
                String idRep = resultSet.getString(1);
                String materiel = resultSet.getString(2);
                double cout = resultSet.getDouble(3);
                
               PrixTotal totprix = new PrixTotal(idRep, materiel, cout);
               return totprix;
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
        return prix;
    }   
}
