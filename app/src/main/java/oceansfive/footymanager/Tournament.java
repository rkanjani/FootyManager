package oceansfive.footymanager;
import java.util.*;

import java.util.ArrayList;

import java.util.ArrayList;

import java.util.ArrayList;

/**
 * Created by rohan on 2015-11-12.
 */
public class Tournament {
<<<<<<< Updated upstream

    private String tournamentName;
    private String tournamentType;
=======
    boolean started= false;

    String tournamentName;
    protected String tournamentType;
    String tournamentLogo;
    int tournamentSize;
>>>>>>> Stashed changes
    List<Team> teams = new ArrayList<Team>();
    List<Game> games = new ArrayList<Game>();

    public Tournament(String tournamentName, String tournamentType, int tournamentSize, String tournamentLogo){
        this.tournamentName = tournamentName;
        this.tournamentType = tournamentType;
        this.tournamentLogo = tournamentLogo;
        this.tournamentSize = tournamentSize;
    }

    public String getTournamentName(){
        return tournamentName;
    }

    public String getTournamentType() {
        return tournamentType;
    }
    public int getSize(){
        return tournamentSize;
    }
    public String getLogo(){
        return tournamentLogo;
    }
    public List<Team> getTeams(){
        return teams;
    }

    public List<Game> getGames(){
        return games;
    }
    public void createGames(List teams){
        Collections.shuffle(teams);
        Team[] teamArr = new Team[teams.size()];
        teams.toArray(teamArr);

        if(tournamentType.equals("Round Robin")){
            for(int i=0; i<teamArr.length-1; i++){
                for(int j=i+1; j<teamArr.length; j++){
                    Game game1 = new Game(teamArr[i], teamArr[j]);
                }
            }
        }

    }
    public void addTeam(String teamName) {
        Team createdTeam = new Team(teamName);
        teams.add(createdTeam);
    }
    public void deleteTeam (Team delTeam){
        teams.remove(delTeam);
    }

    public String toString(){
        return tournamentName;
    }
}
