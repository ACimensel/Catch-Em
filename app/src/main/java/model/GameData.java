package model;

import android.content.Context;

public class GameData {
    private static GameData instance;

    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;
    private static int NUM_POKEMONS = 10 ;


    private Context context;

    public static GameData getInstance() {
        if(instance == null){
            instance = new GameData();
        }
        return instance;
    }
    private GameData() { }


    public int getNumRows() {
        return NUM_ROWS;
    }

    public int getNumCols() {
        return NUM_COLS;
    }

    public int getNumPokemons() {
        return NUM_POKEMONS;
    }
}
