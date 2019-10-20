package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private static final String USERNAME="dbuser";
    private static final String PASSWORD="dbpassword";
    private static final String CONN="jdbc:sqlite://localhost/login";
    private static final String  SQCONNN = "jdbc:sqlite:schoolsystem.sqlite";

    public static Connection getConnection() throws SQLException{
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONNN);
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
