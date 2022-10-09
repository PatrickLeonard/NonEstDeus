package com.nonestdeus.patrickaleonard.nonestdeus.fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nonestdeus.patrickaleonard.nonestdeus.quotes.Quote;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.QuoteBook;
import com.nonestdeus.patrickaleonard.nonestdeus.adapters.QuoteListViewAdapter;
import com.nonestdeus.patrickaleonard.nonestdeus.R;

/**
 * A fragment representing a list of Items.
 *
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class QuoteListFragment extends Fragment {

    private static final String ARG_SORT_BY = "sort-by";
    private static final String ARG_FIRST_VISIBLE_POSITION = "first-visible-position";
    public static final String SORT_BY_AUTHOR = "sort-author";
    public static final String SORT_BY_NUMBER = "sort-number";
    private int mFirstVisiblePosition;
    private String mSortBy;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuoteListFragment() {
    }

    @SuppressWarnings("unused")
    public static QuoteListFragment newInstance(int columnCount,String sortBy,int firstVisiblePosition) {
        QuoteListFragment fragment = new QuoteListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_FIRST_VISIBLE_POSITION,firstVisiblePosition);
        args.putString(ARG_SORT_BY,sortBy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSortBy = getArguments().getString(ARG_SORT_BY);
        }
        if(getArguments() != null) {
            mFirstVisiblePosition = getArguments().getInt(ARG_FIRST_VISIBLE_POSITION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quote_list_fragment, container, false);
        // Set the adapter
        if (view instanceof ListView) {
            ListView listView = (ListView) view;
            listView.setAdapter(new QuoteListViewAdapter(getActivity(), QuoteBook.getQuoteList(getActivity(),mSortBy),mListener,mSortBy));
            View v = listView.getChildAt(0);
            int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());
            listView.setSelectionFromTop(mFirstVisiblePosition,top);
        }
        return view;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context
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
        void onListFragmentInteraction(Quote quote,int position);
    }
}
