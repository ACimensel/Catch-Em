package com.android.cmpt276as3.model;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

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

        gameBoardRows = prefs.getInt("num_rows", 4);
        gameBoardCols = prefs.getInt("num_cols", 6);
        numWildPokemon = prefs.getInt("num_wild_pokemon", 6);
    }

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
