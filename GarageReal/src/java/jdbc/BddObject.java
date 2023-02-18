package jdbc;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.lang.Object;
import java.lang.Class;
import java.lang.reflect.Method;

import connexion.Connect;

public class BddObject {
    String primaryKey;
    String nomTable;
    boolean existTable;
    int nbZeroInitial = 4;
    String motif = getClass().getSimpleName().toUpperCase();//Convertit tous les caractères de cette chaîne en majuscules en utilisant 
                                                            //les règles des paramètres régionaux par défaut, qui sont renvoyés par Locale.getDefault. 
    
    public BddObject getBddObj(BddObject[] lo, String id) throws Exception {
        BddObject b = null;
        for (BddObject bddObject : lo) {
            Field pk = getFieldPk(bddObject);
            // Field pk = bddObject.getClass().getDeclaredField(primaryKey);
            pk.setAccessible(true);
            String idObj = pk.get(bddObject).toString();
            // System.out.println();
            if(idObj.equalsIgnoreCase(id) == true) {
                // System.out.println(idObj);
                return bddObject;
            } 
        }

        return b;
    }

    public String getCurrDate(Connection connection) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }
        
            try {     

                String requete = "select current_date from dual";

                System.out.println(requete);

                ResultSet result = stat.executeQuery(requete);

                result.next();

                String currDate = result.getString(1);

                return currDate;

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("La creation du sequence a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return "curr";
    }

    /*public String genValHisto(BddObject o) throws Exception {
        String val = "";

        if (nomTable == null) {
            nomTable = o.getClass().getSimpleName().toUpperCase();
        }

        String [] allCol = getAllColumnInTable(null, nomTable);
        Field[] lf = getFields2(allCol, o);
        // System.out.println("fields 2 azo");
        // lf[1].setAccessible(true);
        // System.out.println("field 1 val : "+lf[1].get(o));

        String separateur = " | ";

        Object[] valAttr = getValAttributs2(lf, o);
        // System.out.println("val attr azo");
        // System.out.println(valAttr[0]);

        for (int i = 0; i < valAttr.length; i++) {
            val += lf[i].getName() + " : " + valAttr[i].toString() + separateur ;
        }

        return val;
    }*/

    /*public String genValHisto() throws Exception {
        String val = "";

        if (nomTable == null) {
            nomTable = this.getClass().getSimpleName().toUpperCase();
        }

        String [] allCol = getAllColumnInTable(null, nomTable);
        Field[] lf = getFields(allCol);

        String separateur = " | ";

        Object[] valAttr = getValAttributs2(lf);

        for (int i = 0; i < valAttr.length; i++) {
            val += lf[i].getName() + " : " + valAttr[i].toString() + separateur ;
        }

        return val;
    }*/

    /*public BddObject() throws Exception {
        if(isExistInDB(null) == false) {
            create(null);
        }
    }*/

    public String createSeq() throws Exception {        //SEQUENCE
        if(motif == null) motif = getClass().getSimpleName().toUpperCase();
        int seqBrut = getSeqBrut(null);
        String strSeqBrut = String.valueOf(seqBrut);
        int tailleStrSeqBrut = strSeqBrut.length();
        int nbZero = nbZeroInitial - (tailleStrSeqBrut - 1);

        /*String zeros = "";
        for (int i = 0; i < nbZero; i++) {
            zeros += "0";
        }*/

        return strSeqBrut;
    }

    /*public void createSeqBrut(Connection connection) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }
        
            try {     

                String requete = "create sequence "+nomTable+"_SEQ start with 1";

                System.out.println(requete);

                int nbUp = stat.executeUpdate(requete);

                connection.commit();

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("La creation du sequence a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

    }*/

    public int getSeqBrut(Connection connection) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }
        
            try {     

                String requete = "select nextval("+nomTable+"seq)";

                System.out.println(requete);

                ResultSet result = stat.executeQuery(requete);

                result.next();

                int seq = result.getInt(1);

                return seq;

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("La creation du sequence a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return 0;
    }

    public Field[] getFieldsWithoutArray() throws Exception {
        Field[] lf = getClass().getDeclaredFields();
        if(lf.length == 0) throw new Exception("Cet objet n'a aucun attribut");
        Vector v = new Vector();
        for (Field field : lf) {
            if(field.getType().getSimpleName().contains("[]") == false) {
                v.add(field);
            }
        }
        Object[] fieldObj = v.toArray();
        Field [] rep = new Field[v.size()];
        for (int i = 0; i < fieldObj.length; i++) {
            rep[i] = (Field) fieldObj[i];
        }

        return rep;
    }

    public Field[] getFields2(String [] colonnes, BddObject o) throws Exception {
        Field[] rep = new Field[colonnes.length];
        for (int i = 0; i < colonnes.length; i++) {
            Field f = getFieldByColName(colonnes[i], o);
            rep[i] = f;
        }
        return rep;
    }

    public Field[] getFields(String [] colonnes) throws Exception {
        Field[] rep = new Field[colonnes.length];
        for (int i = 0; i < colonnes.length; i++) {
            Field f = getFieldByColName(colonnes[i]);
            rep[i] = f;
        }
        return rep;
    }

    public Field getFieldByColName(String colName, BddObject o) throws Exception {
        Field[] lf = o.getClass().getDeclaredFields();
        for (Field field : lf) {
            String fName = field.getName().toUpperCase();
            if(fName.equals(colName.toUpperCase()) == true) {
                return field;
            }
        }
        
        return null;
    }

    public Field getFieldByColName(String colName) throws Exception {
        Field[] lf = getClass().getDeclaredFields();
        for (Field field : lf) {
            String fName = field.getName().toUpperCase();
            if(fName.equals(colName.toUpperCase()) == true) {
                return field;
            }
        }
        
        return null;
    }

    public Field getFieldPk(BddObject o) throws Exception {
        Field [] lf = o.getClass().getDeclaredFields();
        for (Field field : lf) {
            if(field.getName().equalsIgnoreCase(primaryKey) == true) {
                return field;
            }
        }
        return null;
    }

    /*public Field getFieldPk() throws Exception {
        Field [] lf = getClass().getDeclaredFields();
        for (Field field : lf) {
            if(field.getName().equalsIgnoreCase(primaryKey) == true) {
                return field;
            }
        }
        return null;
    }*/

    public Object[] getValAttributs(String [] colonnes) throws Exception {
        if(colonnes.length <= 0) throw new Exception("Colonnes vides getValAttributs");
        
        Vector v = new Vector();

        for (int i = 0; i < colonnes.length; i++) {
            Field f = getFieldByColName(colonnes[i]);
            f.setAccessible(true);
            if(f.getName().equalsIgnoreCase(primaryKey) == true) {
                String seq = createSeq();
                f.set(this, seq);
            }
            Object val = f.get(this);
            v.add(val);
        }

        return v.toArray();
    }

    /*public String constrSelection(String [] colonnes)throws Exception {
        int k = 0;
        String c = "";
        for (int i = 0; i < colonnes.length - 1; i++) { //le farany tsy misy virgule
            c = c + colonnes[i] + ",";
            k++;
        }

        c = c + colonnes[k];
        return c;
    }*/

    public String [] getAllColumnInTable(Connection connection, String nomTable) throws Exception {

        String [] rep = null;

        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }
            else {
                nomTable = nomTable.toUpperCase();
            }

            try {

                String requete = "Select column_name from information_schema.columns where table_name = '"+nomTable+"'";

                // System.out.println(requete);

                ResultSet result = stat.executeQuery(requete);
                

                Vector colVect = new Vector();

                while(result.next()) {
                    System.out.println("Colonne : "+result.getString(1));
                    colVect.add(result.getString(1));
                }

                Object[] colObj = colVect.toArray();
                rep = new String[colObj.length];
                for (int i = 0; i < colObj.length; i++) {
                    // System.out.println(colObj[i]);
                    rep[i] = (String) colObj[i];
                }
                System.out.println("");
                return rep;
                

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("L'insertion du nouveau nom colonne a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return rep;
    }

    public boolean isType(Field field,String type) throws Exception {

        if(field.getType().getSimpleName().equalsIgnoreCase(type) == true) return true;

        return false;

    }

    /*public void create(Connection connection) throws Exception {

        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            //ex: create table Employe (numero NUMBER(4),nom VARCHAR(12),salaire NUMBER(10));
            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }

            try {

                if(isExistInDB(connection) == false) {
                    String colonnes = "";
                    int ind = 0;
                    // Field[] field = getClass().getDeclaredFields();
                    Field[] field = getFieldsWithoutArray();
                    for (int i=0; i<field.length - 1; i++) { //car ny farany tsy misy virgule
                        // if(field[i].getType().getSimpleName().contains("[]") == false) {
                            field[i].setAccessible(true);
                            if(isType(field[i], "string") == true || isType(field[i], "boolean") == true) {
                                colonnes += field[i].getName() + " " + " VARCHAR(40), ";
                            }
                            else if (isType(field[i], "date") == true) {
                                colonnes += field[i].getName() + " " + " VARCHAR(10), ";
                            }
                            else if(isType(field[i], "int") == true || isType(field[i], "double") == true || isType(field[i], "float") == true) {
                                colonnes += field[i].getName() + " " + " NUMBER(5), ";
                            }
                            else {
                                colonnes += field[i].getName() + " " + " VARCHAR(40), ";
                            }
                        // }
                        ind++;
                    }
        
                    field[ind].setAccessible(true);
                    if(isType(field[ind], "string") == true || isType(field[ind], "boolean") == true) {
                        colonnes += field[ind].getName() + " " + " VARCHAR(40) ";
                    }
                    else if (isType(field[ind], "date") == true) {
                        colonnes += field[ind].getName() + " " + " VARCHAR(10) ";
                    }
                    else if(isType(field[ind], "int") == true || isType(field[ind], "double") == true || isType(field[ind], "float") == true) {
                        colonnes += field[ind].getName() + " " + " NUMBER(5) ";
                    }
                    else {
                        colonnes += field[ind].getName() + " " + " VARCHAR(40) ";
                    }

                    String requete = "create table "+nomTable+" ("+colonnes+")";

                    System.out.println(requete);

                    int nbUp = stat.executeUpdate(requete);

                    connection.commit();

                    System.out.println("table "+nomTable+" creee avec succes");

                    if(primaryKey == null) {
                        //Attribut 1 par defaut pk
                        Field attribut1 = getClass().getDeclaredFields()[0];
                        primaryKey = attribut1.getName();
                    }
                    
                    //alter table Employe ADD CONSTRAINT Employe_pk PRIMARY KEY (nom); ajout du PK
                    String pkReq = "alter table "+nomTable+" ADD CONSTRAINT "+nomTable+"_pk PRIMARY KEY ("+primaryKey+")";
                    
                    int nbPk = stat.executeUpdate(pkReq);

                    connection.commit();
                    
                    System.out.println(pkReq);

                    System.out.println("Success !");

                    System.out.println("pk : "+primaryKey);
                    ///Creation de sequence 
                    createSeqBrut(connection);
                    
                }
        
                else {
                    throw new Exception("Table "+nomTable+" deja creee");
                }

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("L'insertion du nouveau BddObject a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

    }*/

    /*public boolean isExistInDB(Connection connection) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName().toUpperCase();
            }

            try {

                String requete = "SELECT COUNT(TABLE_NAME) FROM USER_TABLES WHERE TABLE_NAME = '"+nomTable+"'";

                // System.out.println(requete);

                ResultSet result = stat.executeQuery(requete);

                result.next();

                int nb = result.getInt(1);

                if(nb > 0) return true;

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("L'insertion du nouveau BddObject a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return false;
    }*/

    public void desc() throws Exception {       ///////////Affichage
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
    //********************************************************************************** */
    
    /*public static void viewAll(BddObject [] lo) throws Exception {
        if(lo.length > 0) {
            System.out.println(lo[0].getClass().getSimpleName()+" : ");
            Field[] lf = lo[0].getClass().getDeclaredFields();
            for (Field field : lf) {
                System.out.print("| "+field.getName()+" |");   
            }
    
            for (BddObject obj : lo) {
                Field [] lfo = obj.getClass().getDeclaredFields();
                System.out.println();
                for (Field field : lfo) {
                    field.setAccessible(true);
                    if(field.getType().getSimpleName().contains("[]") == true) {
                        System.out.print("| "+" array "+" |");
                    }  
                    else if(field.get(obj) == null) {
                        System.out.print("| "+" null "+" |");
                    } 
                    else { 
                        System.out.print("| "+field.get(obj).toString()+" |"); 
                    }
                }
            }
        }

        else {
            throw new Exception("BddObject [] vide");
        }
    }*/

    /*public void insertWithOrdre(Connection connection, String[] nomColonnes, Object[] valAttr) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName();
            }

            Field[] attributs = getClass().getDeclaredFields();

            String colonnes = ""; // colonne 1, colonne 2,....,colonne n
            String values = ""; // attribut 1,attribut 2,....,attribut n
            int j = 0;
            for (int i = 0; i < valAttr.length - 1; i++) {

                colonnes = colonnes + nomColonnes[i] + ", ";

                // if(attributs[i].getType().getSimpleName().equalsIgnoreCase("string") || attributs[i].getType().getSimpleName().equalsIgnoreCase("date")){
                //     values = values +" '"+ valAttr[i].toString() +"' "+ ",";// 'string' -> entre 2 quotes
                // }
                // else {
                    values = values + valAttr[i].toString() + ",";
                // }
                j++;
            }

            colonnes = colonnes + nomColonnes[j];

            // if(attributs[j].getType().getSimpleName().equalsIgnoreCase("string") || attributs[j].getType().getSimpleName().equalsIgnoreCase("date")){
            //     values = values +" '"+ valAttr[j].toString() +"' ";
            // }
            // else {
                values = values + valAttr[j].toString(); // attribut n tsy misy "," ny farany
            // }

            try {

                String requete = "insert into " + nomTable +"  ("+colonnes+")  values (" + values + ")";

                System.out.println(requete);

                int nbUp = stat.executeUpdate(requete);

                connection.commit();

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("L'insertion du nouveau BddObject a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }
    }*/

    public BddObject [] select2 (Connection connection) throws Exception{
        boolean isOpened = false;
        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName();
            }

            Field[] attributs = substituteFieldNullValue();
            Object[] valAttr = getValAttributs2(attributs);
            String selection = "";
            int j = 0;
 
            for (int i = 0; i < attributs.length - 1; i++) {
                if(valAttr[i] != null) {
                    selection = selection + attributs[i].getName()+" LIKE '"+valAttr[i].toString()+"' AND "; //avec 2 quotes ''
                }
                j++;
            }

            if(valAttr[j] != null) { // sans , au dernier
                selection = selection + attributs[j].getName()+" LIKE '"+valAttr[j].toString()+"'"; //avec 2 quotes ''
            }

            try {

                String requete = "select * from "+nomTable+" where "+selection+" order by "+primaryKey;

                if(selection == "") {
                    requete = "select * from "+nomTable+" order by "+primaryKey;
                }

                System.out.println(requete);

                Vector bddObjVect = new Vector();

                ResultSet resultSet = stat.executeQuery(requete);

                // Field[] colonnes = getClass().getDeclaredFields();
                String [] col = getAllColumnInTable(connection, nomTable);

                //Object[] valAttr = getValAttributs(col);
                Field[] colonnes = getFields(col);

                Constructor constructor = getClass().getConstructor();

                while(resultSet.next()){

                    BddObject bddObj = (BddObject) constructor.newInstance();

                    for (Field field : colonnes) {
                        field.setAccessible(true);

                        int indColonne = resultSet.findColumn(field.getName());

                        if(field.getType().getSimpleName().equalsIgnoreCase("Integer")) {
                            Integer integerValue = resultSet.getInt(indColonne);
                            field.set(bddObj, integerValue);
                        }
                        else if(field.getType().getSimpleName().equalsIgnoreCase("double")) {
                            Double doubleValue = resultSet.getDouble(indColonne);
                            field.set(bddObj, doubleValue);
                        }

                        else if(field.getType().getSimpleName().equalsIgnoreCase("float")) {
                            Float floatValue = resultSet.getFloat(indColonne);
                            field.set(bddObj, floatValue);
                        }

                        else if(field.getType().getSimpleName().equalsIgnoreCase("date")) {
                            Date dateValue = resultSet.getDate(indColonne);
                            field.set(bddObj, dateValue);
                        }

                        else if(field.getType().getSimpleName().equalsIgnoreCase("boolean")) {
                            Boolean boolValue = Boolean.parseBoolean(resultSet.getString(indColonne));
                            field.set(bddObj ,boolValue);
                        }

                        else { // type String
                            String stringValue = resultSet.getString(indColonne);
                            field.set(bddObj, stringValue);
                        }
                    }

                    bddObjVect.add(bddObj);
                }

                BddObject[] rep = vectToBddObjects(bddObjVect);

                return rep;

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("La recuperation des "+getClass().getSimpleName()+" dans "+nomTable+" a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return new BddObject[0];
    }

    public BddObject [] select (Connection connection) throws Exception{
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName();
            }

            Field[] attributs = substituteFieldNullValue();
            Object[] valAttr = getValAttributs2(attributs);
            String selection = "";
            int j = 0;
 
            /*for (int i = 0; i < attributs.length - 1; i++) {
                if(valAttr[i] != null) {
                    selection = selection + attributs[i].getName()+" LIKE '"+valAttr[i].toString()+"' AND "; //avec 2 quotes ''
                }
                j++;
            }

            if(valAttr[j] != null) { // sans , au dernier
                selection = selection + attributs[j].getName()+" LIKE '"+valAttr[j].toString()+"'"; //avec 2 quotes ''
            }*/

            try {

                String requete = "select * from "+nomTable+" where "+selection;

                if(selection == "") {
                    requete = "select * from "+nomTable;//+" order by "+primaryKey;
                }

                System.out.println(requete);

                Vector bddObjVect = new Vector();

                ResultSet resultSet = stat.executeQuery(requete);

                Field[] colonnes = getClass().getDeclaredFields();

                Constructor constructor = getClass().getConstructor();

                while(resultSet.next()){

                    BddObject bddObj = (BddObject) constructor.newInstance();

                    for (Field field : colonnes) {
                        field.setAccessible(true);

                        int indColonne = resultSet.findColumn(field.getName());

                        if(field.getType().getSimpleName().equalsIgnoreCase("Integer")) {
                            Integer integerValue = resultSet.getInt(indColonne);
                            field.set(bddObj, integerValue);
                        }
                        else if(field.getType().getSimpleName().equalsIgnoreCase("double")) {
                            Double doubleValue = resultSet.getDouble(indColonne);
                            field.set(bddObj, doubleValue);
                        }

                        else if(field.getType().getSimpleName().equalsIgnoreCase("date")) {
                            Date dateValue = resultSet.getDate(indColonne);
                            field.set(bddObj, dateValue);
                        }

                        else if(field.getType().getSimpleName().equalsIgnoreCase("boolean")) {
                            Boolean boolValue = Boolean.parseBoolean(resultSet.getString(indColonne));
                            field.set(bddObj ,boolValue);
                        }

                        else { // type String
                            String stringValue = resultSet.getString(indColonne);
                            field.set(bddObj, stringValue);
                        }
                    }

                    bddObjVect.add(bddObj);
                }

                BddObject[] rep = vectToBddObjects(bddObjVect);

                return rep;

            }

            catch (Exception e) {

                //connection.rollback();

                System.out.println("La recuperation des "+getClass().getSimpleName()+" dans "+nomTable+" a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

        return new BddObject[0];
    }


    public BddObject [] vectToBddObjects(Vector v) throws Exception {
        if(v.size() == 0) throw new Exception("Vector null de size 0 BddObject.java vectToBddObjects");

        BddObject[] rep = new BddObject[v.size()];
        for (int i = 0; i < v.size(); i++) {
            rep[i] = (BddObject) v.get(i);
        }

        return rep;
    }

    //***********INSERT*************************** */
    public void insert(Connection connection) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            
            
            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName();
            }

             //Object[] valAttr = getValAttributs();
             //Field[] attributs = getClass().getDeclaredFields();

            //String [] colonnes = getAllColumnInTable(connection, nomTable);

            //Object[] valAttr = getValAttributs2(attributs);
            Object[] valAttr = getValAttributs();
            Field[] attributs = getClass().getDeclaredFields();

            //Field[] attributs = getFields(colonnes);
            //Object[] valAttr = getValAttributs2(attributs);


            String values = ""; // attribut 1,attribut 2,....,attribut n
            int j = 0;
            for (int i = 0; i < valAttr.length - 1; i++) {
                if(attributs[i].getType().getSimpleName().equalsIgnoreCase("String") || attributs[i].getType().getSimpleName().equalsIgnoreCase("date") || attributs[i].getType().getSimpleName().equalsIgnoreCase("boolean")){
                    values = values +" '"+ valAttr[i].toString() +"' "+ ",";// 'string' -> entre 2 quotes
                }
                else {
                    values = values + valAttr[i].toString() + ",";
                }
                j++;
            }

            if(attributs[j].getType().getSimpleName().equalsIgnoreCase("String") || attributs[j].getType().getSimpleName().equalsIgnoreCase("date") || attributs[j].getType().getSimpleName().equalsIgnoreCase("boolean")){
                values = values +" '"+ valAttr[j].toString() +"' ";
            }
            else {
                values = values + valAttr[j].toString(); // attribut n tsy misy "," ny farany
            }

            try {


                ///////HISTORIQUE
                /*if(nomTable.equalsIgnoreCase("HIST") == false) {
                    String valHisto = genValHisto();
                    Field pk = getFieldPk();
                    // Field pk = getClass().getField(primaryKey);
                    pk.setAccessible(true);
                    Object valPk = pk.get(this);
                    String idBddObject = valPk.toString();
                    // System.out.println("id Obj "+ idBddObject);
     
                    //HIST h = new HIST(nomTable, valHisto, "INSERT", idBddObject, getCurrDate(connection));
                    //h.insert(connection);

                    System.out.println("insertion historique succes INSERT");
                }*/
                /////////

                String requete = "insert into " + nomTable + " values (" + values + ")";

                
                 //int nbUp = 
                         stat.executeUpdate(requete);
                //Boolean nbUp = stat.execute("insert into genre values( 3,'dada')");
                System.out.println(requete);
                //connection.commit();

            }

            catch (Exception e) {

                //connection.rollback();

                System.out.println("L'insertion du nouveau BddObject a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }
    }

    public Field[] substituteFieldNullValue() throws Exception {
        // Field[] lf = getClass().getDeclaredFields();
        Field[] lf = getFieldsWithoutArray();
        int j = 0;
        for (int i = 0; i < lf.length; i++) {
            lf[i].setAccessible(true);
            if(lf[i].get(this) == null) {
                Field temp = lf[j];
                lf[j] = lf[i];
                lf[i] = temp;
                j++;
            }
        }
        return lf;
    }

    //****************UPDATE*****************************************
    public void update(Connection connection, String pkValue) throws Exception {
        boolean isOpened = false;

        try {
            if (connection == null) {

                isOpened = true;

                Connect connexion = new Connect();

                connection = connexion.getConnectionPostGresql();

            }

            Statement stat = connection.createStatement();

            if (nomTable == null) {
                nomTable = this.getClass().getSimpleName();
            }
        
            // get value of primary key
            // Field pk = getClass().getDeclaredField(primaryKey);
            // pk.setAccessible(true);
            // //String pkValue = pk.get(this).toString();

            Field[] attributs = substituteFieldNullValue();
            Object[] valAttr = getValAttributs2(attributs);
            String sets = "";
            int j = 0;
 
            for (int i = 0; i < attributs.length - 1; i++) {
                if(valAttr[i] != null) {
                    if(attributs[i].getType().getSimpleName().equalsIgnoreCase("string") || attributs[i].getType().getSimpleName().equalsIgnoreCase("date")){
                        sets = sets + attributs[i].getName()+" = '"+valAttr[i].toString()+"' ,"; //avec 2 quotes ''
                    }
                    else {
                        sets = sets + attributs[i].getName()+" = "+valAttr[i].toString()+" ,";
                    }
                }
                j++;
            }

            if(valAttr[j] != null) { // sans , au dernier
                if(attributs[j].getType().getSimpleName().equalsIgnoreCase("string") || attributs[j].getType().getSimpleName().equalsIgnoreCase("date")){
                    sets = sets + attributs[j].getName()+" = '"+valAttr[j].toString()+"'"; //avec 2 quotes ''
                }
                else {
                    sets = sets + attributs[j].getName()+" = "+valAttr[j].toString();
                }
            }

            try {

                ///////HISTORIQUE
                if(nomTable.equalsIgnoreCase("HIST") == false) {
                    BddObject temp = getClass().getConstructor().newInstance();
                    BddObject[] objList = temp.select(connection);
                    // System.out.println("eeeto");
                    BddObject oldObj = getBddObj(objList, pkValue);
                    
                    //String valHisto = genValHisto(oldObj);
                    // System.out.println("val histo azo");
                    // Field pk = getFieldPk(oldObj);
                    // // Field pk = getClass().getField(primaryKey);
                    // pk.setAccessible(true);
                    // Object valPk = pk.get(this);
                    // String idBddObject = valPk.toString();
                    // System.out.println("id Obj "+ idBddObject);
     
                    //HIST h = new HIST(nomTable, valHisto, "UPDATE", pkValue, getCurrDate(connection));
                    //h.insert(connection);

                    System.out.println("insertion historique succes UPDATE");
                }
                /////////

                // exemple : update Employe set numero = 2, nom = 'Jean' where nom = 'Harena';

                Field pk = getClass().getDeclaredField(primaryKey);

                if(pk.getType().getSimpleName().equalsIgnoreCase("string") || pk.getType().getSimpleName().equalsIgnoreCase("date") || pk.getType().getSimpleName().equalsIgnoreCase("boolean")){
                    pkValue = "'"+pkValue+"'"; //mettre '' sur pkValue
                }                

                String requete = "update "+nomTable+" set "+sets+" where "+primaryKey+" = "+pkValue;

                System.out.println(requete);

                int nbUp = stat.executeUpdate(requete);

                connection.commit();

            }

            catch (Exception e) {

                connection.rollback();

                System.out.println("L'insertion du nouveau BddObject a echoue");

                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }

        finally {
            if (isOpened == true) {
                connection.close();
            }
        }

    }

    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Object[] getValAttributs2(Field[] attributs, BddObject o) throws Exception {
        Object[] valAttr = new Object[attributs.length];
        // System.out.println("heeeey "+attributs[0].getName());
        for (int i = 0; i < attributs.length; i++) {
            attributs[i].setAccessible(true);
            valAttr[i] = attributs[i].get(o);
            // System.out.println("valeur de "+attributs[i].getName()+ " = "+valAttr[i].toString());
        }

        return valAttr;
    }

    public Object[] getValAttributs2(Field[] attributs) throws Exception {
        Object[] valAttr = new Object[attributs.length];
        // System.out.println("heeeey "+attributs[0].getName());
        for (int i = 0; i < attributs.length; i++) {
            attributs[i].setAccessible(true);
            valAttr[i] = attributs[i].get(this);
            // System.out.println("valeur de "+attributs[i].getName()+ " = "+valAttr[i].toString());
        }

        return valAttr;
    }

    public Object[] getValAttributs() throws Exception {

        Field[] attributs = getClass().getDeclaredFields();
        Object[] valAttr = new Object[attributs.length];
        for (int i = 0; i < attributs.length; i++) {
            attributs[i].setAccessible(true);
            valAttr[i] = attributs[i].get(this);
            // System.out.println("valeur de "+attributs[i].getName()+ " = "+valAttr[i].toString());
        }

        return valAttr;
    }
}