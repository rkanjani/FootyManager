package oceansfive.footymanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

public class TournamentCreation extends AppCompatActivity {

    private static final int SELECT_LOGO = 1;

    final CharSequence tournamentTypes[] = new CharSequence[] {"Combinational", "Knock-Out", "Round Robin"};

    private String selectedLogoPath;
    private String filemanagerstring;
    private String selectedStyle = null;
    private String currentImage;

    private static TournamentData data = TournamentData.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_creation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Create Tournament");
        currentImage="noImage";

        NumberPicker np = (NumberPicker) findViewById(R.id.TextView);
        ImageView logo = (ImageView) findViewById(R.id.tournamentLogo);


        String [] teamSizes = new String[65];

        for(int x=0;x<teamSizes.length;x++)
            teamSizes[x] = Integer.toString(x);

        np.setMinValue(0);
        np.setMaxValue(teamSizes.length - 1);
        np.setWrapSelectorWheel(false);
        np.setDisplayedValues(teamSizes);
        np.setValue(2);
        np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        logo.setTag(R.drawable.default_logo, "default_logo");


    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
if (resultCode == RESULT_OK) {

            if(requestCode == SELECT_LOGO){

                this.currentImage =data.getStringExtra("image");
                int image = this.getResources().getIdentifier(currentImage, "drawable", this.getPackageName());


                /*Context context = getApplicationContext();
                CharSequence text = "result "+currentImage;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/


                ((ImageView) findViewById(R.id.tournamentLogo)).setImageResource(image);

            }
        }
    }

    public void tournamentStyleSelect(View view){
        TextView title, message;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Button tournamentStyle = (Button)findViewById(R.id.tournamentType);
        builder.setTitle("Select a tournament style");
        builder.setItems(tournamentTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int type) {
                System.out.println(tournamentTypes[type]);
                selectedStyle = tournamentTypes[type].toString();
                tournamentStyle.setText(selectedStyle);

            }
        });
        builder.show();
    }

    public void tournamentLogoSelect(View view){
        /*Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_LOGO);*/

        Intent intent = new Intent(getApplicationContext(), SelectLogo.class); //Application Context and Activity
        startActivityForResult(intent,SELECT_LOGO);
    }
    public void addTournament(View view){
        NumberPicker numOfTeams = (NumberPicker) findViewById(R.id.TextView);
        EditText name = (EditText) findViewById(R.id.tournamentName);
        ImageView logo = (ImageView) findViewById(R.id.tournamentLogo);
        Button type = (Button) findViewById(R.id.tournamentType);
        Drawable image = logo.getDrawable();


        String goodToast = "Successfully added "+name.getText();


        //Tournament information must be filled out before continuing
        if(numOfTeams.getValue()>1 && !name.getText().equals(null) && !type.getText().equals("Tournament Type")){
            Tournament tournament = new Tournament(name.getText().toString(), type.getText().toString(), numOfTeams.getValue(), this.currentImage, new Team[numOfTeams.getValue()]);
            data.tournaments.add(tournament);
            Toast.makeText(getApplicationContext(), goodToast, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }


}
