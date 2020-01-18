package com.patrickleonard.nonestdeus.atheismquotes.fragments;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.ColorPalette;
import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.PaletteWheel;
import com.patrickleonard.nonestdeus.atheismquotes.MainActivity;
import com.patrickleonard.nonestdeus.atheismquotes.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LicenseFragment extends Fragment {

    public LicenseFragment() {
        // Required empty public constructor
    }

    /**
     * New instance returns no-arg constructor
     * @return A new instance of fragment LicenseFragment.
     */
    public static LicenseFragment newInstance() {
        return new LicenseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.license_fragment,container,false);
        ColorPalette colorPalette = PaletteWheel.getPalette(view.getContext(), PreferenceManager.getDefaultSharedPreferences(view.getContext()).getInt(MainActivity.THEME_PREFERENCE_KEY,1));
        TextView textView = view.findViewById(R.id.message);
        textView.setText(String.format(Locale.getDefault(),getString(R.string.license),MainActivity.APP_VERSION));
        textView.setTextColor(colorPalette.getQuoteTextColor());
        RelativeLayout relativeLayout = view.findViewById(R.id.licenseLayout);
        relativeLayout.setBackgroundColor(colorPalette.getQuoteBackgroundColor());
        return view;
    }
}
