package com.android.cmpt276as3.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.android.cmpt276as3.R;

public class MusicPlayer {
    private static Context appContext;
    private static MediaPlayer ref;

    private MusicPlayer() {
    }

    public static void play(Context context) {
        if (appContext == null) {
            appContext = context;
        }

        if (ref == null) {
            ref = MediaPlayer.create(appContext, R.raw.battle_hall_music);
            ref.start();
            ref.setLooping(true);
        }
    }
}
