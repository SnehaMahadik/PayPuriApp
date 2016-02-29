package com.benow.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import com.benow.R;
import com.benow.fragments.DashboardFragment;
import com.benow.fragments.DrawerFragment;
import com.benow.fragments.InboxFragment;
import com.benow.fragments.OrderListFragment;
import com.benow.interfaces.DashboardFragmentListener;


public class MainActivity extends AppCompatActivity implements DrawerFragment.FragmentDrawerListener,DashboardFragmentListener {

    private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;
    private DrawerFragment drawerFragment;
    private FragmentTransaction fragmentTransaction;
    private static TabHost mTabHost;
    TextView mTextViewTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  mTextViewTitle= (TextView) toolbar.findViewById(R.id.toolbar_title);
        setActionBarTitle("PayPuri");
        setSupportActionBar(toolbar);

        drawerFragment = (DrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);




        // Begin the transaction
        changeFragment(new DashboardFragment(), "MobileRegisterValidationFragment");
    }



    public void changeFragment(Fragment fragment, String tag){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    public void clearBackstack(){
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        // launchBarcodeScanner();
    }



    @Override
    public void onGridItemClick() {
        changeFragment(new OrderListFragment(), "InboxFragment");

    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    @Override
    public void onOrderListClick() {
        changeFragment(new InboxFragment(), "InboxFragment");
    }

    //  Switching between fragments on clicks of
    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
//            case 0:
//                fragment = new HomeFragment();
//                title = getString(R.string.title_home);
//                break;
//            case 1:
//                fragment = new FriendsFragment();
//                title = getString(R.string.title_friends);
//                break;
//            case 2:
//                fragment = new MessagesFragment();
//                title = getString(R.string.title_messages);
//                break;
//            default:
//                break;
        }

        if (fragment != null) {
            changeFragment(fragment, title);
//                       // set the toolbar title
//            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } 
    public void setActionBarTitle(String title){
       // mTextViewTitle.setText(title);
    }
}

