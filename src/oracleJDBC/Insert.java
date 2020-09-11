package oracleJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Insert {

    public static final String PLAYER_SQL = "INSERT INTO Player (Country_id,gender,playing_role,batting_style,bowling_style,bdate,test_debut,odi_debut,t20_debut,first_name,last_name) values(?,?,?,?,?,?,?,?,?,?,?)";
    public static final String COUNTRY_SQL = "insert into Country (Country_Name) values(?)";
    public static final String EMP_SQL = "insert into employee (empName,empPassword) values (?,?)";
    public static final String TOURNAMENT_SQL = "insert into TOURNAMENT (tournament_name,start_date,end_date,tournament_type,number_of_teams,winner,runner_up) values (?,?,?,?,?,?,?)";

    private PreparedStatement pst;
    private Connection connection;

    public Insert(String SQL){
        try {
            connection = new JDBC().getConnection();
            pst = connection.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertTable(ArrayList <String> Info){
        try {
            for (int i = 1 ; i <= Info.size() ; i++){
                pst.setString(i, Info.get(i - 1));
            }
            pst.executeUpdate();
            pst.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
