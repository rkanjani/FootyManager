package oceansfive.footymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class EnterScore extends AppCompatActivity {
    TournamentData data  = TournamentData.getInstance();
    final Game game = data.tournaments.get(getIntent().getExtras().getInt("tournament")).games.get(getIntent().getExtras().getInt("game"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);
        setTitle("Enter Score");
    }

    public void enterGame(){
        EditText home_score = (EditText)findViewById(R.id.home_score);
        EditText away_score = (EditText)findViewById(R.id.away_score);
        int homeScore = Integer.parseInt(home_score.getText().toString());
        int awayScore = Integer.parseInt(away_score.getText().toString());
        game.enterScore(homeScore, awayScore);

        EditText home_yellow = (EditText) findViewById(R.id.home_yellow);
        Team team1 = game.getTeam1();
        int homeYellow = Integer.parseInt(home_yellow.getText().toString());
        team1.setYellowCards(homeYellow);

        EditText away_yellow = (EditText) findViewById(R.id.away_yellow);
        Team team2 = game.getTeam2();
        int awayYellow = Integer.parseInt(away_yellow.getText().toString());
        team2.setYellowCards(awayYellow);

        EditText home_red = (EditText) findViewById(R.id.home_red);
        int homeRed = Integer.parseInt(home_red.getText().toString());
        team1.setRedCards(homeRed);

        EditText away_red = (EditText) findViewById(R.id.away_red);
        int awayRed = Integer.parseInt(away_red.getText().toString());
        team2.setRedCards(awayRed);

        game.finishGame();
    }
}
