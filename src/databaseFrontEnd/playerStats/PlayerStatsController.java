package databaseFrontEnd.playerStats;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import databaseFrontEnd.Main;
import databaseFrontEnd.PlayerProfileController;
import databaseFrontEnd.table.BattingStat;
import databaseFrontEnd.table.BowlingStat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.text.Text;
import oracleJDBC.Query;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerStatsController implements Initializable {

    @FXML
    private JFXTreeTableView <BattingStat> batStatTable;

    @FXML
    private JFXTreeTableView <BowlingStat> bowlStatTable;

    @FXML
    private Text batStatHeader;

    @FXML
    private Text bowlStatHeader;

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

    private ArrayList <BattingStat> getBatsmanStats(String playerName){
        String query = "select * from table(BATSMAN_STAT('" + playerName + "'))";
        Query query1 = new Query(query);
        ResultSet resultSet = query1.querySet();
        ArrayList < BattingStat > battingStatArrayList = new ArrayList<>();
        try{
            while(resultSet.next()){
                BattingStat battingStat = new BattingStat(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getString(12));
                battingStatArrayList.add(battingStat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return battingStatArrayList;
    }

    private ArrayList <BowlingStat> getBowlerStats(String playerName){
        String query = "select * from table(BOWLER_STAT('" + playerName + "'))";
        Query query1 = new Query(query);
        ResultSet resultSet = query1.querySet();
        ArrayList < BowlingStat > bowlingStatArrayList = new ArrayList<>();
        try{
            while(resultSet.next()){
                BowlingStat bowlingStat = new BowlingStat(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10));
                bowlingStatArrayList.add(bowlingStat);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bowlingStatArrayList;
    }

    private void populateBatStat(ArrayList <BattingStat> battingStatArrayList){
        JFXTreeTableColumn <BattingStat,String> format = new JFXTreeTableColumn<>("Format");
        format.setPrefWidth(105);
        format.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().formatProperty();
        });
        batStatTable.getColumns().add(format);

        JFXTreeTableColumn <BattingStat,String> innings_no = new JFXTreeTableColumn<>("Innings");
        innings_no.setPrefWidth(105);
        innings_no.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().innings_noProperty();
        });
        batStatTable.getColumns().add(innings_no);

        JFXTreeTableColumn <BattingStat,String> not_outs = new JFXTreeTableColumn<>("Not Outs");
        not_outs.setPrefWidth(105);
        not_outs.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().not_outsProperty();
        });
        batStatTable.getColumns().add(not_outs);

        JFXTreeTableColumn <BattingStat,String> runs_scored = new JFXTreeTableColumn<>("Runs");
        runs_scored.setPrefWidth(105);
        runs_scored.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().runs_scoredProperty();
        });
        batStatTable.getColumns().add(runs_scored);

        JFXTreeTableColumn <BattingStat,String> highest_score = new JFXTreeTableColumn<>("Highest Score");
        highest_score.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().highest_scoreProperty();
        });
        batStatTable.getColumns().add(highest_score);

        JFXTreeTableColumn <BattingStat,String> average = new JFXTreeTableColumn<>("Average");
        average.setPrefWidth(105);
        average.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().averageProperty();
        });
        batStatTable.getColumns().add(average);

        JFXTreeTableColumn <BattingStat,String> balls_faced = new JFXTreeTableColumn<>("Balls Faced");
        balls_faced.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().balls_facedProperty();
        });
        batStatTable.getColumns().add(balls_faced);

        JFXTreeTableColumn <BattingStat,String> strike_rate = new JFXTreeTableColumn<>("Strike Rate");
        strike_rate.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().strike_rateProperty();
        });
        batStatTable.getColumns().add(strike_rate);

        JFXTreeTableColumn <BattingStat,String> centuries = new JFXTreeTableColumn<>("100s");
        centuries.setPrefWidth(105);
        centuries.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().centuriesProperty();
        });
        batStatTable.getColumns().add(centuries);

        JFXTreeTableColumn <BattingStat,String> half_centuries = new JFXTreeTableColumn<>("50s");
        half_centuries.setPrefWidth(105);
        half_centuries.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().half_centuriesProperty();
        });
        batStatTable.getColumns().add(half_centuries);

        JFXTreeTableColumn <BattingStat,String> fours = new JFXTreeTableColumn<>("4s");
        fours.setPrefWidth(110);
        fours.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().foursProperty();
        });
        batStatTable.getColumns().add(fours);

        JFXTreeTableColumn <BattingStat,String> sixes = new JFXTreeTableColumn<>("6s");
        sixes.setPrefWidth(110);
        sixes.setCellValueFactory((TreeTableColumn.CellDataFeatures <BattingStat,String> param) ->{
            return param.getValue().getValue().sixesProperty();
        });
        batStatTable.getColumns().add(sixes);

        ObservableList <BattingStat> battingStatObservableList = FXCollections.observableArrayList(battingStatArrayList);
        TreeItem <BattingStat> treeItem = new RecursiveTreeItem<>(battingStatObservableList,RecursiveTreeObject::getChildren);
        batStatTable.setRoot(treeItem);
        batStatTable.setShowRoot(false);
    }

    private void populateBowlStat(ArrayList <BowlingStat> bowlingStatArrayList){
        JFXTreeTableColumn <BowlingStat,String> format = new JFXTreeTableColumn<>("Format");
        format.setPrefWidth(120);
        format.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().formatProperty();
        });
        bowlStatTable.getColumns().add(format);

        JFXTreeTableColumn <BowlingStat,String> innings_no = new JFXTreeTableColumn<>("Innings");
        innings_no.setPrefWidth(120);
        innings_no.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().innings_noProperty();
        });
        bowlStatTable.getColumns().add(innings_no);

        JFXTreeTableColumn <BowlingStat,String> balls_bowled = new JFXTreeTableColumn<>("Balls Bowled");
        balls_bowled.setPrefWidth(120);
        balls_bowled.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().balls_bowledProperty();
        });
        bowlStatTable.getColumns().add(balls_bowled);

        JFXTreeTableColumn <BowlingStat,String> runs_conceded = new JFXTreeTableColumn<>("Runs Conceded");
        runs_conceded.setPrefWidth(120);
        runs_conceded.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().runs_concededProperty();
        });
        bowlStatTable.getColumns().add(runs_conceded);

        JFXTreeTableColumn <BowlingStat,String> wickets_taken = new JFXTreeTableColumn<>("Wickets Taken");
        wickets_taken.setPrefWidth(120);
        wickets_taken.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().wickets_takenProperty();
        });
        bowlStatTable.getColumns().add(wickets_taken);

        JFXTreeTableColumn <BowlingStat,String> best_figure = new JFXTreeTableColumn<>("Best Figure");
        best_figure.setPrefWidth(120);
        best_figure.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().best_figureProperty();
        });
        bowlStatTable.getColumns().add(best_figure);

        JFXTreeTableColumn <BowlingStat,String> average = new JFXTreeTableColumn<>("Average");
        average.setPrefWidth(120);
        average.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().averageProperty();
        });
        bowlStatTable.getColumns().add(average);

        JFXTreeTableColumn <BowlingStat,String> strike_rate = new JFXTreeTableColumn<>("Strike Rate");
        strike_rate.setPrefWidth(120);
        strike_rate.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().strike_rateProperty();
        });
        bowlStatTable.getColumns().add(strike_rate);

        JFXTreeTableColumn <BowlingStat,String> economy = new JFXTreeTableColumn<>("Economy");
        economy.setPrefWidth(120);
        economy.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().economoyProperty();
        });
        bowlStatTable.getColumns().add(economy);

        JFXTreeTableColumn <BowlingStat,String> five_wickets = new JFXTreeTableColumn<>("Five Wickets");
        five_wickets.setPrefWidth(150);
        five_wickets.setCellValueFactory((TreeTableColumn.CellDataFeatures <BowlingStat,String> param) ->{
            return param.getValue().getValue().five_wicketsProperty();
        });
        bowlStatTable.getColumns().add(five_wickets);

        ObservableList <BowlingStat> battingStatObservableList = FXCollections.observableArrayList(bowlingStatArrayList);
        TreeItem <BowlingStat> treeItem = new RecursiveTreeItem<>(battingStatObservableList,RecursiveTreeObject::getChildren);
        bowlStatTable.setRoot(treeItem);
        bowlStatTable.setShowRoot(false);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String pName = PlayerProfileController.playerForProfile.getValue().getPlayer_firstName() + " " +
                PlayerProfileController.playerForProfile.getValue().getPlayer_lastName();

        ArrayList <BattingStat> battingStatArrayList = getBatsmanStats(pName);
        populateBatStat(battingStatArrayList);

        ArrayList <BowlingStat> bowlingStatArrayList = getBowlerStats(pName);
        populateBowlStat(bowlingStatArrayList);

        batStatHeader.setText("Batting stats of " + pName);
        bowlStatHeader.setText("Bowling stats of " + pName);
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseFrontEnd","PlayerProfile");
    }
}
