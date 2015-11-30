package oceansfive.footymanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    private static int menuItemSelected;
    tournamentListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Active Tournaments");
        listAdapter = new tournamentListAdapter(this, data.tournaments);
        View addTournamentButton = (View)findViewById(R.id.addTournament);


        addTournamentButton.bringToFront();


        ListView tournamentList = (ListView) findViewById(R.id.tournamentList);
        tournamentList.setAdapter(listAdapter);


        //Click on element in tournament list
        tournamentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getApplicationContext(), EditTournament.class); //Application Context and Activity
                intent.putExtra("tournament", position);
                startActivityForResult(intent, position);
            }
        });


        registerForContextMenu(tournamentList);

    }

    /*public void arrayToList(String [] arr, ArrayList<String> list){
        for(int x = 0; x<arr.length; x++){
            list.add(arr[x]);
        }

    }*/

    public void addTournament(View view) {
        Intent intent = new Intent(getApplicationContext(), TournamentCreation.class); //Application Context and Activity
        startActivityForResult (intent,0);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.tournamentList) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menuItemSelected = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;

            menu.setHeaderTitle(data.tournaments.get(info.position).toString());

            String[] menuItems = new String[] {"Edit", "Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Edit"){
            Intent intent = new Intent(getApplicationContext(), EditTournament.class); //Application Context and Activity
            intent.putExtra("tournament", menuItemSelected);
            startActivityForResult(intent, 0);

        }
        else if(item.getTitle()=="Delete"){
            int position = menuItemSelected;
            String name = data.tournaments.get(position).toString().concat(" removed!");
            data.tournaments.remove(position);
            listAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), name,Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
