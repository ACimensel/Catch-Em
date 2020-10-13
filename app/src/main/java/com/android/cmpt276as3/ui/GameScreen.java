package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.cmpt276as3.R;

public class GameScreen extends AppCompatActivity {
    private static final String TAG = "GameScreen";

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, GameScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        this.setTitle(TAG);
    }
}