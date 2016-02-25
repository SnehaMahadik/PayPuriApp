package com.benow.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.models.APIResponse;
import com.benow.models.SendOTPResponse;
import com.benow.models.ValidateOTPResponse;
import com.benow.network.requestbuilder.UserRegistrationRequestBuilder;


/**
 * Created by swapnil kulkarni
 */
public class OTPValidationFragment extends Fragment implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    private Button btnContinue;
    private String strMobileNo;
    private TextView tvMobileNo, tvResendOTP;
    private ProgressBar progressBar;
    private EditText edtOTP;
    private TextInputLayout input_layout_name;


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
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        edtOTP = (EditText) view.findViewById(R.id.edtOTP);
        tvResendOTP = (TextView)view.findViewById(R.id.tvRegenOTP);
        input_layout_name = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        tvMobileNo.setText("to "+strMobileNo);
        btnContinue.setOnClickListener(this);
        tvResendOTP.setOnClickListener(this);
        showStep(view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        hiddenKeyboard(v);
        switch (v.getId()){
            case R.id.btnContinue:
            if (!TextUtils.isEmpty(edtOTP.getText().toString())) {
                UserRegistrationRequestBuilder userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
                userRegistrationRequestBuilder.validateOTP(this, strMobileNo,edtOTP.getText().toString());
                progressBar.setVisibility(View.VISIBLE);

            } else {
//                input_layout_name.setError(getString(R.string.str_valid_otp));
                edtOTP.setError(getString(R.string.str_valid_otp));
            }
                break;

            case R.id.tvRegenOTP:
                UserRegistrationRequestBuilder userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
                userRegistrationRequestBuilder.resendOTP(this,strMobileNo);
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        progressBar.setVisibility(View.GONE);
        if (response instanceof ValidateOTPResponse) {
            if (((APIResponse) response).getResponseFromAPI().equals("true")) {
                UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobileNo",strMobileNo);
                userDetailsFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, userDetailsFragment);
                fragmentTransaction.addToBackStack("UserDetailsFragment");
                fragmentTransaction.commit();
            } else {
                Toast.makeText(getActivity(), getString(R.string.err_valid_otp), Toast.LENGTH_SHORT).show();
            }
        }else if(response instanceof SendOTPResponse){
            Toast.makeText(getActivity(), "New OTP sent.", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("deprecation")
    private void showStep(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.findViewById(R.id.dot1).setBackground(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot2).setBackground(
                    getResources().getDrawable(R.drawable.circle));

        } else {
            view.findViewById(R.id.dot1).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot2).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));

        }
        ((TextView)view.findViewById(R.id.tvProgress)).setText("2/4");
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
