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
 * Created by rohan on 2015-11-12.
 */
public class tournamentListAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;

    //***Later we should pass an array of tournaments instead of 4 Lists****//
    List<Tournament> tournaments;

    public tournamentListAdapter(Context context, List<Tournament> tournaments) {
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
            vi = inflater.inflate(R.layout.tournament_row_item, null);
        TextView name = (TextView) vi.findViewById(R.id.tournamentName);
        TextView size = (TextView) vi.findViewById(R.id.tournamentSize);
        TextView type = (TextView) vi.findViewById(R.id.tournamentType);

        String logo = tournaments.get(position).getTournamentLogo();
        int drawableResourceId = context.getResources().getIdentifier(logo, "drawable", context.getPackageName());
        ImageView imageView1 = (ImageView) vi.findViewById(R.id.tournamentLogo);
        imageView1.setImageResource(drawableResourceId);


        name.setText(tournaments.get(position).getTournamentName());
        size.setText("Number of teams: " + tournaments.get(position).getTournamentSize());
        type.setText(tournaments.get(position).getTournamentType());

        if(tournaments.get(position).finished)
            ((TextView) vi.findViewById(R.id.finished)).setText("FINISHED");

        return vi;
    }

}
