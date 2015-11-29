package oceansfive.footymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;

public class team_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        TextView text = (TextView) findViewById(R.id.teamName);
        text.setText("your text!");
    }
}
