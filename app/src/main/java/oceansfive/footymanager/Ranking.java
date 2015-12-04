package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    int tournamentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final int tournamentIndex = getIntent().getExtras().getInt("tournament");
        tournamentPosition = tournamentIndex;
        final int [] values = new int[2];
        Tournament tournament = data.tournaments.get(tournamentIndex);

        setTitle("League Standing");


        ListView ranking = (ListView) findViewById(R.id.standings);
        standingsAdapter adapter = new standingsAdapter(this, tournament);


        ranking.setAdapter(adapter);

        ranking.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                //(parent.getItemAtPosition(position))
                values[0] = tournamentIndex;
                values[1] = position;
                Intent intent = new Intent(getApplicationContext(), TeamInfo.class); //Application Context and Activity
                intent.putExtra("info", values);
                startActivityForResult(intent, 0);
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(data.tournaments.get(tournamentPosition).getFinished()){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityForResult(intent, 0);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Schedule.class);
                    intent.putExtra("tournament", tournamentPosition);
                    startActivityForResult(intent, 0);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
