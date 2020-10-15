package model;

import java.util.Random;

public class GameState {
    //Got the idea from https://www.youtube.com/watch?v=nORt4szAmkI
    GameData gameData = GameData.getInstance();
    private static final int NUM_ROWS = 4;
    private static final int NUM_COLS = 5;
    private static int NUM_POKEMONS = 10 ;


    //Create Table
    //private int [][] table = new int [NUM_ROWS][NUM_COLS];

    //Create the table and populate Pokemons
    public int[][] createTable(){
        // Random for generating numbers
        Random r = new Random();

        int [][] table = new int [NUM_ROWS][NUM_COLS];
        for(int row = 0; row < NUM_ROWS; row ++){
            table[row] = new int [NUM_COLS];
        }
        //Populate Pokemons
        while(NUM_POKEMONS > 0){
            int row = r.nextInt(NUM_ROWS);
            int col = r.nextInt(NUM_COLS);

            //-1 is the Pokemon
            if(table[row][col] != -1){
                table[row][col] = -1;
                NUM_POKEMONS--;
            }
        }

        calculateTableValues(table);
        return table;
    }

    public void calculateTableValues( int[][] table ){
        for(int row = 0; row < NUM_ROWS; row++){
            for(int col = 0; col < NUM_COLS; col++){
                table[row][col] = getPokemonNumber(table, row, col);
            }
        }
    }

    public int getPokemonNumber(int[][] table, int row, int col){
        //If they are Pokemon
        //TODO: I don't know if this is needed
        if(table[row][col] == -1){
            return -1;
        }

        int count = 0;
        //Check its column
        for(int i = 0; i < NUM_ROWS ; i ++){
            if(table[i][col] == -1 ){
                count++;
            }
        }
        //Check its row
        for(int i = 0; i < NUM_COLS; i++){
            if(table[row][i] == -1){
                count++;
            }
        }

        return count;
    }


}
