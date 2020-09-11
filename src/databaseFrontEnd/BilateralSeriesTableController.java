package databaseFrontEnd;

import databaseFrontEnd.table.BilateralSeries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import oracleJDBC.Pl_Sql_Query;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BilateralSeriesTableController implements Initializable {

    static String countryA;
    static String countryB;

    private static ArrayList <BilateralSeries> bilateralSeriesArrayList;
    private String [] formats = {"All","Test","ODI","T20"};
    private String minYear;
    private String maxyear;

    @FXML
    private Label counA;

    @FXML
    private Label counB;

    @FXML
    private ComboBox <String> formatBox;

    @FXML
    private ComboBox <String> yearBox;

    @FXML
    private TableView <BilateralSeries> bilateralSeriesTableView;

    @FXML
    private TableColumn <BilateralSeries,String> seriesName;

    @FXML
    private TableColumn <BilateralSeries,String> year;

    @FXML
    private TableColumn <BilateralSeries,String> seriesFormat;

    private void loadFXML(String packageName, String FXML) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    private void getAllBilateralSeries(){
        String query = "{call fetch_tournament(?,?,?,?,?,?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try{
            plSqlQuery.getCst().setString(1,countryA);
            plSqlQuery.getCst().setString(2,countryB);
            plSqlQuery.getCst().registerOutParameter(4, Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(5,Types.ARRAY,"T_AR");
            plSqlQuery.getCst().registerOutParameter(6,Types.VARCHAR);
            plSqlQuery.getCst().registerOutParameter(7,Types.VARCHAR);

            bilateralSeriesArrayList = new ArrayList<>();

            for(String format : formats){

                if(format.equals("All")) continue;
                plSqlQuery.getCst().setString(3,format);

                plSqlQuery.getCst().executeUpdate();
                Array s_arr = plSqlQuery.getCst().getArray(4);
                Array y_arr = plSqlQuery.getCst().getArray(5);

                String [] series = (String []) s_arr.getArray();
                String [] year = (String []) y_arr.getArray();

                for(int i = 0 ; i < series.length ; i++){
                    BilateralSeries bilateralSeries = new BilateralSeries(series[i],year[i],format);
                    bilateralSeriesArrayList.add(bilateralSeries);
                }
            }

            minYear = plSqlQuery.getCst().getString(6);
            maxyear = plSqlQuery.getCst().getString(7);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void populateTable(ArrayList <BilateralSeries> arrayList){
        if(seriesName == null){
            seriesName = new TableColumn<>("Series Name");
            seriesName.setCellValueFactory(new PropertyValueFactory<>("seriesName"));
            bilateralSeriesTableView.getColumns().add(seriesName);
        }

        if(year == null) {
            year = new TableColumn<>("Year");
            year.setCellValueFactory(new PropertyValueFactory<>("year"));
            bilateralSeriesTableView.getColumns().add(year);
        }

        if(seriesFormat == null) {
            seriesFormat = new TableColumn<>("Format");
            seriesFormat.setCellValueFactory(new PropertyValueFactory<>("format"));
            bilateralSeriesTableView.getColumns().add(seriesFormat);
        }

        ObservableList <BilateralSeries> observableList = FXCollections.observableArrayList(arrayList);
        bilateralSeriesTableView.setItems(observableList);
        //bilateralSeriesTableView.getColumns().addAll(seriesName,year);
    }

    private void setCountryLabel(){
        counA.setText(countryA);
        counB.setText(countryB);
    }

    private void populateFormatBox(){
        formatBox.setItems(FXCollections.observableArrayList(Arrays.asList(formats)));

        /*formatBox.getItems().addAll(
                "All","Test","ODI","T20"
        );*/
    }

    private void populateYearBox(){
        int a = 0;
        if( minYear != null) a = Integer.parseInt(minYear);
        int b = 0;
        if( maxyear != null) b = Integer.parseInt(maxyear);

        yearBox.getItems().addAll("All");

        if(a != 0 && b != 0) {
            for (int i = a; i <= b; i++) {
                yearBox.getItems().addAll(Integer.toString(i));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllBilateralSeries();
        populateTable(bilateralSeriesArrayList);
        setCountryLabel();
        populateFormatBox();
        populateYearBox();

        // select format

        formatBox.setOnAction(event -> {
            String format = formatBox.getValue();
            String year = yearBox.getValue();

            ArrayList <BilateralSeries> arrayList = new ArrayList<>();

            for(BilateralSeries bilateralSeries : bilateralSeriesArrayList){
                if(year == null || year.equals("All")){
                    if(format.equals("All") || format.equals(bilateralSeries.getFormat())){
                        arrayList.add(bilateralSeries);
                    }
                } else{
                    if((format.equals("All") || format.equals(bilateralSeries.getFormat())) && year.equals
                            (bilateralSeries.getYear())){
                        arrayList.add(bilateralSeries);
                    }
                }
            }
            populateTable(arrayList);
        });

        // select year

        yearBox.setOnAction(event -> {
            String year = yearBox.getValue();
            String format = formatBox.getValue();

            ArrayList <BilateralSeries> arrayList = new ArrayList<>();

            for(BilateralSeries bilateralSeries : bilateralSeriesArrayList){
                if(format == null || format.equals("All")){
                    if(year.equals("All") || year.equals(bilateralSeries.getYear())){
                        arrayList.add(bilateralSeries);
                    }
                } else {
                    if((year.equals("All") || year.equals(bilateralSeries.getYear())) && format.equals
                            (bilateralSeries.getFormat())){
                        arrayList.add(bilateralSeries);
                    }
                }
            }
            populateTable(arrayList);
        });

        bilateralSeriesTableView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                BilateralSeries bilateralSeries = bilateralSeriesTableView.getSelectionModel().getSelectedItem();
                MatchTableController.tournament_name = bilateralSeries.getSeriesName();
                MatchTableController.format = bilateralSeries.getFormat();
                MatchTableController.year = bilateralSeries.getYear();
                try {
                    loadFXML("databaseFrontEnd", "MatchTable");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        loadFXML("databaseFrontEnd","SearchBilateralSeries");
    }
}
