package databaseFrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/databaseFrontEnd/welcome/WelcomeToDatabase.fxml"));
        primaryStage.setTitle("Cricket Database Management");
        primaryStage.setScene(new Scene(root, 1200, 850));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
