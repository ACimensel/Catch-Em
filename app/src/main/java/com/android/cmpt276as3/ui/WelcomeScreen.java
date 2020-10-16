package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.android.cmpt276as3.R;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeScreen extends AppCompatActivity {
    private static final String TAG = "WelcomeScreen";
    private GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        this.setTitle(TAG);

        gif = findViewById(R.id.loading_pokeball);

        Button butMain = findViewById(R.id.button_gotomain);
        butMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gif.setAlpha(1f);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = MainMenu.makeLaunchIntent(WelcomeScreen.this);
                        startActivity(i);
                    }
                },2500);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        gif.setAlpha(0f);
    }
}