package databaseFrontEnd.table;

public class Bowler {
    private String name;
    private int overs;
    private int runs;
    private int wickets;

    public Bowler(String name, int overs, int runs, int wickets) {
        this.name = name;
        this.overs = overs;
        this.runs = runs;
        this.wickets = wickets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOvers() {
        return overs;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "Bowler{" +
                "name='" + name + '\'' +
                ", overs=" + overs +
                ", runs=" + runs +
                ", wickets=" + wickets +
                '}';
    }
}
