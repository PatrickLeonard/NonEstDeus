package com.nonestdeus.patrickaleonard.nonestdeus.quotes;
import android.content.Context;

import com.nonestdeus.patrickaleonard.nonestdeus.R;
import com.nonestdeus.patrickaleonard.nonestdeus.fragments.QuoteListFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Patrick A Leonard on 7/22/2015.
 */
public class QuoteBook {
    //Member variables (properties about the object)
    //Create array of facts
    private static final Quote[] nQuoteArray = new Quote[]{
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
            new Quote(R.string.percy_shelley_quote_2, R.string.percy_shelley_author),
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
            new Quote(R.string.holyoake_quote_1, R.string.holyoake_author),
            new Quote(R.string.laertius_quote_1,R.string.laertius_author),
            new Quote(R.string.charles_chaplin_quote_1, R.string.charles_chaplin_author),
            new Quote(R.string.tennesse_williams_quote_1, R.string.tennesse_willaims_author),
            new Quote(R.string.amis_quote_2, R.string.amis_author),
            new Quote(R.string.chomsky_quote_1, R.string.chomsky_author),
            new Quote(R.string.hepburn_quote_1, R.string.hepburn_author),
            new Quote(R.string.hawking_quote_2, R.string.hawking_author),
            new Quote(R.string.wozniak_quote_1, R.string.wozniak_author),
            new Quote(R.string.bukowski_quote_1, R.string.bukowski_author),
            new Quote(R.string.carlin_quote_2, R.string.carlin_author),
            new Quote(R.string.sagan_quote_2, R.string.sagan_author),
            new Quote(R.string.nietzsche_quote_1, R.string.nietzsche_author),
            new Quote(R.string.fields_quote_1, R.string.fields_author),
            new Quote(R.string.jefferson_quote_1, R.string.jefferson_author),
            new Quote(R.string.aquinas_quote_1, R.string.aquinas_author),
            new Quote(R.string.hicks_quote_1, R.string.hicks_author),
            new Quote(R.string.carlin_quote_3, R.string.carlin_author),
            new Quote(R.string.byron_quote_1, R.string.byron_author),
            new Quote(R.string.ohair_quote_1, R.string.ohair_author),
            new Quote(R.string.dikkers_quote_1, R.string.dikkers_quote_1),
            new Quote(R.string.bertrand_russel_quote_2, R.string.bertrand_russell_author),
            new Quote(R.string.barker_quote_1, R.string.barker_author),
            new Quote(R.string.lawrence_quote_1, R.string.lawrence_author),
            new Quote(R.string.carlyle_quote_1, R.string.carlyle_author),
            new Quote(R.string.feynman_quote_1, R.string.feynman_author),
            new Quote(R.string.attenborough_quote_1, R.string.attenborough_author),
            new Quote(R.string.dennett_quote_1, R.string.dennett_author),
            new Quote(R.string.mencken_quote_1, R.string.mencken_author),
            new Quote(R.string.baggini_quote_2, R.string.baggini_author),
            new Quote(R.string.gould_quote_1, R.string.gould_author),
            new Quote(R.string.darrow_quote_2, R.string.darrow_author),
            new Quote(R.string.kilmister_quote_1, R.string.kilmister_author),
            new Quote(R.string.ehrman_quote_1, R.string.ehrman_author),
            new Quote(R.string.rorty_quote_1, R.string.rorty_quote_1),
            new Quote(R.string.percy_shelley_quote_3, R.string.percy_shelley_author),
            new Quote(R.string.besant_quote_1, R.string.besant_author),
            new Quote(R.string.waters_quote_1, R.string.waters_author),
            new Quote(R.string.villanueva_quote_1, R.string.villanueva_author),
            new Quote(R.string.zacharias_quote_1, R.string.zacharias_author),
            new Quote(R.string.goldman_quote_1, R.string.goldman_author),
            new Quote(R.string.mencken_quote_2, R.string.mencken_author),
            new Quote(R.string.krans_quote_1, R.string.krans_author),
            new Quote(R.string.radcliffe_quote_1, R.string.radcliffe_author),
            new Quote(R.string.banks_quote_1, R.string.banks_author),
            new Quote(R.string.dewitt_quote_1, R.string.dewitt_author),
            new Quote(R.string.gore_vidal_quote_2, R.string.gore_vidal_author),
            new Quote(R.string.cantero_quote_1, R.string.cantero_author),
            new Quote(R.string.ra_quote_1, R.string.ra_author),
            new Quote(R.string.andy_rooney_quote_4, R.string.andy_rooney_author),
            new Quote(R.string.fitzgerald_quote_1, R.string.fitzgerald_author),
            new Quote(R.string.hook_quote_1, R.string.hook_author),
            new Quote(R.string.roddenberry_quote_2, R.string.roddenberry_author),
            new Quote(R.string.nietzsche_quote_2, R.string.nietzsche_author),
            new Quote(R.string.thurber_quote_1, R.string.thurber_author),
            new Quote(R.string.sartre_author, R.string.sartre_author),
            new Quote(R.string.rubin_quote_1, R.string.rubin_author),
            new Quote(R.string.asimov_quote_2, R.string.asimov_author),
            new Quote(R.string.coates_quote_1, R.string.coates_author),
            new Quote(R.string.lemaitre_quote_1, R.string.lemaitre_author),
            new Quote(R.string.dan_barker_quote_1, R.string.dan_barker_author),
            new Quote(R.string.gervais_quote_3, R.string.gervais_author),
            new Quote(R.string.maher_quote_1, R.string.maher_author),
            new Quote(R.string.mccarthy_quote_1, R.string.mccarthy_author),
            new Quote(R.string.baggini_quote_1, R.string.baggini_author),
            new Quote(R.string.hawking_quote_1, R.string.hawking_author),
            new Quote(R.string.james_quote_1, R.string.james_author),
            new Quote(R.string.heinlein_quote_1, R.string.heinlein_author),
            new Quote(R.string.rubin_quote_2, R.string.rubin_author),
            new Quote(R.string.ehrman_quote_2, R.string.ehrman_author),
            new Quote(R.string.coates_quote_2, R.string.coates_author),
            new Quote(R.string.dillahunty_quote_1, R.string.dillahunty_author),
            new Quote(R.string.rubin_quote_3, R.string.rubin_author),
            new Quote(R.string.sagan_quote_3, R.string.sagan_author),
            new Quote(R.string.garst_quote_1, R.string.garst_author),
            new Quote(R.string.cs_lewis_quote_1, R.string.cs_lewis_author),
            new Quote(R.string.chomsky_quote_2, R.string.chomsky_author),
            new Quote(R.string.sagan_quote_4, R.string.sagan_author),
            new Quote(R.string.coates_quote_3, R.string.coates_author),
            new Quote(R.string.diderot_quote_1, R.string.diderot_author),
            new Quote(R.string.dan_barker_quote_2, R.string.dan_barker_author),
            new Quote(R.string.dillahunty_quote_2, R.string.dillahunty_author),
            new Quote(R.string.sinatra_quote_1, R.string.sinatra_author),
            new Quote(R.string.protagoras_quote_1, R.string.protagoras_author),
            new Quote(R.string.bradlaugh_quote_1, R.string.bradlaugh_author),
            new Quote(R.string.attenborough_quote_2, R.string.attenborough_author),
            new Quote(R.string.mcfarlane_quote_1, R.string.mcfarlane_author),
            new Quote(R.string.gervais_quote_4, R.string.gervais_author),
            new Quote(R.string.carlin_quote_4, R.string.carlin_author),
            new Quote(R.string.stern_quote_1, R.string.stern_author),
            new Quote(R.string.rushdie_quote_1, R.string.rushdie_author),
            new Quote(R.string.dan_barker_quote_3, R.string.dan_barker_author),
            new Quote(R.string.asimov_quote_3, R.string.asimov_author),
            new Quote(R.string.einstein_quote_2, R.string.einstein_author),
            new Quote(R.string.feynman_quote_2, R.string.feynman_author),
            new Quote(R.string.salinger_quote_1, R.string.salinger_author),
            new Quote(R.string.sagan_quote_5, R.string.sagan_author),
            new Quote(R.string.heinlein_quote_2, R.string.heinlein_author),
            new Quote(R.string.metzinger_quote_1, R.string.metzinger_author),
            new Quote(R.string.hood_quote_1, R.string.hood_author),
            new Quote(R.string.kellogg_quote_1, R.string.kellogg_author),
            new Quote(R.string.huxley_quote_1, R.string.huxley_author),
            new Quote(R.string.knightley_quote_1, R.string.knightley_author),
            new Quote(R.string.gilman_quote_1, R.string.gilman_author),
            new Quote(R.string.gage_quote_1, R.string.gage_author),
            new Quote(R.string.fahringer_quote_1, R.string.fahringer_author),
            new Quote(R.string.stanton_quote_2, R.string.stanton_author),
            new Quote(R.string.adams_quote_1, R.string.adams_author),
            new Quote(R.string.goddard_quote_1, R.string.goddard_author),
            new Quote(R.string.west_quote_1, R.string.west_author),
            new Quote(R.string.smoker_quote_1, R.string.smoker_author)
    };
    private static final Random nRandom = new Random();
    private static int lastInt = -2;
    //Methods (abilities; tings the object can do
    public static Quote getRandomQuote() {
        //Button was clicked, so update the data for new fact.
        Quote newQuote;
        int randomInt;
        //Get a random integer based on the size of the factArray without repeating the last number
        do {
            randomInt = nRandom.nextInt(nQuoteArray.length);
        }while(randomInt == lastInt);

        newQuote = nQuoteArray[randomInt];
        lastInt = randomInt+1;
        newQuote.quoteNum=lastInt;
        return newQuote;
    }

    public static List<Quote> getQuoteList(final Context context, String sortBy) {
        List<Quote> quoteList = new ArrayList<>();
        for(int i=0;i<nQuoteArray.length;i++) {
            nQuoteArray[i].quoteNum=i+1;
            quoteList.add(i,nQuoteArray[i]);
        }
        if(sortBy.equals(QuoteListFragment.SORT_BY_AUTHOR)) {
            Collections.sort(quoteList, (o1, o2) -> context.getString(o1.quoteAuthorId).compareToIgnoreCase(context.getString(o2.quoteAuthorId)));
        }
        return quoteList;
    }
}
