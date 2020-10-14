package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.cmpt276as3.R;

public class OptionsScreen extends AppCompatActivity {
    private static final String TAG = "OptionsScreen";

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, OptionsScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        this.setTitle(TAG);

        createRadioButtonsNumRowsColumns();
        createRadioButtonsNumWildPokemon();
        setupSaveButton();
    }

    private void createRadioButtonsNumRowsColumns() {
        RadioGroup groupBoardSize = (RadioGroup) findViewById(R.id.radio_group_num_rows_cols);
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
                    Toast.makeText(OptionsScreen.this, "Rows: " + numRows + ", Cols: " + numCols, Toast.LENGTH_SHORT).show();
                }
            });

            // add to radio group
            groupBoardSize.addView(button);
        }

        // TODO: change set check to the shared pref
        if(numRowsArr.length > 0 && numColsArr.length > 0) {
            ((RadioButton) groupBoardSize.getChildAt(0)).setChecked(true);
        }
    }

    private void createRadioButtonsNumWildPokemon() {
        RadioGroup groupNumPokemon = (RadioGroup) findViewById(R.id.radio_group_num_wild_pokemon);
        int[] numWildPokemonArr = getResources().getIntArray(R.array.num_wild_pokemon);

        for(int i = 0; i < numWildPokemonArr.length; i++) {
            final int numWildPokemon = numWildPokemonArr[i];

            // create new radio button
            RadioButton button = new RadioButton(this);
            button.setText(getString(R.string.num_wild_pokemon_option, numWildPokemon));

            // set on-click callbacks
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionsScreen.this, "You clicked " + numWildPokemon, Toast.LENGTH_SHORT).show();
                }
            });

            // add to radio group
            groupNumPokemon.addView(button);
        }

        // TODO: change set check to the shared pref
        if(numWildPokemonArr.length > 0) {
            ((RadioButton)groupNumPokemon.getChildAt(0)).setChecked(true);
        }
    }

    private void setupSaveButton() {
        Button saveButton = (Button) findViewById(R.id.save_options);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup group = (RadioGroup) findViewById(R.id.radio_group_num_wild_pokemon);
                int idOfSelected = group.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(idOfSelected);
                String message = radioButton.getText().toString();

                Toast.makeText(OptionsScreen.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}