package oceansfive.footymanager;

import java.util.List;

/**
 * Created by Samsore on 2015-11-11.
 */
public class Team {
    private String teamName;
    private Player[] players = new Player[28];

    public Team(String teamName)
    {
        this.teamName = teamName;
    }

}
