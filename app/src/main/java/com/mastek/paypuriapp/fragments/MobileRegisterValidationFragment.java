package com.mastek.paypuriapp.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mastek.paypuriapp.R;
import com.mastek.paypuriapp.interfaces.OnMobileFragmentsListener;

/**
 * Created by Swapnil kulkarni
 * <p/>
 * Fragment for user registration and validation functionality
 */
public class MobileRegisterValidationFragment extends Fragment implements View.OnClickListener {

    //  private ProgressBar mProgressBar;
    private OnMobileFragmentsListener onMobileFragmentsListener;
    private Button btnContinue;
    private EditText edtMobileNo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    public MobileRegisterValidationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mobile_register, container, false);
        btnContinue = (Button) view.findViewById(R.id.btnContinue);
        edtMobileNo = (EditText) view.findViewById(R.id.edtMobileNo);
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
                OTPValidationFragment otpValidationFragment = new OTPValidationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobileNo", edtMobileNo.getText().toString());
                otpValidationFragment.setArguments(bundle);
                onMobileFragmentsListener.fragmentChange(otpValidationFragment, "OTPValidationFragment");
            }
        }
    }
}
