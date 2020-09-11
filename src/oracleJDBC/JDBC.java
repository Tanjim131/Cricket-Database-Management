package oracleJDBC;

import java.sql.*;

public class JDBC {

    private String username;
    private String password;
    private final String CONN_STRING = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private Connection connection;

    public JDBC(){
        this.username = "Cricket_Database_Management";
        this.password = "CSE216";
    }

    public Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(CONN_STRING,username,password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public String getCONN_STRING() {
        return CONN_STRING;
    }
}
