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

    static String [] logos={"logo1","logo2","logo3","logo4"};
    static String [] teamLogos={"logo1","logo2"};


    private static TournamentData ourInstance = new TournamentData();

    public static TournamentData getInstance() {
        return ourInstance;
    }
    ////THESE LISTS NEED TO BE REPLACED BY INSTANCES OF A TOURNAMENT

    Team t1 = new Team("Real Madrid", "logo0");
    Team t2 = new Team("Man U", "logo1");
    Team t3 = new Team("Barcelona", "logo2");
    Team t4 = new Team("TFC", "logo3");
    Team t5 = new Team("Liverpool", "logo1");
    Team t6 = new Team("Chelsea", "logo0");
    Team t7 = new Team("Manchester City", "logo1");
    Team t8 = new Team("BVB", "logo2");
    Team t9 = new Team("Spain", "logo3");
    Team t10 = new Team("Germany", "logo1");
    Team t11 = new Team("Argentina", "logo0");
    Team t12 = new Team("Arsenal", "logo1");
    Team t13 = new Team("Birmingham City", "logo2");
    Team t14 = new Team("Montreal Wings", "logo3");
    Team t15 = new Team("Ajax Crawlers", "logo1");
    Team t16 = new Team("Vaughn Birds", "logo0");


    Team [] teams4 = new Team [] {t1, t2, t3, t4};
    Team [] teams8 = new Team [] {t1, t2, t3, t4, t5, t6, t7, t8};
    Team [] teams2 = new Team [] {t1, t2};
    Team [] teams16 = new Team [] {t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16};


    Tournament one = new Tournament("La Liga", "Round Robin", 8, "logo0", teams8);
    Tournament two = new Tournament("English Premier League", "Combinational", 8, "logo1", teams8);
    Tournament three = new Tournament("Bundesliga", "Knock Out", 8, "logo2", teams8);






    List<Tournament> tournaments = new LinkedList<Tournament>(Arrays.asList(
            one,
            two,
            three
    ));




    private TournamentData() {
    }
}
