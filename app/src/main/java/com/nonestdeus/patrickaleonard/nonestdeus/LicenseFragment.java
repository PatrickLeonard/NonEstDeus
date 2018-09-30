package com.nonestdeus.patrickaleonard.nonestdeus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.license_fragment,container,false);
        TextView textView = view.findViewById(R.id.message);
        textView.setText(String.format(Locale.getDefault(),getString(R.string.license),MainActivity.APP_VERSION));
        RelativeLayout relativeLayout = view.findViewById(R.id.licenseLayout);
        relativeLayout.setBackgroundColor(ColorWheel.getColor());
        return view;
    }
}
