package com.android.cmpt276as3.model;

import java.util.Random;

public class GameState {


    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }


}
