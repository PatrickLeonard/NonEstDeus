package com.nonestdeus.patrickaleonard.nonestdeus.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nonestdeus.patrickaleonard.nonestdeus.palletWheel.PalletWheel;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.Quote;
import com.nonestdeus.patrickaleonard.nonestdeus.R;

import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;

public class QuoteFragment extends Fragment {

    public static final String TAG = QuoteFragment.class.getSimpleName();

    TextView mQuoteText;
    TextView mQuoteAuthorText;
    TextView mQuoteNumText;
    ImageView mCopyQuoteIcon;
    RelativeLayout mRelativeLayout;

    protected ClipboardManager mClipboardManager;

    public static QuoteFragment newInstance(Quote quote) {
        QuoteFragment quoteFragment = new QuoteFragment();
        Bundle args = new Bundle();
        args.putInt(Quote.ARG_QUOTE_NUM,quote.quoteNum);
        args.putInt(Quote.ARG_QUOTE_AUTHOR,quote.quoteAuthorId);
        args.putInt(Quote.ARG_QUOTE_TEXT,quote.quoteTextId);
        quoteFragment.setArguments(args);
        return quoteFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quote_fragment,container,false);
        mCopyQuoteIcon = view.findViewById(R.id.copyQuoteIcon);
        mQuoteAuthorText = view.findViewById(R.id.quoteAuthorTextView);
        mQuoteText = view.findViewById(R.id.quoteTextView);
        mQuoteNumText = view.findViewById(R.id.quoteNumTextView);
        mRelativeLayout = view.findViewById(R.id.relativeLayout);
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        mCopyQuoteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String textToCopy;
                if(getArguments() == null) {
                    toast = Toast.makeText(getActivity(), R.string.error_occurred, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
                else {
                    textToCopy = String.format(Locale.getDefault(),"\"%s\" -- %s",getString(getArguments().getInt(Quote.ARG_QUOTE_TEXT)),
                            getString(getArguments().getInt(Quote.ARG_QUOTE_AUTHOR)));
                    ClipData clip = ClipData.newPlainText(getString(R.string.clip_description_label), textToCopy);
                    mClipboardManager.setPrimaryClip(clip);
                    toast = Toast.makeText(getActivity(), R.string.clipboard_copy_toast, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });
        setLayoutToNewQuote();
        return view;
    }

    private void setLayoutToNewQuote() {
        if(getArguments() == null) {
            mQuoteText.setText(getString(R.string.error_occurred));
            mQuoteAuthorText.setText(getString(R.string.error_occurred));
            mQuoteNumText.setText(String.format(Locale.getDefault(), "#%d",0));
            return;
        }
        else {
            //Get a random quote from the QuoteBook and display it to the user.
            mQuoteText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_TEXT, 0)));
            mQuoteAuthorText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_AUTHOR), 0));
            String quoteNum = "#" + getArguments().getInt(Quote.ARG_QUOTE_NUM, 0);
            mQuoteNumText.setText(quoteNum);
        }
        //Get a random color in from the PalletWheel and set to background
        mRelativeLayout.setBackgroundColor(PalletWheel.getPallet(getActivity()).getQuoteBackgroundColor());
        int quoteTextColor = PalletWheel.getPallet(getActivity()).getQuoteTextColor();
        mQuoteText.setTextColor(quoteTextColor);
        mQuoteNumText.setTextColor(quoteTextColor);
        mQuoteAuthorText.setTextColor(quoteTextColor);
        mCopyQuoteIcon.setColorFilter(quoteTextColor);
    }
}
