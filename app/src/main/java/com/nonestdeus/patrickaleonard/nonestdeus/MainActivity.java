package com.nonestdeus.patrickaleonard.nonestdeus;

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

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(mFragmentManager.findFragmentById(R.id.container) != null) {
                mFragmentManager.beginTransaction().remove( mFragmentManager.findFragmentById(R.id.container)).commit();
            }
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    randomQuoteFragment(fragmentTransaction);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_by_number);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_by_author);
                    return true;
            }
            return false;
        }

    };
    private FragmentManager mFragmentManager;

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
                //TODO  Add license fragment
                return true;
            case R.id.menu_report_problem:
                //TODO Add report problem fragment
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void randomQuoteFragment(FragmentTransaction fragmentTransaction) {
        mTextMessage.setText(R.string.title_random);
        RandomQuoteFragment randomQuoteFragment = RandomQuoteFragment.newInstance();
        fragmentTransaction.add(R.id.container,randomQuoteFragment);
        fragmentTransaction.commit();
    }
}
