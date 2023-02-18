/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personne;

import connexion.Connect;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import jdbc.BddObject;
import java.util.Date;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import personne.*;
import jdbc.*;


/**
 *
 * @author ITU
 */
public class Client extends Mere{
        String id;
        String mail;
        String mdp;
        Date datedenaissance;

    
    
    
    //constructor
    public Client(String mail, String mdp) {
        this.mail = mail;
        this.mdp = mdp;
    }

    public Client(String id, String mail, String mdp) {
        this.id = id;
        this.mail = mail;
        this.mdp = mdp;
    }

    public Client(String id, String mail, String mdp, Date datedenaissance) {
        this.id = id;
        this.mail = mail;
        this.mdp = mdp;
        this.datedenaissance = datedenaissance;
    }

    public void setDatedenaissance(Date datedenaissance) {
        this.datedenaissance = datedenaissance;
    }

    public Date getDatedenaissance() {
        return datedenaissance;
    }

    public Client() {
    }

    
    
    
    //getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    public boolean isAnniversaire(Date date) {
    
        Date now = new Date();
        int monthnow = now.getMonth();
        int daynow = now.getDay();
        System.out.println("le mois today est:"+monthnow+",,,,,,,,,,le jour today est: "+daynow);
        int month = date.getMonth();
        int day = date.getDay();
        Client c = new Client();
        if(month == monthnow && day== daynow){
            return true;
        }else{
            return false;
        }
    }
        
    //fonction
    public Boolean testLogin(String mail,String mdp) throws Exception
    {
        Client client=new Client();
        
        String[] nomColonne=new String[2];
        nomColonne[0]="mail";
        nomColonne[1]="mdp";
        String[] valeurs=new String[2];
        valeurs[0]=mail;
        valeurs[1]=mdp;
        
        Object[] listClient=client.select(nomColonne,valeurs);
        if(listClient.length==0)
            return false;
        else
            return true;
    }
    
    public String getIdLogin(String mail,String mdp) throws Exception
    {
        Client client=new Client();
        
        String[] nomColonne=new String[2];
        nomColonne[0]="mail";
        nomColonne[1]="mdp";
        String[] valeurs=new String[2];
        valeurs[0]=mail;
        valeurs[1]=mdp;
        
        //Object[] listClient=client.select(nomColonne,valeurs);
       Object[] listClient=client.select(nomColonne,valeurs);
        if(listClient.length==0)
            return "noId";
        else
        {
            client=(Client)listClient[0];
            return client.getId();
        }
    }
    
    public static void main(String [] args) throws Exception {
        String dtnStr = "2023-02-09";     
        Date dtn=new SimpleDateFormat("yyyy-MM-dd").parse(dtnStr);
        Client c = new Client();
        System.out.println(c.isAnniversaire(dtn));
    }
    
    
    //fonction
    /*public Client[] listClient(String[] colonne,String[] valeur) throws Exception
    {
        Object[] listJoueur=select(colonne,valeur);
        Client[] listJoueurs=new Client[listJoueur.length];
        for(int i=0;i<listJoueurs.length;i++)
        {
            listJoueurs[i]=(Client)listJoueur[i];
        }

        return listJoueurs;
    }*/
    
    /*public static Client [] bddListToClient(BddObject [] l) throws Exception {
        Client [] le = new Client[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Client) l[i];
        }
        return le;
    }*/
}
