package com.nonestdeus.patrickaleonard.nonestdeus.fragments;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nonestdeus.patrickaleonard.nonestdeus.MainActivity;
import com.nonestdeus.patrickaleonard.nonestdeus.R;

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
        TextView textView = view.findViewById(R.id.message);
        textView.setText(String.format(Locale.getDefault(),getString(R.string.license),MainActivity.APP_VERSION));
        return view;
    }
}
