package oceansfive.footymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Samsore on 2015-11-29.
 */
public class standingsAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    TournamentData data = TournamentData.getInstance();
    Tournament tournament;
    Team[] standings;

    private Context context;

    public standingsAdapter(Context context, Tournament tournament) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.tournament = tournament;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.standings = tournament.getRanking();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.standings_row, null);

        TextView name = (TextView) vi.findViewById(R.id.teamName);
        TextView winLossRatio = (TextView) vi.findViewById(R.id.winLoss);
        TextView standing = (TextView) vi.findViewById(R.id.standingsNum);



        name.setText(standings[position].getTeamName());
        winLossRatio.setText("Wins: " + standings[position].getWins() + " Losses: " + standings[position].getLosses());
        standing.setText("#"+Integer.toString(position+1));


        if(standings[position].getTeamLogo() != null) {
            //displays the teams logo
            String logo = standings[position].getTeamLogo();
            int drawableResourceId = context.getResources().getIdentifier(logo, "drawable", context.getPackageName());
            ImageView imageView1 = (ImageView) vi.findViewById(R.id.teamLogo);
            imageView1.setImageResource(drawableResourceId);
        }

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
