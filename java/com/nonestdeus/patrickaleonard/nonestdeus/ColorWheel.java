package com.nonestdeus.patrickaleonard.nonestdeus;

import android.graphics.Color;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by Patrick A Leonard on 7/21/2015.
 */
public class ColorWheel {

    //Member variables (properties about the object)
    //Create array of facts
    static public String[] nColors = {"#39add1","#3079ab","#c25975",
            "#e15258","#f9845b","#838cc7","#7d569e","#53bbb4",
            "#51b46d","#e0ab18","#637a91","#f092b0","#b7c0c7",
            "#FFAEB9","#CD8C95","#CD2990","#BA55D3","#3D59AB",
            "#436EEE","#BCD2EE","#00E5EE","#00EE76","#4EEE94",
            "#7CFC00","#B3EE3A","#EEAD0E","#CD8500","#FF4040",
            "#FF6A6A","#FF7256","#CD8162","#8B4513","#ED9121"
    };
    static private Random nRandom = new Random();
    static private int lastInt = -1;
    //Methods (abilities; tings the object can do
    static public int getColor() {
        //Button was clicked, so update the data for new fact.
        String newColor;
        int randomInt;
        //Get a random integer based on the size of the factArray without repeating the last number
        do {
            randomInt = nRandom.nextInt(nColors.length);
        }while(randomInt == lastInt);

        newColor = nColors[randomInt];
        lastInt = randomInt;
        return Color.parseColor(newColor);
    }
}
