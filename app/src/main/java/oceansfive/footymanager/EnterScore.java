package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Enter Score");
        tournamentIndex = getIntent().getIntArrayExtra("tournamentGameInfo")[0];
        gameIndex = getIntent().getIntArrayExtra("tournamentGameInfo")[1];

        Tournament tournament = data.tournaments.get(tournamentIndex);
        Game game = tournament.getGames().get(gameIndex);

        //initializes the views and variables
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
        if(game.getTeam1().getTeamLogo() != null) {
            String logo = game.getTeam1().getTeamLogo();
            int drawableResourceId = this.getResources().getIdentifier(logo, "drawable", this.getPackageName());
            ImageView imageView1 = (ImageView) findViewById(R.id.home_team);
            imageView1.setImageResource(drawableResourceId);
        }

        if(game.getTeam2().getTeamLogo()!= null) {
            String logo2 = game.getTeam2().getTeamLogo();
            int drawableResourceId2 = this.getResources().getIdentifier(logo2, "drawable", this.getPackageName());
            ImageView imageView2 = (ImageView) findViewById(R.id.away_team_logo);
            imageView2.setImageResource(drawableResourceId2);
        }




        if(game.isGamePlayed()){
            teamOneScore.setText(Integer.toString(game.getTeam1Score()));
            teamTwoScore.setText(Integer.toString(game.getTeam2Score()));

            teamOneYellow.setText(Integer.toString(game.getTeam1Fouls()[0]));
            teamOneRed.setText(Integer.toString(game.getTeam1Fouls()[1]));

            teamTwoYellow.setText(Integer.toString(game.getTeam2Fouls()[0]));
            teamTwoRed.setText(Integer.toString(game.getTeam2Fouls()[1]));
        }


    }

    public void enterGame(){
        Tournament tournament = data.tournaments.get(tournamentIndex);
        Game game = tournament.games.get(gameIndex);

        EditText home_score = (EditText)findViewById(R.id.home_score);
        EditText away_score = (EditText)findViewById(R.id.away_score);


        //checks if the user entered a value before entering the score

        int homeScore = returnNumber(home_score.getText().toString());

        int awayScore = returnNumber(away_score.getText().toString());

        game.enterScore(homeScore, awayScore);


        //adds the goals to each team
        game.getTeam1().addGoals(homeScore);
        game.getTeam2().addGoals(awayScore);


        //adds the yellow cards from the games to the team
        EditText home_yellow = (EditText) findViewById(R.id.home_yellow);
        Team team1 = game.getTeam1();
        int homeYellow;
        String homeYellowString = home_yellow.getText().toString();

        if(homeYellowString.equals(""))
            homeYellow = 0;
        else
            homeYellow = returnNumber(homeYellowString);

        team1.setYellowCards(homeYellow);


        EditText away_yellow = (EditText) findViewById(R.id.away_yellow);
        Team team2 = game.getTeam2();
        int awayYellow;
        String awayYellowString = away_yellow.getText().toString();

        if(awayYellowString.equals(""))
            awayYellow = 0;
        else
            awayYellow = returnNumber(awayYellowString);

        team2.setYellowCards(awayYellow);


        // Adds the red cards from the game to the team
        EditText home_red = (EditText) findViewById(R.id.home_red);
        int homeRed;
        String homeRedString = home_red.getText().toString();

        if(homeRedString.equals(""))
            homeRed = 0;
        else
            homeRed = returnNumber(homeRedString);

        team1.setRedCards(homeRed);


        EditText away_red = (EditText) findViewById(R.id.away_red);
        int awayRed;
        String awayRedString = away_red.getText().toString();

        if(awayRedString.equals(""))
            awayRed = 0;
        else
            awayRed = returnNumber(awayRedString);

        team2.setRedCards(awayRed);

        game.finishGame();

        Intent intent = new Intent(getApplicationContext(), Schedule.class); //Application Context and Activity
        intent.putExtra("tournament", tournamentIndex);
        startActivityForResult(intent, 0);
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

    public int returnNumber(String s){
        if(s.equals("") || s == null){
            return 0;
        }
        else{
            return Integer.parseInt(s);
        }

    }

    //Checks to see if the game resulted in a tie
    public void checkTie(View view){
        EditText homeScore = (EditText) findViewById(R.id.home_score);
        int home = returnNumber(homeScore.getText().toString());

        EditText awayScore = (EditText) findViewById(R.id.away_score);
        int away = returnNumber(awayScore.getText().toString());

        if(away == home || (away == 0 && home == 0)){
            Toast.makeText(getApplicationContext(), "Cannot be a tie",
                    Toast.LENGTH_SHORT).show();
        }
        else
            enterGame();
    }

}
