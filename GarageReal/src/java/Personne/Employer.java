/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import connexion.Connect;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import jdbc.BddObject;

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
public class Employer extends BddObject{

    
    String idEmployer;
    String nom;
    String prenom;
    String addresse;
    String contact;
    String idGenre;
    Date datedenaissance;
    //String strdateNaissance;
    Date dateembauche;

  public Employer(String idEmployer, String nom, String prenom, String addresse, String contact, String idGenre, Date dateNaissance) {
        this.idEmployer = idEmployer;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.contact = contact;
        this.idGenre = idGenre;
        this.datedenaissance = dateNaissance;
    }

    
    public Employer( String nom, String prenom, String addresse,String contact, String idGenre, Date dateNaissance) throws Exception {
        //this.idEmployer = idEmployer;
        this.nom = nom;
        this.prenom = prenom;
        this.addresse = addresse;
        this.contact = contact;
        this.datedenaissance = dateNaissance;
        this.idGenre = idGenre;
        //this.dateembauche = dateembauche;
    }
    
    public Employer() throws Exception {
    }
    

    /*public static Employer [] bddListToEmp(BddObject [] l) throws Exception {
        Employer [] le = new Employer[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Employer) l[i];
        }
        return le;
    }*/

    
    /*public void setDateNaissance(Date dateNaissance) throws Exception {
        if(!isMajeur(dateNaissance)) throw new Exception("Vous etes mineur"); 
        this.dateNaissance = dateNaissance;
    }*/
    
    public boolean isMajeur(Date date) {
    
        Date now = new Date();
        int anneeNow = now.getYear() +1900;
        int yearNaiss = date.getYear() + 1900;
        System.out.println("annee now "+ anneeNow);
        System.out.println("annee naiss "+yearNaiss);
        System.out.println(anneeNow - yearNaiss);
        
        return (anneeNow - yearNaiss > 18);
    }

    public String getIdEmployer() {
        return idEmployer;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getContact() {
        return contact;
    }

    public Date getDateNaissance() {
        return datedenaissance;
    }

    public String getIdGenre() {
        return idGenre;
    }

    public Date getDateembauche() {
        return dateembauche;
    }

    public void setIdEmployer(String idEmployer) {
        this.idEmployer = idEmployer;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setaddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setIdGenre(String idGenre) {
        this.idGenre = idGenre;
    }

    public void setDateembauche(Date dateembauche) {
        this.dateembauche = dateembauche;
    }

    public static Employer getNewEmp() throws Exception {
        /*Connection connection = null;
        PreparedStatement statement = null;

        Employer employer = null;

        try {
            connection = new Connect().getConnectionPostGresql();
            String sql = "SELECT * FROM Employer ORDER BY idEmployer DESC LIMIT 1";
            statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id = resultSet.getString("idemployer");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String addresse = resultSet.getString("addresse");
                String genre = resultSet.getString("idGenre");
                // Genre genre2 = Genre.getById(genre);
                String contact = resultSet.getString("contact");
                Date dtn = resultSet.getDate("datedenaissance");
                 //Date dateembauche = resultSet.getDate("dateEmbauche");

                // Employer(String idEmployer, String nom, String prenom, String addresse,String contact, Date dateNaissance, String idGenre, Date dateembauche) 
                employer = new Employer(nom, prenom, addresse,contact, genre,dtn);
            }
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
        return employer;*/
        
        Employer iniemp=new Employer();
        
        BddObject[] listeemp=iniemp.select(null);
        int taille=listeemp.length;
        Employer employer=(Employer) listeemp[taille-1];
        
        return employer;
    }
    
    public void insertEmp(Connection connection) throws Exception {
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
            String sql = "INSERT INTO employer VALUES ('EMP'||nextval('" +this.getClass().getSimpleName()+"seq'),'"+getNom()+"','"+getPrenom()+"','"+getAddresse()+"','"+getContact()+"',"+getIdGenre()+",'"+getDateNaissance()+"')";
            //(nextval('Employerseq')

            statement = connection.prepareStatement(sql);

            /*statement.setString(1, idEmployer);
            statement.setString(2, nom);
            statement.setString(3, prenom);
            statement.setString(4, addresse);
            statement.setString(5, contact);
            statement.setString(6, idGenre);
            statement.setString(7, dateNaissance);*/
            //statement.setDate(8, dateembauche);
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
        Date dtn=new Date();    
        Employer g = new Employer("kkkkk","kkkk","kkkkk","0785566","1", dtn);
            //Employer gg = new Employer("5", "hdhd" , "hhh" , "hhhh" , "078556666666666666666" , "1");
            //g.insertEmp(null);
            /*if(g.isMajeur(dtn) == true){
                System.out.println("ampy taona");
            }
            else System.out.println("tsy ampy taona");*/
           
            
            //String nom = "Employer";
            //BddObject [] resultSelect = g.select(null); 
            /*String [] colonnes =g.getAllColumnInTable(null, "Employer");
            for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }*/
            //g2.insert(null);

            /*for (BddObject bddObject : resultSelect) {
                Employer p = (Employer) bddObject;
                p.desc(); 
                //System.out.println(p.getEmployer());
            }*/
         //}
        }
}
   

