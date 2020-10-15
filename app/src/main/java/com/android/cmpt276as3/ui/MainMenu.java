package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.cmpt276as3.R;
import com.android.cmpt276as3.model.OptionsManager;

public class MainMenu extends AppCompatActivity {
    private static final String TAG = "MainMenu";
    private OptionsManager optManager;

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, MainMenu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        this.setTitle(TAG);
        setUpStartGameButton();
        setUPOptionsButton();
        setUpHelpButton();

        optManager = OptionsManager.getInstance();
        optManager.update(this);
    }

    private void setUpStartGameButton() {
        Button btn= findViewById(R.id.btnStartGame);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = GameScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });
    }

    private void setUPOptionsButton() {
        Button btn= findViewById(R.id.btnOptions);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OptionsScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });
    }

    private void setUpHelpButton() {
        Button btn= findViewById(R.id.btnHelp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = HelpScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });

    }




}