package oceansfive.footymanager;

import java.util.List;
import java.util.*;
/**
 * Created by Samsore on 2015-11-11.
 */
public class Team {
    private String teamName;
    private List<Player> players = new ArrayList<Player>();

    //keeps track of the size of the team.
    private int teamSize = 0;

    public Team(String teamName)
    {
        this.teamName = teamName;
    }

    public String getTeamName(){
        return teamName;
    }

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

    //edits the players posiiton
    public void editPlayerPosition(Player p1, char pos){
        p1.setPosition(pos);
    }

}
