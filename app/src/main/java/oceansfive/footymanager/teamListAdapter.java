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
import android.widget.Toast;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by rohan on 2015-11-23.
 */
public class teamListAdapter extends BaseAdapter{
    Context context;
    private static LayoutInflater inflater = null;

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
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
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
    public boolean fieldsFilled(){
        for(int x=0;x<teams.length;x++){
            if(teams[x] == null)
                    return false;
        }
        return true;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //System.out.println("GET VIEW CALLED");
        refreshTeams();

        /*System.out.println("teams:");
        for(int i=0;i<this.teams.length;i++){
            System.out.println(teams[i]);
            System.out.println("");
        }*/

        //System.out.println("POSITION: "+position);
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.edit_team_row_item, null);
        final int index = position;
        //System.out.println("index: "+index);
        final EditText name = (EditText) vi.findViewById(R.id.teamName);

        ImageView logo = (ImageView) vi.findViewById(R.id.teamLogo);

        logo.setTag(Integer.toString(position));

        //If the team has a name, fill it in, else leave it blank for user
        if(teams[index]!=null){
            name.setText(teams[index].getTeamName(), TextView.BufferType.EDITABLE);
            if(teams[index].getTeamLogo()!=null) {
                int image = context.getResources().getIdentifier(teams[index].getTeamLogo(), "drawable", context.getPackageName());
                logo.setImageResource(image);
            }
            else{
                System.out.println("team logo is null: " + teams[index]);
            }

        }
        else{
            System.out.println("NULL TEAMM!");
        }



        /*
        if(teams[index].getTeamLogo()!=null){
            int image = context.getResources().getIdentifier(teams[index].getTeamLogo(), "drawable", context.getPackageName());
            logo.setImageResource(image);
        }
        else{
            System.out.println("THIS IS THE FUCK thing .... TEAMNAME: "+teams[index].getTeamName()+"   TEAMLOGO: "+teams[index].getTeamLogo());
        }
        */


        TextWatcher teamNameWatcher = new TextWatcher() {
            int tournamentIndex = data.tournaments.indexOf(tournament);
            //Changes a Team name to new name
            public void afterTextChanged(Editable s) {
                String tempTeamLogo=null;
                if(teams[index]!=null){
                    tempTeamLogo=teams[index].getTeamLogo();
                }
                data.tournaments.get(tournamentIndex).teams[index] = new Team(s.toString(), tempTeamLogo);
               // data.tournaments.get(tournamentIndex).teams[index].setTeamName(s.toString());
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

    public void update(){
        this.notifyDataSetChanged();
    }

    public void refreshTeams(){
        int tournamentIndex = data.tournaments.indexOf(tournament);
        this.teams= data.tournaments.get(tournamentIndex).getTeams();
    }

}
