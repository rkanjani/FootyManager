package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Active Tournaments");
        View tournamentListView = (View)findViewById(R.id.addTournament);

        tournamentListView.bringToFront();

        ListView tournamentList = (ListView) findViewById(R.id.tournamentList);

        TournamentData data = TournamentData.getInstance();

        String [] tournaments = data.tournaments;

        final ArrayList<String> adaptiveTournaments = new ArrayList<String>();

        arrayToList(tournaments, adaptiveTournaments);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, adaptiveTournaments);
        tournamentList.setAdapter(adapter);
        tournamentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void arrayToList(String [] arr, ArrayList<String> list){
        for(int x = 0; x<arr.length; x++){
            list.add(arr[x]);
        }

    }
    public void addTournament(View view) {
        Intent intent = new Intent(getApplicationContext(), TournamentCreation.class); //Application Context and Activity
        startActivityForResult (intent,0);
    }
}
