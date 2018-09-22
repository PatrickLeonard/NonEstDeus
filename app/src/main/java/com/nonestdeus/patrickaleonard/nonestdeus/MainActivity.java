package com.nonestdeus.patrickaleonard.nonestdeus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements QuoteListFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String APP_VERSION = "1.1.0";
    private TextView mTextMessage;
    private FragmentManager mFragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            clearFragment();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    randomQuoteFragment(fragmentTransaction);
                    return true;
                case R.id.navigation_by_number:
                    quoteListFragment(fragmentTransaction);
                    return true;
                case R.id.navigation_by_author:
                    mTextMessage.setText(R.string.title_by_author);
                    return true;
            }
            return false;
        }

    };

    private void quoteListFragment(FragmentTransaction fragmentTransaction) {
        QuoteListFragment quoteListFragment = QuoteListFragment.newInstance(1);
        fragmentTransaction.add(R.id.container,quoteListFragment);
        fragmentTransaction.commit();
    }

    private void clearFragment() {
        if(mFragmentManager.findFragmentById(R.id.container) != null) {
            mFragmentManager.beginTransaction().remove( mFragmentManager.findFragmentById(R.id.container)).commit();
        }
    }

    private void randomQuoteFragment(FragmentTransaction fragmentTransaction) {
        RandomQuoteFragment randomQuoteFragment = RandomQuoteFragment.newInstance();
        fragmentTransaction.add(R.id.container,randomQuoteFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Toast toast =Toast.makeText(this, R.string.opening_toast_text, LENGTH_LONG);
        toast.setGravity(Gravity.TOP | Gravity.START, 0, 0);
        toast.show();
        mFragmentManager = getSupportFragmentManager();
        randomQuoteFragment(mFragmentManager.beginTransaction());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_license:
                clearFragment();
                displayLicense();
                return true;
            case R.id.menu_send_feedback:
                sendFeedback();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayLicense() {
        mTextMessage.setText("NonEstDeus App. Ver. " + MainActivity.APP_VERSION  + "\n\n" +
                "Copyright (c) 2018, Patrick Leonard\n\n" +
                "This software is provided \'as is\' and without any express or implied " +
                "warranties, including, but no limited to, the implied warranties of " +
                "merchantability and fitness for a particular purpose are disclaimed." +
                "\n\nNonEstDeus is an open source project. The source code for NonEstDeus " +
                "can be found, with license, on Github:\nhttps://github.com/PatrickLeonard/NonEstDeus");
    }

    private void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"patrickleonard789@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "NonEstDeus Feedback Report");
        intent.putExtra(Intent.EXTRA_TEXT   , "We'd really like to receive your feedback." +
                " Please let us know how we can improve the application below:\n\n");
        try {
            startActivity(Intent.createChooser(intent, "Choose email client..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListFragmentInteraction(Quote quote) {
        Toast.makeText(MainActivity.this,quote.quoteNum+" "+ getString(quote.quoteTextId)+ " was clicked", Toast.LENGTH_LONG).show();
    }
}
