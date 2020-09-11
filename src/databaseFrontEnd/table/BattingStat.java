package databaseFrontEnd.table;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BattingStat extends RecursiveTreeObject<BattingStat> {
    private StringProperty format;
    private StringProperty innings_no;
    private StringProperty not_outs;
    private StringProperty runs_scored;
    private StringProperty highest_score;
    private StringProperty average;
    private StringProperty balls_faced;
    private StringProperty strike_rate;
    private StringProperty centuries;
    private StringProperty half_centuries;
    private StringProperty fours;
    private StringProperty sixes;

    public BattingStat(String format, String innings_no, String not_outs, String runs_scored, String highest_score, String average, String balls_faced, String strike_rate, String centuries, String half_centuries, String fours, String sixes) {
        this.format = new SimpleStringProperty(format);
        this.innings_no = new SimpleStringProperty(innings_no);
        this.not_outs = new SimpleStringProperty(not_outs);
        this.runs_scored = new SimpleStringProperty(runs_scored);
        this.highest_score = new SimpleStringProperty(highest_score);
        this.average = new SimpleStringProperty(average);
        this.balls_faced = new SimpleStringProperty(balls_faced);
        this.strike_rate = new SimpleStringProperty(strike_rate);
        this.centuries = new SimpleStringProperty(centuries);
        this.half_centuries = new SimpleStringProperty(half_centuries);
        this.fours = new SimpleStringProperty(fours);
        this.sixes = new SimpleStringProperty(sixes);
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

    public String getNot_outs() {
        return not_outs.get();
    }

    public StringProperty not_outsProperty() {
        return not_outs;
    }

    public void setNot_outs(String not_outs) {
        this.not_outs.set(not_outs);
    }

    public String getRuns_scored() {
        return runs_scored.get();
    }

    public StringProperty runs_scoredProperty() {
        return runs_scored;
    }

    public void setRuns_scored(String runs_scored) {
        this.runs_scored.set(runs_scored);
    }

    public String getHighest_score() {
        return highest_score.get();
    }

    public StringProperty highest_scoreProperty() {
        return highest_score;
    }

    public void setHighest_score(String highest_score) {
        this.highest_score.set(highest_score);
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

    public String getBalls_faced() {
        return balls_faced.get();
    }

    public StringProperty balls_facedProperty() {
        return balls_faced;
    }

    public void setBalls_faced(String balls_faced) {
        this.balls_faced.set(balls_faced);
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

    public String getCenturies() {
        return centuries.get();
    }

    public StringProperty centuriesProperty() {
        return centuries;
    }

    public void setCenturies(String centuries) {
        this.centuries.set(centuries);
    }

    public String getHalf_centuries() {
        return half_centuries.get();
    }

    public StringProperty half_centuriesProperty() {
        return half_centuries;
    }

    public void setHalf_centuries(String half_centuries) {
        this.half_centuries.set(half_centuries);
    }

    public String getFours() {
        return fours.get();
    }

    public StringProperty foursProperty() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours.set(fours);
    }

    public String getSixes() {
        return sixes.get();
    }

    public StringProperty sixesProperty() {
        return sixes;
    }

    public void setSixes(String sixes) {
        this.sixes.set(sixes);
    }

    @Override
    public String toString() {
        return "BattingStat{" +
                "format=" + format +
                ", innings_no=" + innings_no +
                ", not_outs=" + not_outs +
                ", runs_scored=" + runs_scored +
                ", highest_score=" + highest_score +
                ", average=" + average +
                ", balls_faced=" + balls_faced +
                ", strike_rate=" + strike_rate +
                ", centuries=" + centuries +
                ", half_centuries=" + half_centuries +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}
