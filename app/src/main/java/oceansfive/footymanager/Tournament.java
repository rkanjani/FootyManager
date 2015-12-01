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
    Team[] teams;
    List<Game> games = new ArrayList<Game>();


    public Tournament(String tournamentName, String tournamentType, int tournamentSize, String tournamentLogo, Team[] teams){
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
        this.tournamentSize = tournamentSize;
        this.tournamentLogo = tournamentLogo;
        this.teams = teams;
        teams = new Team[tournamentSize];

    }

    public Team[] getRanking()
    {
        for (int i = teams.length - 1; i >= 0; i--)
        {
            for (int j = 1; j<= i; j++ )
            {
                if ( teams[j-1].getWins() > teams[j].getWins())
                {
                    Team temp = teams[j-1];
                    teams[j-1] = teams[j];
                    teams[j] = temp;
                }
            }

        }

        return this.teams;
    }

    //Getters
    public String getTournamentName(){ return tournamentName; }
    public String getTournamentType() {
        return tournamentType;
    }
    public Team[] getTeams(){
        return teams;
    }
    public List<Game> getGames(){
        return games;
    }
    public String getTournamentLogo(){ return tournamentLogo; }
    public int getTournamentSize(){ return tournamentSize; }

    //Setters
    public void setTournamentName(String name){
        tournamentName = name;
    }
    public void setTournamentType(String tournamentType){
        this.tournamentType = tournamentType;
    }

    public void addTeam(Team t, int position){
        teams[position] = t;
    }
    public void addTeamRoster(Team[] teams){
        this.teams = teams;
    }

    public void setTournamentLogo(String tournamentLogo){ this.tournamentLogo = tournamentLogo; }

    //Creates the games in a round robin
    public Game[] createRoundRobin(Team[] teams)
    {
        Game games[] = new Game[teams.length*(teams.length-1)/2];
        int gameNum = 0;
        for(int i = 0; i < teams.length-1; i++)
        {
            for(int j=i+1; j < teams.length; j++)
            {
                games[gameNum] = new Game(teams[i], teams[j]);
            }
        }
<<<<<<< HEAD
<<<<<<< HEAD
        Collections.shuffle((Arrays.asList(games)));
        return games;
=======
=======
>>>>>>> master
        for(int x=0;x<gameSchedule.length;x++)
            System.out.println(gameSchedule[x]);

        Collections.shuffle((Arrays.asList(gameSchedule)));

        games = Arrays.asList(gameSchedule);
        return gameSchedule;
>>>>>>> master
    }
<<<<<<< HEAD

    //Creates the games in a round robin
=======
    //Creates the games in a round of knockout
>>>>>>> master
    public Game[] createKnockout(Team[] teams)
    {
        Game games[] = null;

        // Perfect Tournament of 2^n teams
        if  (((Math.log(teams.length) / Math.log(2)) % 1) == 0) {
            games = new Game[teams.length / 2];

            for (int i = 0; i < teams.length - 1; i++) {
                games[i] = new Game(teams[i], teams[teams.length - 1 - i]);
            }


        }
        else // Tournament is not Perfect
        {
            int n = 0;
            double extraGames = 0;
            while(teams.length - Math.pow(2, n) > 0) {
                extraGames = teams.length - Math.pow(2, n);
                n++;
            }
            games = new Game[(int)extraGames]; // Total Games for first round + extra when not perfect bracket
            int num = 0;
            for (int i = teams.length; i > teams.length - extraGames; i=i-2)
            {
                games[i] = new Game(teams[i], teams[i-1]);
            }
        }
        return games;
    }

    public void updateRound(Game[] games)
    {
        if  (((Math.log(teams.length) / Math.log(2)) % 1) == 0) { //Tournament is not perfect
            int n = 0;
            double extraGames = 0;
            while (teams.length - Math.pow(2, n) > 0) {
                extraGames = teams.length - Math.pow(2, n);
                n++;
            }
            n--;
            Team newTeam[] = new Team[(int) Math.pow(2, n)];

            for (int i = 0; i < teams.length - (extraGames * 2); i++) //Fills the first portion of the array
            {
                newTeam[i] = teams[i];
            }
            for(int i = teams.length - (int)(extraGames * 2); i < newTeam.length; i++ ) //Fills the second portion with the winners of the last games.
            {
                newTeam[i] = games[i - (teams.length - (int)(extraGames *2))].getWinner();
            }


        }

    }

    /*public void createGames(List teams){
        Collections.shuffle(teams);
        Team[] teamArr = new Team[teams.size()];
        teams.toArray(teamArr);

        if(tournamentType.equals("Round Robin") || tournamentType.equals("Combinational")){
            for(int i=0; i<teamArr.length-1; i++){
                for(int j=i+1; j<teamArr.length; j++){
                    Game game1 = new Game(teamArr[i], teamArr[j]);
                }
            }
        }
        else if(tournamentType.equals("Knock Out")) {
            int i = 0;
            int j = teamArr.length;

            if (j % 2 == 0) {
                while (i < j) {
                    Game game1 = new Game(teamArr[i], teamArr[j]);
                    i++;
                    j--;
                }
            } else {
                while (i <= j) {
                    if (i == j) {
                        Game game3 = new Game(teamArr[i], null);
                        i++;
                    }
                    Game game2 = new Game(teamArr[i], teamArr[j]);
                    i++;
                    j--;
                }
            }
        }
    }
    */

    //creates bracket for combinational tournament
    public void createPlayoffs(List Teams){

    }



    public String toString(){
        return tournamentName;
    }
}
