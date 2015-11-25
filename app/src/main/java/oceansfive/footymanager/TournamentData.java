package oceansfive.footymanager;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by rohan on 2015-11-10.
 */
public class TournamentData {
    private static TournamentData ourInstance = new TournamentData();

    public static TournamentData getInstance() {
        return ourInstance;
    }
    ////THESE LISTS NEED TO BE REPLACED BY INSTANCES OF A TOURNAMENT

    Tournament one = new Tournament("La Liga", "Round Robin", 6, "@drawable/logo0");
    Tournament two = new Tournament("English Premier League", "Combinational", 4, "@drawable/logo1");
    Tournament three = new Tournament("Bundesliga", "Knock Out", 24, "@drawable/logo2");
    Tournament four = new Tournament("Major League Soccer", "Round Robin", 13, "@drawable/logo3");
    Tournament five = new Tournament("International Football", "Combinational", 16, "@drawable/logo4");
    Tournament six = new Tournament("RPL", "Knock Out", 17, "@drawable/logo5");
    Tournament seven = new Tournament("EUFA Champions League", "Round Robin", 12, "@drawable/logo6");
    Tournament eight = new Tournament("Europa League", "Combinational", 9, "@drawable/logo7");
    Tournament nine = new Tournament("Football League Championship", "Knock Out", 22, "@drawable/logo8");



    List<Tournament> tournaments = new LinkedList<Tournament>(Arrays.asList(
            one,
            two,
            three,
            four,
            five,
            six,
            seven,
            eight,
            nine
    ));




    private TournamentData() {
    }
}
