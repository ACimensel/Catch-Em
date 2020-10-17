package com.android.cmpt276as3.model;

import java.util.Random;

import model.PrintTable;

public class GameState {

    private OptionsManager optManager = OptionsManager.getInstance();
    private final int NUM_ROWS = optManager.getGameBoardRows();
    private final int NUM_COLS = optManager.getGameBoardCols();
    private int NUM_POKEMONS = optManager.getNumWildPokemon();

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


    public int getCountScan(){
        countScan = 0;
        for(int row = 0; row < NUM_ROWS ; row++){
            for(int col = 0; col < NUM_COLS; col++){
                if(isButtonScanned(row,col)){
                    countScan ++;
                }
            }
        }
        return countScan;
    }

    public int getNumberOfPokemonLeft() {
        numberOfPokemonLeft = 0;
        for(int row = 0; row < NUM_ROWS ; row++){
            for(int col = 0; col < NUM_COLS; col++){
                if(isButtonPokemon(row,col) && isButtonClicked(row,col)){
                    numberOfPokemonLeft++;
                }
            }
        }
        return numberOfPokemonLeft;
    }


    public boolean isButtonScanned(int row, int col) {
        return scannedButtons[row][col];
    }

    public void setScannedButtons(int row, int col) {
        //if it is a grass
        if(!isButtonPokemon(row,col)){
            scannedButtons[row][col] = true;
        }
        //if it is a pokemon
        if(isButtonPokemon(row,col)){
            //if it is clicked, scan
            if(isButtonClicked(row,col)){
                scannedButtons[row][col] = true;
            }
            //if it is not clicked, don't scan
        }
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
