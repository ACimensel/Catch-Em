package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.cmpt276as3.R;
import com.android.cmpt276as3.model.OptionsManager;

public class OptionsScreen extends AppCompatActivity {
    private static final String TAG = "OptionsScreen";
    private OptionsManager optManager;
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
        this.setTitle(TAG);

        selectedNumWildPokemon = getNumWildPokemon(this);
        selectedNumRows = getNumRows(this);
        selectedNumCols = getNumCols(this);

        optManager = OptionsManager.getInstance();

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

            if(numRows == getNumRows(this) && numCols == getNumCols(this)) {
                button.setChecked(true);
            }
        }
        /*
        if(numRowsArr.length > 0 && numColsArr.length > 0) {
            ((RadioButton) groupBoardSize.getChildAt(0)).setChecked(true);
        }*/
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

            if (numWildPokemon == getNumWildPokemon(this)) {
                button.setChecked(true);
            }
        }
        /*
        if(groupNumWildPokemon.getCheckedRadioButtonId() == -1 && numWildPokemonArr.length > 0) {
            ((RadioButton)groupNumWildPokemon.getChildAt(0)).setChecked(true);
        }*/
    }

    private void setupSaveButton() {
        Button saveButton = findViewById(R.id.save_options);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(selectedNumRows, selectedNumCols, selectedNumWildPokemon);
                optManager.update(OptionsScreen.this);
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
    }

    static public int getNumRows(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);
        return prefs.getInt("num_rows", context.getResources().getInteger(R.integer.default_num_rows));
    }

    static public int getNumCols(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);
        return prefs.getInt("num_cols", context.getResources().getInteger(R.integer.default_num_cols));
    }

    static public int getNumWildPokemon(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("shared_settings", MODE_PRIVATE);
        return prefs.getInt("num_wild_pokemon", context.getResources().getInteger(R.integer.default_num_wild_pokemon));
    }
}