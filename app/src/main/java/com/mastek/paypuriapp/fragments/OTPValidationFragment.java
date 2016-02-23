package com.mastek.paypuriapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mastek.paypuriapp.R;
import com.mastek.paypuriapp.interfaces.OnMobileFragmentsListener;

/**
 * Created by swapnil kulkarni
 */
public class OTPValidationFragment extends Fragment implements View.OnClickListener {


    private OnMobileFragmentsListener onMobileFragmentsListener;
    private Button btnContinue;
    private String strMobileNo;
    private TextView tvMobileNo;


    public OTPValidationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            strMobileNo = getArguments().getString("mobileNo");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_otpvalidation, container, false);
        btnContinue = (Button) view.findViewById(R.id.btnContinue);
        tvMobileNo = (TextView) view.findViewById(R.id.tvMobileNo);
        tvMobileNo.setText(strMobileNo);
        btnContinue.setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onMobileFragmentsListener = (OnMobileFragmentsListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onMobileFragmentsListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnContinue) {
            if (onMobileFragmentsListener != null) {
                onMobileFragmentsListener.otpConfirmation();
            }
        }
    }
}
