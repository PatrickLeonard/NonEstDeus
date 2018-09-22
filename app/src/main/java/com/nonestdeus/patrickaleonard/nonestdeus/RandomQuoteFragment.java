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

public class RandomQuoteFragment extends Fragment {

    public static final String TAG = RandomQuoteFragment.class.getSimpleName();

    TextView mQuoteText;
    TextView mQuoteAuthorText;
    TextView mQuoteNumText;
    ImageView mCopyQuoteIcon;
    RelativeLayout mRelativeLayout;

    protected ClipboardManager mClipboardManager;
    protected Quote mQuote;

    public static RandomQuoteFragment newInstance() {
        return new RandomQuoteFragment();
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
                Toast toast = Toast.makeText(getActivity(), R.string.clipboard_copy_toast, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                String textToCopy = String.format(Locale.getDefault(),"\"%s\" -- %s",getString(mQuote.quoteTextId),
                                                 getString(mQuote.quoteAuthorId));
                ClipData clip = ClipData.newPlainText(getString(R.string.clip_description_label), textToCopy);
                mClipboardManager.setPrimaryClip(clip);
            }
        });
        setLayoutToNewQuote();
        return view;
    }

    private void setLayoutToNewQuote() {
        //Get a random quote from the QuoteBook and display it to the user.
        mQuote = QuoteBook.getRandomQuote();
        mQuoteText.setText(getString(mQuote.quoteTextId));
        mQuoteAuthorText.setText(getString(mQuote.quoteAuthorId));
        String quoteNum = "#" + QuoteBook.lastInt;
        mQuoteNumText.setText(quoteNum);
        //Get a random color in from the ColorWheel and set to background
        mRelativeLayout.setBackgroundColor(ColorWheel.getColor());
    }
}