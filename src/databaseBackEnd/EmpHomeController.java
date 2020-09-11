package databaseBackEnd;

import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class EmpHomeController {

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
    void back(ActionEvent event) {
        loadFXML("databaseFrontEnd","EmpLogin");
    }

    @FXML
    void logOut(ActionEvent event) {
        loadFXML("databaseFrontEnd","EmpLogin");
    }

    @FXML
    void insertCountry(ActionEvent event) {
        InsertCountryController.prevFXML = "EmpHome";
        loadFXML("databaseBackEnd","InsertCountry");
    }

    @FXML
    void insertPlayer(ActionEvent event) {
        InsertPlayerController.prevFXML = "EmpHome";
        loadFXML("databaseBackEnd","InsertPlayer");
    }

    @FXML
    void insertTournament(ActionEvent event) {
        InsertTournamentController.prevFXML = "EmpHome";
        loadFXML("databaseBackEnd","InsertTournament");
    }
}
