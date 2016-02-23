package com.mastek.paypuriapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mastek.paypuriapp.MainActivity;
import com.mastek.paypuriapp.R;

/**
 * Created by sneha13498 on 2/5/2016.
 */
public class SelectBankFragment extends Fragment  {
    private static final String TAG ="" ;
    RadioGroup mRadioGroup;
    Button buttonContinue;

    public SelectBankFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selectbank, container, false);

        buttonContinue= (Button) view.findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        mRadioGroup= (RadioGroup) view.findViewById(R.id.radiogroupBankAccounts);
        addRadioButtons(getActivity().getResources().getStringArray(R.array.array_accountname).length);

        return view;
    }

    private void addRadioButtons(int radiobuttoncount) {

        RadioGroup.LayoutParams rprms;

        for(int i=0;i<radiobuttoncount;i++){
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(getActivity().getResources().getStringArray(R.array.array_accountname)[i]);
            radioButton.setId(i);
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            mRadioGroup.addView(radioButton, rprms);
        }
    }



}
