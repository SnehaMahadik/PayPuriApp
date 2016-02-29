package com.benow.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.activity.ForgotPasswordActivity;
import com.benow.adapters.OrderListAdapter;
import com.benow.models.CreateUser;
import com.benow.models.GetAllOrdersResponse;
import com.benow.models.QuickPayPhoneContact;
import com.benow.network.requestbuilder.CreateUserRequestBuilder;
import com.benow.network.requestbuilder.OrderListRequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AddwithIFSCFragment extends Fragment implements Response.Listener, Response.ErrorListener {
    private EditText inputAccnum, inputIFSCcode, inputBeneficiaryName;
    private TextInputLayout inputLayoutAccnum, inputLayoutIFSCcode, inputLayoutBeneficiaryName;
    private Button btnReset;
    private Context mContext;
    private ProgressBar mProgressbar;
    private CreateUser mCreateUser;
    private ArrayList<CreateUser> mCreateUsers;
    private JSONObject jsonObj;


    public AddwithIFSCFragment() {
        // Required empty public constructor
    }


    public static AddwithIFSCFragment newInstance(String param1, String param2) {
        AddwithIFSCFragment fragment = new AddwithIFSCFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addwith_ifsc, container, false);

        inputLayoutAccnum = (TextInputLayout) view.findViewById(R.id.input_layout_ACCNum);
        inputLayoutIFSCcode = (TextInputLayout) view.findViewById(R.id.input_layout_ifsc_code);
        inputLayoutBeneficiaryName = (TextInputLayout) view.findViewById(R.id.input_layout_beneficiary_name);
        inputAccnum = (EditText) view.findViewById(R.id.edtACCNum);
        inputIFSCcode = (EditText) view.findViewById(R.id.edtifsc_code);
        inputBeneficiaryName = (EditText) view.findViewById(R.id.edtbeneficiary_name);
        btnReset = (Button) view.findViewById(R.id.btn_Add);

        mProgressbar = (ProgressBar) view.findViewById(R.id.progress_bar);
        // inputOldPassword.addTextChangedListener(new MyTextWatcher(inputOldPassword));
        // inputNewPassword.addTextChangedListener(new MyTextWatcher(inputNewPassword));
        //inputConfirmPassword.addTextChangedListener(new MyTextWatcher(inputConfirmPassword));

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        return view;
    }

    private void submitForm()
    {
//        mProgressbar.setVisibility(View.VISIBLE);
        mCreateUsers=new ArrayList<CreateUser>();
         jsonObj = new JSONObject();
        try {
            jsonObj.put("contactName", inputBeneficiaryName.getText().toString());
            jsonObj.put("mobileNumber", "");
            jsonObj.put("mmid", "");
            jsonObj.put("accountNumber",inputAccnum.getText());
            jsonObj.put("ifscCode", inputIFSCcode.getText());
            jsonObj.put("contactType", "BankAccount");
            jsonObj.put("appUserId", "ganesh");

            CreateUserRequestBuilder mCreateUserRequestBuilder = new CreateUserRequestBuilder(mContext);
            mCreateUserRequestBuilder.getCreatedUser_IFSC(this, jsonObj);
        } catch (JSONException e) {
            e.printStackTrace();
        }



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
        // Creating request and fetch Orders list

    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(mContext, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
        mProgressbar.setVisibility(View.GONE);
    }


    @Override
    public void onResponse(Object response) {
        if(response  instanceof CreateUser){

            mCreateUser = (CreateUser) response;
            Toast.makeText(mContext, "Contact Added Successfuly", Toast.LENGTH_SHORT).show();
            /*if(mCreateUser.getOrders().size() > 0) {
                OrderListAdapter orderListAdapter = new OrderListAdapter(mContext, mCreateUser.getOrders());
                orderListAdapter.setOnOrderListClickListener(this);
                mRecyclerView.setAdapter(orderListAdapter);
                mProgressbar.setVisibility(View.GONE);


            }*/
        }
    }
}
