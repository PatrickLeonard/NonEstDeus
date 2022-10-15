package com.nonestdeus.patrickaleonard.nonestdeus.fragments;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.Quote;
import com.nonestdeus.patrickaleonard.nonestdeus.R;

import java.util.Locale;

import static android.content.Context.CLIPBOARD_SERVICE;

public class QuoteFragment extends Fragment {

    public static final String TAG = QuoteFragment.class.getSimpleName();

    private TextView mQuoteText;
    private TextView mQuoteAuthorText;
    private TextView mQuoteNumText;
    private ShareButton mShareButton;

    private ClipboardManager mClipboardManager;

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

        final View view = inflater.inflate(R.layout.quote_fragment,container,false);
        ImageView mCopyQuoteIcon = view.findViewById(R.id.copyQuoteIcon);
        mQuoteAuthorText = view.findViewById(R.id.quoteAuthorTextView);
        mQuoteText = view.findViewById(R.id.quoteTextView);
        mQuoteNumText = view.findViewById(R.id.quoteNumTextView);
        mShareButton = view.findViewById(R.id.fb_share_button);
        mClipboardManager = (ClipboardManager) view.getContext().getSystemService(CLIPBOARD_SERVICE);
        mCopyQuoteIcon.setOnClickListener(v -> {
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
            toast.show();
        });
        setLayoutToNewQuote();
        shareToFacebook();
        return view;
    }

    private void setLayoutToNewQuote() {
        if(getArguments() == null) {
            mQuoteText.setText(getString(R.string.error_occurred));
            mQuoteAuthorText.setText(getString(R.string.error_occurred));
            mQuoteNumText.setText(String.format(Locale.getDefault(), "#%d",0));
        }
        else {
            //Get a random quote from the QuoteBook and display it to the user.
            mQuoteText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_TEXT, 0)));
            mQuoteAuthorText.setText(getString(getArguments().getInt(Quote.ARG_QUOTE_AUTHOR), 0));
            String quoteNum = "#" + getArguments().getInt(Quote.ARG_QUOTE_NUM, 0);
            mQuoteNumText.setText(quoteNum);
            shareToFacebook(); //make sure to execute after setting the quote/author texts
        }
    }

    private void shareToFacebook() {
        try {
            final ShareLinkContent content = new ShareLinkContent.Builder()
                    .setShareHashtag(new ShareHashtag.Builder().setHashtag("#nonestdeus").build())
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.nonestdeus.patrickaleonard.nonestdeus"))
                    .build();
            mShareButton.setShareContent(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
