package com.benow.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.benow.R;
import com.benow.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentSuccessFragment extends Fragment {

    private TextView tvOrderNo, tvMobileNo, tvAccountNo, tvAmountPaid;
    private Button btnClose;
    private Bundle bundle;



    public PaymentSuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            bundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_success, container, false);

        tvOrderNo = (TextView) view.findViewById(R.id.tvOrderNo);
        tvMobileNo  = (TextView) view.findViewById(R.id.tvPayerMobile);
        tvAccountNo  = (TextView) view.findViewById(R.id.tvAccountNo);
        tvAmountPaid = (TextView) view.findViewById(R.id.tvAmountPaid);
        btnClose = (Button) view.findViewById(R.id.btnClose);

        tvAccountNo.setText(""+bundle.getString("bank"));
        tvOrderNo.setText(""+bundle.getString("orderno"));
        tvMobileNo.setText("9898989898");
        tvAmountPaid.setText(getString(R.string.rupee) + " " + bundle.getString("amount"));

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().remove(PaymentSuccessFragment.this).commit();
                ((MainActivity)getActivity()).clearBackstack();
             //   ((MainActivity)getActivity()).launchBarcodeScanner();
                DashboardFragment newFragment = new DashboardFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.commitAllowingStateLoss();
            }
        });
        return view;
    }



}
