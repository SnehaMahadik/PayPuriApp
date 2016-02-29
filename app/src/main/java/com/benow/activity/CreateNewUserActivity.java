package com.benow.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.benow.R;
import com.benow.fragments.AddBeneficiaryFragment;
import com.benow.fragments.DashboardFragment;

public class CreateNewUserActivity extends AppCompatActivity {

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setTitle("Add Beneficiary");

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changeFragment(new AddBeneficiaryFragment(), "Add BeneficiaryFragment");

    }

    public void changeFragment(Fragment fragment, String tag){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
    @Override
    public void onResume() {
        super.onResume();
        changeFragment(new AddBeneficiaryFragment(), "Add BeneficiaryFragment");
        // Creating request and fetch Orders list

    }

}
