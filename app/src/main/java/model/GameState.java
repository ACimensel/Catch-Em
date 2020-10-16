package model;

import android.util.Log;

import java.util.Random;

public class GameState {

    GameData gameData = GameData.getInstance();
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;
    private static int NUM_POKEMONS = 10 ;

    int [][] tableForPokemon = new int[NUM_ROWS][NUM_COLS];
    boolean [][] isButtonClicked = new boolean[NUM_ROWS][NUM_COLS];
    boolean [][] isButtonScanned = new boolean[NUM_ROWS][NUM_COLS];

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

    public boolean getIsButtonClicked(int row, int col){
        //keep track of the buttons that are clicked
        return isButtonClicked[row][col];
    }

    public void setIsButtonClicked(int row, int col){
        isButtonClicked[row][col] = true;
    }

    public int updatePokemonNumber(int row, int col){
        int count = 0;
        //Check its column
        for(int i = 0; i < NUM_ROWS ; i ++){
            if(isButtonPokemon(i,col)){
                if(!isButtonClicked[i][col]) { //pokemon is not clicked yet
                    count++;
                }
            }

        }
        //Check its row
        for(int i = 0; i < NUM_COLS; i++){
            if(isButtonPokemon(row,i)){
                if(!isButtonClicked[row][i]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getCountScan() {
        return countScan;
    }

    public void calculateScan() {
        //The first time you pick Pokemon, it shouldn't be scanned


        //If you see

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
