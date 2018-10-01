package com.nonestdeus.patrickaleonard.nonestdeus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nonestdeus.patrickaleonard.nonestdeus.fragments.LicenseFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.fragments.QuoteFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.fragments.QuoteListFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.palletWheel.PalletWheel;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.Quote;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.QuoteBook;

public class MainActivity extends AppCompatActivity implements QuoteListFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String APP_VERSION = "1.1.0";
    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            setColorPalette();
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    quoteFragment(fragmentTransaction.addToBackStack(null),QuoteBook.getRandomQuote());
                    return true;
                case R.id.navigation_by_number:
                    quoteListFragment(fragmentTransaction,QuoteListFragment.SORT_BY_NUMBER);
                    return true;
                case R.id.navigation_by_author:
                    quoteListFragment(fragmentTransaction,QuoteListFragment.SORT_BY_AUTHOR);
                    return true;
            }
            return false;
        }

    };

    private void quoteListFragment(FragmentTransaction fragmentTransaction,String sortBy) {
        QuoteListFragment quoteListFragment = QuoteListFragment.newInstance(1,sortBy);
        fragmentTransaction.replace(R.id.container,quoteListFragment);
        fragmentTransaction.commit();
    }

    private void quoteFragment(FragmentTransaction fragmentTransaction, Quote quote) {
        QuoteFragment quoteFragment = QuoteFragment.newInstance(quote);
        fragmentTransaction.replace(R.id.container,quoteFragment);
        fragmentTransaction.commit();
    }

    private void licenseFragment(FragmentTransaction fragmentTransaction) {
        LicenseFragment licenseFragment = LicenseFragment.newInstance();
        fragmentTransaction.replace(R.id.container,licenseFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PalletWheel.setPallet(this.getBaseContext());
        setToolBar();
        setBottomNavigation();
        mFragmentManager = getSupportFragmentManager();
        quoteFragment(mFragmentManager.beginTransaction(),QuoteBook.getRandomQuote());
    }

    @Override
    public void onBackPressed() {
        if(mFragmentManager != null &&
                mFragmentManager.getBackStackEntryCount() >= 1) {
            mFragmentManager.popBackStackImmediate();
        }
        else {
            super.onBackPressed();
        }
    }

    private void setBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setBackgroundColor(PalletWheel.getPallet(this).getmBarBackgroundColorID());
        //mBottomNavigationView.setItemIconTintList(ColorStateList.valueOf(PalletWheel.getColor()));
        //mBottomNavigationView.setItemTextColor(ColorStateList.valueOf(PalletWheel.getColor()));
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setToolBar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setBackgroundColor(PalletWheel.getPallet(this.getBaseContext()).getmBarBackgroundColorID());
        toolbar.setTitleTextColor(PalletWheel.getPallet(this.getBaseContext()).getTitleTextColor());
        toolbar.setSubtitle(R.string.actionbar_subtitle);
        setSupportActionBar(toolbar);
    }

    private void setColorPalette () {
        setToolBar();
        mBottomNavigationView.setBackgroundColor(PalletWheel.getPallet(this).getmBarBackgroundColorID());
        //mBottomNavigationView.setItemIconTintList(ColorStateList.valueOf(PalletWheel.getColor()));
        //mBottomNavigationView.setItemTextColor(ColorStateList.valueOf(PalletWheel.getColor()));
        //mBottomNavigationView.setItemTextAppearanceActive(R.style.TextAppearance_AppCompat_Inverse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_license:
                licenseFragment(mFragmentManager.beginTransaction().addToBackStack(null));
                return true;
            case R.id.menu_send_feedback:
                sendFeedback();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"patrickleonard789@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "NonEstDeus Feedback Report");
        intent.putExtra(Intent.EXTRA_TEXT   , "We'd really like to receive your feedback." +
                " Please let us know how we can improve the application below:\n\n");
        try {
            startActivity(Intent.createChooser(intent, "Choose email client to send app feedback..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onListFragmentInteraction(Quote quote) {
        quoteFragment(mFragmentManager.beginTransaction().addToBackStack(null),quote);
    }
}
