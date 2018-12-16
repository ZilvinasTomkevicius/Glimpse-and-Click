package lt.ziltom.zilvinastomkevicius.projektas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lt.ziltom.zilvinastomkevicius.projektas.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private DynamicArray<Game> mGameList;

    public RecyclerViewAdapter(DynamicArray<Game> gameList) {
        mGameList = gameList;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        final Game game = mGameList.get(position);
        holder.gameName.setText(game.getName());
        holder.gamePoints.setText("points: " + Integer.toString(game.getPoints()));
        holder.gameDate.setText(game.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mGameList.count();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView gameName;
        private TextView gamePoints;
        private TextView gameDate;

        public ViewHolder(View view) {
            super(view);

            gameName = view.findViewById(R.id.history_item_gameName);
            gamePoints = view.findViewById(R.id.history_item_gamePoints);
            gameDate = view.findViewById(R.id.history_item_gameDate);
        }
    }
}
