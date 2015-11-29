package oceansfive.footymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class TeamInfo extends AppCompatActivity {
    TournamentData data  = TournamentData.getInstance();

    final Team team = data.tournaments.get(getIntent().getExtras().getInt("tournament")).teams[getIntent().getExtras().getInt("team")];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        EditText teamName = (EditText) findViewById(R.id.teamName);
        teamName.setText(team.getTeamName());

        ImageView img= (ImageView) findViewById(R.id.image);

        img.setImageDrawable(team.getTeamLogo());


        TextView wins = (TextView) findViewById(R.id.numOfWin);
        wins.setText(team.getWins());

        TextView losses = (TextView) findViewById(R.id.numOfLoss);
        losses.setText(team.getLosses());

        TextView red = (TextView) findViewById(R.id.numOfRed);
        red.setText(team.getRedCards());

        TextView yellow = (TextView) findViewById(R.id.numOfWin);
        yellow.setText(team.getYellowCards());


    }
}
