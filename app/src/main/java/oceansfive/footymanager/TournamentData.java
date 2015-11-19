package oceansfive.footymanager;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rohan on 2015-11-10.
 */
public class TournamentData {
    private static TournamentData ourInstance = new TournamentData();

    public static TournamentData getInstance() {
        return ourInstance;
    }
    ////THESE LISTS NEED TO BE REPLACED BY INSTANCES OF A TOURNAMENT
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

    List tournamentSizes = Arrays.asList(
            20,
            21,
            24,
            13,
            16,
            17,
            12,
            9,
            22,
            21
    );

    List tournamentLogos = Arrays.asList(
            "@drawable/logo1",
            "@drawable/logo2",
            "@drawable/logo3",
            "@drawable/logo4",
            "@drawable/logo5",
            "@drawable/logo6",
            "@drawable/logo7",
            "@drawable/logo8",
            "@drawable/logo9",
            "@drawable/logo10"
    );

    List tournamentTypes = Arrays.asList(
            "Round Robin",
            "Combinational",
            "Knock Out",
            "Round Robin",
            "Combinational",
            "Knock Out",
            "Round Robin",
            "Combinational",
            "Knock Out",
            "Round Robin"
    );




    private TournamentData() {
    }
}
