package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    private static final String connString = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String user = "etapa3";
    private static final String pass = "etapa3";

    private static Connection conn = null;

    private DatabaseConfiguration(){

    }

    public static Connection getDatabaseInstance(){
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(connString, user, pass);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeDB(){
        try{
            if(conn != null && !conn.isClosed()){
                conn.close();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


}
