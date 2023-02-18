package connexion;

import java.sql.*;

public class Connect {

    public Connection getConnectionPostGresql() throws Exception {
        try {
            // Class dbDriver = Class.forName("org.postgresql.Driver");
            Class.forName("org.postgresql.Driver");
            String jdbcURL = "jdbc:postgresql://localhost:5432/garage";
            Connection con = DriverManager.getConnection(jdbcURL, "fyfy", "0000");

            con.setAutoCommit(true);

            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("Connexion postgres interrompue");

    }

}
