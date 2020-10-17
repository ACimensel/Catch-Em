package com.android.cmpt276as3.model;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.cmpt276as3.R;

public class OptionsManager {
    private static OptionsManager instance;
    private int gameBoardRows;
    private int gameBoardCols;
    private int numWildPokemon;

    private OptionsManager() {
    }

    public static OptionsManager getInstance() {
        if(instance == null) { instance = new OptionsManager(); }
        return instance;
    }

    public void update(Activity activity) {
        SharedPreferences prefs = activity.getApplicationContext().getSharedPreferences("shared_settings", Context.MODE_PRIVATE);

        gameBoardRows = prefs.getInt("num_rows", activity.getApplicationContext().getResources().getInteger(R.integer.default_num_rows));
        gameBoardCols = prefs.getInt("num_cols", activity.getApplicationContext().getResources().getInteger(R.integer.default_num_cols));
        numWildPokemon = prefs.getInt("num_wild_pokemon", activity.getApplicationContext().getResources().getInteger(R.integer.default_num_wild_pokemon));
    }

    // TODO: move static functions in OptionsScreen to here
    public int getGameBoardRows() {
        return gameBoardRows;
    }

    public int getGameBoardCols() {
        return gameBoardCols;
    }

    public int getNumWildPokemon() {
        return numWildPokemon;
    }
}
