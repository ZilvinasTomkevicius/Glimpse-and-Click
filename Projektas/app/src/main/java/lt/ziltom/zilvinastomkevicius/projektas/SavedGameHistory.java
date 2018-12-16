package lt.ziltom.zilvinastomkevicius.projektas;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SavedGameHistory {

    public static DynamicArray<Game> gameHistory = new DynamicArray<>();
}
