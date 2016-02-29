package com.benow.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.models.QuickPayContacts;
import com.benow.models.QuickPayPhoneContact;
import com.benow.network.requestbuilder.CreateUserRequestBuilder;
import com.benow.network.requestbuilder.PeerContactPaymentRequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PayPeerContactFragment extends Fragment implements View.OnClickListener,Response.Listener, Response.ErrorListener {

    private Context mContext;
    private Activity mActivity;
    private QuickPayContacts mQuickPayContacts;
    //    private Spinner mSpinner;
    private Button btnConfirmPayment;
    private Bundle bundle;
    private String strAmount;
    private TextView tvPayeeName, tvShowBalance, btnViewInvoiceDetails;
    private TextView tvAmountPayable;
    private TextView tvChangeBank;
    private Spinner mSpinner;
    private ArrayList<QuickPayPhoneContact> mContacts;
    private int position;
    private JSONObject jsonObjPeerPayment;
    private ProgressBar mProgressbar;
    private JSONObject jsonObjpeerContact;
    Bundle mBundlePeerPayment;


    public PayPeerContactFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_bank, container, false);

      //  btnViewInvoiceDetails = (TextView) view.findViewById(R.id.btnInvoiceDetails);
        tvPayeeName= (TextView) view.findViewById(R.id.textViewPayee);
        btnConfirmPayment = (Button) view.findViewById(R.id.btnConfirmPayment);
        tvAmountPayable = (EditText) view.findViewById(R.id.tvAmountPayable);
      //  tvShowBalance = (TextView) view.findViewById(R.id.tvShowBal);
      //  tvChangeBank = (TextView) view.findViewById(R.id.tvChangeAccount);
        btnConfirmPayment.setOnClickListener(this);
//        btnViewInvoiceDetails.setOnClickListener(this);
      //  mSpinner = (Spinner) view.findViewById(R.id.spBanks);
       // tvAmountPayable.setText(getString(R.string.rupee) + " " + strAmount);
        //tvShowBalance.setOnClickListener(this);
       // tvChangeBank.setOnClickListener(this);

     //   mProgressbar = (ProgressBar) view.findViewById(R.id.progress_bar);
     //   mProgressbar.setVisibility(View.VISIBLE);

        return view;
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
    @Override
    public void onResume() {
        super.onResume();
        mActivity = getActivity();
        Bundle args = getArguments();
        if (args != null) {
            mQuickPayContacts= args.getParcelable("CONTACT_DETAILS");
            mContacts= args.getParcelableArrayList("PHONE_CONTACTS");
            position= args.getInt("POSITION");
            tvPayeeName.setText(mContacts.get(position).getContactName());

        }

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.btnConfirmPayment:

                mBundlePeerPayment=new Bundle();
                
                mBundlePeerPayment.putString("PEER_Amount", tvAmountPayable.getText().toString());
                mBundlePeerPayment.putString("PEER_PAYEE_NAME", mContacts.get(position).getContactName());
                mBundlePeerPayment.putInt("mpin", 12234);
                mBundlePeerPayment.putString("contactAppUserId", "");
                mBundlePeerPayment.putString("appUserId", "ganesh");
                mBundlePeerPayment.putString("id", "1");
                mBundlePeerPayment.putString("contactName", mContacts.get(position).getContactName());
                mBundlePeerPayment.putString("mobileNumber", mContacts.get(position).getMobileNo());
                mBundlePeerPayment.putString("mmid", "76272634g");
                mBundlePeerPayment.putString("accountNumber", "");
                mBundlePeerPayment.putString("ifscCode", "");
                mBundlePeerPayment.putString("contactType", "Mobile");
                mBundlePeerPayment.putString("appUserId", "ganesh");

                ProceedToMPIN(mBundlePeerPayment);

               // PaytoPeerContact();
        }

    }

    private void ProceedToMPIN(Bundle mBundlePeerPayment) {
        MpinFragment mpinFragment = new MpinFragment ();
        mpinFragment.setArguments(mBundlePeerPayment);

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, mpinFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    private void PaytoPeerContact()
    {

        /*try {

            PeerContactPaymentRequestBuilder mPeerContactPaymentRequestBuilder = new PeerContactPaymentRequestBuilder(mContext);
            mPeerContactPaymentRequestBuilder.getPeerPaymentResponse(this, CreateJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    private JSONObject CreateJson() {
        jsonObjPeerPayment = new JSONObject();


        jsonObjpeerContact = new JSONObject();


        try {
            jsonObjPeerPayment.put("mpin", "1234");
            jsonObjPeerPayment.put("contactAppUserId", "");
            jsonObjPeerPayment.put("appUserId", "ganesh");
            jsonObjPeerPayment.put("payableAmount", tvAmountPayable.getText());
            //

            jsonObjpeerContact.put("id", "1");
            jsonObjpeerContact.put("contactName", mContacts.get(position).getContactName());
            jsonObjpeerContact.put("mobileNumber", mContacts.get(position).getMobileNo());
            jsonObjpeerContact.put("mmid", "76272634g");
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

           /* {
                "mpin":"2363",
                    "contactAppUserId":"",
                    "appUserId":"ganesh",
                    "payableAmount":"450.9",

                    "peerContact":
                {
                "id":1,
                    "contactName":"swapnil",
                    "mobileNumber":"9833267435",
                    "mmid":"76272634g",
                    "accountNumber":"",
                    "ifscCode":"",
                    "contactType":"Mobile",
                    "appUserId":"ganesh"
                }
            }*/


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjPeerPayment;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mProgressbar.setVisibility(View.GONE);
        Log.i("Error", error.toString());

    }

    @Override
    public void onResponse(Object response) {
        mProgressbar.setVisibility(View.GONE);
        Log.i("Success ", response.toString());
    }
}
