package databaseFrontEnd.table;

public class Batsman {
    private String name;
    private int  runs;
    private int balls;
    private int fours;
    private int sixes;
    private String notOut;

    public Batsman(String name, int runs, int balls, int fours, int sixes, String notOut) {
        this.name = name;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.notOut = notOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public String getNotOut() {
        return notOut;
    }

    public void setNotOut(String notOut) {
        this.notOut = notOut;
    }

    @Override
    public String toString() {
        return "Batsman{" +
                "name='" + name + '\'' +
                ", runs=" + runs +
                ", balls=" + balls +
                ", fours=" + fours +
                ", sixes=" + sixes +
                ", notOut='" + notOut + '\'' +
                '}';
    }
}
