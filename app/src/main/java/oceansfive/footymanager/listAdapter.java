package oceansfive.footymanager;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Iterator;
import java.util.List;

/**
 * Created by rohan on 2015-11-12.
 */
public class listAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;

    //***Later we should pass an array of tournaments instead of 4 Lists****//
    List<Tournament> tournaments;

    public listAdapter(Context context, List<Tournament> tournaments) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.tournaments = tournaments;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return tournaments.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return tournaments.get(position);
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
            vi = inflater.inflate(R.layout.row, null);
        TextView name = (TextView) vi.findViewById(R.id.tournamentName);
        TextView size = (TextView) vi.findViewById(R.id.tournamentSize);
        TextView type = (TextView) vi.findViewById(R.id.tournamentType);
        ImageView logo = (ImageView) vi.findViewById(R.id.tournamentLogo);


        name.setText(tournaments.get(position).tournamentName);
        size.setText("Number of teams: " + tournaments.get(position).tournamentSize);
        type.setText(tournaments.get(position).getTournamentType());
        logo.setImageResource(this.context.getResources().getIdentifier(tournaments.get(position).getLogo(), "drawable", this.context.getPackageName()));

        return vi;
    }

}
