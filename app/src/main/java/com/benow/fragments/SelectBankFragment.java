package com.benow.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.activity.MainActivity;
import com.benow.adapters.SelectPreferredBankAdapter;


/**
 * Created by sneha13498 on 2/5/2016.
 */
public class SelectBankFragment extends Fragment implements Response.Listener, Response.ErrorListener {
    private static final String TAG ="" ;
    private RecyclerView rvSelectBank;
    private SelectPreferredBankAdapter selectPreferredBankAdapter;
    private RadioGroup mRadioGroup;
    private Button buttonContinue;
    private String strMobileNo;

    public SelectBankFragment() {
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
        View view = inflater.inflate(R.layout.fragment_selectbank, container, false);

        buttonContinue= (Button) view.findViewById(R.id.buttonContinue);
        rvSelectBank = (RecyclerView) view.findViewById(R.id.rvSelectBank);
        rvSelectBank.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvSelectBank.setLayoutManager(llm);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });

        mRadioGroup= (RadioGroup) view.findViewById(R.id.radiogroupBankAccounts);
        addRadioButtons(getActivity().getResources().getStringArray(R.array.array_accountname).length);
        showStep(view);
        return view;
    }

    private void addRadioButtons(int radiobuttoncount) {

        RadioGroup.LayoutParams rprms;

        for(int i=0;i<radiobuttoncount;i++){
            RadioButton radioButton = new RadioButton(getActivity());

            radioButton.setPadding(0,10,0,10);
            radioButton.setText(getActivity().getResources().getStringArray(R.array.array_accountname)[i]);
            radioButton.setId(i);
            rprms= new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            mRadioGroup.addView(radioButton, rprms);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        UserRegistrationRequestBuilder userRegistrationRequestBuilder = new UserRegistrationRequestBuilder(getActivity());
//        HashMap<String, String> params = new HashMap<>();
//        params.put("name","Ganesh Ghag");
//        params.put("email","ganesh.ghag@mastek.com");
//        params.put("mobile","9876543211");
//        userRegistrationRequestBuilder.getaccounts(this, params);
//      progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

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
            view.findViewById(R.id.dot4).setBackground(
                    getResources().getDrawable(R.drawable.circle));
        } else {
            view.findViewById(R.id.dot1).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot2).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot3).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));
            view.findViewById(R.id.dot4).setBackgroundDrawable(
                    getResources().getDrawable(R.drawable.circle));

        }
        ((TextView)view.findViewById(R.id.tvProgress)).setText("4/4");
    }
}
