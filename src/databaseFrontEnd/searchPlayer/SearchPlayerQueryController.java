package databaseFrontEnd.searchPlayer;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import databaseFrontEnd.Main;
import databaseFrontEnd.PlayerProfileController;
import databaseFrontEnd.table.Player;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchPlayerQueryController implements Initializable{

    public static String searchCountry;
    private static final int ALL = 1;
    private static final int TEST = 2;
    private static final int ODI = 3;
    private static final int T20 = 4;

    @FXML
    private Label label;

    @FXML
    private JFXTreeTableView <Player> allPlayerTable;

    @FXML
    private JFXTreeTableView <Player> testPlayerTable;

    @FXML
    private JFXTreeTableView <Player> odiPlayerTable;

    @FXML
    private JFXTreeTableView <Player> t20PlayerTable;


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
        ArrayList <Player> playerArrayList = new ArrayList<>();

        ResultSet rs = new oracleJDBC.Query(query).querySet();
        try{
            while(rs.next()){
                ArrayList < String > arrayList = new ArrayList<>();

                // disregarding i = 1 or player_id

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
                playerArrayList.add(player);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return playerArrayList;
    }

    private void initTable(int type,ArrayList <Player> arrayList){

        JFXTreeTableColumn <Player,String> countryNameCol = new JFXTreeTableColumn<>("Country Name");
        countryNameCol.setPrefWidth(375);
        JFXTreeTableColumn <Player,String> playerFirstNameCol = new JFXTreeTableColumn<>("First Name");
        playerFirstNameCol.setPrefWidth(375);
        JFXTreeTableColumn <Player,String> playerLastNameCol = new JFXTreeTableColumn<>("Last Name");
        playerLastNameCol.setPrefWidth(375);

        countryNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
            return param.getValue().getValue().country_nameProperty();
        });

        playerFirstNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
            return param.getValue().getValue().player_firstNameProperty();
        });

        playerLastNameCol.setCellValueFactory((TreeTableColumn.CellDataFeatures <Player,String> param) ->{
            return param.getValue().getValue().player_lastNameProperty();
        });


        ObservableList <Player> observableList = FXCollections.observableArrayList(arrayList);
        TreeItem <Player> playerTreeItem = new RecursiveTreeItem<>(observableList, RecursiveTreeObject::getChildren);

        if(type == ALL){
            allPlayerTable.setRoot(playerTreeItem);
            allPlayerTable.setShowRoot(false);
            allPlayerTable.getColumns().addAll(countryNameCol,playerFirstNameCol,playerLastNameCol);
        }
        else if(type == TEST){
            testPlayerTable.setRoot(playerTreeItem);
            testPlayerTable.setShowRoot(false);
            testPlayerTable.getColumns().addAll(countryNameCol,playerFirstNameCol,playerLastNameCol);
        }
        else if(type == ODI){
            odiPlayerTable.setRoot(playerTreeItem);
            odiPlayerTable.setShowRoot(false);
            odiPlayerTable.getColumns().addAll(countryNameCol,playerFirstNameCol,playerLastNameCol);
        }
        else{
            t20PlayerTable.setRoot(playerTreeItem);
            t20PlayerTable.setShowRoot(false);
            t20PlayerTable.getColumns().addAll(countryNameCol,playerFirstNameCol,playerLastNameCol);
        }
    }

    private void clickOnTable(){ // clicking twice on any row will show player profile
        allPlayerTable.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2){
                PlayerProfileController.playerForProfile = allPlayerTable.getSelectionModel().getSelectedItem();
                PlayerProfileController.prevFXML = "SearchPlayerQuery";
                loadFXML("DatabaseFrontEnd","PlayerProfile");
            }
        });

        testPlayerTable.setOnMouseClicked(event -> {
            PlayerProfileController.playerForProfile = testPlayerTable.getSelectionModel().getSelectedItem();
            PlayerProfileController.prevFXML = "SearchPlayerQuery";
            loadFXML("DatabaseFrontEnd","PlayerProfile");
        });

        odiPlayerTable.setOnMouseClicked(event -> {
            PlayerProfileController.playerForProfile = odiPlayerTable.getSelectionModel().getSelectedItem();
            PlayerProfileController.prevFXML = "SearchPlayerQuery";
            loadFXML("DatabaseFrontEnd","PlayerProfile");
        });

        t20PlayerTable.setOnMouseClicked(event -> {
            PlayerProfileController.playerForProfile = t20PlayerTable.getSelectionModel().getSelectedItem();
            PlayerProfileController.prevFXML = "SearchPlayerQuery";
            loadFXML("DatabaseFrontEnd","PlayerProfile");
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList <Player> arrayList;

        arrayList = populateTable("SELECT * FROM PLAYER P JOIN COUNTRY C ON (P.COUNTRY_ID = C.COUNTRY_ID) WHERE C.COUNTRY_NAME = '"  + searchCountry + "'");
        initTable(ALL,arrayList);

        arrayList = populateTable("SELECT * FROM PLAYER P JOIN COUNTRY C ON (P.COUNTRY_ID = C.COUNTRY_ID) WHERE C.COUNTRY_NAME = '" + searchCountry + "' AND test_debut is not null");
        initTable(TEST,arrayList);

        arrayList = populateTable("SELECT * FROM PLAYER P JOIN COUNTRY C ON (P.COUNTRY_ID = C.COUNTRY_ID) WHERE C.COUNTRY_NAME = '" + searchCountry + "' AND odi_debut is not null");
        initTable(ODI,arrayList);

        arrayList = populateTable("SELECT * FROM PLAYER P JOIN COUNTRY C ON (P.COUNTRY_ID = C.COUNTRY_ID) WHERE C.COUNTRY_NAME = '" + searchCountry + "' AND t20_debut is not null");
        initTable(T20,arrayList);


        label.setText(searchCountry);
        label.setAlignment(Pos.CENTER);

        clickOnTable();
    }

    @FXML
    void back(ActionEvent event) {
       loadFXML("databaseFrontEnd/searchPlayer","SearchPlayer");
    }

}
