package databaseBackEnd;

import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class InsertTournamentController {

    public static String prevFXML;

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
    void createNewTournament(ActionEvent event) {
        loadFXML("databaseBackEnd","InsertNewTournament");
    }

    @FXML
    void insertMatchIntoTournament(ActionEvent event) {
        loadFXML("databaseBackEnd","InsertNewMatch");
    }

    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseBackEnd",prevFXML);
    }

    @FXML
    void logOut(ActionEvent event) {
        if(prevFXML.equals("DatabaseHomePage")){
            loadFXML("databaseFrontEnd","AdminLogin");
        } else{
            loadFXML("databaseFrontEnd","EmpLogin");
        }
    }
}
