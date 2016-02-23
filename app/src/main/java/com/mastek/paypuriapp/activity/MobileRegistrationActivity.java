package com.mastek.paypuriapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Swapnil Kulkarni
 * Fragment for mobile number validation
 */
import com.mastek.paypuriapp.fragments.MobileRegisterValidationFragment;
import com.mastek.paypuriapp.fragments.UserDetailsFragment;
import com.mastek.paypuriapp.interfaces.OnMobileFragmentsListener;

public class MobileRegistrationActivity extends AppCompatActivity implements OnMobileFragmentsListener {

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // Begin the transaction

        changeFragment(new MobileRegisterValidationFragment(), "MobileRegisterValidationFragment");

    }

    public void changeFragment(Fragment fragment, String tag){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    @Override
    public void fragmentChange(Fragment fragment, String tag) {
        changeFragment(fragment,tag);
    }

    @Override
    public void otpConfirmation() {
        //startActivity(new Intent(this, MainActivity.class));
        changeFragment(new UserDetailsFragment(),"UserDetailsFragment");
       // this.finish();
    }
}
