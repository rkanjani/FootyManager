package oceansfive.footymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class winner extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    Team[] standings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        setTitle("Winner!");


      /*  int tournamentIndex = getIntent().getIntArrayExtra("info")[0];
        standings = data.tournaments.get(tournamentIndex).getRanking();

        Team winner = standings[0];

        TextView winnerName = (TextView) findViewById(R.id.winnerName);
        winnerName.setText(winner.getTeamName());

        TextView numWin = (TextView) findViewById(R.id.winnerWins);
        numWin.setText(winner.getWins());

        TextView numLoss = (TextView) findViewById(R.id.winnerLoss);
        numLoss.setText(winner.getLosses());
        */
    }
}
