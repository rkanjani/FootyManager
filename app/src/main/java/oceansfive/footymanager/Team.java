package oceansfive.footymanager;

import java.util.List;
import java.util.*;
/**
 * Created by Samsore on 2015-11-11.
 */
public class Team {
    private String teamName;
    private String teamLogo;
    private List<Player> players = new ArrayList<Player>();

    //keeps track of the size of the team.
    private int teamSize = 0;

    public Team(String teamName, String teamLogo)
    {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
    }

    public String getTeamName(){
        return teamName;
    }
    public String getTeamLogo() { return teamLogo; }

    //adds player to the team
    public void addPlayer(String name, char position, int jerseyNumber){
        if(teamSize<=28) {
            Player player1 = new Player(name, position, jerseyNumber);
            players.add(player1);
            teamSize++;
        }
        System.out.println("no more splace on the roster");
    }

    //deletes player
    public void deletePlayer(Player guy){
        players.remove(guy);
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
    public void setTeamLogo(String teamLogo){
        this.teamLogo = teamLogo;
    }

    //edits the players posiiton
    public void editPlayerPosition(Player p1, char pos){
        p1.setPosition(pos);
    }

}
