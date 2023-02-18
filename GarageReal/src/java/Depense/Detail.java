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
public class Detail {
    String idReparation;
    String reparation;
    double dure;
    Integer nombre;
    double salaire;
    double prix;

    public Detail() {
    }

    public Detail(String idReparation, String reparation, double dure, Integer nombre, double salaire, double prix) {
        this.idReparation = idReparation;
        this.reparation = reparation;
        this.dure = dure;
        this.nombre = nombre;
        this.salaire = salaire;
        this.prix = prix;
    }
    
    public static Detail select(String idReparation) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;

        Detail detail = null;

        try {
            connection = new Connect().getConnectionPostGresql();
            String sql = "SELECT * FROM ssrs WHERE idReparation="+idReparation;
            statement = connection.prepareStatement(sql);   

            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
                String idRep = resultSet.getString(1);
                String materiel = resultSet.getString(2);
                double dure = resultSet.getDouble(3);
                Integer nb = resultSet.getInt("nombre");
                double sal = resultSet.getDouble("salaire");
                double prix = resultSet.getDouble("prix");
                
               Detail detaile = new Detail(idRep, materiel, dure,nb, sal, prix);
               return detaile;
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
        return detail;
    }
}
