package oceansfive.footymanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rohan on 2015-11-10.
 */
public class TournamentData {

    List<Tournament> tournamentList = new ArrayList();
    public TournamentData(){
        System.out.println("TournamentData created!");
    }

    public void addTournament(Tournament temp){//currently does not accept size or logo... should be implemented into Tournament
        tournamentList.add(temp);
    }

    public List<Tournament> getTournamentList(){
        return tournamentList;
    }


    /*
    List tournamentNames = Arrays.asList(
            "La Liga",
            "English Premier League",
            "Bundesliga",
            "Major League Soccer",
            "International Football",
            "RPL",
            "EUFA Champions League",
            "Europa League",
            "Football League Championship",
            "Ligue 1"
    );
=======
    ////THESE LISTS NEED TO BE REPLACED BY INSTANCES OF A TOURNAMENT
>>>>>>> origin/master

    Tournament first = new Tournament("La Liga", "Round Robin", 20, "@drawable/ic_logo_00");

    Tournament second = new Tournament("English Premier League", "Combinational", 21, "@drawable/ic_logo_01");

    Tournament third = new Tournament("Bundesliga", "Knock Out", 24, "@drawable/ic_logo_02");

    Tournament fourth = new Tournament("Major League Soccer", "Combinational", 13, "@drawable/ic_logo_03");

    Tournament fifth = new Tournament("International Football", "Round Robin", 16, "@drawable/ic_logo_04");

    Tournament sixth = new Tournament("RPL", "Knock Out", 17, "@drawable/ic_logo_05");

    Tournament seventh = new Tournament("EUFA Champions League", "Combinational", 12, "@drawable/ic_logo_06");

    Tournament eighth = new Tournament("Europa League", "Round Robin", 9, "@drawable/ic_logo_07");

    Tournament nineth = new Tournament("Football League Championship", "Knock Out", 22, "@drawable/ic_logo_08");

    Tournament tenth = new Tournament("Ligue 1", "Combinational", 21, "@drawable/ic_logo_09");



    List<Tournament> tournaments = Arrays.asList(
            first,
            second,
            third,
            fourth,
            fifth,
            sixth,
            seventh,
            eighth,
            nineth,
            tenth
    );

    private TournamentData() {
    }
    */
}
