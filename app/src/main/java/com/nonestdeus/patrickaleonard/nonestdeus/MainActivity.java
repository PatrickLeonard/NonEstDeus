package com.nonestdeus.patrickaleonard.nonestdeus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.facebook.FacebookSdk;
import com.google.android.material.tabs.TabLayout;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nonestdeus.patrickaleonard.nonestdeus.fragments.LicenseFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.fragments.QuoteFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.fragments.QuoteListFragment;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.Quote;
import com.nonestdeus.patrickaleonard.nonestdeus.quotes.QuoteBook;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements QuoteListFragment.OnListFragmentInteractionListener {

    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final String APP_VERSION = "1.4.0";
    public static final String THEME_PREFERENCE_KEY = "theme_key";
    private TabLayout mTabLayout;
    private FragmentManager mFragmentManager;
    private int mSelectedListViewPosition;
    private final int[] mThemeArray = {R.style.BlueGray_Cyan,R.style.LightGreen_Pink,R.style.Pink_Indigo,
                                    R.style.Orange_Blue,R.style.Purple_Amber,R.style.Brown_Blue};

    private final int[] mAlertDialogThemeArray = {R.style.BlueGray_Cyan_AlertDialogTheme,R.style.LightGreen_Pink_AlertDialogTheme,
                                            R.style.Pink_Indigo_AlertDialogTheme,R.style.Orange_Blue_AlertDialogTheme,
                                            R.style.Purple_Amber_AlertDialogTheme,R.style.Brown_Blue_AlertDialogTheme};

    private final int[] mDrawableTintListArray = {R.color.bluegray_cyan_tab_color_tint_list,R.color.lightgreen_pink_tab_color_tint_list,
                                            R.color.pink_indigo_tab_color_tint_list,R.color.orange_blue_tab_color_tint_list,
                                            R.color.purple_amber_tab_color_tint_list,R.color.brown_blue_tab_color_tint_list};
    private int mCurrentThemeNum;


    private void quoteListFragment(String sortBy) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        QuoteListFragment quoteListFragment = QuoteListFragment.newInstance(1,sortBy,mSelectedListViewPosition);
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
        mCurrentThemeNum = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1);
        setTheme(mThemeArray[mCurrentThemeNum]);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mTabLayout = findViewById(R.id.navigation);
        mTabLayout.setTabIconTintResource(mDrawableTintListArray[mCurrentThemeNum]);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
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
        Objects.requireNonNull(mTabLayout.getTabAt(0)).select();
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
        int itemId = item.getItemId();
        if(itemId == R.id.menu_license) {
            licenseFragment();
            return true;
        }
        else if(itemId == R.id.menu_send_feedback) {
            sendFeedback();
            return true;
        }
        else if(itemId == R.id.menu_palette_swap) {
            showThemeSelectionDialog().show();
            return true;
        }
        else {
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

    public void onListFragmentInteraction(Quote quote,int position) {
        quoteFragment(quote);
        mSelectedListViewPosition = position;
    }

    private AlertDialog showThemeSelectionDialog() {
        String[] themeList = getResources().getStringArray(R.array.theme_titles);
        AlertDialog.Builder builder = new AlertDialog.Builder(this,mAlertDialogThemeArray[mCurrentThemeNum]);
        builder.setTitle(R.string.select_theme_color_scheme)
                .setIcon(R.drawable.ic_color_palette_white)
                .setSingleChoiceItems(themeList, PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getInt(MainActivity.THEME_PREFERENCE_KEY,1), (dialog, which) -> PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putInt(MainActivity.THEME_PREFERENCE_KEY, which).apply())
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    recreate();
                }).setPositiveButtonIcon(ResourcesCompat.getDrawable(getResources(), android.R.drawable.ic_menu_set_as, null))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit().putInt(MainActivity.THEME_PREFERENCE_KEY, mCurrentThemeNum).apply();
                    dialog.dismiss();
                })
                .setNegativeButtonIcon(ResourcesCompat.getDrawable(getResources(), android.R.drawable.ic_menu_close_clear_cancel, null));
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialog -> {
            Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
            Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) positiveButton.getLayoutParams();
            params.weight = 10;
            negativeButton.setLayoutParams(params);
            positiveButton.setLayoutParams(params);
            negativeButton.invalidate();
            positiveButton.invalidate();
        });
        return alertDialog;
    }
}
