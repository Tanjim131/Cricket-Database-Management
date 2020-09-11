package databaseFrontEnd.table;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BowlingStat extends RecursiveTreeObject<BowlingStat> {
    private StringProperty format;
    private StringProperty innings_no;
    private StringProperty balls_bowled;
    private StringProperty runs_conceded;
    private StringProperty wickets_taken;
    private StringProperty best_figure;
    private StringProperty average;
    private StringProperty strike_rate;
    private StringProperty economoy;
    private StringProperty five_wickets;

    public BowlingStat(String format, String innings_no, String balls_bowled, String runs_conceded, String
            wickets_taken, String best_figure, String average, String strike_rate, String economoy, String five_wickets) {
        this.format = new SimpleStringProperty(format);
        this.innings_no = new SimpleStringProperty(innings_no);
        this.balls_bowled = new SimpleStringProperty(balls_bowled);
        this.runs_conceded = new SimpleStringProperty(runs_conceded);
        this.wickets_taken = new SimpleStringProperty(wickets_taken);
        this.best_figure = new SimpleStringProperty(best_figure);
        this.average = new SimpleStringProperty(average);
        this.strike_rate = new SimpleStringProperty(strike_rate);
        this.economoy = new SimpleStringProperty(economoy);
        this.five_wickets = new SimpleStringProperty(five_wickets);
    }

    public String getFormat() {
        return format.get();
    }

    public StringProperty formatProperty() {
        return format;
    }

    public void setFormat(String format) {
        this.format.set(format);
    }

    public String getInnings_no() {
        return innings_no.get();
    }

    public StringProperty innings_noProperty() {
        return innings_no;
    }

    public void setInnings_no(String innings_no) {
        this.innings_no.set(innings_no);
    }

    public String getBalls_bowled() {
        return balls_bowled.get();
    }

    public StringProperty balls_bowledProperty() {
        return balls_bowled;
    }

    public void setBalls_bowled(String balls_bowled) {
        this.balls_bowled.set(balls_bowled);
    }

    public String getRuns_conceded() {
        return runs_conceded.get();
    }

    public StringProperty runs_concededProperty() {
        return runs_conceded;
    }

    public void setRuns_conceded(String runs_conceded) {
        this.runs_conceded.set(runs_conceded);
    }

    public String getWickets_taken() {
        return wickets_taken.get();
    }

    public StringProperty wickets_takenProperty() {
        return wickets_taken;
    }

    public void setWickets_taken(String wickets_taken) {
        this.wickets_taken.set(wickets_taken);
    }

    public String getBest_figure() {
        return best_figure.get();
    }

    public StringProperty best_figureProperty() {
        return best_figure;
    }

    public void setBest_figure(String best_figure) {
        this.best_figure.set(best_figure);
    }

    public String getAverage() {
        return average.get();
    }

    public StringProperty averageProperty() {
        return average;
    }

    public void setAverage(String average) {
        this.average.set(average);
    }

    public String getStrike_rate() {
        return strike_rate.get();
    }

    public StringProperty strike_rateProperty() {
        return strike_rate;
    }

    public void setStrike_rate(String strike_rate) {
        this.strike_rate.set(strike_rate);
    }

    public String getEconomoy() {
        return economoy.get();
    }

    public StringProperty economoyProperty() {
        return economoy;
    }

    public void setEconomoy(String economoy) {
        this.economoy.set(economoy);
    }

    public String getFive_wickets() {
        return five_wickets.get();
    }

    public StringProperty five_wicketsProperty() {
        return five_wickets;
    }

    public void setFive_wickets(String five_wickets) {
        this.five_wickets.set(five_wickets);
    }

    @Override
    public String toString() {
        return "BowlingStat{" +
                "format=" + format +
                ", innings_no=" + innings_no +
                ", balls_bowled=" + balls_bowled +
                ", runs_conceded=" + runs_conceded +
                ", wickets_taken=" + wickets_taken +
                ", best_figure=" + best_figure +
                ", average=" + average +
                ", strike_rate=" + strike_rate +
                ", economoy=" + economoy +
                ", five_wickets=" + five_wickets +
                '}';
    }
}
