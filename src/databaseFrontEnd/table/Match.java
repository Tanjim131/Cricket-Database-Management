package databaseFrontEnd.table;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Match{
    private String duration;
    private String venue;
    private String toss;
    private String winner;
    private String scores;

    public Match(ArrayList <String> arrayList,ArrayList <Score> scores){
        int i = 2; // start from venue

        for (Field field : getClass().getDeclaredFields()){
            field.setAccessible(true);
            if(field.getType().isAssignableFrom(String.class) && field.getName().equals("duration")){
                try{
                    if(arrayList.get(0).equals(arrayList.get(1))){
                        // that is if start_date and end_dates are same
                        field.set(this,arrayList.get(0));

                    } else{
                        //this is a test match
                        String [] date = arrayList.get(0).split(",");
                        // Now the start date which was like 23 November ,2017
                        // will become date[0] = 23 November and date[1] = 2017
                        field.set(this,date[0] + "- " + arrayList.get(1));
                    }
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(field.getType().isAssignableFrom(String.class) && field.getName().equals("scores")){
                try{
                    field.set(this,listToString(scores));
                }catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(field.getType().isAssignableFrom(String.class)){
                try{
                    field.set(this,arrayList.get(i));
                    i++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    private String listToString(ArrayList <Score> arrayList){
        StringBuilder result = new StringBuilder();
        for(Score score : arrayList){
            result.append(score);
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public String toString() {
        return "Match{" +
                "duration='" + duration + '\'' +
                ", venue='" + venue + '\'' +
                ", toss='" + toss + '\'' +
                ", winner='" + winner + '\'' +
                ", scores='" + scores + '\'' +
                '}';
    }
}
