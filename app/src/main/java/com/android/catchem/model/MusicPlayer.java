package com.android.catchem.model;

import android.content.Context;
import android.media.MediaPlayer;

import com.android.catchem.R;

/**
 * A class for playing sounds when the Pokemon is found and when the user scans. Also, for playing background music.
 *
 * Background music is played once and is put on a loop. This is so multiple instances of the music are not played every
 * time the user goes to the main screen, which is where it is called from.
 */

public class MusicPlayer {
    private static MediaPlayer bgMusic;
    private static MediaPlayer scanSound;
    private static MediaPlayer pokemonSound;

    private MusicPlayer() {
        // static class: prevent other classes from creating new ones
    }

    public static void init(Context context) {
        if (bgMusic == null && scanSound == null && pokemonSound == null) {
            bgMusic = MediaPlayer.create(context, R.raw.battle_hall_music);
            bgMusic.setLooping(true);

            scanSound = MediaPlayer.create(context, R.raw.scan_sound);

            pokemonSound = MediaPlayer.create(context, R.raw.pokemon_sound);
        }
    }

    public static void playMusic() {
        if (bgMusic != null) {
            bgMusic.start();
        }
    }

    public static void pauseMusic() {
        if (bgMusic != null) {
            bgMusic.pause();
        }
    }

    public static void playScanSound() {
        scanSound.seekTo(0);
        scanSound.start();
    }

    public static void playPokemonSound() {
        pokemonSound.seekTo(0);
        pokemonSound.start();
    }
}
