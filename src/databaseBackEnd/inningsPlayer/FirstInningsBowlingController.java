package databaseBackEnd.inningsPlayer;

import databaseBackEnd.Insert1stInningsController;
import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import oracleJDBC.Pl_Sql_Query;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.ResourceBundle;

public class FirstInningsBowlingController implements Initializable {
    @FXML
    private AnchorPane aPane;

    private void loadFXML(String packageName, String FXML){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    private void populateComboBox(ComboBox <String> comboBox){

        String query = "{call FETCH_FTEAM_PLAYER(?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);

        try{
            plSqlQuery.getCst().setString(1, Insert1stInningsController.f_1_InningsID);
            plSqlQuery.getCst().registerOutParameter(2, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().executeUpdate();
            Array array = plSqlQuery.getCst().getArray(2);
            String [] playerList = (String []) array.getArray();

            if(comboBox.getId().equals("wicketKeeperName")){
                for(String string : playerList){
                    String wkQuery = "{call get_playing_role (?,?,?)}";
                    Pl_Sql_Query plSqlQuery1 = new Pl_Sql_Query(wkQuery);

                    plSqlQuery1.getCst().setString(1,string);
                    plSqlQuery1.getCst().registerOutParameter(2,Types.VARCHAR);
                    plSqlQuery1.getCst().registerOutParameter(3,Types.VARCHAR);
                    plSqlQuery1.getCst().executeUpdate();
                    String pRole = plSqlQuery1.getCst().getString(2);

                    if(pRole.equals("Wicket-Keeper")){
                        comboBox.getItems().addAll(string);
                    }
                }
            } else{
                for(String string : playerList){
                    String wkQuery = "{call get_playing_role (?,?,?)}";
                    Pl_Sql_Query plSqlQuery1 = new Pl_Sql_Query(wkQuery);

                    plSqlQuery1.getCst().setString(1,string);
                    plSqlQuery1.getCst().registerOutParameter(2,Types.VARCHAR);
                    plSqlQuery1.getCst().registerOutParameter(3,Types.VARCHAR);
                    plSqlQuery1.getCst().executeUpdate();
                    String pRole = plSqlQuery1.getCst().getString(2);
                    String bowlStyle = plSqlQuery1.getCst().getString(3);

                    if(!pRole.equals("Wicket-Keeper") && bowlStyle != null){
                        comboBox.getItems().addAll(string);
                    }
                }
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void populateAllComboBox(){
        for (Node node : aPane.getChildren()) {
            //System.out.println("Id: " + node.getId());
            if (node instanceof ComboBox) {
                populateComboBox((ComboBox) node);
            }
        }
    }

    private String [][] getPlayerData(){
        String [][] playerInfo = new String[11][];

        for(int i = 0 ; i < 11 ; i++){
            if(i == 10){
                playerInfo[i] = new String[3];
            } else{
                playerInfo[i] = new String[5];
            }
        }

        int i = 0;
        int row,col;

        for (Node node : aPane.getChildren()) {

            row = i / 5;
            col = i % 5;
            i++;

            if(row == 11) break;

            if (node instanceof ComboBox) {
                if(((ComboBox) node).getValue() != null){
                    playerInfo[row][col] = ((ComboBox) node).getValue().toString();
                } else{
                    playerInfo[row][col] = "";
                }
            } else if(node instanceof TextField){
                if(((TextField) node).getText() != null){
                    playerInfo[row][col] = ((TextField) node).getText();
                } else{
                    playerInfo[row][col] = "";
                }
            }
        }
        return playerInfo;
    }

    private void insertIntoDatabase(){

        String [][] playerInfo = getPlayerData();

        for(int i = 0 ; i < playerInfo.length ; i++) {
            boolean flag = true;

            for (int j = 0; j < playerInfo[i].length; j++) {
                if (playerInfo[i][j].equals("")) {
                    flag = false;
                    break;
                }
            }

            try {
                if (flag && i != 10) {
                    // insert this player to database
                    String query = "{call INSERT_BOIP_B(?,?,?,?,?,?)}";
                    Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
                    plSqlQuery.getCst().setString(1, Insert1stInningsController.f_1_InningsID);
                    for (int j = 0; j < playerInfo[i].length; j++) {
                        plSqlQuery.getCst().setString(j + 2,playerInfo[i][j]);
                    }
                    plSqlQuery.getCst().executeUpdate();
                }
                else if (flag) {
                    String query = "{call INSERT_BOIP_WK(?,?,?,?)}";
                    Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
                    plSqlQuery.getCst().setString(1, Insert1stInningsController.f_1_InningsID);
                    for (int j = 0; j < playerInfo[i].length; j++) {
                        plSqlQuery.getCst().setString(j + 2,playerInfo[i][j]);
                    }
                    plSqlQuery.getCst().executeUpdate();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateAllComboBox();
    }

    @FXML
    void nextPage(ActionEvent event) {
        Optional <ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Next Page")
                .showAndWait();
        if(result.isPresent() && result.get().getText().equals("Yes")){
            insertIntoDatabase();
            loadFXML("databaseBackEnd/inningsPlayer","SecondInningsBatting");
        }
    }
}
