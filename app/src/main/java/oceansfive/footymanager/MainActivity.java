package oceansfive.footymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import static android.widget.ListView.*;

public class MainActivity extends AppCompatActivity {

    static final int CREATE_TOURNAMENT_REQUEST = 1;  // The request code to create a tournament


    TournamentData data = new TournamentData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Active Tournaments");

        //***CLEAN THIS UP
        Tournament temp1= new Tournament("fag","gay");
        data.addTournament(temp1);

        //View tournamentListView = (View)findViewById(R.id.addTournament);

        //tournamentListView.bringToFront();

        /* MOVED TO ON RESUME
        ListView tournamentList = (ListView) findViewById(R.id.tournamentList);
<<<<<<< HEAD
        tournamentList.setAdapter(new listAdapter(this, data.getTournamentList()));

        tournamentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });
=======
        tournamentList.setAdapter(new listAdapter(this, data.tournaments));
>>>>>>> origin/master

        registerForContextMenu(tournamentList);
         */

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        //populates the tournament list

        ListView tournamentList = (ListView) findViewById(R.id.tournamentList);
        tournamentList.setAdapter(new listAdapter(this, data.getTournamentList()));

        tournamentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(tournamentList);

    }

    public void arrayToList(String [] arr, ArrayList<String> list){
        for(int x = 0; x<arr.length; x++){
            list.add(arr[x]);
        }

    }
    public void addTournament(View view) {//on button click... addtournament... creates 'createTournament' activity and returns a result
        Intent intent = new Intent(getApplicationContext(), TournamentCreation.class); //Application Context and Activity
        startActivityForResult(intent, CREATE_TOURNAMENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == CREATE_TOURNAMENT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                String name = data.getStringExtra("name");
                String type = data.getStringExtra("type");
                Tournament temp1= new Tournament(name,type);
                this.data.addTournament(temp1);

                Context context = getApplicationContext();
                CharSequence text = "created new tournament!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "no tournament created.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.tournamentList) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(data.getTournamentList().get(info.position).toString());
            String[] menuItems = new String[] {"Edit", "Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Edit"){
            //Change activity to edit screen
        }
        else if(item.getTitle()=="Delete"){
            //Delete tournament from the list
        }
        return true;
    }
}
