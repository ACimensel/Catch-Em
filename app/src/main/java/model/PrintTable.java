package model;

import android.util.Log;

public class PrintTable {
    public static void print(int [][] table, final int row, final int col){
        for(int x = 0; x < row ; x++){
            String printedText = " | ";
            for(int y = 0; y < col; y++){
                printedText += String.valueOf(table[x][y]).replace("-1","B") + " | ";
            }
            Log.e("", printedText);
        }
    }

}
