package oceansfive.footymanager;

/**
 * Created by Hottie2body on 15-11-15.
 */
public class Game {
    private String location;
    private String date;
    private int homeScore = undefined;
    private int awayScore = undefined;
    private Team winner = null;
    private Team homeTeam;
    private Team awayTeam;

    public void Game(String location, String date, Team homeTeam, Team awayTeam){
        this.location=location;
        this.date=date;
        this.homeTeam=homeTeam;
        this.awayTeam=awayTeam;
    }

    public void enterScore(int home, int away){
        homeScore = home;
        awayScore = away;
        if (home>away){
            winner = homeTeam;
        }
        else{
            winner = awayTeam;
        }
    }

    public String getLocation(){
        return location;
    }

    public String getDate(){
        return date;
    }

    public Team getHomeTeam(){
        return homeTeam;
    }

    public Team getAwayTeam(){
        return awayTeam;
    }

    public Team getWinner(){
        return winner;
    }

    public String getScore(){
        return homeScore + " - " + awayScore;
    }

}
