package databaseFrontEnd;

import com.jfoenix.controls.JFXComboBox;
import databaseFrontEnd.searchPlayer.SearchPlayerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchBilateralSeriesController implements Initializable {

    @FXML
    private JFXComboBox<String> countryA;

    @FXML
    private JFXComboBox <String> countryB;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countryA.setEditable(true);
        countryB.setEditable(true);

        SearchPlayerController.populateComboBox(countryA);
        SearchPlayerController.populateComboBox(countryB);
    }

    @FXML
    void search(ActionEvent event) {
        String cA = countryA.getValue();
        String cB = countryB.getValue();

        if(cA == null || cB == null){
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Search Bilateral Series Null Country");
        }
        else if(cA.equals(cB)){
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Search Bilateral Series Same Country");
        } else{
            BilateralSeriesTableController.countryA = cA;
            BilateralSeriesTableController.countryB = cB;
            loadFXML("databaseFrontEnd","BilateralSeriesTable");
        }
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseFrontEnd","HomePage");
    }
}
