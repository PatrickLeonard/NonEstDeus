package com.nonestdeus.patrickaleonard.nonestdeus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nonestdeus.patrickaleonard.nonestdeus.QuoteListFragment.OnListFragmentInteractionListener;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Quote} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */
public class QuoteRecyclerViewAdapter extends RecyclerView.Adapter<QuoteRecyclerViewAdapter.ViewHolder> {

    private final List<Quote> mValues;
    private final OnListFragmentInteractionListener mListener;

    public QuoteRecyclerViewAdapter(List<Quote> items, OnListFragmentInteractionListener listener) {
        for(int i=0;i<items.size();i++) {
            items.get(i).quoteNum=i+1;
        }
        mValues = items;
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
        String shortenedQuoteString = holder.mContentView.getContext().getString(mValues.get(position).quoteTextId);
        shortenedQuoteString = shortenedQuoteString.substring(0,(shortenedQuoteString.length() < 35 ? shortenedQuoteString.length() : 35))+"...";
        holder.mContentView.setText(shortenedQuoteString);

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
