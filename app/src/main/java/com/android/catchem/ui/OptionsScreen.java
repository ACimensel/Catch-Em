package com.android.catchem.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.catchem.R;
import com.android.catchem.model.MusicPlayer;
import com.android.catchem.model.OptionsManager;

/**
 *  Class for Options Screen UI
 *
 *  Options screen allows the user to select board size and number of wild pokemon.
 *  Board size can be:
 *      4 rows by 6 columns
 *      5 rows by 10 columns
 *      6 rows by 15 columns
 *  User can select number of wild pokemon, from options including:
 *      6 pokemon
 *      10 pokemon
 *      15 pokemon
 *      20 pokemon
 * The game size, and number of pokemon are saved between application runs.
 * Allows the user to reset number of times game has been played, and best scores for each game configuration
 * */

public class OptionsScreen extends AppCompatActivity {
    private int selectedNumWildPokemon;
    private int selectedNumRows;
    private int selectedNumCols;
    private final float RADIO_BUTTON_BASE_TEXT_SIZE = 14;
    private final float BASE_SCREEN_SIZE = 5;

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, OptionsScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        //delete the bar on top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        selectedNumWildPokemon = OptionsManager.getNumWildPokemon();
        selectedNumRows = OptionsManager.getGameBoardRows();
        selectedNumCols = OptionsManager.getGameBoardCols();

        createRadioButtonsNumRowsColumns();
        createRadioButtonsNumWildPokemon();

        setupSaveButton();
        setupResetButton();
    }

    private void createRadioButtonsNumRowsColumns() {
        RadioGroup groupBoardSize = findViewById(R.id.radio_group_num_rows_cols);
        int[] numRowsArr = getResources().getIntArray(R.array.num_rows);
        int[] numColsArr = getResources().getIntArray(R.array.num_columns);

        for(int i = 0; i < numRowsArr.length && i < numColsArr.length; i++) {
            final int numRows = numRowsArr[i];
            final int numCols = numColsArr[i];

            // create new radio button
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_rows_cols_option, numRows, numCols));
            button.setTextSize(((float) getScreenSizeInInches() / BASE_SCREEN_SIZE) * RADIO_BUTTON_BASE_TEXT_SIZE);

            // set on-click callbacks
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedNumRows = numRows;
                    selectedNumCols = numCols;
                }
            });

            // add to radio group
            groupBoardSize.addView(button);

            if(numRows == OptionsManager.getGameBoardRows() && numCols == OptionsManager.getGameBoardCols()) {
                button.setChecked(true);
            }
        }
    }

    private void createRadioButtonsNumWildPokemon() {
        RadioGroup groupNumWildPokemon = findViewById(R.id.radio_group_num_wild_pokemon);
        int[] numWildPokemonArr = getResources().getIntArray(R.array.num_wild_pokemon);

        for (final int numWildPokemon : numWildPokemonArr) {
            // create new radio button
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_wild_pokemon_option, numWildPokemon));
            button.setTextSize(((float) getScreenSizeInInches() / BASE_SCREEN_SIZE) * RADIO_BUTTON_BASE_TEXT_SIZE);

            // set on-click callbacks
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedNumWildPokemon = numWildPokemon;
                }
            });

            // add to radio group
            groupNumWildPokemon.addView(button);

            if (numWildPokemon == OptionsManager.getNumWildPokemon()) {
                button.setChecked(true);
            }
        }
    }

    private void setupSaveButton() {
        ImageButton saveButton = findViewById(R.id.save_options);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(selectedNumRows, selectedNumCols, selectedNumWildPokemon);
                finish();
            }
        });
    }

    private void saveData(int numRows, int numCols, int numWildPokemon) {
        SharedPreferences prefs = getSharedPreferences("shared_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("num_rows", numRows);
        editor.putInt("num_cols", numCols);
        editor.putInt("num_wild_pokemon", numWildPokemon);
        editor.apply();
        OptionsManager.update(this);
    }

    private void setupResetButton() {
        ImageButton resetButton = findViewById(R.id.btnResetRecords);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsManager.resetPlaysAndScores(OptionsScreen.this);
                Toast.makeText(OptionsScreen.this, R.string.reset_toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double getScreenSizeInInches() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double widthInches = (double) displayMetrics.widthPixels  / displayMetrics.xdpi;
        double heightInches = (double) displayMetrics.heightPixels / displayMetrics.ydpi;
        double screenInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
        return screenInches;
    }

    @Override
    protected void onResume() {
        super.onResume();
        MusicPlayer.playMusic();
    }
}