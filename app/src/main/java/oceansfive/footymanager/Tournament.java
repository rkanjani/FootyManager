package oceansfive.footymanager;


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
    List<Team> teams = new ArrayList<Team>();
    List<Game> games = new ArrayList<Game>();

    public Tournament(String tournamentName, String tournamentType, int tournamentSize, String tournamentLogo){
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
        this.tournamentSize = tournamentSize;
        this.tournamentLogo = tournamentLogo;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public String getTournamentType() {
        return tournamentType;
    }
    public List<Team> getTeams(){
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

    public void createGames(List teams){
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

    //creates bracket for combinational tournament
    public void createPlayoffs(List Teams){

    }

    public void addTeam(String teamName) {
        Team createTeam = new Team(teamName);
        teams.add(createTeam);
    }

    //deletes team from team list
    public void deleteTeam (Team delTeam){
        teams.remove(delTeam);
    }

    public String toString(){
        return tournamentName;
    }
}
