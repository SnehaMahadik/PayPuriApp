package com.benow.fragments;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.models.APIResponse;
import com.benow.network.requestbuilder.UserRegistrationRequestBuilder;
import com.benow.utils.Validations;


/**
 * Created by Swapnil kulkarni
 * <p/>
 * Fragment for user registration and validation functionality
 */
public class MobileRegisterValidationFragment extends Fragment implements View.OnClickListener, Response.Listener, Response.ErrorListener {

    //  private ProgressBar mProgressBar;
//    private OnMobileFragmentsListener onMobileFragmentsListener;
    private Button btnContinue;
    private EditText edtMobileNo;
    private ImageView ivCall;
    private ProgressBar progressBar;
    private FragmentTransaction fragmentTransaction;


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
        ivCall = (ImageView) view.findViewById(R.id.ivCall);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        btnContinue.setOnClickListener(this);


        edtMobileNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ivCall.setImageResource(R.drawable.ic_call_24dp_blue);
                } else {
                    ivCall.setImageResource(R.drawable.ic_call_24dp_grey);
                }
            }
        });


        showStep(view);

//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        String url ="http://192.168.1.103:8080//appUsers";
//
//        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String> params = new HashMap<>();
//                params.put("username","hello");
//                params.put("password","hello");
//                params.put("email","hello@hello.com");
//                params.put("mobileNumber","1212121212");
//                params.put("parentUsername","parent");
//                params.put("referralCode","hello");
//                return params;
//            }
//
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> headerMap = new HashMap<String, String>();
//
//                    String credentials = "ganesh" + ":" + "ganesh";
//                    String base64EncodedCredentials =
//                            Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
//                    headerMap.put("Authorization", "Basic " + base64EncodedCredentials);
//                    headerMap.put("Content-Type", "application/json; charset=utf-8");
//                    return headerMap;
//            }
//        };
//
//        queue.add(strRequest);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        try {
//            onMobileFragmentsListener = (OnMobileFragmentsListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        onMobileFragmentsListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnContinue) {
            hiddenKeyboard(v);
                 if(!TextUtils.isEmpty(edtMobileNo.getText().toString()) && edtMobileNo.getText().toString().length() > 9 && Validations.isPhoneNumber(edtMobileNo, true)) {
//            if (Validations.isValidMobile(edtMobileNo.getText().toString()) && !TextUtils.isEmpty(edtMobileNo.getText().toString()) && edtMobileNo.getText().toString().length() > 9) {
                UserRegistrationRequestBuilder userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
                userRegistrationRequestBuilder.sendOTP(this, edtMobileNo.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
            } else {
                edtMobileNo.setError(getString(R.string.err_valid_mobile_no));
            }
        }
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressBar.setVisibility(View.GONE);
        Log.e("SendOTP", "" + error.getMessage());
        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        progressBar.setVisibility(View.VISIBLE);

        if (response instanceof APIResponse) {
            if (((APIResponse) response).getResponseFromAPI().equals("true")) {
                OTPValidationFragment otpValidationFragment = new OTPValidationFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobileNo", edtMobileNo.getText().toString());
                otpValidationFragment.setArguments(bundle);
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, otpValidationFragment);
                fragmentTransaction.addToBackStack("MobileRegisterValidationFragment");
                fragmentTransaction.commit();
            } else {
                Toast.makeText(getActivity(), getString(R.string.err_valid_mobile_no), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void showStep(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.findViewById(R.id.dot1).setBackground(
                    getResources().getDrawable(R.drawable.circle));

        } else {
            view.findViewById(R.id.dot1).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));

        }
    }
}
