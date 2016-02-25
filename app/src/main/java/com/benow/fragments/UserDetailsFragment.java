package com.benow.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.benow.models.AppUser;
import com.benow.network.requestbuilder.UserRegistrationRequestBuilder;
import com.benow.utils.Validations;


/**
 * Created by sneha13498 on 2/4/2016.
 */
public class UserDetailsFragment extends Fragment implements TextWatcher, Response.Listener, Response.ErrorListener, View.OnClickListener {
    private Button buttonContinue;
    private EditText edtUserId, edtEmail, edtPassword, edtRefferalCode;
    private TextView tvTermsConditions;
    private ProgressBar progressBar;
    private UserRegistrationRequestBuilder userRegistrationRequestBuilder;
    private Bundle bundle;
    private String mobileNo;
    private boolean isUsernameAvialable = false;
    private TextInputLayout inputUserId, inputUserEmail, inputPassword, inputReferralCode;

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
            mobileNo = bundle.getString("mobileNo");
            mobileNo = "9898989898";
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userdetails, container, false);
        buttonContinue = (Button) view.findViewById(R.id.buttonContinue);
        edtUserId = (EditText) view.findViewById(R.id.edtUserId);
        edtEmail = (EditText) view.findViewById(R.id.edtEmail);
        edtPassword = (EditText) view.findViewById(R.id.edtPassword);
        edtRefferalCode = (EditText) view.findViewById(R.id.edtRefferalCode);
        tvTermsConditions = (TextView) view.findViewById(R.id.tvTermsAndCondditions);
       // inputUserId = (Text), inputUserEmail, inputPassword, inputReferralCode;
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        showStep(view);

        Spannable wordtoSpan1 = new SpannableString(getString(R.string.str_by_registering));
        wordtoSpan1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 35, wordtoSpan1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //  wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 17, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan1.setSpan(new UnderlineSpan(), 35, wordtoSpan1.length(), 0);
        tvTermsConditions.setText(wordtoSpan1);

        buttonContinue.setOnClickListener(this);

        edtRefferalCode.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    buttonContinue.performClick();
                    return true;
                }
                return false;
            }
        });

//        edtUserId.setText("@paypuri.com");
//        if(edtUserId.requestFocus()) {
//            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
//        }
//        Selection.setSelection(edtUserId.getText(), 0);
        edtUserId.addTextChangedListener(this);
//        buttonContinue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.container, new SelectBankFragment());
//                fragmentTransaction.addToBackStack("SelectBankFragment");
//                fragmentTransaction.commit();
//            }
//        });

        edtUserId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //  edtUserId.setImageResource(R.drawable.ic_call_24dp_blue);
                } else {
                    if (!TextUtils.isEmpty(edtUserId.getText().toString()) && !edtUserId.getText().toString().trim().contains(" ")) {
                        userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
                        userRegistrationRequestBuilder.checkUserIdAvailability(UserDetailsFragment.this, edtUserId.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        //  edtUserId.setImageResource(R.drawable.ic_call_24dp_grey);
                    }else {
                        edtUserId.setError(getString(R.string.err_valid_id));
                        isUsernameAvialable = false;
                    }
                }
            }
        });
        return view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
//        if(!s.toString().contains("@PayPuri.com")){
//            edtUserId.setText("@PayPuri.com");
//            Selection.setSelection(edtUserId.getText(), 0);
//        }
//        if(s.toString().length() > 4){
//            UserRegistrationRequestBuilder userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
//            userRegistrationRequestBuilder.checkUserIdAvailability(this, edtUserId.getText().toString());
//            progressBar.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progressBar.setVisibility(View.GONE);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        SelectBankFragment selectBankFragment = new SelectBankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mobileNo",mobileNo);
        selectBankFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, new SelectBankFragment());
        fragmentTransaction.addToBackStack("SelectBankFragment");
        fragmentTransaction.commit();
//        Intent intent = new Intent(getActivity(), MainActivity.class);
//        getActivity().finish();
//        startActivity(intent);
//        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {
        Log.d("UserDetails", response.toString());
        progressBar.setVisibility(View.GONE);

        if (response instanceof APIResponse) {
            if (((APIResponse) response).getResponseFromAPI().equals("true")) {
                isUsernameAvialable = true;
//                edtUserId.setCompoundDrawables(null, null, getResources().getDrawable(android.R.drawable.arrow_down_float), null);
            } else {
                Toast.makeText(getActivity(), getString(R.string.err_username_already_available), Toast.LENGTH_SHORT).show();
                isUsernameAvialable = false;
            }
        } else if(response instanceof AppUser) {
            AppUser appUser = (AppUser)response;
            Log.d("AppUserEmail", appUser.getEmail());
            if(isUsernameAvialable) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                SelectBankFragment selectBankFragment = new SelectBankFragment();
                Bundle bundle = new Bundle();
                bundle.putString("mobileNo", mobileNo);
                selectBankFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, new SelectBankFragment());
                fragmentTransaction.addToBackStack("SelectBankFragment");
                fragmentTransaction.commit();
            }else{
                edtUserId.setError(getString(R.string.err_username_already_available));
                edtUserId.requestFocus();
            }
        }
    }

    @Override
    public void onClick(View v) {
        hiddenKeyboard(v);
        switch (v.getId()) {
            case R.id.buttonContinue:
                if (!TextUtils.isEmpty(edtUserId.getText().toString()) && !edtUserId.getText().toString().trim().contains(" ")) {
                    if (Validations.isValidEmail(edtEmail.getText().toString())) {
                        if (!TextUtils.isEmpty(edtPassword.getText())) {
                            progressBar.setVisibility(View.VISIBLE);
                            userRegistrationRequestBuilder.userRegistration(this, edtUserId.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(), edtRefferalCode.getText().toString(), mobileNo);
                        } else {
                            edtPassword.setError(getString(R.string.err_password_empty));
                            edtPassword.requestFocus();
                        }
                    } else {
                        edtEmail.setError(getString(R.string.err_valid_email));
                        edtEmail.requestFocus();
                    }
                } else {
                    edtUserId.setError(getString(R.string.err_valid_id));
                    edtUserId.requestFocus();
                }
                break;
        }
    }

    private void hiddenKeyboard(View v) {
        InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @SuppressWarnings("deprecation")
    private void showStep(View view) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.findViewById(R.id.dot1).setBackground(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot2).setBackground(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot3).setBackground(
                    getResources().getDrawable(R.drawable.circle));


        } else {
            view.findViewById(R.id.dot1).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot2).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot3).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));


        }
        ((TextView) view.findViewById(R.id.tvProgress)).setText("3/4");
    }


}
