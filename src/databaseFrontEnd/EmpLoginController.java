package databaseFrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utilityPackage.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmpLoginController implements Initializable {

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
    void homePage(ActionEvent event) {
        loadFXML("databaseFrontEnd/welcome","WelcomeToDatabase");
    }

    @FXML
    void enter(ActionEvent event) {
        if(validateEmpLogin(empID.getText(),empPassword.getText())){
            System.out.println("Login Successful");
            loadFXML("databaseBackEnd","EmpHome");
        }
        else UtilityClass.showDialog(Alert.AlertType.ERROR,"Login");
    }

    boolean validateEmpLogin(String empID,String empPassword){
        String sqlQuery = "Select * from employee";
        ResultSet resultSet = new oracleJDBC.Query(sqlQuery).querySet();
        try{
            while(resultSet.next()){
                String id = resultSet.getString("empName");
                String password = resultSet.getString("empPassword");
                if(id.equals(empID) && password.equals(empPassword)) return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empID.setPromptText("Enter Employee ID");
        empPassword.setPromptText("Enter Password");
    }
}
