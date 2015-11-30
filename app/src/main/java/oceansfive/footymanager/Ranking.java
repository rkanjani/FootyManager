package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        final int tournamentIndex = getIntent().getExtras().getInt("tournament");
        final int [] values = new int[2];
        Tournament tournament = data.tournaments.get(tournamentIndex);

        setTitle("League Standing");


        ListView ranking = (ListView) findViewById(R.id.standings);
        standingsAdapter adapter = new standingsAdapter(this, tournament);


        ranking.setAdapter(adapter);

        ranking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                values[0] = tournamentIndex;
                values[1] = position;
                Intent intent = new Intent(getApplicationContext(), TeamInfo.class); //Application Context and Activity
                intent.putExtra("info", values);
                startActivityForResult(intent, 0);
            }
        });


    }

}
