package databaseFrontEnd.searchPlayer;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import databaseFrontEnd.Main;
import databaseFrontEnd.PlayerProfileController;
import databaseFrontEnd.table.Player;
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

public class SearchPlayerController implements Initializable {

    @FXML
    private JFXTreeTableView <Player> playerTable;

    @FXML
    private JFXTextField acPlayerSearch;

    @FXML
    private JFXComboBox <String> countryComboBox;

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

    private ArrayList <Player> populateTable(String query){
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        ArrayList <Player> playerTableList = new ArrayList<>();
        try{
            while(rs.next()){
                ArrayList < String > arrayList = new ArrayList<>();
                for(int i = 2 ; i <= Player.playerColumnNumber + 1 ; i++){
                    if(i == 2){
                        // getting the country_name from the country_id
                        ResultSet resultSet = new oracleJDBC.Query("select country_name from country where country_id = " +                                                     rs.getString(i)).querySet();
                        resultSet.next();
                        arrayList.add(resultSet.getString("country_name"));

                    }
                    else arrayList.add(rs.getString(i));

                }
                Player player = new Player(arrayList);
                playerTableList.add(player);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return playerTableList;
    }

    private void initTable(ArrayList <Player> playerArrayList){

        JFXTreeTableColumn <Player,String> countryNameCol = new JFXTreeTableColumn<>("Country");
        countryNameCol.setPrefWidth(350);
        countryNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
            return param.getValue().getValue().country_nameProperty();
        });

        JFXTreeTableColumn <Player,String> playerFirstNameCol = new JFXTreeTableColumn<>("First Name");
        playerFirstNameCol.setPrefWidth(350);
        playerFirstNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
           return param.getValue().getValue().player_firstNameProperty();
        });

        JFXTreeTableColumn <Player,String> playerLastNameCol = new JFXTreeTableColumn<>("Last Name");
        playerLastNameCol.setPrefWidth(350);
        playerLastNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
            return param.getValue().getValue().player_lastNameProperty();
        });

        ObservableList <Player> observableList = FXCollections.observableArrayList(playerArrayList);
        TreeItem <Player> playerTreeItem = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);
        playerTable.setRoot(playerTreeItem);
        playerTable.setShowRoot(false);
        playerTable.getColumns().addAll(countryNameCol,playerFirstNameCol,playerLastNameCol);

        playerSearch();
    }

    private void playerSearch(){
        acPlayerSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                playerTable.setPredicate(new Predicate<TreeItem<Player>>() {
                    @Override
                    public boolean test(TreeItem <Player> playerTreeItem) {
                        return playerTreeItem.getValue().getPlayer_firstName().toUpperCase().contains(newValue.toUpperCase());
                    }
                });
            }
        });
    }

    public static void populateComboBox(JFXComboBox <String> comboBox){
        String query = "SELECT COUNTRY_NAME FROM COUNTRY ORDER BY COUNTRY_NAME";
        ResultSet rs = new oracleJDBC.Query(query).querySet();
        try {
            while(rs.next()){
                comboBox.getItems().addAll(
                    rs.getString("COUNTRY_NAME")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void selectCountry(){
        countryComboBox.setOnAction(event ->{
            SearchPlayerQueryController.searchCountry = countryComboBox.getValue();
            loadFXML("databaseFrontEnd/searchPlayer","SearchPlayerQuery");
        });
    }

    private void playerProfile(){
        playerTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                PlayerProfileController.playerForProfile = playerTable.getSelectionModel().getSelectedItem();
                PlayerProfileController.prevFXML = "SearchPlayer";
                loadFXML("databaseFrontEnd","PlayerProfile");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList <Player> arrayList = populateTable("SELECT * FROM PLAYER");
        initTable(arrayList);
        populateComboBox(countryComboBox);

        // country list drop down menu

        selectCountry();

        // triggers an action event when a row of the table is clicked twice

        playerProfile();
    }

    @FXML
    private void back(ActionEvent event) {
        loadFXML("databaseFrontEnd","HomePage");
    }
}
