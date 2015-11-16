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

    // creates game for the specific tournament
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

    //adds a team to the team list
    public void addTeam(String teamName){
        Team createTeam = new Team(teamName);
        teams.add(createTeam);
    }

    //deletes team from team list
    public void deleteTeam (Team delTeam){
        teams.remove(delTeam);
    }
}
