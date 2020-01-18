package com.patrickleonard.nonestdeus.atheismquotes.palletWheel;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.patrickleonard.nonestdeus.atheismquotes.R;

/**
 * Created by Patrick A Leonard on 7/21/2015.
 */
public class PaletteWheel {

    //Member variables (properties about the object)
    //String arrays that hold the related color values
    static private int[] nPallets = { R.array.red_fuzz_smoke_storm,
            R.array.azure_black_sea_serpent, R.array.platinum_pastel_power_burgundy,
            R.array.mantis_mint_tea_apple,R.array.grey_redwood_taupe_raisin,
            R.array.jungle_acajou_peach_ochre_mustard
    };

    static public ColorPalette getPalette(Context context, int themeNum) {
        Log.println(Log.DEBUG,"PaletteWheel","Creating ColorPalette!");
        //Get a random integer based on the size of the nPallets array without repeating the last number
        int palletStringArrayID = nPallets[themeNum];
        String[] palletStringArray = context.getResources().getStringArray(palletStringArrayID);
        return new ColorPalette(Color.parseColor(palletStringArray[0]) ,
                Color.parseColor(palletStringArray[1]), Color.parseColor(palletStringArray[2]),
                Color.parseColor(palletStringArray[3]),Color.parseColor(palletStringArray[4]));
    }
}
