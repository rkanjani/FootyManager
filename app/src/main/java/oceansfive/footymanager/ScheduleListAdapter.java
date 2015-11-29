package oceansfive.footymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Matthew on 11/29/2015.
 */
public class ScheduleListAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;

    //***Later we should pass an array of tournaments instead of 4 Lists****//
    List<Team> teams;

    public ScheduleListAdapter(Context context, Tournament Tournaments) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.teams = teams;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return teams.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return teams.get(position);
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
            vi = inflater.inflate(R.layout.tournament_row_item, null);
        TextView name = (TextView) vi.findViewById(R.id.tournamentName);
        TextView size = (TextView) vi.findViewById(R.id.tournamentSize);
        TextView type = (TextView) vi.findViewById(R.id.tournamentType);

        ImageView logo = (ImageView) vi.findViewById(R.id.tournamentLogo);
        String uri = teams.get(position).getTeamLogo();


        name.setText(teams.get(position).getTeamName());
//        size.setText("Number of teams: " + teams.get(position).getTournamentSize());
//        type.setText(teams.get(position).getTeamName());
        logo.setImageResource(this.context.getResources().getIdentifier(uri, "drawable", this.context.getPackageName()));

        return vi;
    }

}
