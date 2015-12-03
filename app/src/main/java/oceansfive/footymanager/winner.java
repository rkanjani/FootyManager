package oceansfive.footymanager;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.*;

public class winner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);
        setTitle("Winner!");
        getSupportActionBar().hide();

        String [] info = getIntent().getStringArrayExtra("info");



        TextView winnerName = (TextView) findViewById(R.id.winnerName);
        winnerName.setText(info[0]);

        TextView numWin = (TextView) findViewById(R.id.winnerWins);
        numWin.setText(info[1]);

        TextView numLoss = (TextView) findViewById(R.id.winnerLoss);
        numLoss.setText(info[2]);

    }
    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(intent, 0);

    }
}
