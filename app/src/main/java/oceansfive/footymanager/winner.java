package oceansfive.footymanager;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class winner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        setTitle("Winner!");
        getSupportActionBar().hide();
        Context context = getApplicationContext();

        String [] info = getIntent().getStringArrayExtra("info");



        TextView winnerName = (TextView) findViewById(R.id.winnerName);
        winnerName.setText(info[0]);

        TextView numWin = (TextView) findViewById(R.id.winnerWins);
        numWin.setText(info[1]);

        TextView numLoss = (TextView) findViewById(R.id.winnerLoss);
        numLoss.setText(info[2]);

        ImageView logo = (ImageView) findViewById(R.id.teamLogo);
        if(info[3]!=null)
            logo.setImageResource(context.getResources().getIdentifier(info[3], "drawable", context.getPackageName()));

    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
    public void goToMain(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }
}
