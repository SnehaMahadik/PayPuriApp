package com.benow.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.benow.R;


public class ChangePasswordActivity extends AppCompatActivity {
    private EditText inputOldPassword, inputNewPassword, inputConfirmPassword;
    private TextInputLayout inputLayoutOldPassword, inputLayoutNewPassword, inputLayoutConfirmPassword;
    private Button btnReset;
    TextView textViewForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mToolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setTitle("Change Password");

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inputLayoutOldPassword = (TextInputLayout) findViewById(R.id.input_layout_oldpassword);
        inputLayoutNewPassword = (TextInputLayout) findViewById(R.id.input_layout_newpassword);
        inputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.input_layout_confirmpassword);
        inputOldPassword = (EditText) findViewById(R.id.edtOldPassword);
        inputNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        inputConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        btnReset = (Button) findViewById(R.id.btn_reset);
        textViewForgotPassword= (TextView) findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
            }
        });

        inputOldPassword.addTextChangedListener(new MyTextWatcher(inputOldPassword));
        inputNewPassword.addTextChangedListener(new MyTextWatcher(inputNewPassword));
        inputConfirmPassword.addTextChangedListener(new MyTextWatcher(inputConfirmPassword));

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


    }
    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateOldPassword()) {
            return;
        }

        if (!ValidateConfirmPassword()) {
            return;
        }
        if (!validateNewPassword()) {
            return;
        }

       Toast.makeText(getApplicationContext(), "Password Reset Successfully!", Toast.LENGTH_SHORT).show();
    }

    private boolean ValidateConfirmPassword() {
        if (!inputConfirmPassword.getText().toString().trim().equalsIgnoreCase(inputNewPassword.getText().toString().trim()))
        {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputConfirmPassword);
            return false;
        } else
        {
            inputLayoutConfirmPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateOldPassword() {
        if (inputOldPassword.getText().toString().trim().isEmpty()) {
            inputLayoutOldPassword.setError("Please enter your old password");
            requestFocus(inputOldPassword);
            return false;
        } else {
            inputLayoutOldPassword.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateNewPassword() {
        if (inputNewPassword.getText().toString().trim().isEmpty()) {
            inputLayoutNewPassword.setError("Please enter password");
            requestFocus(inputNewPassword);
            return false;
        } else {
            inputLayoutOldPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = inputNewPassword.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutNewPassword.setError(getString(R.string.err_msg_email));
            requestFocus(inputNewPassword);
            return false;
        } else {
            inputLayoutNewPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (inputConfirmPassword.getText().toString().trim().isEmpty())
        {
            inputLayoutConfirmPassword.setError(getString(R.string.err_msg_password));
            requestFocus(inputConfirmPassword);
            return false;
        } else
        {
            inputLayoutConfirmPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()) {
                case R.id.edtOldPassword:
                    validateOldPassword();
                    break;
                case R.id.edtConfirmPassword:
                    ValidateConfirmPassword();
                    break;
                case R.id.edtNewPassword:
                    validateNewPassword();
                    break;
            }
        }

        public void afterTextChanged(Editable editable) {

        }
    }
}

