package com.benow.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.benow.R;
import com.benow.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MpinFragment extends Fragment implements View.OnClickListener {

    private TextView tvGenMPIN;
    private Button btnSubmit;
    private EditText edtMpin;
    private Bundle bundle;


    public MpinFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            bundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mpin, container, false);
        tvGenMPIN = (TextView) view.findViewById(R.id.tvGenMPIN);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        edtMpin = (EditText) view.findViewById(R.id.edtMPIN);
        btnSubmit.setOnClickListener(this);

        edtMpin.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btnSubmit.performClick();
                    return true;
                }
                return false;
            }
        });

        Spannable wordtoSpan = new SpannableString(getString(R.string.str_dont_have_mpin));

        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 17, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      //  wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 17, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordtoSpan.setSpan(new UnderlineSpan(), 17, 30, 0);
        tvGenMPIN.setText(wordtoSpan);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmit){
            hideSoftKeybord();
            if(edtMpin.getText().toString().length() == 4) {
                ((MainActivity)getActivity()).clearBackstack();
                PaymentSuccessFragment newFragment = new PaymentSuccessFragment();
                newFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment,"PaymentSuccessFragment" );
                transaction.commit();
            }else{
                edtMpin.setError("Please enter valid MPIN.");
            }
        }
    }

    public void hideSoftKeybord(){

        if (edtMpin != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtMpin.getWindowToken(), 0);
        }
    }
}
