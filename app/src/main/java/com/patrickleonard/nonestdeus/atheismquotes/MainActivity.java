package com.patrickleonard.nonestdeus.atheismquotes;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.patrickleonard.nonestdeus.atheismquotes.fragments.LicenseFragment;
import com.patrickleonard.nonestdeus.atheismquotes.fragments.QuoteFragment;
import com.patrickleonard.nonestdeus.atheismquotes.fragments.QuoteListFragment;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.Quote;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.QuoteBook;

public class MainActivity extends AppCompatActivity implements QuoteListFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String APP_VERSION = "1.2.2";
    public static final String THEME_PREFERENCE_KEY = "theme_key";
    private TabLayout mTabLayout;
    private FragmentManager mFragmentManager;

    private void quoteListFragment(String sortBy) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        QuoteListFragment quoteListFragment = QuoteListFragment.newInstance(1,sortBy);
        fragmentTransaction.replace(R.id.container,quoteListFragment);
        fragmentTransaction.commit();
    }

    private void quoteFragment(Quote quote) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        QuoteFragment quoteFragment = QuoteFragment.newInstance(quote);
        fragmentTransaction.replace(R.id.container,quoteFragment);
        fragmentTransaction.commit();
    }

    private void licenseFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        LicenseFragment licenseFragment = LicenseFragment.newInstance();
        fragmentTransaction.replace(R.id.container,licenseFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.LightGreen_Pink);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mColorPalette = PaletteWheel.getPalette(this.getBaseContext(),PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1));
        mFragmentManager = getSupportFragmentManager();
        mTabLayout = findViewById(R.id.navigation);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);
        quoteFragment(QuoteBook.getRandomQuote());
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(MainActivity.TAG,"Tab position selected: " + mTabLayout.getSelectedTabPosition());
                switchFragmentDisplayed();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switchFragmentDisplayed();
            }
        });
        mTabLayout.getTabAt(0).select();
    }

    private void switchFragmentDisplayed() {
        switch (mTabLayout.getSelectedTabPosition()) {
            case 1:
                quoteListFragment(QuoteListFragment.SORT_BY_NUMBER);
                break;
            case 2:
                quoteListFragment(QuoteListFragment.SORT_BY_AUTHOR);
                break;
            default:
                quoteFragment(QuoteBook.getRandomQuote());
        }
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
                licenseFragment();
                return true;
            case R.id.menu_send_feedback:
                sendFeedback();
                return true;
            case R.id.menu_palette_swap:
                showThemeSelectionDialog().show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendFeedback() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"nonestdeusfeedback@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "NonEstDeus Feedback Report");
        intent.putExtra(Intent.EXTRA_TEXT   , "We'd really like to receive your feedback." +
                " Please let us know how we can improve the application below:\n\n");
        try {
            startActivity(Intent.createChooser(intent, "Choose email client to send app feedback..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void onListFragmentInteraction(Quote quote) {
        quoteFragment(quote);
    }

    private AlertDialog showThemeSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        String[] themeList = getResources().getStringArray(R.array.theme_titles);
        ConstraintLayout paletteDialogConstraintLayout = (ConstraintLayout) inflater.inflate(R.layout.palette_dialog_layout,null);
        //paletteDialogConstraintLayout.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        final RadioGroup radioGroup = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_radio_group);
        for(int i=0;i<themeList.length;i++) {
            //ColorPalette colorPalette = PaletteWheel.getPalette(this,i);
            android.support.v7.widget.AppCompatRadioButton radioButton = new AppCompatRadioButton(this);
            radioButton.setText(themeList[i]);
            //radioButton.setTextColor(colorPalette.getQuoteTextColor());
            //radioButton.setBackgroundColor(colorPalette.getQuoteBackgroundColor());
            //CompoundButtonCompat.setButtonTintList(radioButton,new ColorStateList( new int[][]{new int[]{-android.R.attr.state_checked},new int[]{android.R.attr.state_checked}},
                    //new int[]{colorPalette.getQuoteTextColor(),colorPalette.getBarBackgroundColorID()}));
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(radioButton,i);
            if(i==PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1)) {
                radioGroup.check(radioButton.getId());
            }
        }
        TextView title = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_title_text_view);
        //title.setTextColor(mColorPalette.getTitleTextColor());
        ImageView titleIcon = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_title_icon);
        //titleIcon.setColorFilter(mColorPalette.getTitleTextColor(), PorterDuff.Mode.SRC_IN);
        AppCompatButton cancelButton = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_cancel_button);
        AppCompatButton okButton = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_ok_button);
        //cancelButton.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        //cancelButton.setTextColor(mColorPalette.getTitleTextColor());
        cancelButton.setBackground(getResources().getDrawable(R.drawable.button_border));
        //okButton.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        //okButton.setTextColor(mColorPalette.getTitleTextColor());
        okButton.setBackground(getResources().getDrawable(R.drawable.button_border));
        builder.setView(R.layout.palette_dialog_layout);
        builder .setCancelable(false)
                .setView(paletteDialogConstraintLayout);
        final AlertDialog alertDialog = builder.create();
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indexSelected = radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
                int current = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1);
                if(current != indexSelected) {
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putInt(MainActivity.THEME_PREFERENCE_KEY, indexSelected).apply();
                    recreate();
                }
                else {
                    alertDialog.dismiss();
                }
            }
        });
        return alertDialog;
    }
}
