package com.android.cmpt276as3.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.android.cmpt276as3.R;

public class MusicPlayer {
    private static MediaPlayer bgMusic;
    private static MediaPlayer scanSound;
    private static MediaPlayer pokemonSound;

    private MusicPlayer() {
    }

    public static void playMusic(Context context) {
        if (bgMusic == null) {
            bgMusic = MediaPlayer.create(context, R.raw.battle_hall_music);
            bgMusic.start();
            bgMusic.setLooping(true);
        }
    }

    public static void playScanSound(Context context) {
        if (scanSound == null) {
            scanSound = MediaPlayer.create(context, R.raw.scan_sound);
        }
        scanSound.seekTo(0);
        scanSound.start();
    }

    public static void playPokemonSound(Context context) {
        if (pokemonSound == null) {
            pokemonSound = MediaPlayer.create(context, R.raw.pokemon_sound);
        }
        pokemonSound.seekTo(0);
        pokemonSound.start();
    }
}
