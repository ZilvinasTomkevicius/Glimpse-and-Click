package lt.ziltom.zilvinastomkevicius.projektas.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import lt.ziltom.zilvinastomkevicius.projektas.DynamicArray;
import lt.ziltom.zilvinastomkevicius.projektas.Game;
import lt.ziltom.zilvinastomkevicius.projektas.SavedGameHistory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lt.ziltom.zilvinastomkevicius.projektas.R;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SavedGameHistory.gameHistory.count() == 0) {
            SavedGameHistory.gameHistory = new DynamicArray<>();
            getHistory();
        } else {
            setHistory();
        }
    }

    public void intentToGame(View v) {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    public void intentToHistory(View v) {
        startActivity(new Intent(this, HistoryActivity.class));
        finish();
    }

    public void intentToAbout(View view) {
        startActivity(new Intent(this, AboutActivity.class));
        finish();
    }

    public void exitApp(View v) {
        this.finish();
        System.exit(0);
    }

    public void setHistory() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("history", serializeToJsonHistory());
        editor.commit();
    }

    public String serializeToJsonHistory() {
        Gson gson = new Gson();
        String jsonCheckpoints = gson.toJson(SavedGameHistory.gameHistory);

        return jsonCheckpoints;
    }

    public void serializeFromJsonHistory(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<DynamicArray<Game>>(){}.getType();

        SavedGameHistory.gameHistory = gson.fromJson(json, type);
    }


    public void getHistory() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        serializeFromJsonHistory(preferences.getString("history", null));
    }
}
