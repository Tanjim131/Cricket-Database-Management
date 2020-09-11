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
import javafx.scene.control.*;
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

public class SecondInningsBattingController implements Initializable{

    @FXML
    private AnchorPane aPane;

    @FXML
    private Label headline;

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

        if(comboBox.getId().endsWith("Out")){
            comboBox.getItems().addAll("Yes","No");
            return;
        }

        String query = "{call FETCH_BTEAM_PLAYER(?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try{
            plSqlQuery.getCst().setString(1, Insert1stInningsController.f_2_InningsID);
            plSqlQuery.getCst().registerOutParameter(2, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().executeUpdate();
            Array array = plSqlQuery.getCst().getArray(2);
            String [] playerList = (String []) array.getArray();

            for(String string : playerList){
                comboBox.getItems().addAll(string);
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

    private void insertIntoDatabase(){

        String [][] playerInfo = getPlayerData();

        for(int i = 0 ; i < playerInfo.length ; i++){
            boolean flag = true;

            for(int j = 0 ; j < playerInfo[i].length ; j++){
                if(playerInfo[i][j].equals("")){
                    flag = false;
                    break;
                }
            }

            if(flag){
                // insert this player to database
                String query = "{call INSERT_BAIP(?,?,?,?,?,?,?)}";
                Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
                try{
                    plSqlQuery.getCst().setString(1,Insert1stInningsController.f_2_InningsID);
                    for(int j = 0 ; j < playerInfo[i].length ; j++){
                        if(playerInfo[i][j].equals("Yes")){
                            plSqlQuery.getCst().setString(j + 2,"1");
                        } else if(playerInfo[i][j].equals("No")){
                            plSqlQuery.getCst().setString(j + 2,"0");
                        } else{
                            plSqlQuery.getCst().setString(j + 2,playerInfo[i][j]);
                        }
                    }
                    plSqlQuery.getCst().executeUpdate();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateAllComboBox();
    }

    private String [][] getPlayerData(){
        String [][] playerInfo = new String[11][6];

        int i = 0;
        int row,col;

        for (Node node : aPane.getChildren()) {

            row = i / 6;
            col = i % 6;
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

    @FXML
    void nextPage(ActionEvent event) {
        Optional <ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Next Page")
                .showAndWait();
        if(result.isPresent() && result.get().getText().equals("Yes")){
            insertIntoDatabase();
            loadFXML("databaseBackEnd/inningsPlayer","SecondInningsBowling");
        }
    }
}
