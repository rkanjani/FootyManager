package oceansfive.footymanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.widget.Button;

import org.w3c.dom.Text;

public class TournamentCreation extends AppCompatActivity {

    private static final int SELECT_LOGO = 1;

    final CharSequence tournamentTypes[] = new CharSequence[] {"Combinational", "Knock-Out", "Round Robin"};

    private String selectedLogoPath;
    private String filemanagerstring;

    private String selectedStyle = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_creation);
        setTitle("Create Tournament");


    }

    public String getPath(Uri uri) {
        // Displays error if image cannot be chosen
        if( uri == null ) {
            Toast.makeText(getApplicationContext(), "Image cannot be chosen", Toast.LENGTH_SHORT).show();
            return null;
        }
        // Tries to look for images in gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        else return null;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == SELECT_LOGO) {
            Uri selectedImageUri = data.getData();
            System.out.println("URI "+ selectedImageUri);
            ((ImageView) findViewById(R.id.tournamentLogo)).setImageURI(selectedImageUri);
        }
    }

    public void tournamentStyleSelect(View view){
        TextView title, message;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final Button tournamentStyle = (Button)findViewById(R.id.tournamentStyleButton);
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
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_LOGO);
    }

}
