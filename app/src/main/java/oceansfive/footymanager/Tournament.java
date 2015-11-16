package oceansfive.footymanager;
import java.util.*;
/**
 * Created by rohan on 2015-11-12.
 */
public class Tournament {
    private String tournamentName;
    private String tournamentType;
    List<Team> teams = new ArrayList<Team>();
    List<Game> games = new ArrayList<Game>();

    public void Tournament(String tournamentName, String tournamentType){
        this.tournamentName = tournamentType;
        this.tournamentType = tournamentName;
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

    public void createGames(teams){
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
    public void addTeam(String teamName){
        Team createTeam = new Team(teamName);
        teams.add(createTeam);
    }

    public void deleteTeam (Team delTeam){
        teams.remove(delTeam);
    }
}
