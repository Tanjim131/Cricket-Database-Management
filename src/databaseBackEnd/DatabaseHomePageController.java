package databaseBackEnd;

import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class DatabaseHomePageController {
    private void loadFXML(String packageName, String FXML) {
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
    void insertPlayer(ActionEvent event) {
        InsertPlayerController.prevFXML = "DatabaseHomePage";
        loadFXML("databaseBackEnd","InsertPlayer");
    }

    @FXML
    void insertCountry(ActionEvent event) {
        InsertCountryController.prevFXML = "DatabaseHomePage";
        loadFXML("databaseBackEnd","InsertCountry");
    }

    @FXML
    void databaseHomePageBack(ActionEvent event) {
        loadFXML("databaseFrontEnd","AdminLogin");
    }

    @FXML
    void logout(ActionEvent event) {
        loadFXML("databaseFrontEnd","AdminLogin");
    }

    @FXML
    void createNewEmp(ActionEvent event) { loadFXML("databaseBackEnd","CreateEmp"); }

    @FXML
    void insertTournament(ActionEvent event) {
        InsertTournamentController.prevFXML = "DatabaseHomePage";
        loadFXML("databaseBackEnd","InsertTournament");
    }

}
