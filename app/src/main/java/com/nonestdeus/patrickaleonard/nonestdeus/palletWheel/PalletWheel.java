package com.nonestdeus.patrickaleonard.nonestdeus.palletWheel;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.nonestdeus.patrickaleonard.nonestdeus.R;

import java.util.Random;

/**
 * Created by Patrick A Leonard on 7/21/2015.
 */
public class PalletWheel {

    //Member variables (properties about the object)
    static private int[] nPallets = { R.array.red_fuzz_smoke_storm,
            R.array.azure_black_sea_serpent, R.array.platinum_pastel_power_burgundy
    };
    static private Random nRandom = new Random();
    static private int lastPallet = -1;
    static private ColorPallet colorPallet;
    static public ColorPallet getPallet(Context context) {
        if(PalletWheel.colorPallet == null) {
            setPallet(context);
        }
        return PalletWheel.colorPallet;
    }
    /**
     * Retrieves a random integer used to determine te displayed color pallet
     */
    static public void setPallet(Context context) {
        ColorPallet colorPallet;
        Log.println(Log.DEBUG,"PalletWheel","Finding random int");
        int randomInt;
        //Get a random integer based on the size of the nPallets array without repeating the last number
        do {
            randomInt = nRandom.nextInt(nPallets.length);
        }while(randomInt == lastPallet); //More than one String Array of Colors is required
        int palletStringArrayID = nPallets[randomInt];
        lastPallet = randomInt;
        String[] palletStringArray = context.getResources().getStringArray(palletStringArrayID);
        colorPallet = new ColorPallet(Color.parseColor(palletStringArray[0]) ,
                Color.parseColor(palletStringArray[1]), Color.parseColor(palletStringArray[2]),
                Color.parseColor(palletStringArray[3]),Color.parseColor(palletStringArray[4]));
        PalletWheel.colorPallet = colorPallet;
    }
}
