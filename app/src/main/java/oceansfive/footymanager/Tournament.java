package oceansfive.footymanager;


import android.graphics.drawable.Drawable;

import java.util.*;

import java.util.ArrayList;


/**
 * Created by rohan on 2015-11-12.
 */
public class Tournament {

    private String tournamentName;
    private String tournamentType;
    private String tournamentLogo;
    private int tournamentSize;
    boolean tournamentStarted;
    boolean finished;
    Team winner;
    Team[] teams;         //This stores all the Teams that are in a given tournament
    Team[] competingTeams;//(ONLY FOR KNOCKOUT) Used to store who is still alive in the tournament
    List<Game> games = new ArrayList<Game>();
    boolean weakGames =false;// 0th round where the weak teams play each other.
    boolean comboRound; // false if still in roundrobin


    public Tournament(String tournamentName, String tournamentType, int tournamentSize, String tournamentLogo, Team[] teams){
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
        this.tournamentSize = tournamentSize;
        this.tournamentLogo = tournamentLogo;
        this.teams = teams;
        this.tournamentStarted = false;
        teams = new Team[tournamentSize];
        competingTeams = teams.clone();
        finished = false;
        comboRound = false;

    }

    public Team[] getRanking()
    {
        if (this.weakGames == true)
            return teams;
        for (int i = 0; i < teams.length; i++)
        {
            for (int j = i+1; j<teams.length; j++ )
            {
                if ( teams[j].getWins() > teams[i].getWins())
                {
                    Team temp = teams[i];
                    teams[i] = teams[j];
                    teams[j] = temp;
                }
            }

        }
        if (this.comboRound == false)
            competingTeams = teams.clone();
        return this.teams;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public String getTournamentType() {
        return tournamentType;
    }
    public Team[] getTeams(){
        return teams;
    }

    public List<Game> getGames(){
        return games;
    }

    public String getTournamentLogo(){
        return tournamentLogo;
    }
    public int getTournamentSize(){
        return tournamentSize;
    }

    public void setTournamentLogo(String logo){
        this.tournamentLogo=logo;
    }

    //Creates the games in a round robin
    public Game[] createRoundRobin(Team[] teams)
    {
        Game [] gameSchedule = new Game[teams.length*(teams.length-1)/2];
        int gameNum = 0;
        for(int i = 0; i < teams.length-1; i++)
        {
            for(int j=i+1; j < teams.length; j++)
            {
                gameSchedule[gameNum] = new Game(teams[i], teams[j]);
                gameNum++;
            }
        }
        for(int x=0;x<gameSchedule.length;x++)
            System.out.println(gameSchedule[x]);

        Collections.shuffle((Arrays.asList(gameSchedule)));

        games = Arrays.asList(gameSchedule);
        return gameSchedule;
    }

    //Creates the games in a round of knockout
    public Game[] createKnockout(Team[] teams)
    {
        Game gameSchedule[] = null;
        comboRound = true;

        // Perfect Tournament of 2^n teams
        if  (((Math.log(teams.length) / Math.log(2)) % 1) == 0) {
            gameSchedule = new Game[teams.length / 2];


            for (int i = 0; i < teams.length /2 ; i++) {
                gameSchedule[i] = new Game(teams[i], teams[teams.length - 1 - i]);
            }


        }
        else // Tournament is not Perfect
        {
            weakGames = true;
            int n = 0;
            double extraGames = 0;
            while(teams.length - Math.pow(2, n) > 0) {
                extraGames = teams.length - Math.pow(2, n);
                n++;
            }
            n--;//Restores n to the proper power
            gameSchedule = new Game[(int)extraGames]; // Only plays extra games first round
            int num = 0;
            for (int i = teams.length; i > Math.pow(2,n) - extraGames;  i=i-2)
            {
                gameSchedule[num] = new Game(teams[i-1], teams[i-2]);
                num++;
            }
        }
        games = Arrays.asList(gameSchedule);
        return gameSchedule;
    }

    //This method is called when a Round in a knockout tournament is completed to fill
    //in the competingTeams with the winner of the round.
    //After calling this, you must call createKnockout to make the next round
    public Team[] updateRound(Game[] games)
    {


        if(games.length == 1 && weakGames == false) //Final game of Knockout
        {

            System.out.println("FINISHED");
            this.comboRound = false;
            winner = games[0].getWinner();
            competingTeams = new Team[]{winner};
            finished = true;
            return competingTeams;
        }

        if(this.getTournamentType().equals("Round Robin")) //Final Round Robin Game
        {
            getRanking();
            winner = getRanking()[0];
            competingTeams = new Team[]{winner};
            return competingTeams;
        }

        if  (((Math.log(competingTeams.length) / Math.log(2)) % 1) != 0) { //Tournament is not perfect
            int n = 0;
            double extraGames = 0;
            while (teams.length - Math.pow(2, n) > 0) {
                extraGames = teams.length - Math.pow(2, n);
                n++;
            }
            n--; //Restores n to the proper power
            Team newTeam[] = new Team[(int) Math.pow(2, n)];

            for (int i = 0; i < teams.length - (extraGames * 2); i++) //Fills the first portion of the array
            {
                newTeam[i] = teams[i];
            }
            for(int i = teams.length - (int)(extraGames * 2); i < newTeam.length; i++ ) //Fills the second portion with the winners of the last games.
            {
                newTeam[i] = games[i - (teams.length - (int)(extraGames *2))].getWinner();
            }
            competingTeams = newTeam.clone();

        }
        else if(((Math.log(competingTeams.length) / Math.log(2)) % 1) == 0) //Tournament is perfect
        {
            Team newTeam[] = new Team[competingTeams.length/2];

            for (int i = 0; i< newTeam.length; i++)
            {
                newTeam[i] = games[i].getWinner();
            }
            competingTeams = newTeam.clone(); //Updates competingteams with winners of the round

        }

        weakGames = false;
        return competingTeams;

    }
    public boolean checkTeamNames(){
        for(int x=0; x<teams.length;x++){
            if(teams[x]==null || teams[x].getTeamName().equals(""))
                return false;
        }
        return true;
    }

    public void updateGame(int index, Game game){
        Collections.replaceAll(games, games.get(index), game);
    }

    public Team getWinner()
    {
        return this.winner;
    }
    public void startTournament(){
        tournamentStarted = true;
    }
    public boolean getTournamentStatus(){
        return tournamentStarted;
    }
    public void addTeam(Team t, int position){
        teams[position] = t;
    }
    public void addTeamRoster(Team[] teams){
        this.teams = teams;
    }
    public void setTournamentName(String name){
        tournamentName = name;
    }
    public void setTournamentType(String type)
    {
        this.tournamentType = type;
    }
    public boolean getFinished(){return this.finished;}
    public void setFinished(){this.finished = true;}

    //Checks if there is a tie in Round Robin Tournament
    public void checkforTie()
    {
        Team[] tieTeams;
        int topWins = winner.getWins();
        int tiedTeams = 0;

        if (topWins == teams[1].getWins())
        {
            for (int i = 1; i < teams.length; i++)
            {
                if (topWins == teams[i].getWins()) {
                    tiedTeams++;
                }
                else {
                    break;
                }

            }
            tieTeams = new Team[tiedTeams+1];
            for (int i = 0; i <= tiedTeams; i++)
            {
                tieTeams[i] = teams[i];
            }

            for(int i = 0; i < tieTeams.length; i++)
            {
                if(tieTeams[i].getTotalGoals() > winner.getTotalGoals())
                    winner = tieTeams[i];
            }


        }
        else{
            return;
        }



    }


    public String toString(){
        return tournamentName;
    }

    public void getGameSchedule(){
        for(int x=0; x<games.size(); x++){
            System.out.println("Game "+(x+1)+": "+games.get(x).getTeam1() + " plays" + games.get(x).getTeam2());
        }
    }
}
