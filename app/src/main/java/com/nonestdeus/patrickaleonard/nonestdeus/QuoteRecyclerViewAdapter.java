package com.nonestdeus.patrickaleonard.nonestdeus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonestdeus.patrickaleonard.nonestdeus.QuoteListFragment.OnListFragmentInteractionListener;


import java.util.Comparator;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Quote} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
public class QuoteRecyclerViewAdapter extends RecyclerView.Adapter<QuoteRecyclerViewAdapter.ViewHolder> {

    private final List<Quote> mValues;
    private final OnListFragmentInteractionListener mListener;
    private String mSortBy;

    public QuoteRecyclerViewAdapter(List<Quote> items, OnListFragmentInteractionListener listener,String sortBy) {
        mValues = items;
        mSortBy = sortBy;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quote_item_fragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).quoteNum+"");
        int randomBackgroundColor = ColorWheel.getColor();
        holder.mIdView.setBackgroundColor(randomBackgroundColor);
        holder.mContentView.setText(getShortenedDisplayString(holder, position));
        holder.mContentView.setBackgroundColor(randomBackgroundColor);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @NonNull
    private String getShortenedDisplayString(ViewHolder holder, int position) {
        String shortenedDisplayString;
        if(mSortBy.equals(QuoteListFragment.SORT_BY_AUTHOR)) {
            shortenedDisplayString = holder.mContentView.getContext().getString(mValues.get(position).quoteAuthorId);
        }
        else {
            shortenedDisplayString = holder.mContentView.getContext().getString(mValues.get(position).quoteTextId);
        }
        shortenedDisplayString = shortenedDisplayString.substring(0,(shortenedDisplayString.length() < 35 ? shortenedDisplayString.length() : 35));
        shortenedDisplayString = shortenedDisplayString.length() == 35 ? shortenedDisplayString+"..." : shortenedDisplayString;
        return shortenedDisplayString;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Quote mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
