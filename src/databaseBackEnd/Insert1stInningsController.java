package databaseBackEnd;

import com.jfoenix.controls.JFXComboBox;
import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import oracleJDBC.Pl_Sql_Query;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.ResourceBundle;

public class Insert1stInningsController implements Initializable {

    public static String f_1_InningsID;
    public static String f_2_InningsID;

    @FXML
    private TextField secondTeamWides;

    @FXML
    private JFXComboBox <String> firstTeamBat;

    @FXML
    private TextField firstTeamRuns;

    @FXML
    private TextField firstTeamWickets;

    @FXML
    private TextField firstTeamOvers;

    @FXML
    private TextField firstTeamFours;

    @FXML
    private TextField firstTeamSixes;

    @FXML
    private TextField firstTeamNoBalls;

    @FXML
    private TextField firstTeamWides;

    @FXML
    private JFXComboBox <String> secondTeamBat;

    @FXML
    private TextField secondTeamRuns;

    @FXML
    private TextField secondTeamWickets;

    @FXML
    private TextField secondTeamOvers;

    @FXML
    private TextField secondTeamFours;

    @FXML
    private TextField secondTeamSixes;

    @FXML
    private TextField secondTeamNoBalls;

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

    private void populateCountryComboxBox(JFXComboBox <String> jfxComboBox){
        String query = "{call getTeamNames(?,?,?)}";
        String tA = null,tB = null;
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try{
            System.out.println(InsertNewMatchController.matchID);

            plSqlQuery.getCst().setString(1,InsertNewMatchController.matchID);
            plSqlQuery.getCst().registerOutParameter(2,Types.VARCHAR);
            plSqlQuery.getCst().registerOutParameter(3,Types.VARCHAR);

            plSqlQuery.getCst().executeUpdate();

            tA = plSqlQuery.getCst().getString(2);
            tB = plSqlQuery.getCst().getString(3);

            jfxComboBox.getItems().addAll(tA,tB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstTeamBat.setEditable(true);
        secondTeamBat.setEditable(true);

        populateCountryComboxBox(firstTeamBat);
        populateCountryComboxBox(secondTeamBat);
    }

    private void insertIntoDatabase(){
        String query = "{call INSERT_INNINGS(?,?,?,?,?,?,?,?,?,?,?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);

        try{
            // insert 1st innings of 1st team
            plSqlQuery.getCst().setString(1,"1st");
            plSqlQuery.getCst().setString(2,InsertNewMatchController.matchID);
            plSqlQuery.getCst().setString(3,firstTeamBat.getValue());
            plSqlQuery.getCst().setString(4,secondTeamBat.getValue());
            plSqlQuery.getCst().setString(5,firstTeamRuns.getText());
            plSqlQuery.getCst().setString(6,firstTeamWickets.getText());
            plSqlQuery.getCst().setString(7,firstTeamOvers.getText());
            plSqlQuery.getCst().setString(8,firstTeamFours.getText());
            plSqlQuery.getCst().setString(9,firstTeamSixes.getText());
            plSqlQuery.getCst().setString(10,firstTeamNoBalls.getText());
            plSqlQuery.getCst().setString(11,firstTeamWides.getText());
            plSqlQuery.getCst().registerOutParameter(12, Types.VARCHAR);

            plSqlQuery.getCst().executeUpdate();
            f_1_InningsID = plSqlQuery.getCst().getString(12);

            // insert 1st innings of 2nd team

            plSqlQuery.getCst().setString(1,"1st");
            plSqlQuery.getCst().setString(2,InsertNewMatchController.matchID);
            plSqlQuery.getCst().setString(3,secondTeamBat.getValue());
            plSqlQuery.getCst().setString(4,firstTeamBat.getValue());
            plSqlQuery.getCst().setString(5,secondTeamRuns.getText());
            plSqlQuery.getCst().setString(6,secondTeamWickets.getText());
            plSqlQuery.getCst().setString(7,secondTeamOvers.getText());
            plSqlQuery.getCst().setString(8,secondTeamFours.getText());
            plSqlQuery.getCst().setString(9,secondTeamSixes.getText());
            plSqlQuery.getCst().setString(10,secondTeamNoBalls.getText());
            plSqlQuery.getCst().setString(11,secondTeamWides.getText());
            plSqlQuery.getCst().registerOutParameter(12, Types.VARCHAR);


            plSqlQuery.getCst().executeUpdate();

            f_2_InningsID = plSqlQuery.getCst().getString(12);

            // success
            System.out.println("Innings Success");
            UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Innings");

        } catch (SQLException e){
            System.out.println("Innings Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Innings");
            e.printStackTrace();
        }
    }

    @FXML
    void create(ActionEvent event) {
        if(InsertNewMatchController.format.equals("Test")){
            // cannot create yet
            System.out.println("Innings Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Create Innings");
        } else{
            Optional <ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Create 1st Innings")
                    .showAndWait();
            if(result.isPresent() && result.get().getText().equals("Yes")) {
                insertIntoDatabase();
                loadFXML("databaseBackEnd/inningsPlayer","FirstInningsBatting");
            }
        }
    }

    @FXML
    void next(ActionEvent event) {
        if(InsertNewMatchController.format.equals("Test")){
            Optional <ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Proceed to 2nd " +
                    "Innings")
                    .showAndWait();
            if(result.isPresent() && result.get().getText().equals("Yes")) {
                insertIntoDatabase();
                loadFXML("databaseBackEnd", "Insert2ndInnings");
            }
        } else{
            System.out.println("Innings Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Next Innings");
        }
    }
}
