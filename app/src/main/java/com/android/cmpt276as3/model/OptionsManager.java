package com.android.cmpt276as3.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.cmpt276as3.R;

import static android.content.Context.MODE_PRIVATE;

public class OptionsManager {
    private static int gameBoardRows;
    private static int gameBoardCols;
    private static int numWildPokemon;

    private static int numTimesPlayed;
    private static int highScore_4x6_6p, highScore_4x6_10p, highScore_4x6_15p, highScore_4x6_20p;
    private static int highScore_5x10_6p, highScore_5x10_10p, highScore_5x10_15p, highScore_5x10_20p;
    private static int highScore_6x15_6p, highScore_6x15_10p, highScore_6x15_15p, highScore_6x15_20p;

    private OptionsManager() {
    }

    public static void update(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);

        gameBoardRows = prefs.getInt("num_rows", context.getResources().getInteger(R.integer.default_num_rows));
        gameBoardCols = prefs.getInt("num_cols", context.getResources().getInteger(R.integer.default_num_cols));
        numWildPokemon = prefs.getInt("num_wild_pokemon", context.getResources().getInteger(R.integer.default_num_wild_pokemon));
    }

    public static void resetPlaysAndScores(Activity activity) {
        numTimesPlayed = 0;
        highScore_4x6_6p = highScore_4x6_10p = highScore_4x6_15p = highScore_4x6_20p = Integer.MAX_VALUE;
        highScore_5x10_6p = highScore_5x10_10p = highScore_5x10_15p = highScore_5x10_20p = Integer.MAX_VALUE;
        highScore_6x15_6p = highScore_6x15_10p = highScore_6x15_15p = highScore_6x15_20p = Integer.MAX_VALUE;
    }

    public static int getGameBoardRows() {
        return gameBoardRows;
    }

    public static  int getGameBoardCols() {
        return gameBoardCols;
    }

    public static int getNumWildPokemon() {
        return numWildPokemon;
    }

    public static int getNumPlays() {
        return numTimesPlayed;
    }

    public static int getHighScore() {
        if(gameBoardRows == 4 && gameBoardCols == 6) {
            if(numWildPokemon == 6) { return highScore_4x6_6p; }
            else if(numWildPokemon == 10) { return highScore_4x6_10p; }
            else if(numWildPokemon == 15) { return highScore_4x6_15p; }
            else if(numWildPokemon == 20) { return highScore_4x6_20p; }
        }
        else if(gameBoardRows == 5 && gameBoardCols == 10) {
            if(numWildPokemon == 6) { return highScore_5x10_6p; }
            else if(numWildPokemon == 10) { return highScore_5x10_10p; }
            else if(numWildPokemon == 15) { return highScore_5x10_15p; }
            else if(numWildPokemon == 20) { return highScore_5x10_20p; }
        }
        else if(gameBoardRows == 6 && gameBoardCols == 15) {
            if(numWildPokemon == 6) { return highScore_6x15_6p; }
            else if(numWildPokemon == 10) { return highScore_6x15_10p; }
            else if(numWildPokemon == 15) { return highScore_6x15_15p; }
            else if(numWildPokemon == 20) { return highScore_6x15_20p; }
        }
        return -1;
    }

    // TODO SETUP VARS FUNCTION

    // TODO OR SETUP A MEMBER CONTEXT THAT CAN BE USED IN ALL FUNCTIONS

    public static void incrementPlays(Context context) {
        numTimesPlayed++;
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("num_plays", prefs.getInt("num_plays", 0) + 1);
        editor.apply();
    }

    public static void setHighScore(Context context, int score) {
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if(gameBoardRows == 4 && gameBoardCols == 6) {
            if(numWildPokemon == 6 && prefs.getInt("highScore_4x6_6p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_4x6_6p", score); }
            else if(numWildPokemon == 10 && prefs.getInt("highScore_4x6_10p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_4x6_10p", score); }
            else if(numWildPokemon == 15 && prefs.getInt("highScore_4x6_15p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_4x6_15p", score); }
            else if(numWildPokemon == 20 && prefs.getInt("highScore_4x6_20p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_4x6_20p", score); }
        }
        else if(gameBoardRows == 5 && gameBoardCols == 10) {
            if(numWildPokemon == 6 && prefs.getInt("highScore_5x10_6p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_5x10_6p", score); }
            else if(numWildPokemon == 10 && prefs.getInt("highScore_5x10_10p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_5x10_10p", score); }
            else if(numWildPokemon == 15 && prefs.getInt("highScore_5x10_15p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_5x10_15p", score); }
            else if(numWildPokemon == 20 && prefs.getInt("highScore_5x10_20p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_5x10_20p", score); }
        }
        else if(gameBoardRows == 6 && gameBoardCols == 15) {
            if(numWildPokemon == 6 && prefs.getInt("highScore_6x15_6p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_6x15_6p", score); }
            else if(numWildPokemon == 10 && prefs.getInt("highScore_6x15_10p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_6x15_10p", score); }
            else if(numWildPokemon == 15 && prefs.getInt("highScore_6x15_15p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_6x15_15p", score); }
            else if(numWildPokemon == 20 && prefs.getInt("highScore_6x15_20p", Integer.MAX_VALUE) > score) { editor.putInt("highScore_6x15_20p", score); }
        }
        editor.apply();
    }
}
