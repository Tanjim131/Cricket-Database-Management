package databaseFrontEnd.scorecard;

import databaseFrontEnd.Main;
import databaseFrontEnd.table.Batsman;
import databaseFrontEnd.table.Bowler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oracleJDBC.Pl_Sql_Query;
import utilityPackage.BatsmanTable;
import utilityPackage.BowlerTable;
import utilityPackage.InningsPair;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InningsController implements Initializable{

    public static String teamA;
    public static String teamB;
    public static String startDate;
    public static String endDate;
    public static String curPage;

    @FXML
    private TableView <Batsman> inningsBatting;

    @FXML
    private TableColumn <Batsman,String> batsmanName;

    @FXML
    private TableColumn <Batsman,Integer> runsScored;

    @FXML
    private TableColumn <Batsman,Integer> ballsFaced;

    @FXML
    private TableColumn <Batsman,Integer> fours;

    @FXML
    private TableColumn <Batsman,Integer> sixes;

    @FXML
    private TableColumn <Batsman,String> outStatus;

    @FXML
    private TableView <Bowler> inningsBowling;

    @FXML
    private TableColumn <Bowler,String> bowlerName;

    @FXML
    private TableColumn <Bowler,Integer> overs;

    @FXML
    private TableColumn <Bowler,Integer> runsGiven;

    @FXML
    private TableColumn <Bowler,Integer> wicketsTaken;

    @FXML
    private Label inningsHeader;

    @FXML
    private Label inningsBTeam;

    @FXML
    private Label inningsFTeam;

    private void loadFXML(String packageName, String FXML) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    private String getMatchFormat(){
        String query = "{? = call FETCH_MATCH_FORMAT(?,?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        String format = null;
        try{
            plSqlQuery.getCst().registerOutParameter(1, Types.VARCHAR);
            plSqlQuery.getCst().setString(2,teamA);
            plSqlQuery.getCst().setString(3,teamB);
            plSqlQuery.getCst().setString(4,UtilityClass.dateFormat(startDate,endDate));
            plSqlQuery.getCst().executeUpdate();
            format = plSqlQuery.getCst().getString(1);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return format;
    }

    private void populateBatsmanTale(BatsmanTable batsmanTable,ArrayList <Batsman> arrayList){
        batsmanTable.setBatsmanName(new TableColumn<>("Batsmen"));
        batsmanTable.getBatsmanName().setCellValueFactory(new PropertyValueFactory<>("name"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getBatsmanName());

        batsmanTable.setRuns(new TableColumn<>("R"));
        batsmanTable.getRuns().setCellValueFactory(new PropertyValueFactory<>("runs"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getRuns());

        batsmanTable.setBallsFaced(new TableColumn<>("B"));
        batsmanTable.getBallsFaced().setCellValueFactory(new PropertyValueFactory<>("balls"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getBallsFaced());

        batsmanTable.setFours(new TableColumn<>("4s"));
        batsmanTable.getFours().setCellValueFactory(new PropertyValueFactory<>("fours"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getFours());

        batsmanTable.setSixes(new TableColumn<>("6s"));
        batsmanTable.getSixes().setCellValueFactory(new PropertyValueFactory<>("sixes"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getSixes());

        batsmanTable.setOutStatus(new TableColumn<>("Not Out"));
        batsmanTable.getOutStatus().setCellValueFactory(new PropertyValueFactory<>("notOut"));
        batsmanTable.getBatsmanTableView().getColumns().add(batsmanTable.getOutStatus());

        ObservableList <Batsman> observableList = FXCollections.observableArrayList(arrayList);
        batsmanTable.getBatsmanTableView().setItems(observableList);
    }

    private void populateBowlerTable(BowlerTable bowlerTable, ArrayList <Bowler> arrayList){
        bowlerTable.setBowlerName(new TableColumn<>("Bowler"));
        bowlerTable.getBowlerName().setCellValueFactory(new PropertyValueFactory<>("name"));
        bowlerTable.getBowlerTableView().getColumns().add(bowlerTable.getBowlerName());

        bowlerTable.setOvers(new TableColumn<>("O"));
        bowlerTable.getOvers().setCellValueFactory(new PropertyValueFactory<>("overs"));
        bowlerTable.getBowlerTableView().getColumns().add(bowlerTable.getOvers());

        bowlerTable.setRunsGiven(new TableColumn<>("R"));
        bowlerTable.getRunsGiven().setCellValueFactory(new PropertyValueFactory<>("runs"));
        bowlerTable.getBowlerTableView().getColumns().add(bowlerTable.getRunsGiven());

        bowlerTable.setWicketsTaken(new TableColumn<>("W"));
        bowlerTable.getWicketsTaken().setCellValueFactory(new PropertyValueFactory<>("wickets"));
        bowlerTable.getBowlerTableView().getColumns().add(bowlerTable.getWicketsTaken());

        ObservableList <Bowler> observableList = FXCollections.observableArrayList(arrayList);
        bowlerTable.getBowlerTableView().setItems(observableList);
    }

    public void setLabel(String str){
        inningsHeader.setText(str);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BatsmanTable batsmanTable = new BatsmanTable(inningsBatting,batsmanName,runsScored,ballsFaced,fours,sixes,
                outStatus);
        BowlerTable bowlerTable = new BowlerTable(inningsBowling,bowlerName,overs,runsGiven,wicketsTaken);
        InningsPair inningsPair = UtilityClass.getInningsPlayer(batsmanTable,bowlerTable,curPage,
                inningsBTeam,inningsFTeam);
        populateBatsmanTale(batsmanTable,inningsPair.getBatsmanArrayList());
        populateBowlerTable(bowlerTable,inningsPair.getBowlerArrayList());

        if(curPage.equals("First Innings") || curPage.equals("Second Innings")){
            setLabel("1st Innings");
        } else{
            setLabel("2nd Innings");
        }
    }

    @FXML
    void back(ActionEvent event) {
        if(curPage.equals("First Innings")){
            loadFXML("databaseFrontEnd","MatchTable");
            return;
        } else if(curPage.equals("Second Innings")){
            curPage = "First Innings";
        } else if(curPage.equals("Third Innings")){
            curPage = "Second Innings";
            setLabel("1st Innings");
        } else if(curPage.equals("Fourth Innings")){
            curPage = "Third Innings";
        }

        loadFXML("databaseFrontEnd/scorecard","Innings");
    }

    @FXML
    void nextPage(ActionEvent event) {
        if(curPage.equals("First Innings")) {
            curPage = "Second Innings";
        } else if(curPage.equals("Second Innings")){
            String format = getMatchFormat();
            if(format.toUpperCase().equals("TEST")){
                curPage = "Third Innings";
            } else{
                UtilityClass.showDialog(Alert.AlertType.ERROR,"Next Innings");
                return;
            }
        } else if(curPage.equals("Third Innings")){
            curPage = "Fourth Innings";
        } else if(curPage.equals("Fourth Innings")){
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Cannot go to Next Innings");
            return;
        }
        loadFXML("databaseFrontEnd/scorecard","Innings");
    }

}
