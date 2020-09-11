package databaseFrontEnd.table;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Country extends RecursiveTreeObject <Country> {
    private StringProperty country_name;
    private StringProperty player_count;

    public Country(String country_name, String player_count) {
        this.country_name = new SimpleStringProperty(country_name);
        this.player_count = new SimpleStringProperty(player_count);
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

    public String getPlayer_count() {
        return player_count.get();
    }

    public StringProperty player_countProperty() {
        return player_count;
    }

    public void setPlayer_count(String player_count) {
        this.player_count.set(player_count);
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_name=" + country_name +
                ", player_count=" + player_count +
                '}';
    }
}
