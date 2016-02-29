package com.benow.fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.benow.R;
import com.benow.models.CreateUser;
import com.benow.network.requestbuilder.CreateUserRequestBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class AddwithMMIDFragment extends Fragment implements Response.Listener, Response.ErrorListener{
    private EditText inputMobNum, inputMMID, inputBeneficiaryName;
    private TextInputLayout inputLayoutMobNum, inputLayoutMMID, inputLayoutBeneficiaryName;
    private Button btnReset;
    private Context mContext;
    private ProgressBar mProgressbar;
    private CreateUser mCreateUser;
    private ArrayList<CreateUser> mCreateUsers;
    private JSONObject jsonObj;



    public AddwithMMIDFragment() {
        // Required empty public constructor
    }


    public static AddwithMMIDFragment newInstance(String param1, String param2) {
        AddwithMMIDFragment fragment = new AddwithMMIDFragment();

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
        View view = inflater.inflate(R.layout.fragment_addwith_mmid, container, false);

        inputLayoutMobNum = (TextInputLayout) view.findViewById(R.id.input_layout_MOBNum);
        inputLayoutMMID = (TextInputLayout) view.findViewById(R.id.input_layout_MMID);
        inputLayoutBeneficiaryName = (TextInputLayout) view.findViewById(R.id.input_layout_beneficiary_name);
        inputMobNum = (EditText) view.findViewById(R.id.edtMobNum);
        inputMMID = (EditText) view.findViewById(R.id.edtiMMID);
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
            jsonObj.put("mobileNumber", inputMobNum.getText());
            jsonObj.put("mmid", inputMMID.getText());
            jsonObj.put("accountNumber","");
            jsonObj.put("ifscCode", "");
            jsonObj.put("contactType", "Mobile");
            jsonObj.put("appUserId", "ganesh");

            CreateUserRequestBuilder mCreateUserRequestBuilder = new CreateUserRequestBuilder(mContext);
            mCreateUserRequestBuilder.getCreatedUser_MMID(this, jsonObj);
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
