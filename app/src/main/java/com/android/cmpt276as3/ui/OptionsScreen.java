package com.android.cmpt276as3.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.cmpt276as3.R;

public class OptionsScreen extends AppCompatActivity {
    private static final String TAG = "OptionsScreen";

    public static Intent makeLaunchIntent(Context context) {
        return new Intent(context, OptionsScreen.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);
        this.setTitle(TAG);
    }
}