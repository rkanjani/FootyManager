package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Schedule extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    Tournament tournament;
    private static int tournamentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        tournamentIndex = getIntent().getExtras().getInt("tournament");
        final int [] values = new int[2];


        tournament = data.tournaments.get(tournamentIndex);


        setTitle("League Schedule");


        ListView gameList = (ListView) findViewById(R.id.schedule);
        scheduleAdapter adapter = new scheduleAdapter(this, tournament.games);


        gameList.setAdapter(adapter);

        gameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                values[0] = tournamentIndex;
                values[1] = position;
                Intent intent = new Intent(getApplicationContext(), EnterScore.class); //Application Context and Activity
                intent.putExtra("tournamentGameInfo", values);
                startActivityForResult(intent, 0);
            }
        });


    }
    public void goToRanking(View view){
        Intent intent = new Intent(getApplicationContext(), Ranking.class);
        intent.putExtra("tournament", tournamentIndex);
        startActivityForResult(intent, 0);
    }
}
