package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class EnterScore extends AppCompatActivity {

    TournamentData data  = TournamentData.getInstance();
    private static int tournamentIndex;
    private static int gameIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_score);
        setTitle("Enter Score");
        int [] locations = getIntent().getIntArrayExtra("tournamentGameInfo");
        tournamentIndex = locations[0];
        gameIndex = locations[1];

        Tournament tournament = data.tournaments.get(locations[0]);
        Game game = tournament.getGames().get(locations[1]);

        TextView teamOneName = (TextView) findViewById(R.id.teamOne);
        TextView teamTwoName = (TextView) findViewById(R.id.teamTwo);

        teamOneName.setText(game.getTeam1().getTeamName());
        teamTwoName.setText(game.getTeam2().getTeamName());

        EditText teamOneScore = (EditText)findViewById(R.id.home_score);
        EditText teamTwoScore = (EditText)findViewById(R.id.away_score);

        EditText teamOneYellow = (EditText)findViewById(R.id.home_yellow);
        EditText teamTwoYellow = (EditText)findViewById(R.id.away_yellow);

        EditText teamOneRed = (EditText)findViewById(R.id.home_red);
        EditText teamTwoRed = (EditText)findViewById(R.id.away_red);

        if(game.isGamePlayed()){
            teamOneScore.setText(Integer.toString(game.getTeam1Score()));
            teamTwoScore.setText(Integer.toString(game.getTeam2Score()));

            teamOneYellow.setText(Integer.toString(game.getTeam1Fouls()[0]));
            teamOneRed.setText(Integer.toString(game.getTeam1Fouls()[1]));

            teamTwoYellow.setText(Integer.toString(game.getTeam2Fouls()[0]));
            teamTwoRed.setText(Integer.toString(game.getTeam2Fouls()[1]));
        }


    }

    public void enterGame(View view){
        Tournament tournament = data.tournaments.get(tournamentIndex);
        Game game = tournament.games.get(gameIndex);

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


        Intent intent = new Intent(getApplicationContext(), Schedule.class); //Application Context and Activity
        intent.putExtra("tournament", tournamentIndex);
        startActivityForResult(intent, 0);
    }
}
