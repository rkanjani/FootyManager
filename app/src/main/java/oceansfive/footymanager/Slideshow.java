package oceansfive.footymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Slideshow extends AppCompatActivity {
    String [] images;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        Intent i = getIntent();
        Bundle extras = i.getExtras();

        counter = -1;
        images= extras.getStringArray("images");

        //Toast.makeText(getApplicationContext(), "images length: "+images.length, Toast.LENGTH_SHORT).show();

        if(images==null){
            Context context = getApplicationContext();
            CharSequence text = "no image received in bundle";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            finish();
        }
        nextSlide();
    }

    public void nextSlide(View view){
        nextSlide();
    }

    public void nextSlide(){
        counter++;
        //Toast.makeText(getApplicationContext(), ""+counter, Toast.LENGTH_SHORT).show();
        if (counter>=images.length){
            finish();
        }
        else {
            int image = this.getResources().getIdentifier(images[counter], "drawable", this.getPackageName());
            ((ImageButton) findViewById(R.id.slideshowButton)).setImageResource(image);
        }

    }

}
