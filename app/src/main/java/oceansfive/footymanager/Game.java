package oceansfive.footymanager;

/**
 * Created by Hottie2body on 15-11-15.
 */
public class Game {
    //I took out location and date as it is weird to code for now
    private int team1Score = -1;
    private int team2Score = -1;
    private Team winner = null;
    private Team team1;
    private Team team2;

    public Game(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    public Game(Team team1){
        this.team1 = team1;
    }

    //sets the score of a game
    public void enterScore(int score1, int score2){
        team1Score = score1;
        team2Score = score2;
        if (score1>score2){
            winner = team1;
        }
        else{
            winner = team2;
        }
    }

    public Team getTeam1(){
        return team1;
    }

    public Team getTeam2(){
        return team2;
    }

    public Team getWinner(){
        return winner;
    }

    public void setTeam2(Team team2){
        this.team2 = team2;
    }

    public String getScore(){
        return team1Score + " - " + team2Score;
    }

}
