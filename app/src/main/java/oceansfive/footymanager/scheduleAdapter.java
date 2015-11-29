package oceansfive.footymanager;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rohan on 2015-11-29.
 */
public class scheduleAdapter extends BaseAdapter{

    private static LayoutInflater inflater = null;

    Context context;
    TournamentData data = TournamentData.getInstance();
    Tournament tournament;
    List<Game> games;

    public scheduleAdapter(Context context, Tournament tournament) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.tournament = tournament;
        games = tournament.getGames();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.standings = tournament.getRanking();
    }


    @Override
    public int getCount() {
        return games.size();
    }

    @Override
    public Game getItem(int position) {
        return games.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.tournament_row_item, null);
        /*TextView teamOne = (TextView) vi.findViewById(R.id.teamNameOne);
        TextView teamTwo = (TextView) vi.findViewById(R.id.teamNameTwo);
        TextView winner = (TextView) vi.findViewById(R.id.winnerName);

        TextView teamOneScore = (TextView) vi.findViewById(R.id.scoreOne);
        TextView teamTwoScore = (ImageView) vi.findViewById(R.id.scoreTwo);

        ImageView
        teamOne.setText(games.get(position).getTeam1().getTeamName());
        teamTwo.setText(games.get(position).getTeam2().getTeamName());
        winner.setText(games.get(position).getWinner().getTeamName());

        winLossRatio.setText("Wins: " + standings[position].getWins() + " Losses: " + standings[position].getLosses());
        standing.setText(position+1);*/
        //logo.setImageResource(this.context.getResources().getIdentifier(uri, "drawable", this.context.getPackageName()));

        return vi;
    }
}
