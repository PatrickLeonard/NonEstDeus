package com.patrickleonard.nonestdeus.atheismquotes;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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
import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.ColorPalette;
import com.patrickleonard.nonestdeus.atheismquotes.palletWheel.PaletteWheel;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.Quote;
import com.patrickleonard.nonestdeus.atheismquotes.quotes.QuoteBook;

public class MainActivity extends AppCompatActivity implements QuoteListFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String APP_VERSION = "1.2.2";
    public static final String THEME_PREFERENCE_KEY = "theme_key";
    private BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private ColorPalette mColorPalette;

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
        mColorPalette = PaletteWheel.getPalette(this.getBaseContext(),PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1));
        mFragmentManager = getSupportFragmentManager();
        setToolBar();
        setBottomNavigation();
        setColorPalette();
        quoteFragment(mFragmentManager.beginTransaction(),QuoteBook.getRandomQuote());
        mBottomNavigationView.setSelectedItemId(0);
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
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void setToolBar() {
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        toolbar.setTitleTextColor(mColorPalette.getTitleTextColor());
        toolbar.setSubtitle(R.string.actionbar_subtitle);
        toolbar.setSubtitleTextColor(mColorPalette.getTitleTextColor());
        setSupportActionBar(toolbar);
    }

    private void setColorPalette () {
        setToolBar();
        mBottomNavigationView.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        mBottomNavigationView.setItemIconTintList(new ColorStateList( new int[][]{new int[]{-android.R.attr.state_checked},new int[]{android.R.attr.state_checked}},
              new int[]{mColorPalette.getQuoteBackgroundColor(),mColorPalette.getTitleTextColor()}));
        mBottomNavigationView.setItemTextColor(new ColorStateList( new int[][]{new int[]{-android.R.attr.state_checked},new int[]{android.R.attr.state_checked}},
                new int[]{mColorPalette.getQuoteBackgroundColor(),mColorPalette.getTitleTextColor()}));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        for(int i=0;i<menu.size();i++) {
            SpannableString item = new SpannableString(menu.getItem(i).getTitle());
            item.setSpan(new ForegroundColorSpan(mColorPalette.getTitleTextColor()), 0, item.length(), 0);
            menu.getItem(i).setTitle(item);
            menu.getItem(i).getIcon().setColorFilter(mColorPalette.getTitleTextColor(), PorterDuff.Mode.SRC_IN);
        }
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
        quoteFragment(mFragmentManager.beginTransaction().addToBackStack(null),quote);
    }

    private AlertDialog showThemeSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        String[] themeList = getResources().getStringArray(R.array.theme_titles);
        ConstraintLayout paletteDialogConstraintLayout = (ConstraintLayout) inflater.inflate(R.layout.palette_dialog_layout,null);
        paletteDialogConstraintLayout.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        final RadioGroup radioGroup = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_radio_group);
        for(int i=0;i<themeList.length;i++) {
            ColorPalette colorPalette = PaletteWheel.getPalette(this,i);
            android.support.v7.widget.AppCompatRadioButton radioButton = new AppCompatRadioButton(this);
            radioButton.setText(themeList[i]);
            radioButton.setTextColor(colorPalette.getQuoteTextColor());
            radioButton.setBackgroundColor(colorPalette.getQuoteBackgroundColor());
            CompoundButtonCompat.setButtonTintList(radioButton,new ColorStateList( new int[][]{new int[]{-android.R.attr.state_checked},new int[]{android.R.attr.state_checked}},
                    new int[]{colorPalette.getQuoteTextColor(),colorPalette.getBarBackgroundColorID()}));
            radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            radioGroup.addView(radioButton,i);
            if(i==PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1)) {
                radioGroup.check(radioButton.getId());
            }
        }
        TextView title = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_title_text_view);
        title.setTextColor(mColorPalette.getTitleTextColor());
        ImageView titleIcon = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_title_icon);
        titleIcon.setColorFilter(mColorPalette.getTitleTextColor(), PorterDuff.Mode.SRC_IN);
        AppCompatButton cancelButton = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_cancel_button);
        AppCompatButton okButton = paletteDialogConstraintLayout.findViewById(R.id.palette_dialog_ok_button);
        cancelButton.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        cancelButton.setTextColor(mColorPalette.getTitleTextColor());
        cancelButton.setBackground(getResources().getDrawable(R.drawable.button_border));
        okButton.setBackgroundColor(mColorPalette.getBarBackgroundColorID());
        okButton.setTextColor(mColorPalette.getTitleTextColor());
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
