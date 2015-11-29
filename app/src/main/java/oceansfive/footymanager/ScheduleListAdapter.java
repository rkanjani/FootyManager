package oceansfive.footymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Matt on 2015-11-29.
 */
public class ScheduleListAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context context;
    TournamentData data = TournamentData.getInstance();
    Tournament tournament;
    Team[] standings;

    public ScheduleListAdapter(Context context, Tournament tournament) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.tournament = tournament;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.standings = tournament.getRanking();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.tournament_row_item, null);
        ImageView team1 = (ImageView) vi.findViewById(R.id.teamLogo);
        ImageView team2 = (ImageView) vi.findViewById(R.id.teamLogo);
        ImageView teamWin = (ImageView) vi.findViewById(R.id.teamLogo);


        team1.setImageResource(this.context.getResources().getIdentifier(uri, "drawable", this.context.getPackageName()));
        team2.setImageResource(this.context.getResources().getIdentifier(uri, "drawable", this.context.getPackageName()));
        teamWin.setImageResource(this.context.getResources().getIdentifier(uri, "drawable", this.context.getPackageName()));

        return vi;
    }

    @Override
    public int getCount() {

        return standings.length;
    }

    @Override
    public Team getItem(int position) {
        return standings[position];
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
}
