package databaseFrontEnd.table;

public class Score {
    private String innings_name;
    private String team_batting;
    private String runs_scored;
    private String wickets_fallen;
    private String overs;

    public Score(String innings_name, String team_batting, String runs_scored, String wickets_fallen, String overs) {
        this.innings_name = innings_name;
        this.team_batting = team_batting;
        this.runs_scored = runs_scored;
        this.wickets_fallen = wickets_fallen;
        this.overs = overs;
    }

    public String getInnings_name() {
        return innings_name;
    }

    public void setInnings_name(String innings_name) {
        this.innings_name = innings_name;
    }

    public String getTeam_batting() {
        return team_batting;
    }

    public void setTeam_batting(String team_batting) {
        this.team_batting = team_batting;
    }

    public String getRuns_scored() {
        return runs_scored;
    }

    public void setRuns_scored(String runs_scored) {
        this.runs_scored = runs_scored;
    }

    public String getWickets_fallen() {
        return wickets_fallen;
    }

    public void setWickets_fallen(String wickets_fallen) {
        this.wickets_fallen = wickets_fallen;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    @Override
    public String toString() {
        String format = null;

        if(innings_name == null){
            format = team_batting + " : " + runs_scored + "-" + wickets_fallen + " Overs: " + overs;
        } else{
            format = team_batting + " " + innings_name + " innings " + ": " + runs_scored + "-" + wickets_fallen + " Overs: " + overs;
        }
        return format;
    }
}
