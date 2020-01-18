package com.patrickleonard.nonestdeus.atheismquotes.fragments;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickleonard.nonestdeus.atheismquotes.MainActivity;
import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.ColorPalette;
import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.PaletteWheel;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.Quote;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.QuoteBook;
import com.patrickleonard.nonestdeus.atheismquotes.adapters.QuoteRecyclerViewAdapter;
import com.patrickleonard.nonestdeus.atheismquotes.R;

/**
 * A fragment representing a list of Items.
 *
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class QuoteListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_SORT_BY = "sort-by";
    public static final String SORT_BY_AUTHOR = "sort-author";
    public static final String SORT_BY_NUMBER = "sort-number";
    private int mColumnCount = 1;
    private String mSortBy;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuoteListFragment() {
    }

    @SuppressWarnings("unused")
    public static QuoteListFragment newInstance(int columnCount,String sortBy) {
        QuoteListFragment fragment = new QuoteListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putString(ARG_SORT_BY,sortBy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        if (getArguments() != null) {
            mSortBy = getArguments().getString(ARG_SORT_BY);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_list_fragment, container, false);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            ColorPalette colorPalette = PaletteWheel.getPalette(context, PreferenceManager.getDefaultSharedPreferences(context).getInt(MainActivity.THEME_PREFERENCE_KEY,1));
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new QuoteRecyclerViewAdapter(QuoteBook.getQuoteList(getContext(),mSortBy),mListener,mSortBy,
                    colorPalette.getListItemBackgroundColor(),
                    colorPalette.getQuoteTextColor()));
            recyclerView.setBackgroundColor(colorPalette.getQuoteBackgroundColor());
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Quote quote);
    }
}
