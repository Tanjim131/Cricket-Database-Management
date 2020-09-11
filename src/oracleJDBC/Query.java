package oracleJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    private Connection connection;
    private PreparedStatement pst;
    private String querySQL;

    public Query(String querySQL){
        this.querySQL = querySQL;
        try {
            connection = new JDBC().getConnection();
            pst = connection.prepareStatement(querySQL);
            // how do I call closeConnection? or pst.close?
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet querySet(){
        ResultSet resultSet = null;
        try {
            resultSet = pst.executeQuery(this.querySQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
