package databaseBackEnd;

import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import oracleJDBC.Insert;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InsertPlayerController implements Initializable{

    private static ArrayList <String> playerInfo;

    public static String prevFXML;

    @FXML
    private TextField playerFirstName;

    @FXML
    private TextField playerLastName;

    @FXML
    private ComboBox <String> countryName;

    @FXML
    private ComboBox <String> playerGender;

    @FXML
    private ComboBox <String> playingRole;

    @FXML
    private ComboBox <String> battingStyle;

    @FXML
    private ComboBox <String> bowlingStyle;

    @FXML
    private DatePicker playerBirthdate;

    @FXML
    private DatePicker playerTestDebut;

    @FXML
    private DatePicker playerODIDebut;

    @FXML
    private DatePicker playerT20Debut;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        populateComboBox();
        playerFirstName.setPromptText("Enter First Name");
        playerLastName.setPromptText("Enter Last Name");
    }

    private void populateComboBox(){
        String query = "SELECT COUNTRY_NAME FROM COUNTRY ORDER BY COUNTRY_NAME";
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        try {
            while(rs.next()) {
                countryName.getItems().addAll(
                        rs.getString("COUNTRY_NAME")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        // gender

        playerGender.getItems().addAll(
            "Male","Female"
        );

        //playing role

        playingRole.getItems().addAll(
            "Batsman","Bowler","Wicket-Keeper","All-Rounder"
        );

        //batting style

        battingStyle.getItems().addAll(
            "Right handed batsman","Left handed batsman"
        );

        // bowling style

        bowlingStyle.getItems().addAll(
                "Right arm fast","Left arm fast","Right arm medium","Left arm medium","Right arm slower","Left arm slower","Leg spin"
        );

    }

    public void loadFXML(String packageName, String FXML) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    private void playerInfo(){
        playerInfo = new ArrayList<>(); // instantiate playerInfo

        playerInfo.add(countryName.getValue());
        playerInfo.add(playerGender.getValue());
        playerInfo.add(playingRole.getValue());
        playerInfo.add(battingStyle.getValue());
        playerInfo.add(bowlingStyle.getValue());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        LocalDate localDate = playerBirthdate.getValue();
        if(localDate != null) playerInfo.add(dateTimeFormatter.format(localDate));
        else playerInfo.add("");

        localDate = playerTestDebut.getValue();
        if(localDate != null) playerInfo.add(dateTimeFormatter.format(localDate));
        else playerInfo.add("");

        localDate = playerODIDebut.getValue();
        if(localDate != null) playerInfo.add(dateTimeFormatter.format(localDate));
        else playerInfo.add("");

        localDate = playerT20Debut.getValue();
        if(localDate != null) playerInfo.add(dateTimeFormatter.format(localDate));
        else playerInfo.add("");

        playerInfo.add(playerFirstName.getText());
        playerInfo.add(playerLastName.getText());
    }

    @FXML
    void createPlayer(ActionEvent event){
        playerInfo(); // calling playerInfo to populate playerInfo arrayList

        Insert insert = new oracleJDBC.Insert(Insert.PLAYER_SQL);

        if(insert.insertTable(playerInfo)){
            System.out.println("Player Success");
            UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Player");
        }
        else{
            System.out.println("Player Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Player");
        }
    }

    @FXML
    void insertPlayerBack(ActionEvent event) {
        loadFXML("databaseBackEnd",prevFXML);
    }

    @FXML
    void logOut(ActionEvent event) {
        loadFXML("databaseFrontEnd","AdminLogin");
    }
}
