package databaseFrontEnd.welcome;

import com.jfoenix.controls.JFXComboBox;
import databaseFrontEnd.Main;
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

public class WelcomeToDatabaseController implements Initializable {
    @FXML
    private JFXComboBox <String> loginOption;

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

    private void getLoginMode(){
        loginOption.setOnAction(event -> {
            String string = loginOption.getValue();
            if(string.equals("Admin")){
                loadFXML("databaseFrontEnd","AdminLogin");
            } else if(string.equals("Employee")){
                loadFXML("databaseFrontEnd","EmpLogin");
            } else if(string.equals("Viewer")){
                loadFXML("databaseFrontEnd","HomePage");
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateOption();
        getLoginMode();
    }

    private void populateOption(){
        loginOption.getItems().addAll("Admin","Employee","Viewer");
    }

    @FXML
    void about(ActionEvent event) {
        loadFXML("databaseFrontEnd/welcome","AboutDatabase");
    }

    @FXML
    void exit(ActionEvent event) {
        Optional<ButtonType> result = UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Do you want to exit?").showAndWait();
        if(result.isPresent() && result.get().getText().equals("Yes")){
            System.exit(0);
        }
    }
}
