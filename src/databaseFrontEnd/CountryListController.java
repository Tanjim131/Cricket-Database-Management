package databaseFrontEnd;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import databaseFrontEnd.table.Country;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CountryListController implements Initializable {

    @FXML
    private JFXTreeTableView <Country> countryTable;

    @FXML
    private JFXTreeTableColumn <Country, String> countryNameCol;

    @FXML
    private JFXTreeTableColumn <Country, String> numberOfPlayerCol;

    @FXML
    private JFXTextField countrySearch;

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

    private ArrayList <Country> populateTable(String query){
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        ArrayList <Country> countryList = new ArrayList<>();
        try{
            while(rs.next()){
                Country country = new Country(rs.getString("COUNTRY_NAME"),rs.getString("Player_Count"));
                countryList.add(country);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return countryList;
    }

    private void initTable(ArrayList <Country> arrayList){
        countryNameCol = new JFXTreeTableColumn<>("Country Name");
        countryNameCol.setPrefWidth(700);
        countryNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country,String> param) ->{
            if(countryNameCol.validateValue(param)){
                return param.getValue().getValue().country_nameProperty();
            } else{
                return countryNameCol.getComputedValue(param);
            }
        });

        numberOfPlayerCol = new JFXTreeTableColumn<>("Player Count");
        numberOfPlayerCol.setPrefWidth(500);
        numberOfPlayerCol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Country,String> param) ->{
            if(numberOfPlayerCol.validateValue(param)){
                return param.getValue().getValue().player_countProperty();
            } else{
                return numberOfPlayerCol.getComputedValue(param);
            }
        });

        ObservableList < Country > observableList = FXCollections.observableArrayList(arrayList);
        TreeItem <Country> countryTreeItem = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        countryTable.getColumns().setAll(countryNameCol,numberOfPlayerCol);
        countryTable.setRoot(countryTreeItem);
        countryTable.setShowRoot(false);

        countrySearch();
    }

    private void countrySearch(){
        countrySearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                countryTable.setPredicate(new Predicate<TreeItem<Country>>() {
                    @Override
                    public boolean test(TreeItem <Country> countryTreeItem) {
                        return countryTreeItem.getValue().getCountry_name().toUpperCase().contains(newValue.toUpperCase());
                    }
                });
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList <Country> countryArrayList = populateTable("select c.country_name, count(p.player_id) AS \"Player_Count\"" +
                " \n" +
                "from country c left join player p\n" +
                "ON(c.country_id = p.country_id)\n" +
                "group by c.country_id,c.country_name\n" +
                "order by c.country_name");
        initTable(countryArrayList);
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseFrontEnd","HomePage");
    }
}
