package databaseBackEnd;

import databaseFrontEnd.Main;
import oracleJDBC.Insert;
import utilityPackage.UtilityClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InsertCountryController implements Initializable{
    private static ArrayList <String> countryInfo;

    public static String prevFXML;

    @FXML
    private AnchorPane insertCountryPane;

    @FXML
    private TextField countryName;

    @FXML
    private Button createCountryButton;

    @FXML
    private Button insertCountryBackButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countryName.setPromptText("Enter Country Name");
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

    @FXML
    void createCountry(ActionEvent event) {

        countryInfo = new ArrayList<>();
        countryInfo.add(countryName.getText());

        Insert insert = new Insert(Insert.COUNTRY_SQL);
        if(insert.insertTable(countryInfo)){
            System.out.println("Country Success");
            UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Country");
        }
        else{
            System.out.println("Country Error");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Country");
        }
    }

    @FXML
    void insertCountryBack(ActionEvent event) {
        if(prevFXML.equals("DatabaseHomePage")) {
            loadFXML("databaseBackEnd", "DatabaseHomePage");
        } else{
            loadFXML("databaseBackEnd","EmpHome");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        if(prevFXML.equals("DatabaseHomePage")) {
            loadFXML("databaseFrontEnd", "AdminLogin");
        } else{
            loadFXML("databaseFrontEnd","EmpLogin");
        }
    }
}
