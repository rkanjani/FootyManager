package oceansfive.footymanager;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.content.DialogInterface;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by andrew on 2015-11-29.
 */
public class SelectLogo  extends AppCompatActivity {

    static String [] logos=TournamentData.logos;
    public boolean isTeam = false;
    public int teamPos=-1;



   public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_select_logo);

       Intent i = getIntent();
       Bundle extras = i.getExtras();
       if(extras!=null){
           this.logos=TournamentData.teamLogos;
           this.teamPos= extras.getInt("teamPos");
           isTeam=true;
       }



    GridView gridview = (GridView) findViewById(R.id.gridview);
    gridview.setAdapter(new LogoAdapter(this));

    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Intent data = new Intent();
            //Toast.makeText(SelectLogo.this, "" + logos[position], Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, data);
            if(isTeam){
                data.putExtra("teamPos",""+teamPos);
            }

            data.putExtra("image", ""+logos[position]);
            finish();
        }
    });
}
}
