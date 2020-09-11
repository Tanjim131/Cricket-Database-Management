package utilityPackage;

import databaseFrontEnd.table.Bowler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BowlerTable {
    private TableView <Bowler> bowlerTableView;
    private TableColumn <Bowler,String> bowlerName;
    private TableColumn <Bowler,Integer> overs;
    private TableColumn <Bowler,Integer> runsGiven;
    private TableColumn <Bowler,Integer> wicketsTaken;

    public BowlerTable(TableView<Bowler> bowlerTableView, TableColumn<Bowler, String> bowlerName, TableColumn<Bowler, Integer> overs, TableColumn<Bowler, Integer> runsGiven, TableColumn<Bowler, Integer> wicketsTaken) {
        this.bowlerTableView = bowlerTableView;
        this.bowlerName = bowlerName;
        this.overs = overs;
        this.runsGiven = runsGiven;
        this.wicketsTaken = wicketsTaken;
    }

    public TableView<Bowler> getBowlerTableView() {
        return bowlerTableView;
    }

    public void setBowlerTableView(TableView<Bowler> bowlerTableView) {
        this.bowlerTableView = bowlerTableView;
    }

    public TableColumn<Bowler, String> getBowlerName() {
        return bowlerName;
    }

    public void setBowlerName(TableColumn<Bowler, String> bowlerName) {
        this.bowlerName = bowlerName;
    }

    public TableColumn<Bowler, Integer> getOvers() {
        return overs;
    }

    public void setOvers(TableColumn<Bowler, Integer> overs) {
        this.overs = overs;
    }

    public TableColumn<Bowler, Integer> getRunsGiven() {
        return runsGiven;
    }

    public void setRunsGiven(TableColumn<Bowler, Integer> runsGiven) {
        this.runsGiven = runsGiven;
    }

    public TableColumn<Bowler, Integer> getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(TableColumn<Bowler, Integer> wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}
