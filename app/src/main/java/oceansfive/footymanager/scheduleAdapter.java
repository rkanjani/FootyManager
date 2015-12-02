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
    List<Game> games;
    int tournamentIndex;

    public scheduleAdapter(Context context, List<Game> games) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.games = games;
        games = data.tournaments.get(tournamentIndex).getGames();
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            vi = inflater.inflate(R.layout.schedule_row, null);
        TextView teamOne = (TextView) vi.findViewById(R.id.teamNameOne);
        TextView teamTwo = (TextView) vi.findViewById(R.id.teamNameTwo);

        TextView winnerOne = (TextView) vi.findViewById(R.id.winnerOne);
        TextView winnerTwo = (TextView) vi.findViewById(R.id.winnerTwo);

        TextView teamOneScore = (TextView) vi.findViewById(R.id.scoreOne);
        TextView teamTwoScore = (TextView) vi.findViewById(R.id.scoreTwo);

        TextView gameNumber = (TextView) vi.findViewById(R.id.gameNum);

        teamOne.setText(games.get(position).getTeam1().getTeamName());
        teamTwo.setText(games.get(position).getTeam2().getTeamName());

        if(games.get(position).getWinner()==games.get(position).getTeam1()){
            winnerOne.setText("W");
        }
        else if(games.get(position).getWinner()==games.get(position).getTeam2()){
            winnerTwo.setText("W");
        }

        if(games.get(position).getTeam1Score()==-1){

        }
        else{
            teamOneScore.setText(Integer.toString(games.get(position).getTeam1Score()));
            teamTwoScore.setText(Integer.toString(games.get(position).getTeam2Score()));
        }

        gameNumber.setText("#"+Integer.toString(position+1));

        /*String logo1 = games.get(position).getTeam1().getTeamLogo();
        int drawableResourceId = context.getResources().getIdentifier(logo1, "drawable", context.getPackageName());
        ImageView imageView1 = (ImageView) vi.findViewById(R.id.teamLogoOne);
        imageView1.setImageResource(drawableResourceId);*/

        /*String logo2 = games.get(position).getTeam2().getTeamLogo();
        int drawableResourceId2 = context.getResources().getIdentifier(logo2, "drawable", context.getPackageName());
        ImageView imageView2 = (ImageView) vi.findViewById(R.id.teamLogoTwo);
        imageView2.setImageResource(drawableResourceId2);*/



        return vi;
    }
}
