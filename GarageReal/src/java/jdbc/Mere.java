package jdbc;

import connexion.Connect;
import java.io.*;
import javax.swing.text.AbstractDocument.Content;
import java.util.Vector;
import java.lang.Object;
import java.lang.Class;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.*;
import java.util.Date;
import java.sql.*;
import java.lang.Exception; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JFrame;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Mere
{
    //atribut---------
    String primaryKey=getClass().getDeclaredFields()[0].getName();
    boolean ouvert=false;







    //setters and getters---------
    public void setOuvert(boolean ouver)
    {
        ouvert=ouver;
    }
    public boolean getOuvert()
    {
        return ouvert;
    }

    public void setPrimaryKey(String ouver)
    {
        primaryKey=ouver;
    }
    public String getPrimaryKey()
    {
        return primaryKey;
    }



    
    
    
    
    
    //fonction--------
    public String insert(Connection c) throws Exception
    {           
        if(c==null)
        {
            setOuvert(true);
            Connect conn=new Connect();
            c=conn.getConnectionPostGresql();
        }
        Statement stat = c.createStatement();
        String idObject=getSequence2();
        
        try 
        {
            System.out.println("insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")");
            stat.executeQuery("insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")");
            
            String m="setId";
                Method method=searchMethod(getClass().getMethods(),m);
                String type="String";
                String value=idObject;
                method.invoke(this,getValue(type,value));
            
            if(getOuvert()==true)
            {
//                c.commit();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
//            c.rollback(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
//            c.rollback();
        }
        finally
        {
            if(getOuvert()==true)
            {
                c.close();
                setOuvert(false);
            }
            stat.close();
        }
        return "insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")";
    }
    
    public String insertNoId(Connection c) throws Exception
    {           
        if(c==null)
        {
            setOuvert(true);
            Connect conn=new Connect();
            c=conn.getConnectionPostGresql();
        }
        Statement stat = c.createStatement();
        String idObject=getSequence2();
        
        try 
        {
            System.out.println("insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")");
            stat.executeQuery("insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")");
            
            if(getOuvert()==true)
            {
//                c.commit();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
//            c.rollback(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
//            c.rollback();
        }
        finally
        {
            if(getOuvert()==true)
            {
                c.close();
                setOuvert(false);
            }
            stat.close();
        }
        return "insert into "+ getClass().getSimpleName() + " values ("+getValeur(idObject)+")";
    }

    public String insert2(Connection c,String id) throws Exception
    {
        if(c==null)
        {
            setOuvert(true);
            Connect conn=new Connect();
            c=conn.getConnectionPostGresql();
        }
        Statement stat = c.createStatement();
        
        try 
        {
            System.out.println("insert into "+ getClass().getSimpleName() + " values ("+getValeur(id)+")");
            stat.executeQuery("insert into "+ getClass().getSimpleName() + " values ("+getValeur(id)+")");
            if(getOuvert()==true)
            {
//                c.commit();
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
//            c.rollback(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
//            c.rollback();
        }
        finally
        {
            if(getOuvert()==true)
            {
                c.close();
                setOuvert(false);
            }
            stat.close();
        }
        return "insert into "+ getClass().getSimpleName() + " values ("+getValeur(id)+")";
    }

    public String getValeur2(String id) throws Exception
    {
        String valeur=new String();
        if(1==getClass().getDeclaredFields().length)
            valeur=getClass().getDeclaredFields()[0].getName()+":"+id;
        else
            valeur=getClass().getDeclaredFields()[0].getName()+":"+id+"|";
        for(int i=1;i<getClass().getDeclaredFields().length;i++)
        {
            Method method=getClass().getMethod("get".concat(String.valueOf(getClass().getDeclaredFields()[i].getName().charAt(0)).toUpperCase().concat(getClass().getDeclaredFields()[i].getName().substring(1))));
            String type=getClass().getDeclaredFields()[i].getType().getSimpleName();
            if(i!=getClass().getDeclaredFields().length-1)
            {
                valeur=valeur+getClass().getDeclaredFields()[i].getName()+":"+getForm3(type,method)+"|";
            }
            else
            {
                valeur=valeur+getClass().getDeclaredFields()[i].getName()+":"+getForm3(type,method);
            }
        }
        return valeur;
    }

    public String getValeur(String id) throws Exception
    {
        String valeur=new String();
        if(1==getClass().getDeclaredFields().length)
            valeur="'"+id+"'";
        else
            valeur="'"+id+"',";
        
            for(int i=1;i<getClass().getDeclaredFields().length;i++)
        {
            Method method=getClass().getMethod("get".concat(String.valueOf(getClass().getDeclaredFields()[i].getName().charAt(0)).toUpperCase().concat(getClass().getDeclaredFields()[i].getName().substring(1))));
            String type=getClass().getDeclaredFields()[i].getType().getSimpleName();
            if(i!=getClass().getDeclaredFields().length-1)
            {
                valeur=valeur+getForm(type,method)+",";
            }
            else
            {
                valeur=valeur+getForm(type,method);
            }
        }
        return valeur;
    }

    public String getSequence2() throws Exception
    {
        String valeur=new String();
        Seq seq1=new Seq();
        Object[] seq=seq1.getSequence(getClass().getSimpleName());
        int val=((Seq)seq[0]).getNextVal();
        
        String result=getClass().getSimpleName()+complPar0(val);
        return result;
    }

    public String getForm2(String tableName,int val)
    {        
        String result=getClass().getSimpleName()+complPar0(val);
        return result;
    }

    public String complPar0(int ray)
    {
        String nombre=String.valueOf(ray);
        int taille=nombre.length();
        String zero="";
        for(int i=taille;i<6;i++)
        {
            zero=zero+"0";
        }
        nombre=zero+nombre;
        return nombre;
    }

    public String getForm(String type,Method m) throws Exception
    {
        if(type.equalsIgnoreCase("int"))
        {
            return String.valueOf(m.invoke(this));
        }
        if(type.equalsIgnoreCase("double"))
        {
            return String.valueOf(m.invoke(this));
        }
        if(type.equalsIgnoreCase("String"))
        {
            return "'"+String.valueOf(m.invoke(this))+"'";
        }
        if(type.equalsIgnoreCase("date"))
        {
            return "'"+String.valueOf(m.invoke(this))+"'";
        }
        return null;
    }

    public String getForm3(String type,Method m) throws Exception
    {
        return String.valueOf(m.invoke(this));
    }

    
    
    
    
    public Object[] getSequence(String nomTable) throws SQLException,Exception
    {
        Connect conn=new Connect();
        Connection con=conn.getConnectionPostGresql();
        Statement stat = con.createStatement();
        ResultSet res=null;
        
        // System.out.println("select "+nomTable+getClass().getSimpleName()+".nextval from dual");
        res = stat.executeQuery("select nextval('"+nomTable+getClass().getSimpleName()+"')");

        Object[] listScore;
        
        listScore=tableToListScore(res);
        

        con.close();
        stat.close();
        
        return listScore;
    }

    public Object[] select(String[] nomColonne,String[] valeurs) throws SQLException,Exception
    {
        Connect conn=new Connect();
        Connection con=conn.getConnectionPostGresql();
        Statement stat = con.createStatement();
        ResultSet res=null;
        if(nomColonne==null || valeurs==null)
        {
            res = stat.executeQuery("select * from "+getClass().getSimpleName());
        }
        else
        {
            // System.out.println("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs));
            res = stat.executeQuery("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs));
        }

        Object[] listScore;
        
        listScore=tableToListScore(res);
        

        con.close();
        stat.close();
        
        return listScore;
    }

    public Object[] selectOrderby(String[] nomColonne,String[] valeurs,String order) throws SQLException,Exception
    {
        Connect conn=new Connect();
        Connection con=conn.getConnectionPostGresql();
        Statement stat = con.createStatement();
        ResultSet res=null;
        if(nomColonne==null || valeurs==null)
        {
            res = stat.executeQuery("select * from "+getClass().getSimpleName()+" order by "+order+" desc");
            // System.out.println("select * from "+getClass().getSimpleName()+" order by "+order+" desc");

        }
        else
        {
            // System.out.println("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs)+" order by "+order+" desc");
            res = stat.executeQuery("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs)+" order by "+order+" desc");
        }

        Object[] listScore;
        
        listScore=tableToListScore(res);
        

        con.close();
        stat.close();
        
        return listScore;
    }

    public Object[] selectOrderby2(String[] nomColonne,String[] valeurs,String order) throws SQLException,Exception
    {
        Connect conn=new Connect();
        Connection con=conn.getConnectionPostGresql();
        Statement stat = con.createStatement();
        ResultSet res=null;
        if(nomColonne==null || valeurs==null)
        {
            res = stat.executeQuery("select * from "+getClass().getSimpleName()+" order by "+order+" asc");
            // System.out.println("select * from "+getClass().getSimpleName()+" order by "+order+" asc");

        }
        else
        {
            // System.out.println("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs)+" order by "+order+" asc");
            res = stat.executeQuery("select * from "+getClass().getSimpleName()+" where "+getCondition(nomColonne,valeurs)+" order by "+order+" asc");
        }

        Object[] listScore;
        
        listScore=tableToListScore(res);
        

        con.close();
        stat.close();
        
        return listScore;
    }

    public String getCondition(String[] nomColonne,String[] valeurs) throws Exception
    {
        String valeur=new String();
        for(int i=0;i<nomColonne.length;i++)
        {
            if(i!=nomColonne.length-1)
            {
                valeur=valeur+getConditionForm(nomColonne[i],valeurs[i])+" and ";
            }
            else
            {
                valeur=valeur+getConditionForm(nomColonne[i],valeurs[i]);
            }
        }
        return valeur;
    }

    public String getConditionForm(String nomColonne,String valeur) throws Exception
    {
        String type=getType(nomColonne);
        if(type.equalsIgnoreCase("int"))
        {
            return nomColonne+"="+valeur;
        }
        if(type.equalsIgnoreCase("double"))
        {
            return nomColonne+"="+valeur;
        }
        if(type.equalsIgnoreCase("String"))
        {
            return nomColonne+" like '%"+valeur+"%'";
        }
        if(type.equalsIgnoreCase("date"))
        {
            return nomColonne+" = '"+valeur+"'";
        }
        throw new Exception("la colonne n'existe pas");
    }

    public String getType(String nomColonne) throws Exception
    {
        for(int i=0;i<getClass().getDeclaredFields().length;i++)
        {
            String type=getClass().getDeclaredFields()[i].getType().getSimpleName();
            String nomColonne1=getClass().getDeclaredFields()[i].getName();
            if(nomColonne1.equalsIgnoreCase(nomColonne))
                return type;
        }
        throw new Exception("la colonne n'existe pas");
    }

    public Object[] tableToListScore(ResultSet res) throws SQLException,Exception
    {
        Vector vec=new Vector();
        while(res.next())
        {
            Object score=getClass().newInstance();
            for(int i=0;i<getClass().getDeclaredFields().length;i++)
            {
                String m="set".concat(String.valueOf(getClass().getDeclaredFields()[i].getName().charAt(0)).toUpperCase().concat(getClass().getDeclaredFields()[i].getName().substring(1)));
                Method method=searchMethod(getClass().getMethods(),m);
                String type=getClass().getDeclaredFields()[i].getType().getSimpleName();
                String value=res.getString(i+1);
                method.invoke(score,getValue(type,value));    
            }
            vec.add(score);
        }

        Object[] listScore=new Object[vec.size()];

        for(int i=0;i<vec.size();i++)
        {
            listScore[i]=vec.get(i);
        }

        res.close();
        return listScore;
    }

    public Method searchMethod(Method[] listMethod,String name)
    {
        for(int i=0;i<listMethod.length;i++)
        {
            if(listMethod[i].getName().equalsIgnoreCase(name))
            {
                return listMethod[i];
            }
        }
        return null;
    }

    public Object[] getValue(String type,String value) throws ParseException
    {
        Object[] listeParmetre=new Object[1];
        if(type.equalsIgnoreCase("int"))
        {
            listeParmetre[0]= Integer.parseInt(value);
        }
        if(type.equalsIgnoreCase("double"))
        {
            listeParmetre[0]=Double.valueOf(value);
        }
        if(type.equalsIgnoreCase("String"))
        {
            listeParmetre[0]=value;
        }
        if(type.equalsIgnoreCase("date"))
        {
            Date datedenaissance=new SimpleDateFormat("yyyy-MM-dd").parse(value);
            listeParmetre[0]=datedenaissance;
        }
        return listeParmetre;
    }

    
    
    
    
    
    public void update(Connection c,String[] nomColonne,String[] valeurs,String condition) throws Exception
    {
        if(c==null)
        {
            setOuvert(true);
            Connect conn=new Connect();
            c=conn.getConnectionPostGresql();
        }
        Statement stat = c.createStatement();
        
        try 
        {
            if(condition!=null)
            {
                // System.out.println("update "+ getClass().getSimpleName()+ " set "+getValeurUpdate(nomColonne,valeurs)+" where "+getPrimaryKey()+"="+condition);
                stat.executeQuery("update "+ getClass().getSimpleName()+ " set "+getValeurUpdate(nomColonne,valeurs)+" where "+getPrimaryKey()+"="+condition);
            }
            else
            {
                // System.out.println("update "+ getClass().getSimpleName()+ " set "+getValeurUpdate(nomColonne,valeurs));
                stat.executeQuery("update "+ getClass().getSimpleName()+ " set "+getValeurUpdate(nomColonne,valeurs));
            }
//            if(getOuvert()==true)
//                c.commit();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
//            c.rollback(); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
//            c.rollback();
        }
        finally
        {
            if(getOuvert()==true)
            {
                c.close();
                setOuvert(false);
            }
            stat.close();
        }
    }

    public String getValeurUpdate(String[] nomColonne,String[] valeurs) throws Exception
    {
        String valeur=new String();
        for(int i=0;i<nomColonne.length;i++)
        {
            String type=getClass().getDeclaredFields()[i].getType().getSimpleName();
            if(i!=nomColonne.length-1)
            {
                valeur=valeur+nomColonne[i]+"="+getFormUpdate(type,valeurs[i])+",";
            }
            else
            {
                valeur=valeur+nomColonne[i]+"="+getFormUpdate(type,valeurs[i]);
            }
        }
        return valeur;
    }

    public String getFormUpdate(String type,String valeur) throws Exception
    {
        if(type.equalsIgnoreCase("int"))
        {
            return "'"+valeur+"'";
        }
        if(type.equalsIgnoreCase("double"))
        {
            return "'"+valeur+"'";
        }
        if(type.equalsIgnoreCase("String"))
        {
            return "'"+valeur+"'";
        }
        if(type.equalsIgnoreCase("date"))
        {
            return "'"+valeur+"'";
        }
        return null;
    }
}
    