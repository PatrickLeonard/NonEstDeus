package com.patrickleonard.nonestdeus.atheismquotes.adapters;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.patrickleonard.nonestdeus.atheismquotes.R;
import com.patrickleonard.nonestdeus.atheismquotes.fragments.QuoteListFragment;
import com.patrickleonard.nonestdeus.atheismquotes.fragments.QuoteListFragment.OnListFragmentInteractionListener;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.Quote;

import java.util.List;
import java.util.Locale;

/**
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
public class QuoteListViewAdapter extends BaseAdapter {

    private final List<Quote> mValues;
    private final OnListFragmentInteractionListener mListener;
    private String mSortBy;

    public QuoteListViewAdapter(List<Quote> items, OnListFragmentInteractionListener listener, String sortBy) {
        mValues = items;
        mSortBy = sortBy;
        mListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.quote_item_fragment, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.item_number)).setText(String.format(Locale.getDefault(),"%d",mValues.get(position).quoteNum));
        ((TextView) convertView.findViewById(R.id.content)).setText(getDisplayString(convertView,position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(mValues.get(position));
                }
            }
        });
        return convertView;
    }

    @NonNull
    private String getDisplayString(View convertView, int position) {
        String displayString;
        if(mSortBy.equals(QuoteListFragment.SORT_BY_AUTHOR)) {
            displayString = convertView.getContext().getString(mValues.get(position).quoteAuthorId) +
            " -- " + convertView.getContext().getString(mValues.get(position).quoteTextId);
        }
        else {
            displayString = convertView.getContext().getString(mValues.get(position).quoteTextId);
        }
        return displayString;
    }
    @Override
    public int getCount() {
        return mValues.size();
    }

    @Override
    public Object getItem(int position) {
        return mValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
