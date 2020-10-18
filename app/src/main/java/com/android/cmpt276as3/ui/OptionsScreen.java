package com.android.cmpt276as3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.android.cmpt276as3.R;
import com.android.cmpt276as3.model.OptionsManager;

public class OptionsScreen extends AppCompatActivity {
    private int selectedNumWildPokemon;
    private int selectedNumRows;
    private int selectedNumCols;

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
}