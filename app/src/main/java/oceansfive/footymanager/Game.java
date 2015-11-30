package oceansfive.footymanager;

/**
 * Created by Hottie2body on 15-11-15.
 */
public class Game {
    //I took out location and date as it is weird to code for now
    private int team1Score;
    private int team2Score;
    private int team1Yellow = 0;
    private int team2Yellow = 0;
    private int team1Red = 0;
    private int team2Red = 0;
    private Team winner = null;
    private Team team1;
    private Team team2;
    private boolean gamePlayed;

    public Game(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
        team1Score = -1;
        team2Score = -1;
        gamePlayed = false;
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

    public boolean isGamePlayed(){
        return gamePlayed;
    }

    public Team getTeam1(){
        return team1;
    }

    public int getTeam1Score(){
        return team1Score;
    }
    public int getTeam2Score(){
        return team2Score;
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

    public void finishGame(){
        gamePlayed = true;
    }

    public void setTeam1Fouls(int [] fouls){
        team1Yellow = fouls[0];
        team1Red = fouls[1];

        int currentReds = team1.getRedCards();
        int currentYellows = team1.getYellowCards();

        team1.setRedCards(currentReds+team1Red);
        team1.setYellowCards(currentYellows+team1Yellow);
    }
    public void setTeam2Fouls(int [] fouls){
        team2Yellow = fouls[0];
        team2Red = fouls[1];

        int currentReds = team2.getRedCards();
        int currentYellows = team2.getYellowCards();

        team2.setRedCards(currentReds+team2Red);
        team2.setYellowCards(currentYellows+team2Yellow);
    }
    public int[] getTeam1Fouls(){
        int [] fouls = new int[2];
        fouls[0] = team1Yellow;
        fouls[1] = team1Red;
        return fouls;
    }
    public int[] getTeam2Fouls(){
        int [] fouls = new int[2];
        fouls[0] = team2Yellow;
        fouls[1] = team2Red;
        return fouls;
    }

    public String toString(){
        return team1.getTeamName() + " plays " + team2.getTeamName();
    }

}
