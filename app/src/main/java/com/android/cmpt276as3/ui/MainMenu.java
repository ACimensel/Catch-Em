package com.android.cmpt276as3.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.android.cmpt276as3.R;
import com.android.cmpt276as3.model.MusicPlayer;
import com.android.cmpt276as3.model.OptionsManager;

/**
 *  A class for Main Menu UI
 */

public class MainMenu extends AppCompatActivity {
    public static Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context, MainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //delete the bar on top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUpStartGameButton();
        setUpOptionsButton();
        setUpHelpButton();

        OptionsManager.update(this);

        MusicPlayer.playMusic(getApplicationContext());
    }

    private void setUpStartGameButton() {
        ImageButton btn= findViewById(R.id.btnStartGame);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OptionsManager.incrementPlays(MainMenu.this);
                Intent i = GameScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });
    }

    private void setUpOptionsButton() {
        ImageButton btn= findViewById(R.id.btnOptions);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = OptionsScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });
    }

    private void setUpHelpButton() {
        ImageButton btn= findViewById(R.id.btnHelp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = HelpScreen.makeLaunchIntent(MainMenu.this);
                startActivity(i);
            }
        });
    }
}