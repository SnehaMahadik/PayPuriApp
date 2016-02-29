package com.benow.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.activity.MainActivity;
import com.benow.models.GetAllOrdersResponse;
import com.benow.models.PeerPayResponse;
import com.benow.network.requestbuilder.PeerContactPaymentRequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class MpinFragment extends Fragment implements View.OnClickListener,Response.Listener, Response.ErrorListener {

    private TextView tvGenMPIN;
    private Button btnSubmit;
    private EditText edtMpin;
    private Bundle bundle;
    private JSONObject jsonObjPeerPayment;
    private JSONObject jsonObjpeerContact;
    private Context mContext;
    private PeerPayResponse peerPayResponse;


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
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
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
        jsonObjPeerPayment = new JSONObject();


        jsonObjpeerContact = new JSONObject();

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
        if (v.getId() == R.id.btnSubmit) {

            if (edtMpin.getText().toString().length() == 4) {
                hideSoftKeybord();
            }
            if (getArguments().containsKey("PEER_Amount")) {
                PaytoPeerContact();
            } else {

                ((MainActivity) getActivity()).clearBackstack();
                PaymentSuccessFragment newFragment = new PaymentSuccessFragment();
                newFragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment, "PaymentSuccessFragment");
                transaction.commit();
            }
        } else {
            edtMpin.setError("Please enter valid MPIN.");
        }
    }

    private void PaytoPeerContact()
    {

        try {

            PeerContactPaymentRequestBuilder mPeerContactPaymentRequestBuilder = new PeerContactPaymentRequestBuilder(mContext);
            mPeerContactPaymentRequestBuilder.getPeerPaymentResponse(this, CreateJson());

            PaymentSuccessFragment newFragment = new PaymentSuccessFragment();
            newFragment.setArguments(bundle);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, newFragment, "PaymentSuccessFragment");
            transaction.commit();

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

    }

    private JSONObject CreateJson() {



        try {
            jsonObjPeerPayment.put("mpin", "1234");
            jsonObjPeerPayment.put("contactAppUserId", "");
            jsonObjPeerPayment.put("appUserId", "ganesh");
            jsonObjPeerPayment.put("payableAmount", bundle.getString("PEER_Amount"));
            //

            jsonObjpeerContact.put("id", "1");
            jsonObjpeerContact.put("contactName", bundle.getString("PEER_PAYEE_NAME"));
            jsonObjpeerContact.put("mobileNumber", bundle.getString("mobileNumber"));
            jsonObjpeerContact.put("mmid", "12345");
            jsonObjpeerContact.put("accountNumber", "");
            jsonObjpeerContact.put("ifscCode", "");
            jsonObjpeerContact.put("contactType", "Mobile");
            jsonObjpeerContact.put("appUserId", "ganesh");

            //

            /*{
                "mpin": "2363",
                    "contactAppUserId": "",
                    "appUserId": "ganesh",
                    "payableAmount": "450.9",
                    "peerContact": {
                "id": 1,
                        "contactName": "swapnil",
                        "mobileNumber": "9833267435",
                        "mmid": "76272634g",
                        "accountNumber": "",
                        "ifscCode": "",
                        "contactType": "Mobile",
                        "appUserId": "ganesh"
            }
            }*/

            jsonObjPeerPayment.put("peerContact", jsonObjpeerContact);




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjPeerPayment;
    }
    public void hideSoftKeybord(){

        if (edtMpin != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtMpin.getWindowToken(), 0);
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {
       // mProgressbar.setVisibility(View.GONE);
        Log.i("Error", error.toString());

    }

    @Override
    public void onResponse(Object response) {
       // mProgressbar.setVisibility(View.GONE);
        Log.i("Success ", response.toString());
        try {
            if(response  instanceof PeerPayResponse) {

                peerPayResponse = (PeerPayResponse) response;

                jsonObjpeerContact.put("Transaction_no", peerPayResponse.getTransactionNumber());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
