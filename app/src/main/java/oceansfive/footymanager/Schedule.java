package oceansfive.footymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Schedule extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    Tournament tournament;
    private static int tournamentIndex;
    Context context;
    scheduleAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tournamentIndex = getIntent().getExtras().getInt("tournament");
        final int [] values = new int[2];
        context = this;


        tournament = data.tournaments.get(tournamentIndex);

        if(tournament.getTournamentType().equals("Round Robin"))
            ((Button)(findViewById(R.id.updateRound))).setText("Complete Tournament");


        setTitle("League Schedule");


        ListView gameList = (ListView) findViewById(R.id.schedule);
        adapter = new scheduleAdapter(this, tournament.games);


        gameList.setAdapter(adapter);

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                if(!tournament.getGames().get(position).isGamePlayed()) {
                    values[0] = tournamentIndex;
                    values[1] = position;
                    Intent intent = new Intent(getApplicationContext(), EnterScore.class); //Application Context and Activity
                    intent.putExtra("tournamentGameInfo", values);
                    startActivityForResult(intent, 0);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Game already played!" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        adapter.notifyDataSetChanged();

    }
    public void goToRanking(View view) {
        data.tournaments.set(tournamentIndex, tournament);
        Intent intent = new Intent(getApplicationContext(), Ranking.class);
        intent.putExtra("tournament", tournamentIndex);
        startActivityForResult(intent, 0);
    }
    //Called when update button is pressed
    public void update(View view) {
        if (this.tournament.getTournamentType().equals("Round Robin")) { //Round Robin
            for(int i = 0; i < this.tournament.getGames().size(); i++)
            {
                if (!this.tournament.getGames().get(i).isGamePlayed()) //Checks if all games were played
                {
                    Toast.makeText(getApplicationContext(), "Please play all games before updating",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            this.tournament.getRanking();
            Game[] gamesArray = tournament.getGames().toArray(new Game[tournament.getGames().size()]);
            this.tournament.updateRound(gamesArray);
            this.tournament.checkforTie();
            String [] info = {this.tournament.getWinner().getTeamName(),
                    Integer.toString(tournament.getWinner().getWins()),
                    Integer.toString(tournament.getWinner().getLosses()),
                    tournament.getWinner().getTeamLogo()
            };

            Intent intent = new Intent(getApplicationContext(), winner.class);
            intent.putExtra("info", info);
            this.tournament.setFinished();
            startActivityForResult(intent, 0);
            return;

        }
        else if (this.tournament.getTournamentType().equals("Knock-Out") || this.tournament.comboRound == true) { //Knockout
            for(int i = 0; i < this.tournament.getGames().size(); i++) {
                if (this.tournament.getGames().get(i).getWinner() == null) //Checks if all games were played
                {
                    Toast.makeText(getApplicationContext(), "Please play all games before updating",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Game[] gamesArray = tournament.getGames().toArray(new Game[tournament.getGames().size()]);
            this.tournament.createKnockout(tournament.updateRound(gamesArray));
            if(this.tournament.finished == true)
            {
                String [] info = {tournament.getWinner().getTeamName(),
                        Integer.toString(tournament.getWinner().getWins()),
                        Integer.toString(tournament.getWinner().getLosses()),
                        tournament.getWinner().getTeamLogo()};
                this.tournament.setFinished();
                Intent intent = new Intent(getApplicationContext(), winner.class);
                intent.putExtra("info", info);
                startActivityForResult(intent, 0);
                return;
            }
            // adapter.notifyDataSetChanged();
            finish();
            startActivity(getIntent());
        }
        else if(this.tournament.getTournamentType().equals("Combinational"))
        {
            for(int i = 0; i < this.tournament.getGames().size(); i++)
            {
                if (this.tournament.getGames().get(i).getWinner() == null) //Checks if all games were played
                {
                    Toast.makeText(getApplicationContext(), "Please play all games before updating",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            this.tournament.createKnockout(this.tournament.getRanking());
            this.tournament.comboRound = true;
            Toast.makeText(getApplicationContext(), "Starting Playoffs",
                    Toast.LENGTH_LONG).show();
            finish();
            startActivity(getIntent());

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, 0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);

    }
}
