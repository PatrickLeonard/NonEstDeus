package com.nonestdeus.patrickaleonard.nonestdeus;

import java.util.Random;

/**
 * Created by Patrick A Leonard on 7/22/2015.
 */
public class QuoteBook {
    //Member variables (properties about the object)
    //Create array of facts
    static Quote[] nQuoteArray = new Quote[]{
            new Quote(R.string.dawkins_quote_1, R.string.dawkins_author),
            new Quote(R.string.douglas_adams_quote_1, R.string.douglas_adams_author),
            new Quote(R.string.hitchens_quote_1, R.string.hitchens_author),
            new Quote(R.string.voltaire_quote_1, R.string.voltaire_author),
            new Quote(R.string.asimov_quote_1, R.string.asimov_author),
            new Quote(R.string.napoleon_quote_1, R.string.napoleon_author),
            new Quote(R.string.epicurus_quote_1, R.string.epicurus_author),
            new Quote(R.string.carlin_quote_1, R.string.carlin_author),
            new Quote(R.string.twain_quote_1, R.string.twain_author),
            new Quote(R.string.hitchens_quote_2, R.string.hitchens_author),
            new Quote(R.string.pirsig_quote_1, R.string.author_pirsig),
            new Quote(R.string.colbert_quote_1, R.string.colbert_author),
            new Quote(R.string.vonnegut_quote_1, R.string.vonnegut_author),
            new Quote(R.string.tom_wolfe_quote_1, R.string.tom_wolfe_author),
            new Quote(R.string.neil_degrasse_tyson_quote_1, R.string.neil_degrasse_tyson_author),
            new Quote(R.string.murakami_quote_1, R.string.murakami_author),
            new Quote(R.string.zola_quote_1, R.string.zola_author),
            new Quote(R.string.pryor_quote_1, R.string.pryor_author),
            new Quote(R.string.sagan_quote_1, R.string.sagan_author),
            new Quote(R.string.sam_harris_quote_1, R.string.sam_harris_author),
            new Quote(R.string.roddenberry_quote_1, R.string.roddenberry_author),
            new Quote(R.string.lewis_black_quote_1, R.string.lewis_black_author),
            new Quote(R.string.einstein_quote_1, R.string.einstein_author),
            new Quote(R.string.ruth_green_quote_1, R.string.ruth_green_author),
            new Quote(R.string.lincoln_quote_1, R.string.lincoln_author),
            new Quote(R.string.richard_morgan_quote_1, R.string.richard_morgan_author),
            new Quote(R.string.sam_harris_quote_2, R.string.sam_harris_author),
            new Quote(R.string.duhamel_quote_1, R.string.duhamel_author),
            new Quote(R.string.marquis_de_sade_quote_1, R.string.marquis_de_sade_author),
            new Quote(R.string.ruth_green_quote_2, R.string.ruth_green_author),
            new Quote(R.string.john_c_wright_quote_1,R.string.john_c_wright_author),
            new Quote(R.string.bakunin_quote_1, R.string.bakunin_author),
            new Quote(R.string.twain_quote_2, R.string.twain_author),
            new Quote(R.string.sam_harris_quote_3, R.string.sam_harris_author),
            new Quote(R.string.gervais_quote_1, R.string.gervais_author),
            new Quote(R.string.jong_quote_1, R.string.jong_author),
            new Quote(R.string.sam_harris_quote_4, R.string.sam_harris_author),
            new Quote(R.string.ingersoll_quote_1, R.string.ingersoll_author),
            new Quote(R.string.gore_vidal_quote_1, R.string.gore_vidal_author),
            new Quote(R.string.madison_quote_1, R.string.madison_author),
            new Quote(R.string.bill_gates_quote_1, R.string.bill_gates_author),
            new Quote(R.string.brad_pitt_quote_1, R.string.brad_pitt_author),
            new Quote(R.string.jethro_tull_quote_1, R.string.jethro_tull_author),
            new Quote(R.string.jeni_quote_1, R.string.jeni_author),
            new Quote(R.string.ruth_green_quote_3, R.string.ruth_green_author),
            new Quote(R.string.neil_degrasse_tyson_quote_2,R.string.neil_degrasse_tyson_author),
            new Quote(R.string.stanton_quote_1, R.string.stanton_author),
            new Quote(R.string.almaarri_quote_1, R.string.almaarri_author),
            new Quote(R.string.dholdbach_quote_1, R.string.dholdbach_author),
            new Quote(R.string.ingersoll_quote_2, R.string.ingersoll_author),
            new Quote(R.string.penn_jillette_quote_1, R.string.penn_jillette_author),
            new Quote(R.string.sam_harris_quote_5, R.string.sam_harris_author),
            new Quote(R.string.dershowitz_quote_1, R.string.dershowitz_author),
            new Quote(R.string.penn_jillette_quote_2, R.string.penn_jillette_author),
            new Quote(R.string.ruth_green_quote_4, R.string.ruth_green_author),
            new Quote(R.string.dholdbach_quote_2, R.string.dholdbach_author),
            new Quote(R.string.powers_quote_1, R.string.powers_author),
            new Quote(R.string.sage_quote_2, R.string.sagan_author),
            new Quote(R.string.ingersoll_quote_3,R.string.ingersoll_author),
            new Quote(R.string.andy_rooney_quote_1, R.string.andy_rooney_author),
            new Quote(R.string.mehta_quote_1, R.string.mehta_author),
            new Quote(R.string.smith_ready_quote_1, R.string.smith_ready_author),
            new Quote(R.string.ingersoll_quote_4, R.string.ingersoll_author),
            new Quote(R.string.andy_rooney_quote_2, R.string.andy_rooney_author),
            new Quote(R.string.penn_jillette_quote_3, R.string.penn_jillette_author),
            new Quote(R.string.voltaire_quote_2, R.string.voltaire_author),
            new Quote(R.string.amis_quote_1, R.string.amis_author),
            new Quote(R.string.gervais_quote_2, R.string.gervais_author),
            new Quote(R.string.cox_quote_1, R.string.cox_author),
            new Quote(R.string.andy_rooney_quote_3, R.string.andy_rooney_author),
            new Quote(R.string.bertrand_russel_quote_1, R.string.bertrand_russell_author),
            new Quote(R.string.tochukwu_quote_1, R.string.tochukwu_author),
            new Quote(R.string.neil_degrasse_tyson_quote_3, R.string.neil_degrasse_tyson_author),
            new Quote(R.string.navabi_quote_1, R.string.navabi_author),
            new Quote(R.string.navabi_quote_2, R.string.navabi_author),
            new Quote(R.string.mcclennan_quote_1, R.string.mcclennan_author),
            new Quote(R.string.sam_harris_quote_6, R.string.sam_harris_author),
            new Quote(R.string.navabi_quote_3,R.string.navabi_author),
            new Quote(R.string.navabi_quote_4, R.string.navabi_author),
            new Quote(R.string.culliton_quote_1, R.string.culliton_author),
            new Quote(R.string.shaha_quote_1, R.string.shaha_author),
            new Quote(R.string.robert_jordan_quote_1, R.string.robert_jordan_author),
            new Quote(R.string.dawkins_quote_2, R.string.dawkins_author),
            new Quote(R.string.dawkins_quote_3, R.string.dawkins_author),
            new Quote(R.string.cj_anderson_quote_1, R.string.cj_anderson_author),
            new Quote(R.string.kudari_quote_1, R.string.kudari_author),
            new Quote(R.string.stephen_fry_quote_1, R.string.stephen_fry_author),
            new Quote(R.string.amit_gupta_quote_1, R.string.amit_gupta_author),
            new Quote(R.string.steven_weinberg_quote_1, R.string.steven_weinberg_author),
            new Quote(R.string.darrow_quote_1, R.string.darrow_author),
            new Quote(R.string.percy_shelley_quote_1, R.string.percy_shelley_author),
            new Quote(R.string.schrodinger_quote_1, R.string.schrodinger_author),
            new Quote(R.string.sam_harris_quote_7, R.string.sam_harris_author),
            new Quote(R.string.molyneux_quote_1, R.string.molyneux_author),
            new Quote(R.string.holyoake_quote_1, R.string.holyoake_author),
            new Quote(R.string.laertius_quote_1,R.string.laertius_author),
            new Quote(R.string.charles_chaplan_quote_1, R.string.charles_chaplin_author),
            new Quote(R.string.tennesse_williams_quote_1, R.string.tennesse_willaims_author),
            new Quote(R.string.amis_quote_2, R.string.amis_author),
            new Quote(R.string.chomsky_quote_1, R.string.chomsky_author),
            new Quote(R.string.hepburn_quote_1, R.string.hepburn_author),
            new Quote(R.string.wozniak_quote_1, R.string.wozniak_author)
    };
    static private Random nRandom = new Random();
    static int lastInt = -2;
    //Methods (abilities; tings the object can do
    static Quote getQuote() {
        //Button was clicked, so update the data for new fact.
        Quote newQuote;
        int randomInt;
        //Get a random integer based on the size of the factArray without repeating the last number
        do {
            randomInt = nRandom.nextInt(nQuoteArray.length);
        }while(randomInt == lastInt);

        newQuote = nQuoteArray[randomInt];
        lastInt = randomInt;
        return newQuote;
    }


}
