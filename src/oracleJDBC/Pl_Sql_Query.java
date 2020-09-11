package oracleJDBC;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class Pl_Sql_Query {
    private Connection connection;
    private CallableStatement cst;

    public Pl_Sql_Query(String querySQL){
        try{
            connection = new JDBC().getConnection();
            cst = connection.prepareCall(querySQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public CallableStatement getCst() {
        return cst;
    }
}
