package databaseFrontEnd;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import utilityPackage.UtilityClass;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private JFXTextField adminUsername;

    @FXML
    private JFXPasswordField adminPassword;

    public void loadFXML(String packageName, String FXML){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + packageName + "/" + FXML + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Main.stage.setScene(scene);
    }

    private boolean validateLogin(String adminUsrname,String adminPswrd){
        return (adminUsrname.equals("admin") && adminPswrd.equals("admin"));
    }

    @FXML
    void enterDatabase(ActionEvent event) throws IOException {
        if(validateLogin(adminUsername.getText(),adminPassword.getText())){
            System.out.println("Login Successful!");
            loadFXML("databaseBackEnd","DatabaseHomePage");
        }
        else UtilityClass.showDialog(Alert.AlertType.ERROR,"Login");
    }


    @FXML
    void homePage(ActionEvent event) {
        loadFXML("databaseFrontEnd/welcome","WelcomeToDatabase");
    }
}
