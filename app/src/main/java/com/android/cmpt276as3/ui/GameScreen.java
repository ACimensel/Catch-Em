package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.android.cmpt276as3.R;

import java.util.Random;

import model.GameState;
import model.PrintTable;

public class GameScreen extends AppCompatActivity {
    private static final String TAG = "GameScreen";
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;

    //this must stay here so that it doesn't create a new table over and over again whenever the button is clicked
    GameState gameState = new GameState();
    int[][] table = gameState.createTable();

    Button buttons[][] = new Button[NUM_ROWS][NUM_COLS];

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        this.setTitle(TAG);

        //TODO: Separate the game logic with the UI
        populateButtons();
    }


    private void populateButtons() {
        TableLayout table = (TableLayout) findViewById(R.id.tableForButtons);

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
                btn.setBackgroundResource(R.drawable.tall_grass);
                btn.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

                //when the button is clicked,
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gridButtonClicked(FINAL_ROW, FINAL_COL);
                    }
                });

                tableRow.addView(btn);
                buttons[row][col] = btn;
            }
        }
    }


    private void gridButtonClicked(int row, int col) {
        Toast.makeText(this, "btn clicked",Toast.LENGTH_SHORT).show();
        Button btn = buttons[row][col];

        //Lock Button Sizes:
        lockButtonSizes();

        //If the button is Pokemon, set the backGround to pokemon
        if(btnisPokemon(row,col)){
            int newWidth = btn.getWidth();
            int newHeight = btn.getHeight();
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.charmander);
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
            Resources resource = getResources();
            btn.setBackground(new BitmapDrawable(resource, scaledBitmap));
        }

        //Update Button Numbers
        //updateButtonNumbers();
        //Display Button Numbers

    }


    //TODO: MAKE the function where it knows if it is pokemon or not
    private boolean btnisPokemon(int row, int col) {

        PrintTable.print(table,NUM_ROWS,NUM_COLS);

        //If the table has Pokemon, return true;
        if(table[row][col] == -1){
            return true;
        }
        return false;
    }

    //TODO: Make function where it sets the number for all the clicked buttons
    private void updateButtonNumbers() {

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