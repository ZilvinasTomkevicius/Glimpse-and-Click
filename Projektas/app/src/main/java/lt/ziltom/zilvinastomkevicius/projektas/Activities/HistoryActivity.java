package lt.ziltom.zilvinastomkevicius.projektas.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import lt.ziltom.zilvinastomkevicius.projektas.R;

import lt.ziltom.zilvinastomkevicius.projektas.RecyclerViewAdapter;
import lt.ziltom.zilvinastomkevicius.projektas.SavedGameHistory;

import static java.security.AccessController.getContext;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.history_recyclerView);

        setRecyclerView();
    }

    public void setRecyclerView() {
        if(SavedGameHistory.gameHistory.count() == 0 || SavedGameHistory.gameHistory == null) {
            Toast.makeText(this, "History is empty", Toast.LENGTH_SHORT).show();
        }
        else {
            recyclerViewAdapter = new RecyclerViewAdapter(SavedGameHistory.gameHistory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(recyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void historyBackButton(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
