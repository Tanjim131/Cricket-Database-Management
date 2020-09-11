package databaseBackEnd;

import databaseFrontEnd.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import oracleJDBC.Insert;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.util.ArrayList;

public class CreateEmpController {
    @FXML
    private TextField empID;

    @FXML
    private PasswordField empPassword;

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
    void back(ActionEvent event) {
        loadFXML("databaseBackEnd","DatabaseHomePage");
    }

    @FXML
    void createEmp(ActionEvent event) {
        String id = empID.getText();
        String password = empPassword.getText();


        ArrayList arrayList = new ArrayList<String>(){{
            add(id);
            add(password);
        }};

        Insert insert = new oracleJDBC.Insert(Insert.EMP_SQL);

        if(insert.insertTable(arrayList)){
            System.out.println("Employee Creation Successful");
            UtilityClass.showDialog(Alert.AlertType.CONFIRMATION,"Employee");
        }
        else{
            System.out.println("Employee Creation Failure");
            UtilityClass.showDialog(Alert.AlertType.ERROR,"Employee");
        }
    }

    @FXML
    void logOut(ActionEvent event) {
        loadFXML("databaseFrontEnd","AdminLogin");
    }
}
