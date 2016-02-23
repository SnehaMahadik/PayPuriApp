package com.mastek.paypuriapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastek.paypuriapp.R;


public class TermsandConditionsFragment extends Fragment {


private Context mContext;
    private String mData;

    public TermsandConditionsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_termsand_conditions, container, false);
        // Inflate the layout for this fragment
      //  textViewPrivacyPolicy=(TextView)view.findViewById(R.id.textViewPrivacyPolicy);
       // textViewTermsnConditions=(TextView)view.findViewById(R.id.textViewTermsnConditions);

       // textViewPrivacyPolicy.setOnClickListener(this);
       // textViewTermsnConditions.setOnClickListener(this);
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
mContext=context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
