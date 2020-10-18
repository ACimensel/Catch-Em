package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.android.cmpt276as3.R;

public class HelpScreen extends AppCompatActivity {
    private static final String TAG = "HelpScreen";

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, HelpScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);
        this.setTitle(TAG);

        //delete the bar on top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}