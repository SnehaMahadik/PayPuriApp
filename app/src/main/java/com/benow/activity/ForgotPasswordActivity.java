package com.benow.activity;

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
import android.widget.Toast;

import com.benow.R;


public class ForgotPasswordActivity extends AppCompatActivity {
    private Button btnSubmit;
    private EditText inputEmailID;
    private TextInputLayout inputLayoutEmailid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
        btnSubmit=(Button)findViewById(R.id.btn_reset);
        setSupportActionBar(mToolBar);
        inputEmailID = (EditText) findViewById(R.id.input_emailid);
        inputLayoutEmailid = (TextInputLayout) findViewById(R.id.input_layout_emailid);

        inputEmailID.addTextChangedListener(new MyTextWatcher(inputEmailID));

        mToolBar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        getSupportActionBar().setTitle("Forgot Password");

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });


    }
    private void submitForm() {
        if (!validateEmail()) {
            return;
        }
        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateEmail() {
        String email = inputEmailID.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmailid.setError("Please enter valid email id");
           requestFocus(inputEmailID);
            return false;
        } else {
            inputLayoutEmailid.setErrorEnabled(false);
        }

        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
                case R.id.edtEmailId:
                    validateEmail();
                    break;

            }
        }

        public void afterTextChanged(Editable editable) {

        }
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
