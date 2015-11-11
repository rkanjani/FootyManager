package oceansfive.footymanager;

/**
 * Created by rohan on 2015-11-10.
 */
public class TournamentData {
    private static TournamentData ourInstance = new TournamentData();

    public static TournamentData getInstance() {
        return ourInstance;
    }

    String [] tournaments = new String [] {
            "La Liga",
            "English Premier League",
            "Bundesliga",
            "Spanish Premier League",
            "Major League Soccer",
            "International Football",
            "RPL",
            "EUFA Champions League",
            "Europa League",
            "Football League Championship",
            "Ligue 1"

    };


    private TournamentData() {
    }
}
