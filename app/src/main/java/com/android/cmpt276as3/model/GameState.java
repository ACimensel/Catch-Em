package com.android.cmpt276as3.model;

import android.util.Log;

import java.util.Random;

public class GameState {

    GameData gameData = GameData.getInstance();
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 8;
    private static int NUM_POKEMONS = 16;

    int [][] tableForPokemon = new int[NUM_ROWS][NUM_COLS];
    boolean [][] clickedButtons = new boolean[NUM_ROWS][NUM_COLS];
    boolean [][] scannedButtons = new boolean[NUM_ROWS][NUM_COLS];
    //make another value

    int numberOfPokemonLeft = NUM_POKEMONS;

    int countScan = 0;


    //Create the table and populate Pokemons
    public void setTable(){
        //Got the idea from https://www.youtube.com/watch?v=nORt4szAmkI
        // Random for generating numbers
        Random r = new Random();

        //Populate Pokemons
        while(NUM_POKEMONS > 0){
            int row = r.nextInt(NUM_ROWS);
            int col = r.nextInt(NUM_COLS);

            //-1 is the Pokemon
            if(tableForPokemon[row][col] != -1){
                tableForPokemon[row][col] = -1;
                NUM_POKEMONS--;
            }
        }
    }

    public boolean isButtonClicked(int row, int col){
        //keep track of the buttons that are clicked
        return clickedButtons[row][col];
    }

    public void setClickedButtons(int row, int col){
        clickedButtons[row][col] = true;
    }

    public int updatePokemonNumber(int row, int col){
        int count = 0;
        //Check its column
        for(int i = 0; i < NUM_ROWS ; i ++){
            if(isButtonPokemon(i,col)){
                if(!isButtonClicked(i,col)) { //pokemon is not clicked yet
                    count++;
                }
            }

        }
        //Check its row
        for(int i = 0; i < NUM_COLS; i++){
            if(isButtonPokemon(row,i)){
                if(!isButtonClicked(row,i)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getCountScan() {
        return countScan;
    }

    public void calculateScan(int row, int col) {
        //Case 1 : User press on a grass without Pokemon
        if(!isButtonPokemon(row,col) && !isButtonClicked(row,col)){
            countScan ++;
        }

        //Case 2: User press on a grass with Pokemon for the first time.
        //Shouldn't scan it
        if(isButtonPokemon(row,col) && !isButtonClicked(row, col)){

        }

        //Case 3: User press on Pokemon
        if(isButtonPokemon(row,col) && isButtonClicked(row,col) && !isButtonScanned(row, col)){
            countScan ++;
        }

    }

    public boolean isButtonScanned(int row, int col) {
        return scannedButtons[row][col];
    }

    public void setScannedButtons(int row, int col) {
        scannedButtons[row][col] = true;
    }

    public boolean isButtonPokemon(int row, int col) {
        PrintTable.print(tableForPokemon,NUM_ROWS,NUM_COLS);

        //If the table has Pokemon, return true;
        if(tableForPokemon[row][col] == -1){
            return true;
        }
        return false;
    }

}
