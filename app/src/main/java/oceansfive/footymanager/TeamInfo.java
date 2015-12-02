package oceansfive.footymanager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.*;
import java.io.InputStream;

public class TeamInfo extends AppCompatActivity {

    TournamentData data  = TournamentData.getInstance();
    Team team;

    int tournamentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tournamentIndex = getIntent().getIntArrayExtra("info")[0];
        int teamIndex = getIntent().getIntArrayExtra("info")[1];
        team = data.tournaments.get(tournamentIndex).getRanking()[teamIndex];

        setTitle("Team Information");

        TextView teamName = (TextView) findViewById(R.id.team_name);

        teamName.setText(team.getTeamName());

       String logo = team.getTeamLogo();
        int drawableResourceId = this.getResources().getIdentifier(logo, "drawable", this.getPackageName());
        ImageView imageView = (ImageView) findViewById(R.id.team_logo);
        imageView.setImageResource(drawableResourceId);


        teamName.setKeyListener(null);

        TextView totalGoals = (TextView) findViewById(R.id.num_total_goals);
        totalGoals.setText(Integer.toString(team.getTotalGoals()));

        TextView wins = (TextView) findViewById(R.id.num_wins);
        wins.setText(Integer.toString(team.getWins()));

        TextView losses = (TextView) findViewById(R.id.num_losses);
        losses.setText(Integer.toString(team.getLosses()));

        TextView red = (TextView) findViewById(R.id.num_yellow);
        red.setText(Integer.toString(team.getYellowCards()));

        TextView yellow = (TextView) findViewById(R.id.num_red);
        yellow.setText(Integer.toString(team.getRedCards()));


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), Schedule.class);
                intent.putExtra("tournament", tournamentIndex);
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
