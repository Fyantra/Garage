package jdbc;

import java.sql.*;
import java.lang.Exception; 
import java.util.Vector;
import javax.swing.JFrame;
import java.time.LocalDateTime;

public class Seq extends Mere
{
    //attribut------------
    int nextVal;






    //constructor------------
    public Seq(int nextVa)
    {
        setNextVal(nextVa);
    }
    
    public Seq()
    {}










    //getters and setters------------
    public int getNextVal()
    {
        return nextVal;
    }
    public void setNextVal(int idJoueu)
    {
        nextVal=idJoueu;
    }
    







    //fonction------------
}