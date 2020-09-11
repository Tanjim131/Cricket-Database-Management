package databaseFrontEnd;

import databaseFrontEnd.scorecard.InningsController;
import databaseFrontEnd.table.Match;
import databaseFrontEnd.table.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oracleJDBC.Pl_Sql_Query;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MatchTableController implements Initializable {

    public static String tournament_name;
    public static String year;
    public static String format;

    private static ArrayList <Match> matchArrayList;

    private void loadFXML(String packageName, String FXML) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    @FXML
    private TableView <Match> matchTableView;

    @FXML
    private TableColumn <Match,String> duration;

    @FXML
    private TableColumn <Match,String> venue;

    @FXML
    private TableColumn <Match,String> toss;

    @FXML
    private TableColumn <Match,String> winner;

    @FXML
    private TableColumn < Match,ArrayList <Score> > scoreColumn;

    @FXML
    private Label seriesHeader;

    @FXML
    private Label seriesFormatLabel;

    private void getAllMatches(){
        String query = "{call FETCH_MATCH(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try{
            plSqlQuery.getCst().setString(1,tournament_name);
            plSqlQuery.getCst().setString(2,year);
            plSqlQuery.getCst().setString(3,format);

            plSqlQuery.getCst().registerOutParameter(4, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(5, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(6, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(7, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(8, Types.ARRAY,"T_AR");

            plSqlQuery.getCst().registerOutParameter(9, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(10, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(11, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(12, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(13, Types.ARRAY,"T_AR");

            plSqlQuery.getCst().executeUpdate();

            // get start date
            Array array = plSqlQuery.getCst().getArray(4);
            String [] start_date = (String[]) array.getArray();

            // get end date

            array = plSqlQuery.getCst().getArray(5);
            String [] end_date = (String []) array.getArray();

            // get venue

            array =  plSqlQuery.getCst().getArray(6);
            String [] venues = (String []) array.getArray();

            // get toss

            array = plSqlQuery.getCst().getArray(7);
            String [] toss = (String []) array.getArray();

            // get winner

            array = plSqlQuery.getCst().getArray(8);
            String [] winners = (String []) array.getArray();

            // get scores
            array = plSqlQuery.getCst().getArray(9);
            String [] innings_names = (String []) array.getArray();

            array = plSqlQuery.getCst().getArray(10);
            String [] team_batting = (String []) array.getArray();

            array = plSqlQuery.getCst().getArray(11);
            String [] runs = (String []) array.getArray();

            array = plSqlQuery.getCst().getArray(12);
            String [] wickets = (String []) array.getArray();

            array = plSqlQuery.getCst().getArray(13);
            String [] overs = (String []) array.getArray();


            // now construct Match

            matchArrayList = new ArrayList<>();

            for(int i = 0,k = 0; i < start_date.length ; i++){
                ArrayList <String> arrayList = new ArrayList<>();

                arrayList.add(start_date[i]);
                arrayList.add(end_date[i]);
                arrayList.add(venues[i]);
                arrayList.add(toss[i]);
                arrayList.add(winners[i]);

                // arrayList of String complete
                // now time for score

                ArrayList <Score> scoreArrayList = new ArrayList<>();

                if (format.equals("ODI") || format.equals("T20")) {
                    for (int j = 0; j < 2; j++, k++) {
                        Score score = new Score(innings_names[k], team_batting[k], runs[k], wickets[k], overs[k]);
                        scoreArrayList.add(score);
                    }
                } else {
                    for (int j = 0; j < 4; j++, k++) {
                        Score score = new Score(innings_names[k], team_batting[k], runs[k], wickets[k], overs[k]);
                        scoreArrayList.add(score);
                    }
                }
                Match match = new Match(arrayList,scoreArrayList);
                matchArrayList.add(match);
            }

        } catch (Exception e){
            //UtilityClass.showDialog(Alert.AlertType.ERROR,"No Matches Found");
            e.printStackTrace();
        }
    }

    private void populateTable(ArrayList <Match> arrayList){
        if(duration == null){
            duration = new TableColumn<>("Match Date");
            duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            matchTableView.getColumns().add(duration);
        }

        if(venue == null){
            venue = new TableColumn<>("Venue");
            venue.setCellValueFactory(new PropertyValueFactory<>("venue"));
            matchTableView.getColumns().add(venue);
        }

        if(toss == null){
            toss = new TableColumn<>("Toss");
            toss.setCellValueFactory(new PropertyValueFactory<>("toss"));
            matchTableView.getColumns().add(toss);
        }

        if(winner == null){
            winner = new TableColumn<>("Winner");
            winner.setCellValueFactory(new PropertyValueFactory<>("winner"));
            matchTableView.getColumns().add(winner);
        }

        if(scoreColumn == null){
            scoreColumn = new TableColumn<>("Score");
            scoreColumn.setCellValueFactory(new PropertyValueFactory<>("scores"));
            matchTableView.getColumns().add(scoreColumn);
        }

        ObservableList <Match> observableList = FXCollections.observableArrayList(arrayList);
        matchTableView.setItems(observableList);
    }

    private void setLabel(){
        seriesHeader.setText(tournament_name);
        seriesHeader.setAlignment(Pos.CENTER);
        seriesFormatLabel.setText(format + " Series");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllMatches();
        populateTable(matchArrayList);
        setLabel();
        matchTableView.setOnMouseClicked(event -> {
            Match match = matchTableView.getSelectionModel().getSelectedItem();
            //System.out.println(match.getScores());

            String [] arr = match.getScores().split("\n");
            String [] arr1 = match.getDuration().split("-");

            InningsController.teamB = arr[1].substring(0,arr[1].indexOf("1"));
            InningsController.teamA = arr[0].substring(0,arr[0].indexOf("1"));

            InningsController.teamA = InningsController.teamA.trim();
            InningsController.teamB = InningsController.teamB.trim();

            //System.out.println(InningsController.teamA + " " + InningsController.teamA.length());
            //System.out.println(InningsController.teamB + " " + InningsController.teamB.length());

            InningsController.startDate = arr1[0];
            if(arr1.length > 1) InningsController.endDate = arr1[1];
            else InningsController.endDate = "";

            try {
                InningsController.curPage = "First Innings";

                loadFXML("databaseFrontEnd/scorecard","Innings");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        loadFXML("databaseFrontEnd","BilateralSeriesTable");
    }
}
