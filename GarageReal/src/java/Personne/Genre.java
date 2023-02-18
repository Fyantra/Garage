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

/**
 *
 * @author ITU
 */
public class Genre extends BddObject{
    Integer idGenre;
    String genre;


    public Genre() throws Exception {
        setPrimaryKey("idGenre");
    }
    
    public Genre(Integer idGenre, String genre) throws Exception {
        this.idGenre = idGenre;
        this.genre = genre;
        setPrimaryKey("idGenre");
    }
    
    public Genre( String genre) throws Exception {
        //this.idGenre = idGenre;
        this.genre = genre;
        //setgenre(genre);
        setPrimaryKey("idGenre");
    }

    public static Genre [] bddListToGenre(BddObject [] l) throws Exception {
        Genre [] le = new Genre[l.length];
        for (int i = 0; i < le.length; i++) {
            le[i] = (Genre) l[i];
        }
        return le;  //liste de genres
    }


    public Integer getIdGenre() {
        return this.idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    /////affffiiiiiiccchhherrrrr
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
            Genre g = new Genre();
            Genre g2 = new Genre(4,"dhdhdhd");
            //String nom = "genre";
            //BddObject [] resultSelect = g2.select(null); 
            
            String [] colonnes =g.getAllColumnInTable(null, "Genre");
            for(int i =0; i<colonnes.length; i++){
                System.out.println(colonnes[i]);
            }
            //g2.insert(null);

            /*for (BddObject bddObject : resultSelect) {
                Genre p = (Genre) bddObject;
                //p.desc(); 
                System.out.println(p.getGenre());
            
            }*/
            //g2.insert(null);
            /*Connection connection = null;
            try {
            //if (connection == null) {

                //isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();
               
            //}
            
             Statement stat = connection.createStatement();
            
            // Préparation de la requête d'insertion
            //public Employer(String idEmployer, String nom, String prenom, String addresse,String contact, Date dateNaissance, String idGenre, Date dateembauche)
            //String sql = "INSERT INTO testemployer(idEmployer, nom,prenom,addresse,contact,idGenre) VALUES (?,?,?,?,?,?)";
            String sql = "insert into genre values (4,'ddhdhdhdh')";
            
            System.out.println(sql);
            
            stat.executeUpdate(sql);
            System.out.println("metyy");

        } catch (Exception e) { 
            e.printStackTrace();
        } /*finally {
            if (connection != null) {
                connection.close();
            }

        }*/
    }
            
   }


