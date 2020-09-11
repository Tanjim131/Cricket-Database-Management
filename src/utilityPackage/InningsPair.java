package utilityPackage;

import databaseFrontEnd.table.Batsman;
import databaseFrontEnd.table.Bowler;

import java.util.ArrayList;

public class InningsPair {
    private ArrayList <Batsman> batsmanArrayList;
    private ArrayList <Bowler> bowlerArrayList;

    public InningsPair(ArrayList<Batsman> batsmanArrayList, ArrayList<Bowler> bowlerArrayList) {
        this.batsmanArrayList = batsmanArrayList;
        this.bowlerArrayList = bowlerArrayList;
    }

    public ArrayList<Batsman> getBatsmanArrayList() {
        return batsmanArrayList;
    }

    public void setBatsmanArrayList(ArrayList<Batsman> batsmanArrayList) {
        this.batsmanArrayList = batsmanArrayList;
    }

    public ArrayList<Bowler> getBowlerArrayList() {
        return bowlerArrayList;
    }

    public void setBowlerArrayList(ArrayList<Bowler> bowlerArrayList) {
        this.bowlerArrayList = bowlerArrayList;
    }
}
