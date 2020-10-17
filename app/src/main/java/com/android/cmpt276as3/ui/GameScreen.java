package com.android.cmpt276as3.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.cmpt276as3.R;
import com.android.cmpt276as3.model.GameState;
import com.android.cmpt276as3.model.OptionsManager;

public class GameScreen extends AppCompatActivity {
    private static final String TAG = "GameScreen";
    private OptionsManager optManager = OptionsManager.getInstance();
    private final int NUM_ROWS = optManager.getGameBoardRows();
    private final int NUM_COLS = optManager.getGameBoardCols();
    private final int NUM_POKEMONS = optManager.getNumWildPokemon();

    //this must stay here so that it doesn't create a new table over and over again whenever the button is clicked
    GameState gameState = new GameState();


    Button[][] buttons = new Button[NUM_ROWS][NUM_COLS];

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        this.setTitle(TAG);

        optManager = OptionsManager.getInstance();


        populateButtons();

        displayNumberOfPokemonsLeft();
        displayNumberOfTimeScanned();
    }


    private void populateButtons() {
        TableLayout table = findViewById(R.id.tableForButtons);

        //populating buttons in table
        for(int row = 0; row < NUM_ROWS; row ++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);

            for(int col = 0 ; col < NUM_COLS; col++){
                final int FINAL_ROW = row;
                final int FINAL_COL = col;

                Button btn = new Button(this);
                btn.setBackgroundResource(R.drawable.tiletall_grass);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //TODO: Auto resize the text in the button
                //set text size in button
                int textSize = 0;
                if(NUM_COLS*NUM_ROWS < 25){
                    textSize = 50;
                }
                else if(NUM_COLS*NUM_ROWS < 50){
                    textSize = 45;
                }
                else if(NUM_COLS*NUM_ROWS < 100){
                    textSize = 30;
                }
                btn.setTextSize( textSize);
                //set text color
                btn.setTextColor(getApplication().getResources().getColor(R.color.red, getTheme()));

                //when the button is clicked,
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        populatePokemon(FINAL_ROW, FINAL_COL);
                        scanPokemon(FINAL_ROW, FINAL_COL);
                        displayNumberOfPokemonsLeft();
                        displayNumberOfTimeScanned();
                    }
                });

                tableRow.addView(btn);
                buttons[row][col] = btn;
            }
        }
    }

    private void displayNumberOfTimeScanned() {
        int numberOfTimeScanned = gameState.getCountScan();
        TextView textNumberOfTimeScanned = (TextView) findViewById(R.id.textNumberOfTimesScanned);
        textNumberOfTimeScanned.setText("# Scans Used: " + numberOfTimeScanned);
    }

    private void displayNumberOfPokemonsLeft() {
        int numberOfPokemonLeft = gameState.getNumberOfPokemonLeft();
        TextView textNumberOfPokemonsLeft = (TextView) findViewById(R.id.textNumberOfPokemonLeft);
        textNumberOfPokemonsLeft.setText("Found " + numberOfPokemonLeft + " of " + NUM_POKEMONS + " Pokemon");
    }

    //TODO: Figure out how to count if I had clicked on the button previously or not
    private void scanPokemon(int row, int col) {
        //Calculate Scans

        //gameState.calculateScan(row,col);

        gameState.setScannedButtons(row,col);
        //Let gameState know that the buttons is clicked
        gameState.setClickedButtons(row,col);

        //Display Clicked(Scanned) Button Numbers
        displayNumbers();

    }

    private void displayNumbers() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                if(gameState.isButtonScanned(row,col)) {
                    buttons[row][col].setText("" + gameState.updatePokemonNumber(row, col));
                }
            }
        }
    }

    private void populatePokemon(int row, int col) {

        Button btn = buttons[row][col];

        //Lock Button Sizes:
        lockButtonSizes();

        //Set the pokemon table
        gameState.setTable();

        //If the button is Pokemon, set the backGround to pokemon
        if(gameState.isButtonPokemon(row,col)){
            int newWidth = btn.getWidth();
            int newHeight = btn.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.rand_eevee);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
        }
    }


    private void lockButtonSizes() {
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                Button btn = buttons[row][col];

                int width = btn.getWidth();
                btn.setMinWidth(width);
                btn.setMaxWidth(width);

                int height = btn.getHeight();
                btn.setMinHeight(height);
                btn.setMaxHeight(height);
            }
        }
    }

}