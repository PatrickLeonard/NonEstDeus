package com.nonestdeus.patrickaleonard.nonestdeus;



import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_LONG;


public class NonEstDeusActivity extends AppCompatActivity {

    public static final String TAG = NonEstDeusActivity.class.getSimpleName();

    @BindView(R.id.quoteTextView) TextView mQuoteText;
    @BindView(R.id.quoteAuthorTextView) TextView mQuoteAuthorText;
    @BindView(R.id.quoteNumTextView) TextView mQuoteNumText;
    @BindView(R.id.quoteButton) Button mQuoteButton;
    @BindView(R.id.copyQuoteIcon)  ImageView mCopyQuoteIcon;
    @BindView(R.id.relativeLayout) RelativeLayout mRelativeLayout;

    protected ClipboardManager mClipboardManager;
    protected Quote mQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_est_deus);
        ButterKnife.bind(this);

        mClipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        mQuoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLayoutToNewQuote();
            }
        });

        mCopyQuoteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(NonEstDeusActivity.this, R.string.clipboard_copy_toast, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
                String textToCopy = String.format(Locale.getDefault(),"\"%s\" -- %s",getString(mQuote.quoteTextId),
                                                 getString(mQuote.quoteAuthorId));
                ClipData clip = ClipData.newPlainText(getString(R.string.clip_description_label), textToCopy);
                mClipboardManager.setPrimaryClip(clip);
            }
        });

        Toast toast =Toast.makeText(this, R.string.opening_toast_text, LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);
        toast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setLayoutToNewQuote();
    }

    private void setLayoutToNewQuote() {
        //Get a random quote from the QuoteBook and display it to the user.
        mQuote = QuoteBook.getQuote();
        mQuoteText.setText(getString(mQuote.quoteTextId));
        mQuoteAuthorText.setText(getString(mQuote.quoteAuthorId));
        String quoteNum = "#" + QuoteBook.lastInt;
        mQuoteNumText.setText(quoteNum);
        //Get a random color in from the ColorWheel and
        int color = ColorWheel.getColor();
        mRelativeLayout.setBackgroundColor(color);
        mQuoteButton.setTextColor(color);
    }

}
