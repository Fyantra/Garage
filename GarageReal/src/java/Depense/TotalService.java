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
public class TotalService {
    String idReparation;
    String materiel;
    double prixMateriel;

    public TotalService() {
    }

    public TotalService(String idReparation, String materiel, double prixMateriel) {
        this.idReparation = idReparation;
        this.materiel = materiel;
        this.prixMateriel = prixMateriel;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public String getMateriel() {
        return materiel;
    }

    public double getPrixMateriel() {
        return prixMateriel;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setMateriel(String materiel) {
        this.materiel = materiel;
    }

    public void setPrixMateriel(double prixMateriel) {
        this.prixMateriel = prixMateriel;
    }
    
    public static TotalService select(String idReparation) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        TotalService service = null;

        try {
            connection = new Connect().getConnectionPostGresql();
            String sql = "SELECT * FROM totalService WHERE idReparation="+idReparation;
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
                String idRep = resultSet.getString(1);
                String materiel = resultSet.getString(2);
                double prixMat = resultSet.getDouble(3);
                
               TotalService totservice = new TotalService(idRep, materiel, prixMat);
               return totservice;
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
        return service;
    }
}
