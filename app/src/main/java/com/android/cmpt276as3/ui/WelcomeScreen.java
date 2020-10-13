package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.cmpt276as3.R;

public class WelcomeScreen extends AppCompatActivity {
    private static final String TAG = "WelcomeScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        this.setTitle(TAG);

        Button butMain = findViewById(R.id.button_gotomain);
        butMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = MainMenu.makeLaunchIntent(WelcomeScreen.this);
                startActivity(i);
            }
        });

    }
}