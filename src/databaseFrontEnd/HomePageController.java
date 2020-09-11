package databaseFrontEnd;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private JFXComboBox <String> searchComboBox;

    private void populateSearchCombo(){
        searchComboBox.getItems().addAll("Player","Tournament");
    }


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

    @FXML
    void countryList(ActionEvent event) {
        loadFXML("databaseFrontEnd", "CountryList");
    }

    @FXML
    void exit(ActionEvent event){
        Optional<ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Do you want to exit?").showAndWait();
        if(result.isPresent() && result.get().getText().equals("Yes")){
            System.exit(0);
        }
    }

    private void getSearchMode(){
        searchComboBox.setOnAction(event -> {
            String string = searchComboBox.getValue();
            if(string.equals("Player")){
                loadFXML("databaseFrontEnd/searchPlayer","SearchPlayer");
            } else if(string.equals("Tournament")){
                loadFXML("databaseFrontEnd","SearchBilateralSeries");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateSearchCombo();
        getSearchMode();
    }

    @FXML
    void homePage(ActionEvent event) {
        loadFXML("databaseFrontEnd/welcome","WelcomeToDatabase");
    }
}
