package oceansfive.footymanager;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by rohan on 2015-11-23.
 */
public class teamListAdapter extends BaseAdapter{
    Context context;
    private static LayoutInflater inflater = null;
    private static String newTeamName = null;

    TournamentData data = TournamentData.getInstance();
    Team[] teams;
    Tournament tournament;

    public teamListAdapter(Context context, Tournament tournament) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.teams = tournament.getTeams();
        this.tournament = tournament;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return teams.length;
    }

    @Override
    public Team getItem(int position) {
        // TODO Auto-generated method stub
        return teams[position];
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.edit_team_row_item, null);

        final int index = position;
        final EditText name = (EditText) vi.findViewById(R.id.teamName);

        ImageView logo = (ImageView) vi.findViewById(R.id.teamLogo);

        //If the team has a name, fill it in, else leave it blank for user
        if(teams[index]!=null){
            name.setText(teams[index].getTeamName(), TextView.BufferType.EDITABLE);
        }

        TextWatcher teamNameWatcher = new TextWatcher() {

            //Changes a Team name to new name
            public void afterTextChanged(Editable s) {
                tournament.teams[index] = new Team(s.toString(), null);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        };

        name.addTextChangedListener(teamNameWatcher);
        return vi;
    }

}
