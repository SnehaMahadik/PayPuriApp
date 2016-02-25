package com.benow.activity;

import android.content.DialogInterface;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Swapnil Kulkarni
 * Fragment for mobile number validation
 */
import com.benow.R;
import com.benow.fragments.MobileRegisterValidationFragment;


public class MobileRegistrationActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private TextView mTextViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // mTextViewTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setActionBarTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        // Begin the transaction

        changeFragment();

    }

    private void changeFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new MobileRegisterValidationFragment());
        fragmentTransaction.addToBackStack("MobileRegisterValidationFragment");
        fragmentTransaction.commit();
    }

    //    @Override
//    public void fragmentChange(Fragment fragment, String tag) {
//        changeFragment(fragment, tag);
//    }
//
//    @Override
//    public void otpConfirmation() {
//        //startActivity(new Intent(this, MainActivity.class));
//        changeFragment(new UserDetailsFragment(),"UserDetailsFragment");
//       // this.finish();
//    }
    public void setActionBarTitle(String title) {
//        mTextViewTitle.setText(title);
    }


    @Override
    public void onBackPressed() {

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            // make layout invisible since last fragment will be removed
            AlertDialog.Builder builder = new AlertDialog.Builder(MobileRegistrationActivity.this);
            builder.setMessage("Do you want to exit?");
            builder.setTitle("Alert");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MobileRegistrationActivity.this.finish();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    changeFragment();
                }
            });
            builder.show();
        }
        super.onBackPressed();


    }
}
