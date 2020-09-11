package utilityPackage;

import databaseFrontEnd.table.Batsman;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BatsmanTable {
    private TableView <Batsman> batsmanTableView;
    private TableColumn<Batsman,String> batsmanName;
    private TableColumn <Batsman,Integer> runs;
    private TableColumn <Batsman,Integer> ballsFaced;
    private TableColumn <Batsman,Integer> fours;
    private TableColumn <Batsman,Integer> sixes;
    private TableColumn <Batsman,String> outStatus;

    public BatsmanTable(TableView<Batsman> batsmanTableView, TableColumn<Batsman, String> batsmanName, TableColumn<Batsman, Integer> runs, TableColumn<Batsman, Integer> ballsFaced, TableColumn<Batsman, Integer> fours, TableColumn<Batsman, Integer> sixes, TableColumn<Batsman, String> outStatus) {
        this.batsmanTableView = batsmanTableView;
        this.batsmanName = batsmanName;
        this.runs = runs;
        this.ballsFaced = ballsFaced;
        this.fours = fours;
        this.sixes = sixes;
        this.outStatus = outStatus;
    }

    public TableView<Batsman> getBatsmanTableView() {
        return batsmanTableView;
    }

    public void setBatsmanTableView(TableView<Batsman> batsmanTableView) {
        this.batsmanTableView = batsmanTableView;
    }

    public TableColumn<Batsman, String> getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(TableColumn<Batsman, String> batsmanName) {
        this.batsmanName = batsmanName;
    }

    public TableColumn<Batsman, Integer> getRuns() {
        return runs;
    }

    public void setRuns(TableColumn<Batsman, Integer> runs) {
        this.runs = runs;
    }

    public TableColumn<Batsman, Integer> getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(TableColumn<Batsman, Integer> ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public TableColumn<Batsman, Integer> getFours() {
        return fours;
    }

    public void setFours(TableColumn<Batsman, Integer> fours) {
        this.fours = fours;
    }

    public TableColumn<Batsman, Integer> getSixes() {
        return sixes;
    }

    public void setSixes(TableColumn<Batsman, Integer> sixes) {
        this.sixes = sixes;
    }

    public TableColumn<Batsman, String> getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(TableColumn<Batsman, String> outStatus) {
        this.outStatus = outStatus;
    }
}
