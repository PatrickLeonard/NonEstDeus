package com.patrickleonard.nonestdeus.atheismquotes.palletWheel;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.patrickleonard.nonestdeus.atheismquotes.R;

import java.util.Random;

/**
 * Created by Patrick A Leonard on 7/21/2015.
 */
public class PaletteWheel {

    //Member variables (properties about the object)
    //String arrays that hold the related color values
    static private int[] nPallets = { R.array.red_fuzz_smoke_storm,
            R.array.azure_black_sea_serpent, R.array.platinum_pastel_power_burgundy,
            R.array.mantis_mint_tea_apple,R.array.grey_redwood_taupe_raisin
    };
    static private Random nRandom = new Random();
    static private int lastPallet = -1;
    static private ColorPalette colorPalette;
    static public ColorPalette getPallet(Context context) {
        if(PaletteWheel.colorPalette == null) {
            setPallet(context);
        }
        return PaletteWheel.colorPalette;
    }
    /**
     * Retrieves a random integer used to determine te displayed color pallet
     */
    static public void setPallet(Context context) {
        ColorPalette colorPalette;
        Log.println(Log.DEBUG,"PaletteWheel","Finding random int");
        int randomInt;
        //Get a random integer based on the size of the nPallets array without repeating the last number
        do {
            randomInt = nRandom.nextInt(nPallets.length);
        }while(randomInt == lastPallet); //More than one String Array of Colors is required
        int palletStringArrayID = nPallets[randomInt];
        lastPallet = randomInt;
        String[] palletStringArray = context.getResources().getStringArray(palletStringArrayID);
        colorPalette = new ColorPalette(Color.parseColor(palletStringArray[0]) ,
                Color.parseColor(palletStringArray[1]), Color.parseColor(palletStringArray[2]),
                Color.parseColor(palletStringArray[3]),Color.parseColor(palletStringArray[4]));
        PaletteWheel.colorPalette = colorPalette;
    }
}
