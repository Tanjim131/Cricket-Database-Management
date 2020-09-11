package databaseFrontEnd.table;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Player extends RecursiveTreeObject<Player> {
    private StringProperty country_name;
    private StringProperty player_gender;
    private StringProperty playing_role;
    private StringProperty batting_style;
    private StringProperty bowling_style;
    private StringProperty player_bdate;
    private StringProperty test_debut;
    private StringProperty ODI_debut;
    private StringProperty T20_debut;
    private StringProperty player_firstName;
    private StringProperty player_lastName;
    public static int playerColumnNumber = 11;

    public Player(){}

    public Player(ArrayList <String> arrayList){
        int i = 0;
        for (Field field : getClass().getDeclaredFields()){
            field.setAccessible(true);
            if(field.getType().isAssignableFrom(StringProperty.class)){
                try{
                    field.set(this,new SimpleStringProperty(arrayList.get(i)));
                    i++;
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getCountry_name() {
        return country_name.get();
    }

    public StringProperty country_nameProperty() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name.set(country_name);
    }

    public String getPlayer_gender() {
        return player_gender.get();
    }

    public StringProperty player_genderProperty() {
        return player_gender;
    }

    public void setPlayer_gender(String player_gender) {
        this.player_gender.set(player_gender);
    }

    public String getPlaying_role() {
        return playing_role.get();
    }

    public StringProperty playing_roleProperty() {
        return playing_role;
    }

    public void setPlaying_role(String playing_role) {
        this.playing_role.set(playing_role);
    }

    public String getBatting_style() {
        return batting_style.get();
    }

    public StringProperty batting_styleProperty() {
        return batting_style;
    }

    public void setBatting_style(String batting_style) {
        this.batting_style.set(batting_style);
    }

    public String getBowling_style() {
        return bowling_style.get();
    }

    public StringProperty bowling_styleProperty() {
        return bowling_style;
    }

    public void setBowling_style(String bowling_style) {
        this.bowling_style.set(bowling_style);
    }

    public String getPlayer_bdate() {
        return player_bdate.get();
    }

    public StringProperty player_bdateProperty() {
        return player_bdate;
    }

    public void setPlayer_bdate(String player_bdate) {
        this.player_bdate.set(player_bdate);
    }

    public String getTest_debut() {
        return test_debut.get();
    }

    public StringProperty test_debutProperty() {
        return test_debut;
    }

    public void setTest_debut(String test_debut) {
        this.test_debut.set(test_debut);
    }

    public String getODI_debut() {
        return ODI_debut.get();
    }

    public StringProperty ODI_debutProperty() {
        return ODI_debut;
    }

    public void setODI_debut(String ODI_debut) {
        this.ODI_debut.set(ODI_debut);
    }

    public String getT20_debut() {
        return T20_debut.get();
    }

    public StringProperty t20_debutProperty() {
        return T20_debut;
    }

    public void setT20_debut(String t20_debut) {
        this.T20_debut.set(t20_debut);
    }

    public String getPlayer_firstName() {
        return player_firstName.get();
    }

    public StringProperty player_firstNameProperty() {
        return player_firstName;
    }

    public void setPlayer_firstName(String player_firstName) {
        this.player_firstName.set(player_firstName);
    }

    public String getPlayer_lastName() {
        return player_lastName.get();
    }

    public StringProperty player_lastNameProperty() {
        return player_lastName;
    }

    public void setPlayer_lastName(String player_lastName) {
        this.player_lastName.set(player_lastName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "country_name=" + country_name +
                ", player_gender=" + player_gender +
                ", playing_role=" + playing_role +
                ", batting_style=" + batting_style +
                ", bowling_style=" + bowling_style +
                ", player_bdate=" + player_bdate +
                ", test_debut=" + test_debut +
                ", ODI_debut=" + ODI_debut +
                ", T20_debut=" + T20_debut +
                ", player_firstName=" + player_firstName +
                ", player_lastName=" + player_lastName +
                '}';
    }
}
