package com.android.cmpt276as3.model;

import com.android.cmpt276as3.R;

import java.util.Random;

/**
 * A class to randomize the Pokemons to be generated on the game board
 */

public class GetRandPokemonId {
    private final static int[] idArr = new int[] {
            R.drawable.rand_bulbasaur,
            R.drawable.rand_butterfree,
            R.drawable.rand_caterpie,
            R.drawable.rand_charmander,
            R.drawable.rand_eevee,
            R.drawable.rand_pidgey,
            R.drawable.rand_pikachu,
            R.drawable.rand_porygon,
            R.drawable.rand_squirtle,
            R.drawable.rand_weedle
    };

    private GetRandPokemonId() {
    }

    public static int getId() {
        final int random = new Random().nextInt(idArr.length);
        return idArr[random];
    }
}
