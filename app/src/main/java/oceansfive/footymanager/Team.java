package oceansfive.footymanager;

import android.graphics.drawable.Drawable;
import android.media.Image;

import java.util.List;
import java.util.*;
/**
 * Created by Samsore on 2015-11-11.
 */
public class Team {
    private String teamName;
    private String teamLogo;
    private List<Player> players = new ArrayList<Player>();
    private int wins;
    private int gamesPlayed;
    private int redCards;
    private int yellowCards;


    //keeps track of the size of the team.
    private int teamSize = 0;

    public Team(String teamName, String teamLogo)
    {
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.wins = 0;
        this.gamesPlayed = 0;
        this.redCards = 0;
        this.yellowCards = 0;
    }

    //Getters
    public String getTeamName(){ return teamName; }
    public String getTeamLogo() { return teamLogo; }
    public int getWins() {
        return wins;
    }

    public int getLosses()
    {
        return gamesPlayed - wins;
    }

    public int getRedCards(){
        return redCards;
    }

    public int getYellowCards(){
        return yellowCards;
    }

    //Setters
    public void setTeamName(String teamName){ this.teamName = teamName;}
    public void setTeamLogo(String teamLogo){ this.teamLogo = teamLogo; }
    public void setYellowCards(int yellowCards){
        this.yellowCards = yellowCards;
    }
    public void setRedCards(int redCards){
        this.redCards = redCards;
    }

    //adds player to the team
    public void addPlayer(String name, char position, int jerseyNumber){
        if(teamSize<=28) {
            Player player1 = new Player(name, position, jerseyNumber);
            players.add(player1);
            teamSize++;
        }
        System.out.println("no more space on the roster");
    }

    //deletes player
    public void deletePlayer(Player guy){
        players.remove(guy);
    }

    //edits the players positon
    public void editPlayerPosition(Player p1, char pos){
        p1.setPosition(pos);
    }

    public String winsToString()
    {
        return ("Wins: " + this.wins + "  Losses: " + getLosses());
    }

}
