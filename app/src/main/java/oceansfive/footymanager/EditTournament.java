package oceansfive.footymanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.view.MenuItem;
import android.text.Editable;
import android.text.TextWatcher;

public class EditTournament extends AppCompatActivity {

    TournamentData data = TournamentData.getInstance();
    private static final int SELECT_TOURNAMENT_LOGO = 1;
    private static boolean teamNamesFilled = false;
    private static Tournament tournament = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        setContentView(R.layout.activity_edit_tournament);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tournament = data.tournaments.get(getIntent().getExtras().getInt("tournament"));

        teamListAdapter adapter = new teamListAdapter(this, tournament);

        setTitle("Edit Tournament");

        TextView tournamentName = (TextView) findViewById(R.id.tournamentNameDisplay);
        TextView numOfTeams = (TextView) findViewById(R.id.tournamentNumOfTeams);
        TextView style = (TextView) findViewById(R.id.tournamentStyle);
        ImageView logo = (ImageView) findViewById(R.id.tournamentLogo);
        Button startTournament = (Button) findViewById(R.id.startTournament);

        tournamentName.setText(tournament.getTournamentName());
        numOfTeams.setText(Integer.toString(tournament.getTournamentSize()));
        style.setText(tournament.getTournamentType());
        logo.setImageResource(context.getResources().getIdentifier(tournament.getTournamentLogo(), "drawable", context.getPackageName()));

        if(adapter.fieldsFilled())
            teamNamesFilled=true;
        else
            teamNamesFilled=false;


        ListView teamList = (ListView) findViewById(R.id.teamList);
        teamList.setAdapter(adapter);

        TextWatcher tournamentNameWatcher = new TextWatcher() {
            //Changes Tournament name to new name
            public void afterTextChanged(Editable s) {
                tournament.setTournamentName(s.toString());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        };


        tournamentName.addTextChangedListener(tournamentNameWatcher);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishEditing();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void finishEditing() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //Application Context and Activity
        startActivityForResult (intent,0);
    }

    //Changes the logo for the selected team
    public void logoSelect(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_TOURNAMENT_LOGO);
    }
    //Code for when the tournament is started
    public void startTournament(View view){
        EditText tournamentName = (EditText) findViewById(R.id.tournamentNameDisplay);

        /*if(!teamNamesFilled || tournamentName.getText()==null)
            return;*/
        //System.out.println(tournament.teams.length);
        data.tournaments.get(data.tournaments.indexOf(tournament)).startTournament();
        if(tournament.getTournamentType().equals("Round Robin")){
            data.tournaments.get(data.tournaments.indexOf(tournament)).createRoundRobin(tournament.getTeams());
        }
        else if(tournament.getTournamentType().equals("Knock-Out")){
            data.tournaments.get(data.tournaments.indexOf(tournament)).createKnockout(tournament.getTeams());
        }
        else if(tournament.getTournamentType().equals("Combinational")){
            data.tournaments.get(data.tournaments.indexOf(tournament)).createRoundRobin(tournament.getTeams());
        }
        Intent intent = new Intent(getApplicationContext(), Schedule.class); //Application Context and Activity
        intent.putExtra("tournament", data.tournaments.indexOf(tournament));
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_TOURNAMENT_LOGO) {
            Uri selectedImageUri = data.getData();
            ((ImageView) findViewById(R.id.tournamentLogo)).setImageURI(selectedImageUri);
        }
    }

}
