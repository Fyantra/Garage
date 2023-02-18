/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personne;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.sql.*;
import java.util.Vector;

import connexion.Connect;
import jdbc.*;

/**
 *
 * @author Fy
 */
public class SpecialiteRequise {
    String idSpecialiteRequise;
    String idSpecialite;
    String idReparation;
    double dure;
    Integer nombre;

    public SpecialiteRequise() {
    }

    public SpecialiteRequise(String idSpecialiteRequise, String idSpecialite, String idReparation, double dure, Integer nombre) {
        this.idSpecialiteRequise = idSpecialiteRequise;
        this.idSpecialite = idSpecialite;
        this.idReparation = idReparation;
        this.dure = dure;
        this.nombre = nombre;
    }

    public SpecialiteRequise(String idSpecialite, String idReparation, double dure, Integer nombre) {
        this.idSpecialite = idSpecialite;
        this.idReparation = idReparation;
        this.dure = dure;
        this.nombre = nombre;
    }

    public String getIdSpecialiteRequise() {
        return idSpecialiteRequise;
    }

    public String getIdSpecialite() {
        return idSpecialite;
    }

    public String getIdReparation() {
        return idReparation;
    }

    public double getDure() {
        return dure;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setIdSpecialiteRequise(String idSpecialiteRequise) {
        this.idSpecialiteRequise = idSpecialiteRequise;
    }

    public void setIdSpecialite(String idSpecialite) {
        this.idSpecialite = idSpecialite;
    }

    public void setIdReparation(String idReparation) {
        this.idReparation = idReparation;
    }

    public void setDure(double dure) {
        this.dure = dure;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }
    
}
