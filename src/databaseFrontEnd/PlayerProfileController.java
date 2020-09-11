package databaseFrontEnd;

import databaseFrontEnd.table.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class PlayerProfileController implements Initializable {

    public static TreeItem <Player> playerForProfile;
    public static String prevFXML;

    @FXML
    private Text playerName;

    @FXML
    private Text country;

    @FXML
    private Text playerFullName;

    @FXML
    private Text bdate;

    @FXML
    private Text playerAge;

    @FXML
    private Text playingRole;

    @FXML
    private Text battingStyle;

    @FXML
    private Text bowlingStyle;

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


    private void setPlayerAgeAndBdate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        LocalDate fromDate = LocalDate.parse(playerForProfile.getValue().getPlayer_bdate(),dateTimeFormatter);
        LocalDate toDate = LocalDate.now();
        LocalDate localDate = LocalDate.from(fromDate);
        long years = localDate.until(toDate,ChronoUnit.YEARS);
        localDate = localDate.plusYears(years);
        long days = localDate.until(toDate,ChronoUnit.DAYS);
        playerAge.setText(years + " years " + days + " days");

        bdate.setText(fromDate.format(DateTimeFormatter.ofPattern("MMMM dd , yyyy")));
    }


    private void setup(){
        playerName.setText(playerForProfile.getValue().getPlayer_lastName() + "," + playerForProfile.getValue().getPlayer_firstName());
        country.setText(playerForProfile.getValue().getCountry_name());
        playerFullName.setText(playerForProfile.getValue().getPlayer_firstName() + " " + playerForProfile.getValue().getPlayer_lastName());
        setPlayerAgeAndBdate();
        playingRole.setText(playerForProfile.getValue().getPlaying_role());
        battingStyle.setText(playerForProfile.getValue().getBatting_style());
        if(playerForProfile.getValue().getBowling_style() == null){
            bowlingStyle.setText("Nil");
        }else{
            bowlingStyle.setText(playerForProfile.getValue().getBowling_style());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setup();
    }

    @FXML
    void playerStats(ActionEvent event) {
        loadFXML("databaseFrontEnd/playerStats","PlayerStats");
    }


    @FXML
    void back(ActionEvent event) {
        loadFXML("databaseFrontEnd/searchPlayer",prevFXML);
    }
}
