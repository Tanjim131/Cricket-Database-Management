package utilityPackage;

import databaseFrontEnd.scorecard.InningsController;
import databaseFrontEnd.table.Batsman;
import databaseFrontEnd.table.Bowler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import oracleJDBC.Pl_Sql_Query;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UtilityClass {
    public static Alert showDialog(Alert.AlertType alertType, String message){
        Alert alert = null;
        if(message.equals("Login") && alertType.toString().equals("ERROR")){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Login Unsuccessful");
                alert.setContentText("The username and/or password is incorrect.");
                alert.showAndWait();
        }
        else if(message.equals("Cannot go to Next Innings")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Innings Error");
            alert.setHeaderText("Cannot Load Page");
            alert.setContentText("Cannot go to next page.");
            alert.showAndWait();
        }
        else if(message.equals("Next Page")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to go to the next page?", foo, bar);

            alert.setTitle("Navigation confirmation");
        }
        else if(message.equals("Create 1st Innings")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to create 1st Innings?", foo, bar);

            alert.setTitle("1st Innings creation confirmation.");
        }
        else if(message.equals("Create 2nd Innings")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to create 2nd Innings?", foo, bar);

            alert.setTitle("2nd Innings creation confirmation.");
        }
        else if(message.equals("Proceed to 2nd Innings")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to Proceed to 2nd Innings?", foo, bar);

            alert.setTitle("Innings confirmation.");
        }
        else if(message.equals("Create Match") && alertType.toString().equals("CONFIRMATION")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to create this match?", foo, bar);

            alert.setTitle("Match creation confirmation.");
        }
        else if(message.equals("Do you want to exit?") && alertType.toString().equals("CONFIRMATION")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit?", foo, bar);

            alert.setTitle("Database exit confirmation");
        }
        else if(message.equals("Finish Innings") && alertType.toString().equals("CONFIRMATION")){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType bar = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to finish?", foo, bar);

            alert.setTitle("Match creation confirmation.");
        }
        else if(message.equals("Cannot go to home page")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Page Load Error");
            alert.setHeaderText("Cannot Go to Home Page");
            alert.setContentText("You haven't provided necessary innings information.");
            alert.showAndWait();
        }
        else if(message.equals("Cannot Go To Next Page")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Innings Error");
            alert.setHeaderText("Cannot Load Page");
            alert.setContentText("You haven't provided necessary innings information.");
            alert.showAndWait();
        }
        else if(message.equals("Create Innings")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Innings Error");
            alert.setHeaderText("Cannot Create Innings");
            alert.setContentText("You have to provide 2nd innings information for Test format.");
            alert.showAndWait();
        }
        else if(message.equals("No Matches Found")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Match Error");
            alert.setHeaderText("No matches found");
            alert.setContentText("There are currently no matches in this series.");
            alert.showAndWait();
        }
        else if(message.equals("Next Innings")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Innings Error");
            alert.setHeaderText("Cannot Load Page");
            alert.setContentText("There is no 2nd innings for ODI or T20 format.");
            alert.showAndWait();
        }
        else if(message.equals("Search Bilateral Series Same Country")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Search Error");
            alert.setContentText("Country names cannot be same.");
            alert.showAndWait();
        }
        else if(message.equals("Search Bilateral Series Null Country")){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Search Error");
            alert.setContentText("Please select a valid country name.");
            alert.showAndWait();
        }
        else{
            if(alertType.toString().equals("CONFIRMATION")){
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Database Insertion");
                alert.setHeaderText("Insertion Successful");
                alert.setContentText("The " + message + " has been successfully inserted into the database.");
                alert.showAndWait();
            }
            else if(alertType.toString().equals("ERROR")){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Insertion Unsuccessful");
                alert.setContentText("The " + message + " could not be inserted into the database.");
                alert.showAndWait();
            }
        }
        return alert;
    }

    public static String dateFormat(String startDate,String endDate){
        String [] arr = startDate.split(" ");

        String day = arr[0];
        String year;
        if(endDate.equals("")) year = arr[2].substring(1,arr[2].length());
        else year = endDate.substring(endDate.length() - 4,endDate.length());
        String month = arr[1].substring(0,3);

        String date = day + "-" + month + "-" + year;

        return date;
    }

    public static InningsPair getInningsPlayer(BatsmanTable batsmanTable,BowlerTable bowlerTable,String className,
                                               Label batting,Label bowling){

        ArrayList <Bowler> bowlerArrayList = null;
        ArrayList <Batsman> batsmanArrayList = null;

        String date = dateFormat(InningsController.startDate,InningsController.endDate);
        String query = "{call FETCH_MATCH_IP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        Pl_Sql_Query plSqlQuery = new Pl_Sql_Query(query);
        try{

            System.out.println(InningsController.teamA);
            System.out.println(InningsController.teamB);
            System.out.println(date);

            plSqlQuery.getCst().setString(1,InningsController.teamA);
            plSqlQuery.getCst().setString(2,InningsController.teamB);
            plSqlQuery.getCst().setString(3,date);

            plSqlQuery.getCst().registerOutParameter(4, Types.ARRAY,"T_AR"); // innings_name

            // batting team name and bowling team name will be set by Label
            plSqlQuery.getCst().registerOutParameter(5, Types.ARRAY,"T_AR"); // batting team name
            plSqlQuery.getCst().registerOutParameter(6, Types.ARRAY,"T_AR"); // bowling team name

            plSqlQuery.getCst().registerOutParameter(7, Types.ARRAY,"T_AR"); // batsman name
            plSqlQuery.getCst().registerOutParameter(8, Types.ARRAY,"N_AR"); // runs scored
            plSqlQuery.getCst().registerOutParameter(9, Types.ARRAY,"N_AR"); // bowls faced
            plSqlQuery.getCst().registerOutParameter(10, Types.ARRAY,"N_AR"); // 4s
            plSqlQuery.getCst().registerOutParameter(11, Types.ARRAY,"N_AR"); // 6s
            plSqlQuery.getCst().registerOutParameter(12, Types.ARRAY,"T_AR"); // is Out

            plSqlQuery.getCst().registerOutParameter(13, Types.ARRAY,"T_AR"); // bowler name
            plSqlQuery.getCst().registerOutParameter(14, Types.ARRAY,"N_AR"); // runs conceded
            plSqlQuery.getCst().registerOutParameter(15, Types.ARRAY,"N_AR"); // wickets taken
            plSqlQuery.getCst().registerOutParameter(16, Types.ARRAY,"N_AR"); // overs bowled

            plSqlQuery.getCst().registerOutParameter(17, Types.ARRAY,"N_AR"); // ib
            plSqlQuery.getCst().registerOutParameter(18, Types.ARRAY,"N_AR"); // ifi

            plSqlQuery.getCst().executeUpdate();

            Array array = plSqlQuery.getCst().getArray(17);

            BigDecimal[] ibArr = (BigDecimal []) array.getArray();

            array = plSqlQuery.getCst().getArray(5);
            String [] tbat = (String[]) array.getArray();

            array = plSqlQuery.getCst().getArray(6);
            String [] tbowl = (String []) array.getArray();

            // get 1stInningsBatting inf

            int length = 0;
            int stIndex = 0;

            switch (className){
                case "First Innings":
                    length = (ibArr[1].subtract(ibArr[0])).intValue();
                    stIndex = ibArr[0].intValue() - 1;
                    batting.setText(tbat[0]);
                    bowling.setText(tbowl[0]);
                    break;

                case "Second Innings":
                    length = (ibArr[2].subtract(ibArr[1])).intValue();
                    stIndex = ibArr[1].intValue() - 1;
                    batting.setText(tbat[1]);
                    bowling.setText(tbowl[1]);
                    break;

                case "Third Innings":
                    length = (ibArr[3].subtract(ibArr[2])).intValue();
                    stIndex = ibArr[2].intValue() - 1;
                    batting.setText(tbat[2]);
                    bowling.setText(tbowl[2]);
                    break;

                case "Fourth Innings":
                    length = (ibArr[4].subtract(ibArr[3])).intValue();
                    stIndex = ibArr[3].intValue() - 1;
                    batting.setText(tbat[3]);
                    bowling.setText(tbowl[3]);
                    break;
            }

            array = plSqlQuery.getCst().getArray(7);
            String [] batsmanNameArr = (String[]) array.getArray();

            array = plSqlQuery.getCst().getArray(8);
            BigDecimal [] runsArr = (BigDecimal []) array.getArray();

            array = plSqlQuery.getCst().getArray(9);
            BigDecimal [] bowlFacedArr = (BigDecimal []) array.getArray();

            array = plSqlQuery.getCst().getArray(10);
            BigDecimal [] foursArr = (BigDecimal []) array.getArray();

            array = plSqlQuery.getCst().getArray(11);
            BigDecimal [] sixesArr = (BigDecimal []) array.getArray();

            array = plSqlQuery.getCst().getArray(12);
            String [] isOutArr = (String[]) array.getArray();

            batsmanArrayList = new ArrayList<>();

            for(int i = stIndex ; i < length + stIndex ; i++){
                String name = batsmanNameArr[i];
                int runs = runsArr[i].intValue();
                int bowls = bowlFacedArr[i].intValue();
                int fours = foursArr[i].intValue();
                int sixes = sixesArr[i].intValue();
                String isOut = isOutArr[i];

                Batsman batsman =  new Batsman(name,runs,bowls,fours,sixes,isOut);

                if(isOut.equals("Yes")){
                    batsman.setNotOut("No");
                } else{
                    batsman.setNotOut("Yes");
                }

                batsmanArrayList.add(batsman);
            }

            // batsman Info done

            // get Bowler info

            array = plSqlQuery.getCst().getArray(18);
            BigDecimal [] ifiArr = (BigDecimal []) array.getArray();

            length = (ifiArr[1].subtract(ifiArr[0])).intValue();

            array = plSqlQuery.getCst().getArray(13);
            String [] bowlerNameArr = (String[]) array.getArray();

            array = plSqlQuery.getCst().getArray(14);
            BigDecimal [] runsConcededArr = (BigDecimal[]) array.getArray();

            array = plSqlQuery.getCst().getArray(15);
            BigDecimal [] wicketsArr = (BigDecimal[]) array.getArray();

            array = plSqlQuery.getCst().getArray(16);
            BigDecimal [] oversArr = (BigDecimal[]) array.getArray();

            switch (className){
                case "First Innings":
                    length = (ifiArr[1].subtract(ifiArr[0])).intValue();
                    stIndex = ifiArr[0].intValue() - 1;
                    break;

                case "Second Innings":
                    length = (ifiArr[2].subtract(ifiArr[1])).intValue();
                    stIndex = ifiArr[1].intValue() - 1;
                    break;

                case "Third Innings":
                    length = (ifiArr[3].subtract(ifiArr[2])).intValue();
                    stIndex = ifiArr[2].intValue() - 1;
                    break;

                case "Fourth Innings":
                    length = (ifiArr[4].subtract(ifiArr[3])).intValue();
                    stIndex = ifiArr[3].intValue() - 1;
                    break;
            }

            bowlerArrayList = new ArrayList<>();

            for(int i = stIndex ; i < length + stIndex ; i++){
                String name = bowlerNameArr[i];
                int runs = runsConcededArr[i].intValue();
                int wickets = wicketsArr[i].intValue();
                int overs = oversArr[i].intValue();

                Bowler bowler = new Bowler(name,overs,runs,wickets);

                bowlerArrayList.add(bowler);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return new InningsPair(batsmanArrayList,bowlerArrayList);
    }

    public UtilityClass() {}
}
