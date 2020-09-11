package databaseBackEnd;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import databaseFrontEnd.Main;
import databaseFrontEnd.searchPlayer.SearchPlayerController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import oracleJDBC.Pl_Sql_Query;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class InsertNewMatchController implements Initializable {

    private static String [] formats = {"Test","ODI","T20"};

    public static String matchID;
    public static String format;

    @FXML
    private JFXComboBox <String> mType;

    @FXML
    private JFXComboBox <String> mFormat;

    @FXML
    private JFXDatePicker mStartDate;

    @FXML
    private JFXDatePicker mEndDate;

    @FXML
    private JFXComboBox <String> mTournamentName;

    @FXML
    private JFXComboBox <String> mVenueName;

    @FXML
    private JFXComboBox <String> mFirstTeam;

    @FXML
    private JFXComboBox <String> mSecondTeam;

    @FXML
    private JFXComboBox <String> mToss;

    @FXML
    private JFXComboBox <String> mWinner;


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


    private void getAllVenue(){
        String query = "select venue_name from venue order by venue_name";
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        try {
            while(rs.next()){
                mVenueName.getItems().addAll(
                        rs.getString("venue_name")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void getAllTournament(){
        String query = "select tournament_name from tournament order by tournament_name";
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        try {
            while(rs.next()){
                mTournamentName.getItems().addAll(
                        rs.getString("tournament_name")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void populateComboBox(){

        mFirstTeam.setEditable(true);
        mSecondTeam.setEditable(true);
        mToss.setEditable(true);
        mWinner.setEditable(true);

        SearchPlayerController.populateComboBox(mFirstTeam);
        SearchPlayerController.populateComboBox(mSecondTeam);
        SearchPlayerController.populateComboBox(mToss);
        SearchPlayerController.populateComboBox(mWinner);

        getAllVenue();
        getAllTournament();

        //match Type

        mType.getItems().addAll(
            "International","Domestic"
        );

        //match format

        mFormat.setItems(FXCollections.observableArrayList(Arrays.asList(formats)));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateComboBox();
    }

    @FXML
    void createMatch(ActionEvent event) {
        String query = "{call INSERT_MATCH(?,?,?,?,?,?,?,?,?,?,?)";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try {
            plSqlQuery.getCst().setString(1,mType.getValue());
            plSqlQuery.getCst().setString(2,mFormat.getValue());


            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDate localDate = mStartDate.getValue();

            if(localDate != null) plSqlQuery.getCst().setString(3,dateTimeFormatter.format(localDate));

            localDate = mEndDate.getValue();

            if(localDate != null) plSqlQuery.getCst().setString(4,dateTimeFormatter.format(localDate));

            plSqlQuery.getCst().setString(5,mTournamentName.getValue());
            plSqlQuery.getCst().setString(6,mVenueName.getValue());
            plSqlQuery.getCst().setString(7,mFirstTeam.getValue());
            plSqlQuery.getCst().setString(8,mSecondTeam.getValue());
            plSqlQuery.getCst().setString(9,mToss.getValue());
            plSqlQuery.getCst().setString(10,mWinner.getValue());
            plSqlQuery.getCst().registerOutParameter(11, Types.VARCHAR);

            Optional <ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Create Match")
                    .showAndWait();

            if (result.isPresent() && result.get().getText().equals("Yes")) {
                plSqlQuery.getCst().executeUpdate();
                matchID = plSqlQuery.getCst().getString(11);
                format = mFormat.getValue();

                System.out.println(matchID);

                // success
                System.out.println("Match Success");
                UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Match");
                loadFXML("databaseBackEnd","Insert1stInnings");
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Tournament Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Match");
        }
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseBackEnd","InsertTournament");
    }

    @FXML
    void logOut(ActionEvent event) {
        if(InsertTournamentController.prevFXML.equals("DatabaseHomePage")){
            loadFXML("databaseFrontEnd","AdminLogin");
        } else{
            loadFXML("databaseFrontEnd","EmpLogin");
        }
    }

    @FXML
    void proceedToInningsPlayerInfo(ActionEvent event) {
        loadFXML("databaseBackEnd","FirstInningsBatting");
    }
}
