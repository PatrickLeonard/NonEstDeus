package com.nonestdeus.patrickaleonard.nonestdeus;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quote_fragment,container,false);
        mCopyQuoteIcon = (ImageView)view.findViewById(R.id.copyQuoteIcon);
        mQuoteAuthorText = (TextView)view.findViewById(R.id.quoteAuthorTextView);
        mQuoteText = (TextView)view.findViewById(R.id.quoteTextView);
        mQuoteNumText = (TextView)view.findViewById(R.id.quoteNumTextView);
        mRelativeLayout = (RelativeLayout)view.findViewById(R.id.relativeLayout);
        mClipboardManager = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        mCopyQuoteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String textToCopy;
                if(getArguments() == null) {
                    toast = Toast.makeText(getActivity(), R.string.error_occurred, Toast.LENGTH_LONG);
                }
                else {
                    textToCopy = String.format(Locale.getDefault(),"\"%s\" -- %s",getString(getArguments().getInt(Quote.ARG_QUOTE_TEXT)),
                            getString(getArguments().getInt(Quote.ARG_QUOTE_AUTHOR)));
                    ClipData clip = ClipData.newPlainText(getString(R.string.clip_description_label), textToCopy);
                    mClipboardManager.setPrimaryClip(clip);
                    toast = Toast.makeText(getActivity(), R.string.clipboard_copy_toast, Toast.LENGTH_LONG);
                }
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();;
            }
        });
        setLayoutToNewQuote();
        return view;
    }

    private void setLayoutToNewQuote() {
        if(getArguments() == null) {
            mQuoteText.setText(getString(R.string.error_occurred));
            mQuoteAuthorText.setText(getString(R.string.error_occurred));
            mQuoteNumText.setText("#" + 0);
            return;
        }
        else {
            //Get a random quote from the QuoteBook and display it to the user.
            mQuoteText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_TEXT, 0)));
            mQuoteAuthorText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_AUTHOR), 0));
            String quoteNum = "#" + getArguments().getInt(Quote.ARG_QUOTE_NUM, 0);
            mQuoteNumText.setText(quoteNum);
        }
        //Get a random color in from the ColorWheel and set to background
        mRelativeLayout.setBackgroundColor(ColorWheel.getColor());
        //mQuoteText.setTextColor(ColorWheel.getColor());
        //mQuoteNumText.setTextColor(ColorWheel.getColor());
        //mQuoteAuthorText.setTextColor(ColorWheel.getColor());
        //mCopyQuoteIcon.setColorFilter(ColorWheel.getColor());
    }
}
