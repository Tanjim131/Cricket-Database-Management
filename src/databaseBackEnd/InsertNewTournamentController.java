package databaseBackEnd;

import com.jfoenix.controls.JFXComboBox;
import databaseFrontEnd.Main;
import databaseFrontEnd.searchPlayer.SearchPlayerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import oracleJDBC.Insert;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class InsertNewTournamentController implements Initializable {

    private static String [] formats = {"Test","ODI","T20"};
    private static ArrayList <String> tournamentInfo;
    public static String prevFXML;

    @FXML
    private TextField tName;

    @FXML
    private DatePicker tStartDate;

    @FXML
    private DatePicker tEndDate;

    @FXML
    private JFXComboBox<String> tFormat;

    @FXML
    private JFXComboBox <String> tTeams;

    @FXML
    private JFXComboBox <String> tWinner;

    @FXML
    private JFXComboBox <String> tRunnersUp;

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

    private void numberOfTeams(){
        for(int i = 2 ; i <= 14 ; i++){
            // at least 2 teams and at most 14 teams
            tTeams.getItems().add(Integer.toString(i));
        }
    }

    private void populateFormat(){
        tFormat.setItems(FXCollections.observableArrayList(Arrays.asList(formats)));
    }

    private void tournamentInfo(){
        tournamentInfo = new ArrayList<>();

        if(tName != null) tournamentInfo.add(tName.getText());
        else tournamentInfo.add("");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate localDate = tStartDate.getValue();

        if(localDate != null) tournamentInfo.add(dateTimeFormatter.format(localDate));
        else tournamentInfo.add("");

        localDate = tEndDate.getValue();
        if(localDate != null) tournamentInfo.add(dateTimeFormatter.format(localDate));
        else tournamentInfo.add("");

        if(tFormat != null) tournamentInfo.add(tFormat.getValue());
        else tournamentInfo.add("");

        if(tTeams != null) tournamentInfo.add(tTeams.getValue());
        else tournamentInfo.add("");

        if(tWinner != null) tournamentInfo.add(tWinner.getValue());
        else tournamentInfo.add("");

        if(tRunnersUp != null) tournamentInfo.add(tRunnersUp.getValue());
        else tournamentInfo.add("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SearchPlayerController.populateComboBox(tWinner);
        SearchPlayerController.populateComboBox(tRunnersUp);
        numberOfTeams();
        populateFormat();
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseBackEnd","InsertTournament");
    }

    @FXML
    void create(ActionEvent event) {
        tournamentInfo();

        Insert insert = new oracleJDBC.Insert(Insert.TOURNAMENT_SQL);

        if(insert.insertTable(tournamentInfo)){
            System.out.println("Tournament Success");
            UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Tournament");
        } else{
            System.out.println("Tournament Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Tournament");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        if(InsertTournamentController.prevFXML.equals("DatabaseHomePage")){
            loadFXML("databaseFrontEnd","AdminLogin");
        } else{
            loadFXML("databaseFrontEnd","EmpLogin");
        }
    }

}
